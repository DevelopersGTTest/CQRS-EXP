package com.hck.cqrs.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.HashMap;
import java.util.Map;

public class ClientProducer {

    public static void main(String[] args) {

        // initial properties..
        Map<String, Object> configProps = new HashMap<>();
        configProps.put("bootstrap.servers","localhost:9092");
        configProps.put("acks","all");
        configProps.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        configProps.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");

        // support for generic types
        AsyncSendingProcess instance = new AsyncSendingProcess();
        instance.sendMessage(configProps);
    }

}
