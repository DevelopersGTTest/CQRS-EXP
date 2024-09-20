package com.hck.cqrs.kafka.producer;

import com.hck.cqrs.kafka.common.KafkaConfig;

public class PaymentProducer {
    public static void main(String[] args) {

        PaymentSendingProcess instance = new PaymentSendingProcess();

        instance.sendMessage(KafkaConfig
                .getInstance()
                .getProducerConfig(false));
    }
}
