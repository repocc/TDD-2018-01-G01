package app;

import java.util.*;

public class TicketSystem {

    private static TicketSystem instance;
    private List<User> users;
    private Map <Integer,Project> projects;
    private static int proyectCount = 0;


    public TicketSystem() {
        users = new ArrayList<User>();
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
        //String name = System.console().readLine();
        Scanner scanner = new Scanner(System.in);
        String name =  scanner.nextLine();
        users.add(new User(name));
        System.out.println("Bienvenido " + name);
    }

    public void showFirstMenu() {
        System.out.println("Ingrese alguna de las siguientes opciones: ");
        System.out.println("1- Crear un nuevo proyecto ");
        System.out.println("2- Ver todos los proyectos ");
        System.out.println("3- Modificar algun proyecto");
        System.out.println("4- Salir ");
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        int intOption = Integer.parseInt(option);
        switch (intOption) {
            case 1:
                this.createProjectOption();
                this.showFirstMenu();
                break;
            case 2:
                this.showExistingProjects();
                this.showFirstMenu();
                break;
            case 3:
                this.modifyAProject();
                this.showFirstMenu();
                break;
            case 4:
                System.out.println("ELIGIO SALIR ");
                break;
        }
    }

    public void createProjectOption(){

        System.out.println("Escriba el nombre del nuevo proyecto:");
        Scanner scanner = new Scanner(System.in);
        String name =  scanner.nextLine();
        projects.put((++proyectCount),new Project(name));
    }

    public void showExistingProjects(){
        for (Integer name: projects.keySet()){
            String key = name.toString();
            String value = projects.get(name).getProjectName();
            System.out.println(key + " " + value);
        }
    }

    public void modifyAProject(){
        this.showExistingProjects();
        System.out.println("Ingrese el id del proyecto que desea modificar/cambiar de estado:");
        Scanner scanner = new Scanner(System.in);
        String idProject =  scanner.nextLine();
        System.out.println("Eligio: " + idProject + " para modificar");
        Project project = projects.get(Integer.valueOf(idProject));
        this.showProjectMenu(project);

    }

    private void showProjectMenu(Project project) {
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
                project.addTicket();
                this.showProjectMenu(project);
                break;
            case 2:
                this.showExistingProjects();
                this.showFirstMenu();
                break;
            case 3:
                this.modifyAProject();
                this.showFirstMenu();
                break;
            case 4:
                this.showFirstMenu();
                break;
        }
    }


}

