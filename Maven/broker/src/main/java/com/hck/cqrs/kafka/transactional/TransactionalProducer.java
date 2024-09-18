package com.hck.cqrs.kafka.transactional;

import com.hck.cqrs.kafka.common.IMessageBase;
import com.hck.cqrs.kafka.common.KafkaConfig;
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

        //sample of transactional
        TransactionalMessageProcess transactionalMessageProcess = new TransactionalMessageProcess();
        transactionalMessageProcess.sendMessage(KafkaConfig.getInstance().getConfig(true));
    }

}
