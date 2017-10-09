package com.allen.code.codehttp;

import com.example.RequestType;
import com.example.annotation.FHttp;
import com.example.annotation.UrlParams;

/**
 * 作者：husongzhen on 17/9/30 09:41
 * 邮箱：husongzhen@musikid.com
 */

@FHttp
public class ConstansUrl {


    @UrlParams(requestType = RequestType.POST)
    public static final String url1 = "https://mi.oneapm.com/mobile/app/555023/crashlog#/crashtrace/1248379";
    @UrlParams(requestType = RequestType.GET)
    public static final String url2 = "https://mi.oneapm.com/mobile/app/555023/crashlog#/crashtrace/1248379";
    @UrlParams(requestType = RequestType.POST)
    public static final String url3 = "https://mi.oneapm.com/mobile/app/555023/crashlog#/crashtrace/1248379";


}
