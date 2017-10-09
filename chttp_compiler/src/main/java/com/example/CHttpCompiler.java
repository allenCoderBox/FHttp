package com.example;

import com.example.annotation.FHttp;
import com.example.annotation.UrlParams;
import com.google.auto.service.AutoService;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

/**
 * 作者：husongzhen on 17/9/30 10:17
 * 邮箱：husongzhen@musikid.com
 */

@AutoService(Processor.class)
public class CHttpCompiler extends AbstractProcessor {


    private Filer mFiler;
    private Elements mElementUtils;
    private Messager messager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mFiler = processingEnvironment.getFiler();
        mElementUtils = processingEnvironment.getElementUtils();
        messager = processingEnvironment.getMessager();
    }


    private Map<String, ChttpProxy> mProxyClassMap = new HashMap<>();

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        for (Element element : roundEnvironment.getElementsAnnotatedWith(FHttp.class)) {
//            error(null,element.getSimpleName().toString());
            if (!isValid(FHttp.class, "class", element)) {
                return true;
            }
            TypeElement typeElement = (TypeElement) element;
            ChttpBinding classBind = new ChttpBinding(element);
            List<? extends Element> elements = typeElement.getEnclosedElements();
            for (Element mElement : elements) {
                if (mElement.getKind() == ElementKind.FIELD) {
//                    msg(mElement, mElement.getSimpleName().toString(), "method");
                    classBind.setMethodElement(new ChttpMethodBinding((VariableElement) mElement));
                }
            }
            ChttpProxy proxy = new ChttpProxy(mElementUtils);
            proxy.setClazzBinding(classBind);
            mProxyClassMap.put(typeElement.getQualifiedName().toString(), proxy);
        }


        for (ChttpProxy proxyClass_ : mProxyClassMap.values()) {
            try {
                proxyClass_.generateProxy().writeTo(mFiler);
            } catch (IOException e) {
                error(null, e.getMessage());
            }
        }
        mProxyClassMap.clear();
        return false;
    }

    private boolean isValid(Class<FHttp> fHttpClass, String targetThing, Element element) {
        // 父元素必须是类，而不能是接口或枚举

//        error(null,element.getKind().toString());

        if (element.getKind() != ElementKind.CLASS) {
            error(element, "@%s %s may only be contained in classes. (%s.%s)",
                    fHttpClass.getSimpleName(), targetThing, element.getSimpleName(),
                    element.getSimpleName());
            return false;
        }
        return true;
    }


    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>();
        types.add(FHttp.class.getName());
        types.add(UrlParams.class.getName());
        return types;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    private void error(Element e, String msg, Object... args) {
        messager.printMessage(Diagnostic.Kind.ERROR, String.format(msg, args), e);
    }


    private void msg(Element e, String msg, Object... args) {
        messager.printMessage(Diagnostic.Kind.NOTE, String.format(msg, args), e);
    }
}
