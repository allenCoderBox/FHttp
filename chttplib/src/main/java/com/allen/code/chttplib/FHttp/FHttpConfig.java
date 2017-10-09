package com.allen.code.chttplib.FHttp;

/**
 * 作者：husongzhen on 17/10/9 10:01
 * 邮箱：husongzhen@musikid.com
 */

public class FHttpConfig {


    private static final class ClassInstance {
        public static final FHttpConfig CLIENT = new FHttpConfig();
    }

    public static final FHttpConfig news() {
        return ClassInstance.CLIENT;
    }

    private FHttpConfig() {
    }

    private RequestPlugin plugin;

    public RequestPlugin getPlugin() {
        return plugin;
    }

    public void setPlugin(RequestPlugin plugin) {
        this.plugin = plugin;
    }
}
