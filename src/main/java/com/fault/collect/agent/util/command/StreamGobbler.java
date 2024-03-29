package com.fault.collect.agent.util.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamGobbler extends Thread {
    private InputStream is;
    private String type;

    public StreamGobbler(InputStream is, String type) {
        this.is = is;
        this.type = type;
    }

    @Override
    public void run() {
        try {
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                if (type.equals("Error")) {
                    System.out.println("Error :" + line);
                } else {
                    System.out.println("Debug :" + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
