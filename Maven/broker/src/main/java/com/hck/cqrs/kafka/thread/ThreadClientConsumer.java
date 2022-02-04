package com.hck.cqrs.kafka.thread;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

public class ThreadClientConsumer extends Thread {

    private final KafkaConsumer<String, Object> consumer;

    private static final Logger log = LoggerFactory.getLogger(ThreadClientConsumer.class);

    private static  final AtomicBoolean nextVal = new AtomicBoolean(false);

    public ThreadClientConsumer(KafkaConsumer<String, Object> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void run() {
        consumer.subscribe(Arrays.asList("hck-topic"));
        try {
            while (!nextVal.get()) {
                ConsumerRecords<String, Object> consumerRecords = consumer.poll(Duration.ofMillis(100));
                for(ConsumerRecord<String, Object> consumerRecord: consumerRecords) {
                    log.info("Offset = {}, Key = {}, Value = {}",
                            consumerRecord.offset(), consumerRecord.key(), consumerRecord.value());
                }
            }
        } catch (WakeupException e) {
            if(!nextVal.get()) {
                throw  e;
            }
        } finally {
            consumer.close();
        }
        super.run();
    }

    public void shutdown() {
        nextVal.set(true);
        consumer.wakeup();
    }
}
