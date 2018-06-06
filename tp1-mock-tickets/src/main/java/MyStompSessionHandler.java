import org.springframework.messaging.Message;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import java.util.Random;

public class MyStompSessionHandler extends StompSessionHandlerAdapter {

    private StompSession session = null;

    @Override
    public void afterConnected(
            StompSession session, StompHeaders connectedHeaders) {
        session.subscribe(Constants.TOPIC, this);

        this.session = session;

        while (true) {
            Random rand = new Random();
            Integer age = (Math.abs(rand.nextInt()) % 120);
            publishData("{\"name\": \"Dan\",\"age\": "+ age +"}");
            sleep();
            age = (Math.abs(rand.nextInt()) % 120);
            publishData( "{\"name\": \"Julia\",\"age\": "+ age +"}");
            sleep();
            age = (Math.abs(rand.nextInt()) % 120);
            publishData( "{\"name\": \"Johnny\",\"age\": "+ age +"}");
            sleep();
        }

    }
    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        Message msg = (Message) payload;
        System.out.println(payload);
    }

    public void sleep() {
        try {
            Thread.sleep(Constants.TIME_TO_WAIT);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void publishData(String data) {
        if(this.session != null) {
            this.session.send(Constants.BROKER, data);
        } else {
            throw new RuntimeException("Not connected.");
        }
    }

}
