package com.hck.cqrs.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Iterator;

public class SyncFyCunsumer {

    Iterator nextVal = null;

    onLoadData(Object configProps) {
        try (KafkaConsumer<String, Object> consumer = new KafkaConsumer<>(configProps) ) {
            consumer.subscribe(Arrays.asList("hck-topic"));
            while(nextVal.next().hashCode()) {
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
