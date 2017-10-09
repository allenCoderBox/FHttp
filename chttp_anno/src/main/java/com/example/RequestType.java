package com.example;

/**
 * 作者：husongzhen on 17/9/30 09:31
 * 邮箱：husongzhen@musikid.com
 */

public enum RequestType {
    POST(0), GET(1);


    private int type;


    RequestType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
