package me.mircoporetti.reactiveblog.rest.post;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class PostHandler {

    private final PostReactiveMongoRepository postReactiveMongoRepository;

    public PostHandler(PostReactiveMongoRepository postReactiveMongoRepository) {
        this.postReactiveMongoRepository = postReactiveMongoRepository;
    }

    public Mono<ServerResponse> allPosts(ServerRequest serverRequest){
        return ok().body(postReactiveMongoRepository.findAll(), Post.class);
    }

    public Mono<ServerResponse> insertPost(ServerRequest serverRequest){
        return serverRequest.bodyToMono(Post.class)
                .flatMap(post -> ServerResponse.created(serverRequest.uri())
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(postReactiveMongoRepository.save(post), Post.class));
    }
}
