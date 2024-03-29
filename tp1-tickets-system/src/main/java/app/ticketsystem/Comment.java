package app.ticketsystem;

import app.websocket.JsonMessageSender;

public class Comment {

    private String text;
    private User author;

    JsonMessageSender Logger = JsonMessageSender.getInstance();

    public Comment(String text, User author) {
        this.text = text;
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
