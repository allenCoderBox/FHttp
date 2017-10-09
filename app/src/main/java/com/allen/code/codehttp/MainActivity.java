package com.allen.code.codehttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.allen.code.chttplib.FHttp.FHttpConfig;
import com.allen.code.chttplib.FHttp.MyRequestPlugin;
import com.allen.code.chttplib.FHttp.onResponseListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FHttpConfig.news().setPlugin(new MyRequestPlugin());
        Map map = new HashMap();
        map.put("name", "husongzhen");
        ConstansUrl$$Proxy proxy = new ConstansUrl$$Proxy();
        proxy.url1(map, new onResponseListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onResult() {

            }

            @Override
            public void onFinish() {

            }
        });


    }
}
