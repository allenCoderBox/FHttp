package com.example;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.util.List;
import java.util.Map;

import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

/**
 * 作者：husongzhen on 17/9/30 10:47
 * 邮箱：husongzhen@musikid.com
 */

public class ChttpProxy {
    public static final String SUFFIX = "$$Proxy";

    public static final ClassName responseListener = ClassName.get("com.allen.code.chttplib.FHttp", "onResponseListener");
    public static final ClassName stringType = ClassName.get(String.class);
    public static final ClassName mapType = ClassName.get(Map.class);
    public static final ClassName requestType = ClassName.get(RequestType.class);
    public static final ClassName client = ClassName.bestGuess("com.allen.code.chttplib.FHttp.FHttpConfig");

    private ChttpBinding clazzBinding;

    private Elements mElementUtils;


    public ChttpProxy(Elements mElementUtils) {
        this.mElementUtils = mElementUtils;
    }

    public void setClazzBinding(ChttpBinding clazzBinding) {
        this.clazzBinding = clazzBinding;
    }

    public JavaFile generateProxy() {


        TypeElement clazzElement = (TypeElement) clazzBinding.getTypeElement();
        TypeSpec.Builder proxyBuild = TypeSpec.classBuilder(clazzElement.getSimpleName().toString() + SUFFIX)
                .addModifiers(Modifier.PUBLIC);
        List<ChttpMethodBinding> methods = clazzBinding.getMethodElement();
        for (ChttpMethodBinding methodBinding : methods) {
            String name = methodBinding.getName();


            CodeBlock.Builder builder = CodeBlock.builder();
            builder.beginControlFlow("if(" + methodBinding.getType().getType() + " == " + RequestType.GET.getType() + ")");
            builder.add(CodeBlock.of(client + ".news().getPlugin().get(\"" + methodBinding.getUrl() + "\",params, listener);"));
            builder.nextControlFlow("else");
            builder.add(CodeBlock.of(client + ".news().getPlugin().post(\"" + methodBinding.getUrl() + "\",params, listener);"));
            builder.endControlFlow();


            MethodSpec methodSpec = MethodSpec.methodBuilder(name)
                    .addModifiers(Modifier.PUBLIC)
                    .returns(TypeName.VOID)
                    .addParameter(mapType, "params")
                    .addParameter(responseListener, "listener")
                    .addCode(builder.build())
                    .build();
            proxyBuild.addMethod(methodSpec);
        }


        String packageName = mElementUtils.getPackageOf(clazzElement).getQualifiedName().toString();
        return JavaFile.builder(packageName, proxyBuild.build()).build();
    }
}
