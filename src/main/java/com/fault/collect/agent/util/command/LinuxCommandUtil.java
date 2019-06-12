package com.fault.collect.agent.util.command;

import java.io.IOException;

public class LinuxCommandUtil {
    public static void tail(String logPath, String grep, String toLogPath) {
        String tail = String.format("tail -n 50 %s | grep -E \"%s\" > %s", logPath, grep, toLogPath);
        shell(tail);
    }

    public static void scp(String logPath, String toRemotePath) {
        String scp = String.format("scp %s %s", logPath, toRemotePath);
        shell(scp);
    }

    private static void shell(String cmd) {
        String[] cmds = {"/bin/sh", "-c", cmd};
        Process process;
        try {
            process = Runtime.getRuntime().exec(cmds);
            StreamGobbler errorGobbler = new StreamGobbler(process.getErrorStream(), "Error");
            StreamGobbler outputGobbler = new StreamGobbler(process.getInputStream(), "Output");
            errorGobbler.start();
            outputGobbler.start();
            try {
                process.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
