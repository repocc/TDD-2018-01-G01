package app.websocket;

public class JsonMessageSender {

    private static JsonMessageSender sender;
    private MyStompSessionHandler session;

    private JsonMessageSender() {
       session = WebSocketDataSender.connect(Constants.WS_ENDPOINT_URL);
    }

    public static JsonMessageSender getInstance(){
        if(sender == null) {
            sender = new JsonMessageSender();
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
