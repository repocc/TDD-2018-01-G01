package app;
import java.util.*;

public class Project {

    private String projectName;
    private List<User> users;
    private Map<Integer, Ticket> tickets;
    private int ticketCount;
    //deberia agregarle los estados inicial y final de la vida de los tickets?

    public Project(String name){
        projectName = name;
        users = new ArrayList<User>();
        tickets = new HashMap<Integer, Ticket>();

    }

    public void addUser(User user){
        users.add(user);
    }

    public void addTicket(){
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
        //cuando tenga el id autoincremental seria algo del estilo
        //tickets.put(ticket.getId(), ticket);
    };

    public String getProjectName(){
        return projectName;
    }

    public void showTickets(){
        /*este deberia exponerle al usuario los tickets, de la forma
        id + tituloTicket para que el otro pueda elegir cual ticket modificar
        ingresando el id segun corresponda.*/
    }


}


