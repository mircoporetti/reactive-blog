package me.mircoporetti.reactiveblog.mongodbrepository.post;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostReactiveMongoRepository extends ReactiveMongoRepository<MongoPost, String> {
}
