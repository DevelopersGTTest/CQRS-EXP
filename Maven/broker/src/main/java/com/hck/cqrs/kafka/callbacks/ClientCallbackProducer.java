package com.hck.cqrs.kafka.callbacks;

import org.apache.kafka.clients.producer.RecordMetadata;

public class ClientCallbackProducer {
    @Override
    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
        if (e != null) {
            e.printStackTrace();
        }
    }
    try {
        Object p  = p.send(record).get();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
