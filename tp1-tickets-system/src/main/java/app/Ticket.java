package app;

import java.util.List;

public class Ticket {
    private String title;
    private String description;
    private String type;
    private List<String> states;
    private List<Comment> comments;
    private String actualState;
    private Integer actualStateIndex;

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

    public void changeState() {
        this.actualStateIndex ++;
        this.actualState = this.states.get(this.actualStateIndex);
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }
}
