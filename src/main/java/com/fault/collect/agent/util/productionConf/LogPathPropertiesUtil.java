package com.fault.collect.agent.util.productionConf;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LogPathPropertiesUtil {

    private static HashMap<String, String> map = new HashMap<>();

    private static String CONF_NAME = "log_path.properties";

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

    public static List<String> getPropertyKeys() {
        if (map.size() == 0) {
            load();
        }
        List<String> res = new ArrayList<>(map.keySet());
        return res;
    }

    public static String getPropertyValue(String key) {
        if (map.size() == 0) {
            load();
        }
        return map.get(key);
    }
}
