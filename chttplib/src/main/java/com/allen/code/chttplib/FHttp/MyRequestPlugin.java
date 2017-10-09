package com.allen.code.chttplib.FHttp;

import android.util.Log;

import com.example.RequestType;

import java.util.Map;

/**
 * 作者：husongzhen on 17/10/9 11:11
 * 邮箱：husongzhen@musikid.com
 */

public class MyRequestPlugin implements RequestPlugin {
    @Override
    public void get(String url, Map<String, Object> params, onResponseListener listener) {
        Log.e("get", "url= " + url + "params = " + params.toString());
    }

    @Override
    public void post(String url, Map<String, Object> params, onResponseListener listener) {
        Log.e("pos", "url= " + url + ",params = " + params.toString());
    }
}
