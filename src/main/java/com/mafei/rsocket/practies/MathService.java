package com.mafei.rsocket.practies;

import com.mafei.rsocket.practies.bean.RequestBean;
import com.mafei.rsocket.practies.bean.ResponseBean;
import com.mafei.rsocket.practies.util.ObjectUtil;
import io.rsocket.Payload;
import io.rsocket.RSocket;
import reactor.core.publisher.Mono;

public class MathService implements RSocket {
    @Override
    public Mono<Void> fireAndForget(Payload payload) {
        RequestBean data = ObjectUtil.toObject(payload, RequestBean.class);
        System.out.println("data = " + data);
        return Mono.empty();
    }

    @Override
    public Mono<Payload> requestResponse(Payload payload) {
        return Mono.fromSupplier(() -> {
            RequestBean data = ObjectUtil.toObject(payload, RequestBean.class);
            ResponseBean responseBean = new ResponseBean(data.getInput(), (
                    data.getInput() * data.getInput()
            ));
            return ObjectUtil.toPayload(responseBean);
        });
    }
}
