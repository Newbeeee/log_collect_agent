package com.fault.collect.agent.util;

import java.io.File;

public class PathUtil {
    public static boolean hasFileDir(String path) {
        try {
            File file = new File(path);
            if (file.getParentFile().isDirectory()) {
                if (!file.exists()) {
                    file.mkdirs();
                }
                return true;
            } else {
                throw new Exception("传入目录非标准目录名");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
