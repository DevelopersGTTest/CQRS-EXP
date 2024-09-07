package com.hck.cqrs.kafka.transactional;

import com.hck.cqrs.kafka.common.IMessageBase;
import com.hck.cqrs.kafka.consumer.ClientConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class TransactionalProducer  {

    public static void main(String[] args) {

        // initial properties..
        Map<String, Object> configProps = new HashMap<>();
        configProps.put("bootstrap.servers","localhost:9092");
        configProps.put("acks","all");
        configProps.put("transactional.id","hck-producer-transactional");
        configProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        configProps.put("value.serializer",  "org.apache.kafka.common.serialization.StringSerializer");

        //sample of transactional
        TransactionalMessageProcess transactionalMessageProcess = new TransactionalMessageProcess();
        transactionalMessageProcess.sendMessage(configProps);

    }

}
