package me.mircoporetti.reactiveblog.domain.post.usecase;

import me.mircoporetti.reactiveblog.domain.post.Post;
import me.mircoporetti.reactiveblog.domain.post.PostPersistencePort;
import reactor.core.publisher.Mono;

public class CreateNewPost {
    private final PostPersistencePort postPersistencePort;

    public CreateNewPost(PostPersistencePort postPersistencePort) {
        this.postPersistencePort = postPersistencePort;
    }

    public Mono<Post> execute(Post post){
        return postPersistencePort.save(post);
    }
}
