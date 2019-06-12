package com.fault.collect.agent.collect;

import com.fault.collect.agent.AgentApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@SpringBootTest(classes = AgentApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class CollectLogsHelperTest {

    @Autowired
    CollectLogsHelper collectLogsHelper;

    @Test
    public void grep() {
        String res = collectLogsHelper.grep("1-2-3");
        System.out.println(res);
    }

    @Test
    public void redirect() throws Exception {
//        String logPath = "/Users/xiuxiongding/Desktop/test.log";
//        String toFileName = "test.log";
//        collectLogsHelper.grep("1");
//        collectLogsHelper.redirect(logPath, toFileName);
    }
}