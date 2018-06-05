package app;
import java.util.*;

public class Project {

    private String projectName;
    private List<User> users;
    private Map<Integer, Ticket> tickets;
    private int ticketCount;
    private User owner;
    private List<State> states;
    //deberia agregarle los estados inicial y final de la vida de los tickets?

    public Project(String name){
        projectName = name;
        users = new ArrayList<User>();
        tickets = new HashMap<Integer, Ticket>();
        states = new ArrayList<State>();
    }

    public void addUser(User user){
        users.add(user);
    }

    public void addTicket(User user){
        System.out.println("Nombre del ticket: ");
        Scanner scanner = new Scanner(System.in);
        String title =  scanner.nextLine();
        Ticket ticket = new Ticket(title);
        System.out.println("Agregue una descripcion: ");
        String description =  scanner.nextLine();
        ticket.setDescription(description);
        System.out.println("Tipo del ticket: ");
        String type =  scanner.nextLine();
        ticket.setType(type);
        this.tickets.put((++ticketCount),ticket);
        ticket.setOwner(user);
        System.out.println("Estado actual: " + this.states.get(0).getName());
    }

    public String getProjectName(){
        return projectName;
    }

    public void showTickets(){
        for (Integer id: tickets.keySet()){
            String key = id.toString();
            String value = tickets.get(id).getTitle();
            System.out.println(key + " " + value);
        }
    }

    private void editTicket(User user) {
        this.showTickets();
        System.out.println("Ingrese el id del ticket que desea editar:");
        Scanner scanner = new Scanner(System.in);
        String idTicket =  scanner.nextLine();
        System.out.println("Eligio: " + idTicket + " para editar");
        Ticket ticket = tickets.get(Integer.valueOf(idTicket));
        ticket.showMenu(user, this.states);
    }


    public void showMenu(User user) {
        System.out.println("Que desea hacer?: ");
        System.out.println("1- Agregar ticket ");
        System.out.println("2- Editar ticket ");
        System.out.println("3- Mostrar todos los tickets");
        System.out.println("4- Volver al menu principal ");
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        int intOption = Integer.parseInt(option);
        switch (intOption) {
            case 1:
                this.addTicket(user);
                this.showMenu(user);
                break;
            case 2:
                this.editTicket(user);
                this.showMenu(user);
                break;
            case 3:
                this.showTickets();
                this.showMenu(user);
                break;
            case 4:
                break;
            default:
                this.showMenu(user);
        }
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setStates() {
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

    }

    private void setStateFlow() {
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
    }

    private void setDefaultStates() {
        this.addState(new State("Backlog"));
        this.addState(new State("Finished"));
    }

    private void addState(State state) {
        this.states.add(state);
    }
}


