package me.mircoporetti.reactiveblog.post;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document
public class Post {

    @Id
    private String id;
    private String message;

    public Post() {}

    public Post(String id, String message) {
        this.id = id;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id) && Objects.equals(message, post.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message);
    }
}
