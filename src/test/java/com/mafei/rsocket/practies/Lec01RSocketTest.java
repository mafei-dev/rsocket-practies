package com.mafei.rsocket.practies;

import com.mafei.rsocket.practies.bean.RequestBean;
import com.mafei.rsocket.practies.bean.ResponseBean;
import com.mafei.rsocket.practies.util.ObjectUtil;
import io.rsocket.Payload;
import io.rsocket.RSocket;
import io.rsocket.core.RSocketConnector;
import io.rsocket.transport.netty.client.TcpClientTransport;
import io.rsocket.util.DefaultPayload;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Lec01RSocketTest {
    private RSocket socket;

    @BeforeAll
    public void setUp() {
        socket = RSocketConnector.create().connect(TcpClientTransport.create("localhost", 6565)).block();
    }

    @Test
    public void testFireAndForget() {

//        Payload payload = DefaultPayload.create("hi Mafei");
        RequestBean requestBean = new RequestBean(5);
        Payload payload = ObjectUtil.toPayload(requestBean);
        Mono<Void> voidMono = socket.fireAndForget(payload);
        StepVerifier.create(voidMono).verifyComplete();

        /* -- can't call like this twice one mono.
        StepVerifier.create(voidMono).verifyComplete();
        StepVerifier.create(voidMono).verifyComplete();
        */
    }

    @RepeatedTest(5)
    public void testManyTimesFireAndForget() {
        Payload payload = DefaultPayload.create("hi Mafei");
        Mono<Void> voidMono = socket.fireAndForget(payload);
        StepVerifier.create(voidMono).verifyComplete();
    }


    @Test
    public void testRequestResponse() {
        RequestBean requestBean = new RequestBean(5);
        Payload payload = ObjectUtil.toPayload(requestBean);
        Mono<ResponseBean> responseBeanMono = socket
                .requestResponse(payload)
                .map(_payload -> ObjectUtil.toObject(_payload, ResponseBean.class))
                .doOnNext(System.out::println);
        StepVerifier.create(responseBeanMono)
                .expectNextCount(1)
                .verifyComplete();
    }


    @Test
    public void testRequestStream() {
        RequestBean requestBean = new RequestBean(5);
        Payload payload = ObjectUtil.toPayload(requestBean);
        Flux<ResponseBean> payloadFlux = socket.requestStream(payload)
                .map(_payload -> ObjectUtil.toObject(_payload, ResponseBean.class))
                .doOnNext(System.out::println);

        StepVerifier.create(payloadFlux)
                .expectNextCount(10)
                .verifyComplete();
    }
}
