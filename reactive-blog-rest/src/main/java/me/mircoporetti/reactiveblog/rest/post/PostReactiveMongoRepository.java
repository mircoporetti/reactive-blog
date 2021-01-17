package me.mircoporetti.reactiveblog.rest.post;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PostReactiveMongoRepository extends ReactiveMongoRepository<Post, String> {
}
