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
}
