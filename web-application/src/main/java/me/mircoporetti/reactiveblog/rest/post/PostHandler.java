package me.mircoporetti.reactiveblog.rest.post;

import me.mircoporetti.reactiveblog.domain.post.usecase.GetAllPosts;
import me.mircoporetti.reactiveblog.domain.post.usecase.CreateNewPost;
import me.mircoporetti.reactiveblog.domain.post.Post;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class PostHandler {

    private final GetAllPosts getAllPosts;
    private final CreateNewPost createNewPost;

    public PostHandler(GetAllPosts getAllPosts, CreateNewPost createNewPost) {
        this.getAllPosts = getAllPosts;
        this.createNewPost = createNewPost;
    }

    public Mono<ServerResponse> allPosts(ServerRequest serverRequest){
        return ok().body(getAllPosts.execute(), Post.class);
    }

    public Mono<ServerResponse> insertPost(ServerRequest serverRequest){
        return serverRequest.bodyToMono(PostRequest.class)
                .map(PostRequest::toPost)
                .flatMap(post -> ServerResponse.created(serverRequest.uri())
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(createNewPost.execute(post), Post.class));
    }
}
