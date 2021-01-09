package me.mircoporetti.reactiveblog.post;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@SpringBootTest
@AutoConfigureWebTestClient
public class PostHandlerIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void allPosts() {

        Flux<Post> flux = webTestClient.get().uri("/posts")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .returnResult(Post.class)
                .getResponseBody();

        StepVerifier.create(flux)
                .expectSubscription()
                .expectNext(new Post("Hey man this is the first post!"))
                .expectNext(new Post("Hi! This post is super interesting!"))
                .expectNext(new Post("Here is the last post, bye bye mister!"))
                .verifyComplete();
    }
}

