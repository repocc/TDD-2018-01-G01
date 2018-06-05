package app;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Project {

    private String projectName;
    private List<User> users;
    private Map<Integer, Ticket> tickets;
    //deberia agregarle los estados inicial y final de la vida de los tickets?

    public Project(String name){
        projectName = name;
        users = new ArrayList<User>();
        tickets = new HashMap<Integer, Ticket>();

    }

    public void addUser(User user){
        users.add(user);
    }

    public void addTicket(Ticket ticket){
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


