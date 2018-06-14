package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ticket {

    private String description;
    private String type;
    private String title;
    private List<Comment> comments = new ArrayList<>();
    private String actualState;

    public Ticket(String title, String description, String type) {
        this.title = title;
        this.description = description;
        this.type = type;
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

    public void addComment(String text) {
        this.comments.add(new Comment(text));
    }

    public void setActualState(String actualState) {
        this.actualState = actualState;
    }

    public String getActualState() {
        return actualState;
    }
}
