package me.mircoporetti.reactiveblog.rest.post;

import me.mircoporetti.reactiveblog.domain.post.GetAllPostsUseCase;
import me.mircoporetti.reactiveblog.domain.post.NewPostUseCase;
import me.mircoporetti.reactiveblog.domain.post.Post;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class PostHandler {

    private final GetAllPostsUseCase getAllPostsUseCase;
    private final NewPostUseCase newPostUseCase;

    public PostHandler(GetAllPostsUseCase getAllPostsUseCase, NewPostUseCase newPostUseCase) {
        this.getAllPostsUseCase = getAllPostsUseCase;
        this.newPostUseCase = newPostUseCase;
    }

    public Mono<ServerResponse> allPosts(ServerRequest serverRequest){
        return ok().body(getAllPostsUseCase.execute(), Post.class);
    }

    public Mono<ServerResponse> insertPost(ServerRequest serverRequest){
        return serverRequest.bodyToMono(PostRequest.class)
                .map(PostRequest::toPost)
                .flatMap(post -> ServerResponse.created(serverRequest.uri())
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(newPostUseCase.execute(post), Post.class));
    }
}
