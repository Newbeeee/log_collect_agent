package com.fault.collect.agent.receiver;

import com.fault.collect.agent.collect.CollectLogsHelper;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class StartCollectReceiver {

    @Autowired
    CollectLogsHelper collectLogsHelper;

    @RabbitListener(queues = "start_collect")
    public void startCollectLogs(Message message, Channel channel) {
        //开始收集日志
        System.out.println("开始收集日志");
        collectLogsHelper.start(message.toString());
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
