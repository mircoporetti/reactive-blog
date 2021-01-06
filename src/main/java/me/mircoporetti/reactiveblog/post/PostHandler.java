package me.mircoporetti.reactiveblog.post;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class PostHandler {

    public Mono<ServerResponse> allPosts(ServerRequest serverRequest){
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Flux.just(
                        new Post("Hey man this is the first post!"),
                        new Post("Hi! This post is super interesting!"),
                        new Post("Here is the last post, bye bye mister!")
                        ), Post.class
                );
    }

}
