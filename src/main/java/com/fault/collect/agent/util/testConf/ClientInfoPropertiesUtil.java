package com.fault.collect.agent.util.testConf;

import java.io.File;
import java.util.Properties;

//已经废弃，在生产环境中读取文件失效
public class ClientInfoPropertiesUtil {
    private static Properties prop;

    private static Long lastModified = 0L;
    private static String CONFIG_NAME = "client.properties";

    //初始化加载配置文件
    private static void init() {
        prop = new Properties();
        String filePath = ClientInfoPropertiesUtil.class.getClassLoader()
                .getResource(CONFIG_NAME).getPath();
        BasePropertiesUtil.loadFile(prop, filePath);
    }

    //判断配置文件是否改动
    private static boolean isPropertiesModified() {
        File file = new File(ClientInfoPropertiesUtil.class.getClassLoader()
                .getResource(CONFIG_NAME).getPath());
        if (file.lastModified() > lastModified) {
            lastModified = file.lastModified();
            return true;
        }
        return false;
    }

    private static String getValueByKey(String key) {
        if (prop == null || isPropertiesModified()) {
            init();
        }
        String value = prop.get(key).toString();
        return value;
    }

    public static String getHost() {
        return getValueByKey("client.host");
    }

    public static String getNode() {
        return getValueByKey("client.node");
    }

    public static String getInsertLogUrl() {
        return getValueByKey("client.insert_log_url");
    }

    public static String getStoragePath() {
        return getValueByKey("client.storage_path");
    }

    public static String getScpPath() {
        return getValueByKey("client.scp_path");
    }

    public static String getRedirectPath() {
        return getValueByKey("client.redirect_path");
    }
}
