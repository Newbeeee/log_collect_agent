package com.fault.collect.agent.util.productionConf;

import com.fault.collect.agent.AgentApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@SpringBootTest(classes = AgentApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ClientInfoPropertiesUtilTest {

    @Test
    public void getHost() {
        String res = ClientInfoPropertiesUtil.getHost();
        System.out.println(res);
    }
}