package com.nemnem.board.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.nemnem.board.provider.SocketProvider;

@Configuration
@EnableWebSocket
//? 인터페이스 구현을 위해 implements
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired private SocketProvider socketProvider;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        //? addHandler(어떤 것을 넣을것인지, 연결할 때 어느 경로로 연결할 것인지)
        registry.addHandler(socketProvider, "/web-socket").setAllowedOrigins("*");
    }
}
