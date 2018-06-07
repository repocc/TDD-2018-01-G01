package app;

import app.ticketsystem.TicketSystem;
import app.websocket.JsonMessageSender;

public class Main {

    public static void main(String args[]) {

        JsonMessageSender.getInstance();
        TicketSystem ticketSystem = TicketSystem.getInstance();
        ticketSystem.login();

    }
}
