package me.mircoporetti.reactiveblog.mongodbrepository.post;

import me.mircoporetti.reactiveblog.domain.post.Comment;
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
        return postReactiveMongoRepository.findAll()
                .map(p -> new Post(p.getId(),p.getMessage(), p.getComments(), p.getLikes(), p.getDislikes()));
    }

    @Override
    public Mono<Post> findById(String postId) {
        return postReactiveMongoRepository.findById(postId)
                .map(p -> new Post(p.getId(),p.getMessage(), p.getComments(), p.getLikes(), p.getDislikes()));
    }

    @Override
    public Mono<Post> save(Post post) {
        return postReactiveMongoRepository.save(new MongoPost(post.getId(), post.getMessage(), post.getComments(), post.getLikes(), post.getDislikes()))
                .map(p -> new Post(p.getId(), p.getMessage(), p.getComments(), p.getLikes(), p.getDislikes()));
    }

    @Override
    public Mono<Post> addCommentFor(String postId, Comment comment) {
        return postReactiveMongoRepository.findById(postId)
                .flatMap(mongoPost -> postReactiveMongoRepository.save(mongoPost.addComment(comment)))
                .map(p -> new Post(p.getId(), p.getMessage(), p.getComments(), p.getLikes(), p.getLikes()));
    }
}
