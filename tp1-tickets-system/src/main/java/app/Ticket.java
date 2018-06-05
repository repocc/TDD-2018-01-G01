package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ticket {
    private String title;
    private String description;
    private String type;
    private List<Comment> comments;
    private State actualState;
    private Integer actualStateIndex;
    private User owner;
    private User responsable;

    public Ticket(String title) {
        this.title = title;
        this.comments = new ArrayList<Comment>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void changeState(List<State> states) {
        this.actualStateIndex ++;
        this.actualState = states.get(this.actualStateIndex);
        System.out.println("Estado actual: " + actualState.getName());
    }

    public void addComment(User user) {
        System.out.println("Escriba un comentario:");
        Scanner scanner = new Scanner(System.in);
        String commentText =  scanner.nextLine();
        this.comments.add(new Comment(commentText, user));
    }

    public void showMenu(User user, List<State> states) {
        System.out.println("Que desea hacer?: ");
        System.out.println("1- Editar descripcion ");
        System.out.println("2- Agregar comentario ");
        System.out.println("3- Cambiar de estado");
        System.out.println("4- Volver al menu del proyecto ");
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        int intOption = Integer.parseInt(option);
        switch (intOption) {
            case 1:
                this.editDescription();
                this.showMenu(user, states);
                break;
            case 2:
                this.addComment(user);
                this.showMenu(user, states);
                break;
            case 3:
                this.changeState(states);
                this.showMenu(user, states);
                break;
            case 4:
                break;
        }
    }

    private void editDescription() {
        System.out.println("La actual descripcion del ticket es:");
        System.out.println(this.description);
        System.out.println("Ingrese la nueva descripcion del ticket:");
        Scanner scanner = new Scanner(System.in);
        String newDescription =  scanner.nextLine();
        this.setDescription(newDescription);
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getResponsable() {
        return responsable;
    }

    public void setResponsable(User responsable) {
        this.responsable = responsable;
    }
}
