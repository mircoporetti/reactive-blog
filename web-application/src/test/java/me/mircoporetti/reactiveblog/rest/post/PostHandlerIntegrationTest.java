package me.mircoporetti.reactiveblog.rest.post;

import me.mircoporetti.reactiveblog.domain.post.Comment;
import me.mircoporetti.reactiveblog.domain.post.Post;
import me.mircoporetti.reactiveblog.mongodbrepository.post.MongoPost;
import me.mircoporetti.reactiveblog.mongodbrepository.post.PostReactiveMongoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Collections;
import java.util.List;

@AutoConfigureWebTestClient
public class PostHandlerIntegrationTest extends IntegrationTest {

    @Autowired
    private PostReactiveMongoRepository postReactiveMongoRepository;
    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void allPosts() {
        MongoPost postToBeRetrieved = new MongoPost("anId", "an awesome post", Collections.emptyList());
        Post result = new Post("anId", "an awesome post", Collections.emptyList());

        postReactiveMongoRepository.insert(postToBeRetrieved).block();

        Flux<Post> post = webTestClient.get().uri("/posts")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .returnResult(Post.class)
                .getResponseBody();

        StepVerifier.create(post)
                .expectSubscription()
                .expectNext(result)
                .verifyComplete();

        postReactiveMongoRepository.delete(postToBeRetrieved).block();
    }

    @Test
    public void insertPost() {
        PostRequest postToBeSaved = new PostRequest("an awesome post", Collections.emptyList());

        webTestClient.post().uri("/posts")
                .body(Mono.just(postToBeSaved), Post.class)
                .exchange()
                .expectStatus().isCreated();

        postReactiveMongoRepository.deleteAll().block();
    }


    @Test
    public void commentPost() {
        MongoPost postToBeSaved = new MongoPost("anId", "an awesome post", Collections.emptyList());
        postReactiveMongoRepository.insert(postToBeSaved).block();

        Comment commentBeSaved = new Comment("An Author Name", "A comment message");

        Flux<Post> post = webTestClient.post().uri("/posts/anId/comments")
                .body(Mono.just(commentBeSaved), Comment.class)
                .exchange()
                .expectStatus().isCreated()
                .returnResult(Post.class)
                .getResponseBody();

        StepVerifier.create(post)
                .expectSubscription()
                .expectNextMatches(p -> p.getComments().equals(List.of(commentBeSaved)))
                .verifyComplete();

        postReactiveMongoRepository.delete(postToBeSaved).block();
    }
}

