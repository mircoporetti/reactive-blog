package me.mircoporetti.reactiveblog.domain.post.usecase;

import me.mircoporetti.reactiveblog.domain.post.Post;
import me.mircoporetti.reactiveblog.domain.post.PostPersistencePort;
import reactor.core.publisher.Flux;

public class GetAllPosts {
    private final PostPersistencePort postPersistencePort;

    public GetAllPosts(PostPersistencePort postPersistencePort) {
        this.postPersistencePort = postPersistencePort;
    }

    public Flux<Post> execute(){
        return postPersistencePort.findAll();
    }
}
