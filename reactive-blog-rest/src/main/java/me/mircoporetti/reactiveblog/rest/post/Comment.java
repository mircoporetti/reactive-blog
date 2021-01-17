package me.mircoporetti.reactiveblog.rest.post;

import java.util.Objects;

public class Comment {
    private String message;

    public Comment(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(message, comment.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }
}
