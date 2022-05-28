package me.mircoporetti.reactiveblog.rest.post;

import me.mircoporetti.reactiveblog.domain.post.Comment;
import me.mircoporetti.reactiveblog.domain.post.Post;

import java.util.List;

public record PostRequest(String message, List<Comment> comments) {

    public Post toPost(){
        return new Post(null, message, comments, 0L, 0L);
    }
}
