package app.ticketsystem;

import app.websocket.JsonMessageSender;

import java.util.*;

public class TicketSystem {

    private static TicketSystem instance;
    private Map<Integer, User> users;
    private Map<String,Integer> nameUser_id;
    private Map <Integer,Project> projects;
    private static int projectCount = 0;
    private static int userCount = 0;

    public JsonMessageSender Logger = JsonMessageSender.getInstance();


    public TicketSystem() {
        Parser user_parser = new Parser();
        users = user_parser.parseUsersList();
        nameUser_id = new HashMap<String, Integer>();
        for (int id: users.keySet()){
            String name = users.get(id).getName();
            nameUser_id.put(name,id);
        }
        projects = new HashMap<Integer, Project>();
    }

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
        Scanner scanner = new Scanner(System.in);
        String name =  scanner.nextLine();
        if(nameUser_id.containsKey(name)){
            System.out.println("Bienvenido " + name);
            User user = users.get(nameUser_id.get(name));
            Logger.publishData("{\"newUser\":\""+ name +"\"}");
            this.showFirstMenu(user);
        }else{
            System.out.println("Usuario inexistente");
            login();
        }
    }

    public void showFirstMenu(User user) {
        System.out.println("Ingrese alguna de las siguientes opciones: ");
        System.out.println("1- Crear un nuevo proyecto ");
        System.out.println("2- Ver informacion de algun proyecto ");
        System.out.println("3- Modificar algun proyecto");
        System.out.println("4- Salir ");
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        int intOption = Integer.parseInt(option);
        switch (intOption) {
            case 1:
                this.createProjectOption(user);
                this.showFirstMenu(user);
                break;
            case 2:
                this.showProjectInfo();
                this.showFirstMenu(user);
                break;
            case 3:
                this.modifyAProject(user);
                this.showFirstMenu(user);
                break;
            case 4:
                System.out.println("ELIGIO SALIR ");
                break;
            default:
                this.showFirstMenu(user);
        }
    }

    private void showProjectInfo() {
        this.showExistingProjects();
        System.out.println("Ingrese el id del proyecto del que desea ver los detalles:");
        Scanner scanner = new Scanner(System.in);
        String idProject =  scanner.nextLine();
        Project project = projects.get(Integer.valueOf(idProject));
        project.showInfo();
    }

    public void createProjectOption(User user){

        System.out.println("Escriba el nombre del nuevo proyecto:");
        Scanner scanner = new Scanner(System.in);
        String name =  scanner.nextLine();
        Project project = new Project(name);
        projects.put((++projectCount),project);
        project.setStates();
        user.addProjectCreated(project);
        project.setOwner(user);
        project.setRoles(users);
    }

    public void showExistingProjects(){
        for (Integer name: projects.keySet()){
            String key = name.toString();
            String value = projects.get(name).getProjectName();
            System.out.println(key + " " + value);
        }
    }

    public void modifyAProject(User user){
        this.showExistingProjects();
        System.out.println("Ingrese el id del proyecto que desea modificar/cambiar de estado:");
        Scanner scanner = new Scanner(System.in);
        String idProject =  scanner.nextLine();
        System.out.println("Eligio: " + idProject + " para modificar");
        Project project = projects.get(Integer.valueOf(idProject));
        project.showMenu(user, users);

    }

}

