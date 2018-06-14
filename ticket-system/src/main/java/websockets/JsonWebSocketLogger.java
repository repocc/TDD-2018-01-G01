package websockets;

public class JsonWebSocketLogger {

    private static JsonWebSocketLogger sender = new JsonWebSocketLogger();
    private MyStompSessionHandler session;

    private JsonWebSocketLogger() {
       session = WebSocketDataSender.connect(Constants.WS_ENDPOINT_URL);
    }

    public static JsonWebSocketLogger getInstance(){
        if(sender == null) {
            sender = new JsonWebSocketLogger();
        }

        return sender;
    }

    public void publishData(String data) {
        try {
            session.publishData(data);
        } catch(RuntimeException e) {
            //TODO: Log exception
        }
    }


}
