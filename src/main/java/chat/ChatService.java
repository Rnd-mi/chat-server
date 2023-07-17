package chat;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ChatService {
    private Set<ChatWebSocket> webSockets;

    public ChatService() {
        webSockets = Collections.newSetFromMap(new ConcurrentHashMap<>());
    }

    public void add(ChatWebSocket webSocket) {
        webSockets.add(webSocket);
    }

    public void sendMessage(String data) {

        for (ChatWebSocket webSocket : webSockets) {
            webSocket.send(data);
        }
    }

    public void remove(ChatWebSocket webSocket) {
        webSockets.remove(webSocket);
    }
}
