package com.hck.cqrs.kafka.thread;

import com.hck.cqrs.kafka.common.KafkaConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolClientConsumer {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for(int j=0; j< 5; j++ ) {
            ThreadClientConsumer consumer =  new ThreadClientConsumer(new KafkaConsumer<>(KafkaConfig
                    .getInstance()
                    .getConsumerConfig(false)));
            executorService.execute(consumer);
        }
        while (!executorService.isTerminated());
    }
}
