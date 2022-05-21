package me.mircoporetti.reactiveblog.domain.post;

import java.util.Objects;

public class Comment {
    private String author;
    private String message;

    public Comment(String author, String message) {
        this.author = author;
        this.message = message;
    }

    public String getAuthor() {
        return author;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(author, comment.author) && Objects.equals(message, comment.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, message);
    }
}
