package com.fault.collect.agent.util.testConf;

import com.fault.collect.agent.AgentApplication;
import com.fault.collect.agent.util.testConf.LogPathPropertiesUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest(classes = AgentApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class LogPathPropertiesUtilTest {

    @Test
    public void getAllKeys() {
        LogPathPropertiesUtil.getPropertyKeys();
    }

    @Test
    public void getValueByKey() {
        LogPathPropertiesUtil.getPropertyValue("glance-api");
    }
}