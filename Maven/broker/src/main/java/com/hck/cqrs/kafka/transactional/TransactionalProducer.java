package com.hck.cqrs.kafka.transactional;

import com.hck.cqrs.kafka.common.KafkaConfig;

public class TransactionalProducer  {

    public static void main(String[] args) {

        //sample of transactional
        TransactionalMessageProcess transactionalMessageProcess = new TransactionalMessageProcess();

        transactionalMessageProcess.sendMessage(KafkaConfig
                .getInstance()
                .getProducerConfig(true));
    }
}
