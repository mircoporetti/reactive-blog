package me.mircoporetti.reactiveblog.mongodbrepository.post;

import me.mircoporetti.reactiveblog.domain.post.Post;
import me.mircoporetti.reactiveblog.domain.post.PostPersistencePort;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class PostMongoDbAdapter implements PostPersistencePort {

    private final PostReactiveMongoRepository postReactiveMongoRepository;

    public PostMongoDbAdapter(PostReactiveMongoRepository postReactiveMongoRepository) {
        this.postReactiveMongoRepository = postReactiveMongoRepository;
    }

    @Override
    public Flux<Post> findAll() {
        return postReactiveMongoRepository.findAll().map(post -> new Post(post.getId(),post.getMessage(), post.getComments()));
    }

    @Override
    public Mono<Post> save(Post post) {
        return postReactiveMongoRepository.save(new MongoPost(post.getId(), post.getMessage(), post.getComments()))
                .map(p -> new Post(post.getId(), post.getMessage(), post.getComments()));
    }
}
