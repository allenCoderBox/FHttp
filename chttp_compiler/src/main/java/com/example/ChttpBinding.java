package com.example;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Element;

/**
 * 作者：husongzhen on 17/9/30 10:49
 * 邮箱：husongzhen@musikid.com
 */

public class ChttpBinding {

    private Element typeElement;
    private List<ChttpMethodBinding> methodElement;

    public ChttpBinding(Element typeElement) {
        this.typeElement = typeElement;
        methodElement = new ArrayList<>();


    }


    public void setMethodElement(ChttpMethodBinding element) {
        methodElement.add(element);
    }


    public Element getTypeElement() {
        return typeElement;
    }


    public List<ChttpMethodBinding> getMethodElement() {
        return methodElement;
    }
}
