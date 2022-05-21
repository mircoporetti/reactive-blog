package me.mircoporetti.reactiveblog.domain.post;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PostPersistencePort {

    Flux<Post> findAll();

    Mono<Post> save(Post post);

    Mono<Post> addCommentFor(String postId, Comment comment);
}
