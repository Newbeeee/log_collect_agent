package com.fault.collect.agent.util.command;

import com.fault.collect.agent.AgentApplication;
import com.fault.collect.agent.util.command.LinuxCommandUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest(classes = AgentApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class LinuxCommandUtilTest {

    @Test
    public void tail() {
        String logPath = "/Users/xiuxiongding/Desktop/test.log";
        String grep = "11";
        String toLogPath = "/Users/xiuxiongding/Desktop/testLog/test1.log";
        LinuxCommandUtil.tail(logPath, grep, toLogPath);
    }

    @Test
    public void scp() {
        String logPath = "/Users/xiuxiongding/Desktop/test.log";
        String toRemotePath = "root@192.168.0.200:~/test/";
        LinuxCommandUtil.scp(logPath, toRemotePath);
    }

}