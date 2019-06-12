package com.fault.collect.agent.util.testConf;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

//已经废弃，在生产环境中读取文件失效
public class LogPathPropertiesUtil {

    private static Properties prop;

    private static Long lastModified = 0L;
    private static String CONFIG_NAME = "log_path.properties";

    //初始化加载配置文件
    private static void init() {
        prop = new Properties();
        String filePath = LogPathPropertiesUtil.class.getClassLoader()
                .getResource(CONFIG_NAME).getPath();
        BasePropertiesUtil.loadFile(prop, filePath);
    }

    //判断配置文件是否改动
    private static boolean isPropertiesModified() {
        File file = new File(LogPathPropertiesUtil.class.getClassLoader()
                .getResource(CONFIG_NAME).getPath());
        if (file.lastModified() > lastModified) {
            lastModified = file.lastModified();
            return true;
        }
        return false;
    }

    //获取配置文件中所有key值
    public static List<String> getPropertyKeys() {
        if (prop == null || isPropertiesModified()) {
            init();
        }

        List<String> res = new ArrayList<>();
        ArrayList<Object> keys = Collections.list(prop.keys());
        for (int i = 0; i < keys.size(); i++) {
            System.out.println(keys.get(i).toString());
            res.add(keys.get(i).toString());
        }
        return res;
    }

    //根据key获取value
    public static String getPropertyValue(String key) {
        if (prop == null || isPropertiesModified()) {
            init();
        }
        String value = prop.get(key).toString();
        System.out.println(value);
        return value;
    }
}
