package com.hck.cqrs.kafka.producer;

import com.hck.cqrs.kafka.common.IMessageBase;
import com.hck.cqrs.kafka.entities.Payment;
import com.hck.cqrs.kafka.entities.User;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PaymentSendingProcess implements IMessageBase<Map<String, Object>> {
    @Override
    public void sendMessage(Map<String, Object> config) {
        User user1020 = new User(1, "1020");
        User user1021 = new User(2, "2021");

        List<Payment> paymentList = Arrays.asList(
                new Payment(user1020, "Deposit", 200, OffsetDateTime.now()),
                new Payment(user1020, "Deposit", 100, OffsetDateTime.now()),
                new Payment(user1020, "Deposit", 200, OffsetDateTime.now()),
                new Payment(user1020, "WithDraw", -300, OffsetDateTime.now()),
                new Payment(user1021, "Deposit", 200, OffsetDateTime.now()),
                new Payment(user1021, "Deposit", 200, OffsetDateTime.now())
        );

        try (Producer<String, String> producer = new KafkaProducer<>(config);) {
            for(Payment p: paymentList) {
                producer.send(new ProducerRecord<String, String>(
                        "hck-topic",
                        ((p.getUser().getCode().equals("1020")) ? "1020" : "1021"),
                        p.toString()
                ));
            }
            producer.flush();
        }
    }
}
