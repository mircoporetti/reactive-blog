package me.mircoporetti.reactiveblog.domain.post.usecase;

import me.mircoporetti.reactiveblog.domain.post.Comment;
import me.mircoporetti.reactiveblog.domain.post.Post;
import me.mircoporetti.reactiveblog.domain.post.PostPersistencePort;
import reactor.core.publisher.Mono;

public class CreateNewComment {
    private final PostPersistencePort postPersistencePort;

    public CreateNewComment(PostPersistencePort postPersistencePort) {
        this.postPersistencePort = postPersistencePort;
    }

    public Mono<Post> execute(String postId, Comment comment){
        return postPersistencePort.addCommentFor(postId, comment);
    }
}
