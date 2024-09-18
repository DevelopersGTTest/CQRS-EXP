package com.hck.cqrs.kafka.producer;

import com.hck.cqrs.kafka.common.KafkaConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.HashMap;
import java.util.Map;

public class ClientProducer {

    public static void main(String[] args) {

        // support for generic types
        AsyncSendingProcess instance = new AsyncSendingProcess();
        // sending by dependency injection
        instance.sendMessage(KafkaConfig.getInstance().getConfig(false));
    }

}
