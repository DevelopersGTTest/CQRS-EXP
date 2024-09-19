package com.hck.cqrs.kafka.common;

import java.util.HashMap;
import java.util.Map;

public class KafkaConfig {
    private static KafkaConfig instance;

    public static KafkaConfig getInstance() {
        if( instance == null) {
            instance = new KafkaConfig();
        }

        return instance;
    }

    public Map<String, Object> getConfig(Boolean isTransactional) {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put("bootstrap.servers","localhost:9092");
        configProps.put("group.id","hck-test-group");
        configProps.put("enable.auto.commit","true");
        if(isTransactional) {
            configProps.put("isolation.level", "read_committed");
            configProps.put("acks","all");
            configProps.put("key.serializer",
                    "org.apache.kafka.common.serialization.StringDeserializer");
            configProps.put("value.serializer",
                    "org.apache.kafka.common.serialization.StringDeserializer");
        } else {
            configProps.put("key.deserializer",
                    "org.apache.kafka.common.serialization.StringDeserializer");
            configProps.put("value.deserializer",
                    "org.apache.kafka.common.serialization.StringDeserializer");
        }
        configProps.put("auto.commit.interval.ms","1000");

        return configProps;
    }


    public Map<String, Object> getConsumerConfig() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put("bootstrap.servers","localhost:9092");
        configProps.put("group.id","devs4j-group");
        configProps.put("enable.auto.commit","true");
        configProps.put("auto.commit.interval.ms","1000");
        configProps.put("key.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        configProps.put("value.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");

        return configProps;
    }


    public Map<String, Object> getProducerConfig() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put("bootstrap.servers","localhost:9092");
        configProps.put("aks","1");
        configProps.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        configProps.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");

        return configProps;
    }


}
