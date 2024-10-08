package com.hck.cqrs.kafka.consumer;

import com.hck.cqrs.kafka.common.KafkaConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class ClientConsumer {

    private static final Logger log = LoggerFactory.getLogger(ClientConsumer.class);

    private static  final AtomicBoolean nextVal = new AtomicBoolean(true);

    public static void main(String[] args) {

        try (KafkaConsumer<String, Object> consumer = new KafkaConsumer<>(
                    KafkaConfig.getInstance().getConsumerConfig(false))) {
            consumer.subscribe(Arrays.asList("hck-topic"));
            while(nextVal.get()) {
                ConsumerRecords<String, Object> consumerRecords = consumer.poll(Duration.ofMillis (1000));
                for (ConsumerRecord<String, Object> consumerRecord : consumerRecords ) {
                    log.info("Offset = {}, Key = {}, Value = {}, Partition = {}",
                            consumerRecord.offset(), consumerRecord.key(), consumerRecord.value(), consumerRecord.partition()
                    );
                }
            }
        }
    }
}

