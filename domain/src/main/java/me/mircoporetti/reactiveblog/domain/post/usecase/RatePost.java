package me.mircoporetti.reactiveblog.domain.post.usecase;

import me.mircoporetti.reactiveblog.domain.post.Post;
import me.mircoporetti.reactiveblog.domain.post.PostPersistencePort;
import me.mircoporetti.reactiveblog.domain.post.Rating;
import reactor.core.publisher.Mono;

public class RatePost {
    private final PostPersistencePort postPersistencePort;

    public RatePost(PostPersistencePort postPersistencePort) {
        this.postPersistencePort = postPersistencePort;
    }

    public Mono<Post> execute(String postId, Rating rating) {
        return postPersistencePort.findById(postId)
                .map(post -> {
                    post.rate(rating);
                    return post;
                })
                .flatMap(postPersistencePort::save);
    }
}
