package me.mircoporetti.reactiveblog.domain.post;

import java.util.List;
import java.util.Objects;

public class Post {

    private String id;
    private String message;
    private List<Comment> comments;

    public Post() {}

    public Post(String id, String message, List<Comment> comments) {
        this.id = id;
        this.message = message;
        this.comments = comments;
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public List<Comment> getComments() {
        return comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id) && Objects.equals(message, post.message) && Objects.equals(comments, post.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message, comments);
    }
}
