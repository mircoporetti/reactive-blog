package me.mircoporetti.reactiveblog.domain.post;

import reactor.core.publisher.Flux;

public class GetAllPostsUseCase{
    private final PostPersistencePort postPersistencePort;

    public GetAllPostsUseCase(PostPersistencePort postPersistencePort) {
        this.postPersistencePort = postPersistencePort;
    }

    public Flux<Post> execute(){
        return postPersistencePort.findAll();
    }
}
