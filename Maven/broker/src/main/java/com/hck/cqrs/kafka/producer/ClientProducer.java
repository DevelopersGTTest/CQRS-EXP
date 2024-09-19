package com.hck.cqrs.kafka.producer;

import com.hck.cqrs.kafka.common.KafkaConfig;

public class ClientProducer {

    public static void main(String[] args) {

        // support for generic types
        AsyncSendingProcess instance = new AsyncSendingProcess();

        // sample of producer
        instance.sendMessage(KafkaConfig
                .getInstance()
                .getProducerConfig());
    }

}
