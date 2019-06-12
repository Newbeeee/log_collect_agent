package com.fault.collect.agent.receiver.conf;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReceiverConf {

    @Bean
    public Queue startCollectMessage() {
        return new Queue("start_collect");
    }
}
