package com.hck.cqrs.kafka.callbacks;

import java.util.HashMap;
import java.util.Map;

public class ClientCallbackProducer {

    public static void main(String[] args) {

        Map<String, Object> configProps = new HashMap<>();
        configProps.put("bootstrap.servers","localhost:9092");
        configProps.put("acks","all");
        configProps.put("transactional.id","hck-producer-transactional");
        configProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        configProps.put("value.serializer",  "org.apache.kafka.common.serialization.StringSerializer");
    }

}
