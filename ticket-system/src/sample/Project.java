package sample;

import java.util.*;

public class Project {

    private String projectName;
    private Map<Integer, User> users;
    private Map<Integer, String> roles;
    private Map<Integer, Ticket> tickets;
    private int ticketCount;
    private User owner;
    //private List<State> states;

    public Project(String name){
        projectName = name;
        tickets = new HashMap<Integer, Ticket>();
        //states = new ArrayList<State>();
        users = new HashMap<Integer, User>();
        roles = new HashMap<Integer, String>();

    }

    /*public void addTicket(User user){
        System.out.println("Nombre del ticket: ");
        Scanner scanner = new Scanner(System.in);
        String title =  scanner.nextLine();
        Ticket ticket = new Ticket(title, this.states.get(0).getName());
        System.out.println("Agregue una descripcion: ");
        String description =  scanner.nextLine();
        ticket.setDescription(description);
        System.out.println("Tipo del ticket: ");
        String type =  scanner.nextLine();
        ticket.setType(type);
        ticket.setOwner(user);
        System.out.println("Agregue un responsable: ");
        this.showUsers(users);
        System.out.println("Id usuario: ");
        String idUser = scanner.nextLine();
        Integer idInteger = Integer.valueOf(idUser);
        ticket.setResponsable(this.users.get(idInteger));
        this.tickets.put((++ticketCount),ticket);
        System.out.println("Estado actual: " + this.states.get(0).getName());
    }*/

    public String getProjectName(){
        return projectName;
    }

    public void showTickets(){
        for (Integer id: tickets.keySet()){
            String key = id.toString();
            //String value = tickets.get(id).getTitle();
           // System.out.println(key + " " + value);
        }
    }

    private void editTicket(User user) {
        this.showTickets();
        System.out.println("Ingrese el id del ticket que desea editar:");
        Scanner scanner = new Scanner(System.in);
        String idTicket =  scanner.nextLine();
        System.out.println("Eligio: " + idTicket + " para editar");
        Ticket ticket = tickets.get(Integer.valueOf(idTicket));
        //ticket.showMenu(user, this.states);
    }


    public void showMenu(User user, Map<Integer, User> users) {
        System.out.println("Que desea hacer?: ");
        System.out.println("1- Agregar ticket ");
        System.out.println("2- Editar ticket ");
        System.out.println("3- Ver informacion de algun ticket");
        System.out.println("4- Agregar gente al proyecto");
        System.out.println("5- Volver al menu principal ");
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        int intOption = Integer.parseInt(option);
        switch (intOption) {
            case 1:
                //this.addTicket(user);
                this.showMenu(user, users);
                break;
            case 2:
                this.editTicket(user);
                this.showMenu(user, users);
                break;
            case 3:
                this.showTicketInfo();
                this.showMenu(user, users);
                break;
            case 4:
                this.addUser(users);
                break;
            case 5:
                break;
            default:
                this.showMenu(user, users);
        }
    }

    private void showTicketInfo() {
        this.showTickets();
        System.out.println("Ingrese el id del ticket del que desea ver los detalles:");
        Scanner scanner = new Scanner(System.in);
        String idTicket = scanner.nextLine();
        Ticket ticket = tickets.get(Integer.valueOf(idTicket));
        //ticket.showInfo();
    }

    private void addUser(Map<Integer, User> users) {
        //this.showUsers(users);
        System.out.println("Id usuario a agregar al proyecto: ");
        Scanner scanner = new Scanner(System.in);
        String idUser = scanner.nextLine();
        Integer idInteger = Integer.valueOf(idUser);
        if (!this.users.containsKey(idInteger)) {
            this.users.put(idInteger, users.get(idInteger));
        } else {
            System.out.println("El usuario ya esta en el proyecto");
        }
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    /*public void setStates() {
        System.out.println("Desea modificar el flujo de estados del proyecto? Si elige que no, se setearán un estado inicial y final por default (S/N): ");
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        if (option.equals("S") || option.equals("s")) {
            this.setStateFlow();
        } else if (option.equals("N") || option.equals("n")) {
            this.setDefaultStates();
        } else {
            this.setStates();
        }

    }*/

    /*private void setStateFlow() {
        System.out.println("Estado inicial: ");
        Scanner scanner = new Scanner(System.in);
        String state = scanner.nextLine();
        this.addState(new State(state));
        System.out.println("Siguiente estado (luego de agregar el estado final presione F): ");
        state = scanner.nextLine();
        while (!state.equals("F") && !state.equals("f")) {
            this.addState(new State(state));
            System.out.println("Siguiente estado (luego de agregar el estado final presione F): ");
            state = scanner.nextLine();
        }
    }*/

    /*private void setDefaultStates() {
        this.addState(new State("Backlog"));
        this.addState(new State("Finished"));
    }

    private void addState(State state) {
        this.states.add(state);
    }

    public void setRoles(Map<Integer, User> users) {
        System.out.println("Elija los usuarios que van a colaborar en el proyecto (presione 0 para salir):");
        System.out.println("Los usuarios disponibles son: ");
        this.showUsers(users);
        System.out.println("Id usuario: ");
        Scanner scanner = new Scanner(System.in);
        String idUser = scanner.nextLine();
        Integer idInteger = Integer.valueOf(idUser);
        while (idInteger != 0) {
            if (users.containsKey(idInteger)) {
                this.users.put(idInteger, users.get(idInteger));
                System.out.println("Qué rol va a desempeñar? ");
                String role = scanner.nextLine();
                this.roles.put(idInteger, role);
            } else {
                System.out.println("El id ingresado no corresponde con ningun usuario");
            }
            System.out.println("Id usuario: ");
            idUser = scanner.nextLine();
            idInteger = Integer.valueOf(idUser);
        }
    }

    private void showUsers(Map<Integer, User> users) {
        for (Integer id: users.keySet()){
            String key = id.toString();
            String value = users.get(id).getName();
            System.out.println(key + " " + value);
        }
    }

    public void showInfo() {
        System.out.println("Titulo: " + this.projectName);
        System.out.println("Creador: " + this.owner.getName());
        System.out.println("Participantes: ");
        for (Integer id: users.keySet()){
            String value = users.get(id).getName();
            System.out.println(value + ": " + roles.get(id));
        }
        if (!tickets.isEmpty()) {
            System.out.println("Tickets: " + this.owner.getName());
            for (Integer id : tickets.keySet()) {
                String value = tickets.get(id).getTitle();
                System.out.println(value + ": " + tickets.get(id).getActualState().getName());
            }
        }
    }*/
}


