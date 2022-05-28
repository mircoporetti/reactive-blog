package me.mircoporetti.reactiveblog.domain.post;

import java.util.List;
import java.util.Objects;

public class Post {

    private String id;
    private String message;
    private List<Comment> comments;
    private Long likes;
    private Long dislikes;

    public Post() {}

    public Post(String id, String message, List<Comment> comments, Long likes, Long dislikes) {
        this.id = id;
        this.message = message;
        this.comments = comments;
        this.likes = likes;
        this.dislikes = dislikes;
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

    public Long getLikes() {
        return likes;
    }

    public Long getDislikes() {
        return dislikes;
    }

    public void rate(Rating rating) {
        if(rating.equals(Rating.LIKE)){
            ++ likes;
        }else{
            ++dislikes;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id) && Objects.equals(message, post.message) && Objects.equals(comments, post.comments) && Objects.equals(likes, post.likes) && Objects.equals(dislikes, post.dislikes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message, comments, likes, dislikes);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\'' +
                ", message='" + message + '\'' +
                ", comments=" + comments +
                ", likes=" + likes +
                ", dislikes=" + dislikes +
                '}';
    }
}
