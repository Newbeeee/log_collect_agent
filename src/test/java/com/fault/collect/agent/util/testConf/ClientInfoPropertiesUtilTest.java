package com.fault.collect.agent.util.testConf;

import com.fault.collect.agent.AgentApplication;
import com.fault.collect.agent.util.testConf.ClientInfoPropertiesUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest(classes = AgentApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ClientInfoPropertiesUtilTest {

    @Test
    public void getHost() {
        Assert.assertEquals(ClientInfoPropertiesUtil.getHost(),
                "192.168.0.200");
    }

    @Test
    public void getNode() {
        Assert.assertEquals(ClientInfoPropertiesUtil.getNode(),
                "controller");
    }

    @Test
    public void getInsertLogUrl() {
        Assert.assertEquals(ClientInfoPropertiesUtil.getInsertLogUrl(),
                "http://192.168.0.199:9999/analysis/faultLogs");
    }

    @Test
    public void getStoragePath() {
        Assert.assertEquals(ClientInfoPropertiesUtil.getStoragePath(),
                "/Users/xiuxiongding/Desktop/fault_log/controller/192.168.0.200/");
    }

    @Test
    public void getScpPath() {
        Assert.assertEquals(ClientInfoPropertiesUtil.getScpPath(),
                "xiuxiongding@192.168.0.199:/Users/xiuxiongding/Desktop/fault_log/controller/192.168.0.200/");
    }
}