package me.mircoporetti.reactiveblog.rest.post;

import me.mircoporetti.reactiveblog.domain.post.Comment;
import me.mircoporetti.reactiveblog.domain.post.usecase.CreateNewComment;
import me.mircoporetti.reactiveblog.domain.post.usecase.GetAllPosts;
import me.mircoporetti.reactiveblog.domain.post.usecase.CreateNewPost;
import me.mircoporetti.reactiveblog.domain.post.Post;
import me.mircoporetti.reactiveblog.domain.post.usecase.RatePost;
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
    private final CreateNewComment createNewComment;
    private final RatePost ratePost;

    public PostHandler(GetAllPosts getAllPosts, CreateNewPost createNewPost, CreateNewComment createNewComment, RatePost ratePost) {
        this.getAllPosts = getAllPosts;
        this.createNewPost = createNewPost;
        this.createNewComment = createNewComment;
        this.ratePost = ratePost;
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


    public Mono<ServerResponse> insertComment(ServerRequest serverRequest){
        return serverRequest.bodyToMono(Comment.class)
                .flatMap(comment -> ServerResponse.created(serverRequest.uri())
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(createNewComment.execute(serverRequest.pathVariable("postId"), comment),
                                Post.class));
    }

    public Mono<ServerResponse> ratePost(ServerRequest serverRequest){
        return serverRequest.bodyToMono(RateRequest.class)
                .flatMap(rateRequest -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(ratePost.execute(serverRequest.pathVariable("postId"), rateRequest.rating()),
                                Post.class));
    }

}
