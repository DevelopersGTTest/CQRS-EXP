package com.hck.cqrs.kafka.callbacks;

import com.hck.cqrs.kafka.common.KafkaConfig;

public class ClientCallbackProducer {

    public static void main(String[] args) {

        ClientSendingCallbackProcess instance = new ClientSendingCallbackProcess();

        instance.sendMessage(KafkaConfig
                .getInstance()
                .getProducerConfig(false));
    }

}
