package com.mafei.rsocket.practies;

import io.rsocket.core.RSocketServer;
import io.rsocket.transport.netty.server.CloseableChannel;
import io.rsocket.transport.netty.server.TcpServerTransport;

public class MainRun {
    public static void main(String[] args) {
        RSocketServer rSocketServer = RSocketServer.create(new SocketAcceptorImpl());
        CloseableChannel closeableChannel = rSocketServer.bindNow(TcpServerTransport.create(6565));
        //keep listening
        closeableChannel.onClose().block();
    }
}
