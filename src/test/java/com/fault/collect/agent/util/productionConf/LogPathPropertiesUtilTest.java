package com.fault.collect.agent.util.productionConf;

import com.fault.collect.agent.AgentApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest(classes = AgentApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class LogPathPropertiesUtilTest {

    @Test
    public void getKeys() {
        List<String> res = LogPathPropertiesUtil.getPropertyKeys();
        for (String key : res) {
            System.out.println(key);
        }
    }

    @Test
    public void getOneValue() {
        String res = LogPathPropertiesUtil.getPropertyValue("glance-api");
        System.out.println(res);
    }

}