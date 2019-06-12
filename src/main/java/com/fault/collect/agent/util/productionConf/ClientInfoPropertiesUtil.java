package com.fault.collect.agent.util.productionConf;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class ClientInfoPropertiesUtil {
    private static HashMap<String, String> map = new HashMap<>();

    private static String CONF_NAME = "client.properties";

    private static void load() {
        try {
            InputStream is = LogPathPropertiesUtil.class.getClassLoader()
                    .getResourceAsStream(CONF_NAME);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = br.readLine()) != null) {
                String[] content = line.split("=");
                map.put(content[0], content[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getValueByKey(String key) {
        if (map.size() == 0) {
            load();
        }
        return map.get(key);
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
