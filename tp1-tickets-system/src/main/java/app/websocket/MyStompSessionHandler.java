package app.websocket;

import org.springframework.messaging.Message;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import java.util.Random;

public class MyStompSessionHandler extends StompSessionHandlerAdapter {

    private StompSession session = null;

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        session.subscribe(Constants.TOPIC, this);
        this.session = session;
        System.out.println("Connection established..");

    }
    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        Message msg = (Message) payload;
        System.out.println(payload);
    }

    public void publishData(String data) {
        if(this.session != null) {
            this.session.send(Constants.BROKER, data);
        } else {
            throw new RuntimeException("Not connected.");
        }
    }


}
