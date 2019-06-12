package com.fault.collect.agent.util.testConf;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

//已经废弃，在生产环境中读取文件失效
public class BasePropertiesUtil {

    public static void loadFile(Properties prop, String filePath) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(filePath);
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
