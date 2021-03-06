package com.example.annotation;



import com.example.RequestType;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * 作者：husongzhen on 17/9/30 09:28
 * 邮箱：husongzhen@musikid.com
 */


@Target(FIELD)
@Retention(SOURCE)
public @interface UrlParams {
    RequestType requestType();
}
