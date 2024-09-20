package com.hck.cqrs.kafka.callbacks;

import com.hck.cqrs.kafka.common.IMessageBase;
import com.hck.cqrs.kafka.transactional.TransactionalConsumer;
import org.apache.kafka.clients.producer.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class ClientSendingCallbackProcess implements IMessageBase<Map<String, Object>> {

    private static  Logger log = LoggerFactory.getLogger(TransactionalConsumer.class);

    @Override
    public void sendMessage(Map<String, Object> config) {
        try (Producer<String, String> producer = new KafkaProducer<>(config);) {
            for (int i=0; i < 70000; i++) {

                producer.send(new ProducerRecord<String, String>("hck-topic", String.valueOf(i), "message-hck-" + i),
                        new Callback() {  // handling action after send the message (recordMetadata, exception  ) -> { } // using lambda
                            @Override
                                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                                    if(e != null ) {
                                        log.info("There was and error = {} ", e.getMessage());
                                    }

                                    log.info(
                                        "Callback-msg = {}, Offset = {}, Partition = {}, Topic = {}",
                                        "MSG-" + System.currentTimeMillis(), recordMetadata.offset(), recordMetadata.partition(), recordMetadata.topic()
                                    );
                            }
                        });
            }
            producer.flush();
        }
    }
}
