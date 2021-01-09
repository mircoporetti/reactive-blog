package me.mircoporetti.reactiveblog.post;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
@AutoConfigureWebTestClient
public class PostHandlerIntegrationTest {

    @Autowired
    private MongoPostRepository mongoPostRepository;
    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void allPosts() {
        Post postToBeRetrieved = new Post("anId", "an awesome post");
        mongoPostRepository.insert(postToBeRetrieved).block();

        Flux<Post> flux = webTestClient.get().uri("/posts")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .returnResult(Post.class)
                .getResponseBody();

        StepVerifier.create(flux)
                .expectSubscription()
                .expectNext(postToBeRetrieved)
                .verifyComplete();

        mongoPostRepository.delete(postToBeRetrieved).block();
    }

    @Test
    public void insertPost() {
        Post postToBeSaved = new Post("anId", "an awesome post");

        webTestClient.post().uri("/posts")
                .body(Mono.just(postToBeSaved), Post.class)
                .exchange()
                .expectStatus().isCreated()
                .returnResult(Post.class)
                .getResponseBody();

        mongoPostRepository.delete(postToBeSaved).subscribe();
    }
}

