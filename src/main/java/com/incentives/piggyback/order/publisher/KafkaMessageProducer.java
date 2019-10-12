package com.incentives.piggyback.order.publisher;

import com.incentives.piggyback.order.util.constants.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageProducer {

    @Autowired
    private Environment env;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String message){
        kafkaTemplate.send(env.getProperty(Constant.ORDER_PUBLISHER_TOPIC), message);
    }

}