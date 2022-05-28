package me.mircoporetti.reactiveblog.mongodbrepository.post;

import me.mircoporetti.reactiveblog.domain.post.Comment;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

@Document(collection = "posts")
public class MongoPost {
    @Id
    private String id;
    private String message;
    private List<Comment> comments;
    private Long likes;
    private Long dislikes;

    public MongoPost() {}

    public MongoPost(String id, String message, List<Comment> comments, Long likes, Long dislikes) {
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

    public MongoPost addComment(Comment mongoComment) {
        if(comments == null){
            comments = List.of(mongoComment);
        } else {
            comments.add(mongoComment);
        }
        return new MongoPost(id, message, comments, likes, dislikes);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MongoPost mongoPost = (MongoPost) o;
        return Objects.equals(id, mongoPost.id) && Objects.equals(message, mongoPost.message) && Objects.equals(comments, mongoPost.comments) && Objects.equals(likes, mongoPost.likes) && Objects.equals(dislikes, mongoPost.dislikes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message, comments, likes, dislikes);
    }
}
