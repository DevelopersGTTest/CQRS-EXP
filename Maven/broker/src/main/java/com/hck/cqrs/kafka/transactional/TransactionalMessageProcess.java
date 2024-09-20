package com.hck.cqrs.kafka.transactional;

import com.hck.cqrs.kafka.common.IMessageBase;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class TransactionalMessageProcess implements IMessageBase<Map<String, Object>> {

    private static final Logger log = LoggerFactory.getLogger(TransactionalProducer.class);
    @Override
    public void sendMessage(Map<String, Object> config) {
        try (Producer<String, String> producer = new KafkaProducer<>(config);) {
            try {
                producer.initTransactions();
                producer.beginTransaction();
                for (int i=0; i < 7000; i++) {
                    producer.send(new ProducerRecord<String, String>(
                            "hck-topic", String.valueOf(i),
                            "message-transactional-n-" + i )
                    );

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
