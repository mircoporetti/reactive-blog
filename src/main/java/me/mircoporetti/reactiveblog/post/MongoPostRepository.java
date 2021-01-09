package me.mircoporetti.reactiveblog.post;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MongoPostRepository extends ReactiveMongoRepository<Post, String> {
}
