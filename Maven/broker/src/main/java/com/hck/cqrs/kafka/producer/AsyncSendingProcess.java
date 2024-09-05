package com.hck.cqrs.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Map;

public class AsyncSendingProcess implements IMessageBase<Map<String, Object>> {

    @Override
    public void sendMessage(Map<String, Object> config) {
        System.out.println("run supporting generics...");
        try (Producer<String, String> producer = new KafkaProducer<>(config);) {
            //setting test massive messages
            for (int i=0; i < 70000; i++) {
                producer.send(new ProducerRecord<String, String>("hck-topic", String.valueOf(i), "asd-test-foo"));
            }
            producer.flush();
        }
    }
}
