package com.hck.cqrs.kafka.common;

public interface IMessageBase<T> {

    void sendMessage(T config);
}
