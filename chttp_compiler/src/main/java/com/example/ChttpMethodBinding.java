package com.example;

import com.example.annotation.UrlParams;

import javax.lang.model.element.VariableElement;

/**
 * 作者：husongzhen on 17/9/30 10:52
 * 邮箱：husongzhen@musikid.com
 */

public class ChttpMethodBinding {
    private VariableElement mElement;
    private String name;
    private String url;
    private RequestType type;


    public ChttpMethodBinding(VariableElement mElement) {
        this.mElement = mElement;
        name = mElement.getSimpleName().toString();
        UrlParams params = mElement.getAnnotation(UrlParams.class);
        url = mElement.getConstantValue() + "";
        type = params.requestType();
    }


    public String getUrl() {
        return url;
    }

    public RequestType getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
