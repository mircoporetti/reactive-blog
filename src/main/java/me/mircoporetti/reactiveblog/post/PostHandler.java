package me.mircoporetti.reactiveblog.post;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyExtractors.toMono;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class PostHandler {

    private final MongoPostRepository mongoPostRepository;

    public PostHandler(MongoPostRepository mongoPostRepository) {
        this.mongoPostRepository = mongoPostRepository;
    }

    public Mono<ServerResponse> allPosts(ServerRequest serverRequest){
        return ok().body(mongoPostRepository.findAll(), Post.class);
    }

    public Mono<ServerResponse> insertPost(ServerRequest serverRequest){
        return serverRequest.bodyToMono(Post.class)
                .flatMap(post -> ServerResponse.created(serverRequest.uri())
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mongoPostRepository.save(post), Post.class));
    }
}
