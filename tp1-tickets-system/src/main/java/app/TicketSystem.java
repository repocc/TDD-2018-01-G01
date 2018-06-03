package app;

import java.util.List;

public class TicketSystem {

    private static TicketSystem instance;
    private List<User> users;

    public static TicketSystem getInstance() {
        if(instance != null) {
            return instance;
        } else {
            instance = new TicketSystem();
            return instance;
        }
    }

    public void login() {
        System.out.println("Escriba su nombre: ");
        String name = System.console().readLine();
        users.add(new User(name));
        System.out.println("Bienvenido " + name);
    }
}
