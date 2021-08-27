package com.mafei.rsocket.practies;

import io.rsocket.Payload;
import io.rsocket.RSocket;
import reactor.core.publisher.Mono;

public class MathService implements RSocket {
    @Override
    public Mono<Void> fireAndForget(Payload payload) {
        try {
            Thread.sleep(5000);
            System.out.println("Thread > " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String data = payload.getDataUtf8();
        System.out.println("data = " + data);
        return Mono.empty();
    }
}
