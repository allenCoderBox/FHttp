package com.allen.code.chttplib.FHttp;

import java.util.Map;

/**
 * 作者：husongzhen on 17/10/9 10:02
 * 邮箱：husongzhen@musikid.com
 */

public interface RequestPlugin {
    void get(String url, Map<String, Object> params, onResponseListener listener);

    void post(String url, Map<String, Object> params, onResponseListener listener);
}
