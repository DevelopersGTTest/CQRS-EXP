package com.hck.cqrs.kafka.transactional;

import com.hck.cqrs.kafka.common.KafkaConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;

public class TransactionalConsumer {

    private static final Logger log = LoggerFactory.getLogger(TransactionalConsumer.class);

    public static void main(String[] args) {

        try (KafkaConsumer<String, Object> consumer = new KafkaConsumer<>(KafkaConfig
                .getInstance()
                .getConsumerConfig(true))) {
            consumer.subscribe(Arrays.asList("hck-topic"));
            while(true) {
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
