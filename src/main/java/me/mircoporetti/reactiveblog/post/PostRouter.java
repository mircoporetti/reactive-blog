package me.mircoporetti.reactiveblog.post;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class PostRouter {

    @Bean
    public RouterFunction<ServerResponse> getAllPosts(PostHandler postHandler) {
        return route(GET("/posts").and(accept(MediaType.APPLICATION_JSON)), postHandler::allPosts);
    }

    @Bean
    public RouterFunction<ServerResponse> insertPost(PostHandler postHandler) {
        return route(POST("/posts").and(accept(MediaType.APPLICATION_JSON)), postHandler::insertPost);
    }
}
