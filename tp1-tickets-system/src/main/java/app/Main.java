package app;

import app.ticketsystem.TicketSystem;

public class Main {

    public static void main(String args[]) {

        TicketSystem ticketSystem = TicketSystem.getInstance();
        ticketSystem.login();

    }
}
