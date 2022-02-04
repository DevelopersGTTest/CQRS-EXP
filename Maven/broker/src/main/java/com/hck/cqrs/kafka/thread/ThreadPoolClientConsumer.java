package com.hck.cqrs.kafka.thread;

import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolClientConsumer {

    public static void main(String[] args) {

        // initial properties..
        Map<String, Object> configProps = new HashMap<>();
        configProps.put("bootstrap.servers","localhost:9092");
        configProps.put("group.id","hck-test-group");
        configProps.put("enable.auto.commit","true");
        configProps.put("auto.commit.interval.ms","1000");
        configProps.put("key.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        configProps.put("value.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for(int j=0; j< 5; j++ ) {
            ThreadClientConsumer consumer =  new ThreadClientConsumer(new KafkaConsumer<>(configProps));
            executorService.execute(consumer);
        }
        while (!executorService.isTerminated());
    }
}
