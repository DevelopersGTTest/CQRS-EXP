package com.hck.cqrs.kafka.transactional;

import com.hck.cqrs.kafka.consumer.ClientConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class TransactionalProducer {

    private static final Logger log = LoggerFactory.getLogger(TransactionalProducer.class);

    public static void main(String[] args) {

        // initial properties..
        Map<String, Object> configProps = new HashMap<>();
        configProps.put("bootstrap.servers","localhost:9092");
        configProps.put("acks","all");
        configProps.put("transactional.id","hck-producer-transactional");
        configProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        configProps.put("value.serializer",  "org.apache.kafka.common.serialization.StringSerializer");

        try (Producer<String, String> producer = new KafkaProducer<>(configProps);) {
            try {
                producer.initTransactions();
                producer.beginTransaction();
                for (int i=0; i < 7000; i++) {
                    producer.send(new ProducerRecord<String, String>("hck-topic", String.valueOf(i), "asd-test-foo"));

                    if (i == 200 ) { // simulate crash case
                        throw new Exception("Unexpected error");
                    }
                }
                producer.commitTransaction();
                producer.flush();
            } catch (Exception e) {
                log.error("Error ", e);
                producer.abortTransaction();
            }
        }
    }

}
