package chat;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.io.IOException;

@SuppressWarnings("UnusedDeclaration")
@WebSocket
public class ChatWebSocket {
    private final ChatService service;
    private Session session;

    public ChatWebSocket(ChatService service) {
        this.service = service;
    }

    @OnWebSocketConnect
    public void onOpen(Session session) {
        service.add(this);
        this.session = session;
    }

    @OnWebSocketMessage
    public void sendMessage(String data) {
        service.sendMessage(data);
    }

    @OnWebSocketClose
    public void removeSocket(int statusCode, String reason) {
        service.remove(this);
    }

    public void send(String data) {
        try {
            session.getRemote().sendString(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
