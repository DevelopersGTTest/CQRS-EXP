package com.hck.cqrs.kafka.consumer;

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

        try (KafkaConsumer<String, Object> consumer = new KafkaConsumer<>(configProps) ) {
            consumer.subscribe(Arrays.asList("hck-topic"));
            while(nextVal.get()) {
                ConsumerRecords<String, Object> consumerRecords = consumer.poll(Duration.ofMillis (1000));
                for (ConsumerRecord<String, Object> consumerRecord : consumerRecords ) {
                    log.info("Offset = {}, Key = {}, Value = {}",
                            consumerRecord.offset(), consumerRecord.key(), consumerRecord.value()
                    );
                }
            }
        }
    }
}
