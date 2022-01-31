package com.hck.cqrs.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class ClientProducer {

    public static void main(String[] args) {

        Properties props=new Properties();
        props.put("bootstrap.servers","localhost:9092");
        props.put("acks","all");
        props.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");


        try (Producer<String, String> producer = new KafkaProducer<String, String>(props);) {
            producer.send(new ProducerRecord<String, String>("hck-topic", "test", "asd-test-foo"));
        }

    }

}
