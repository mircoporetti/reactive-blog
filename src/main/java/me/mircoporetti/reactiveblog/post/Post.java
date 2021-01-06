package me.mircoporetti.reactiveblog.post;

import java.util.Objects;

public class Post {
    private String message;

    public Post() {}

    public Post(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(message, post.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    @Override
    public String toString() {
        return "Post{" +
                "message='" + message + '\'' +
                '}';
    }
}
