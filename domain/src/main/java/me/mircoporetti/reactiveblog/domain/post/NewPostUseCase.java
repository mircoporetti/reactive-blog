package me.mircoporetti.reactiveblog.domain.post;

import reactor.core.publisher.Mono;

public class NewPostUseCase {
    private final PostPersistencePort postPersistencePort;

    public NewPostUseCase(PostPersistencePort postPersistencePort) {
        this.postPersistencePort = postPersistencePort;
    }

    public Mono<Post> execute(Post post){
        return postPersistencePort.save(post);
    }
}
