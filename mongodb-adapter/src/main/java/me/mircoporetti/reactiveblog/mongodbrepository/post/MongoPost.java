package me.mircoporetti.reactiveblog.mongodbrepository.post;

import me.mircoporetti.reactiveblog.domain.post.Comment;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Document(collection = "posts")
public class MongoPost {
    @Id
    private String id;
    private String message;
    private List<Comment> comments;

    public MongoPost() {}

    public MongoPost(String id, String message, List<Comment> comments) {
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
        MongoPost post = (MongoPost) o;
        return Objects.equals(id, post.id) && Objects.equals(message, post.message) && Objects.equals(comments, post.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message, comments);
    }

    public MongoPost addComment(Comment mongoComment) {
        if(comments == null){
            comments = List.of(mongoComment);
        } else {
            comments.add(mongoComment);
        }
        return new MongoPost(id, message, comments);
    }

    public List<Comment> getDomainComments() {
        return comments == null ? Collections.emptyList() : comments.stream().map(
                c -> new Comment(c.getAuthor(), c.getMessage())
        ).collect(Collectors.toList());
    }
}
