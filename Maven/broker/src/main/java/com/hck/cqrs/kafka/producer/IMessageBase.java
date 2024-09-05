package com.hck.cqrs.kafka.producer;

public interface IMessageBase<T> {

    void sendMessage(T config);
}
