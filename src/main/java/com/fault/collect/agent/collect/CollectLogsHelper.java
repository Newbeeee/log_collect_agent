package com.fault.collect.agent.collect;

import com.fault.collect.agent.util.PathUtil;
import com.fault.collect.agent.util.command.LinuxCommandUtil;
import com.fault.collect.agent.util.productionConf.ClientInfoPropertiesUtil;
import com.fault.collect.agent.util.productionConf.LogPathPropertiesUtil;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class CollectLogsHelper {

    private String grep;

    public String grep(String keywords) {
        String[] greps = keywords.split("-");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < greps.length; i++) {
            sb.append(greps[i] + "|");
        }
        sb.deleteCharAt(sb.length() - 1);
        grep = sb.toString();
        return grep;
    }

    public void redirect(String logPath, String toFileName) {
        String redirectPath = ClientInfoPropertiesUtil.getRedirectPath();
        //如果没有 ClientInfoPropertiesUtil.getRedirectPath()的目录，要新建
        //如果有目录，或新建成功，则上传文件
        //如果无目录且新建不成功，则抛出异常
        if (PathUtil.hasFileDir(redirectPath)) {
            String toLogPath = redirectPath + toFileName;
            LinuxCommandUtil.tail(logPath, grep, toLogPath);
            upload(toLogPath, toFileName);
        } else {
            System.out.println("无法重定向和上传日志文件:" + toFileName);
        }
    }

    public void insert(String service, String logPath) {
        String node = ClientInfoPropertiesUtil.getNode();
        String storagePath = ClientInfoPropertiesUtil.getStoragePath() + service + ".log";
        String host = ClientInfoPropertiesUtil.getHost();
        //发送http请求
        RestTemplate restTemplate = new RestTemplate();
        //创建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //url
        String url = ClientInfoPropertiesUtil.getInsertLogUrl();
        //构造请求map
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("node", node);
        params.add("service", service);
        params.add("log_path", logPath);
        params.add("storage_path", storagePath);
        params.add("host", host);

        //构建entity
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        //执行http请求
        ResponseEntity<String> responseEntity =
                restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        String body = responseEntity.getBody();
        System.out.println(body);
    }

    public void upload(String logPath, String toFileName) {
        String toRemotePath = ClientInfoPropertiesUtil.getScpPath()
                + toFileName;
        LinuxCommandUtil.scp(logPath, toRemotePath);
    }

    public void start(String keywords) {
        grep(keywords);
        List<String> keys = LogPathPropertiesUtil.getPropertyKeys();
        //遍历 log_path 配置文件
        //把 <服务名，服务日志文件地址> 取出
        //key为服务名，key.log 为重定向的文件名
        //value为服务日志文件地址
        for (String key : keys) {
            String logPath = LogPathPropertiesUtil.getPropertyValue(key);
            String toFileName = key + ".log";
            //重定向日志
            //重定向后的日志上传至中心收集节点
            redirect(logPath, toFileName);
            //发送日志入库请求
            insert(key, logPath);
        }
    }
}
