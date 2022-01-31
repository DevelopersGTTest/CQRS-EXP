package com.hck.cqrs.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ClientProducer {

    public static void main(String[] args) {

        // initial properties..
        Map<String, Object> configProps = new HashMap<>();
        configProps.put("bootstrap.servers","localhost:9092");
        configProps.put("acks","all");
        configProps.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        configProps.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");


        try (Producer<String, String> producer = new KafkaProducer<String, String>(configProps);) {
            //setting test massive messages
            for (int i=0; i < 7000; i++) {
                producer.send(new ProducerRecord<String, String>("hck-topic", String.valueOf(i), "asd-test-foo"));
            }
            producer.flush();
        }
    }

}
