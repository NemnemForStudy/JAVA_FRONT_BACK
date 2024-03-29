package com.nemnem.board.provider;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class SocketGroup {
    private String room;
    private WebSocketSession webSocketSession;
}
@Component
public class SocketProvider extends TextWebSocketHandler {
    
    private List<SocketGroup> sessionList = new ArrayList<>();

    @Override
    //? 처음에 연결하는 작업
    public void afterConnectionEstablished(WebSocketSession webSocketSession) {
        System.out.println("Socket Connected!!");
        System.out.println(webSocketSession.getHandshakeHeaders().getFirst("room"));
        
        String room = webSocketSession.getHandshakeHeaders().getFirst("room");
        sessionList.add(new SocketGroup(room, webSocketSession));
    }

    //? 굳이 Override를 안 적어도 되지만 오타 안내기 위해 적었음
    @Override
    //? 매개변수 WebSocketSession, TextMessage 생성
    //? 사용자가 서버를 연결한 상태에서 특정한 메세지를 보내면 textMessage 형태로 받아서 Payload로 볼 수 있다고 한다.
    //? 텍스트 형태에 메세지가 왔을 적에 작업
    protected void handleTextMessage(WebSocketSession webSocketSession, TextMessage textMessage) throws Exception {
        String messagePayload = textMessage.getPayload();
        String room = webSocketSession.getHandshakeHeaders().getFirst("room");

        for (SocketGroup socketGroup: sessionList) {
            if (socketGroup.getRoom().equals(room))
                socketGroup.getWebSocketSession().sendMessage(textMessage);
        }
    }

    @Override
    //? 연결 끊어졌을 때 어떠한 작업을 할 것인지 작성
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) {
        System.out.println("Socket Closed!");
        System.out.println(webSocketSession.toString());
        System.out.println(closeStatus.toString());
        
        for (SocketGroup socketGroup: sessionList) {
            if (socketGroup.getWebSocketSession().equals(webSocketSession)) {
                sessionList.remove(socketGroup);
            }
        }
        
    }

}
