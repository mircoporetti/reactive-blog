package me.mircoporetti.reactiveblog.rest.post;

import me.mircoporetti.reactiveblog.domain.post.Comment;
import me.mircoporetti.reactiveblog.domain.post.Post;
import me.mircoporetti.reactiveblog.mongodbrepository.post.MongoPost;
import me.mircoporetti.reactiveblog.mongodbrepository.post.PostReactiveMongoRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
@AutoConfigureWebTestClient
public class PostHandlerIntegrationTest {

    @Autowired
    private PostReactiveMongoRepository postReactiveMongoRepository;
    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void allPosts() {
        MongoPost postToBeRetrieved = new MongoPost("anId", "an awesome post", Collections.emptyList());
        Post post = new Post("anId", "an awesome post", Collections.emptyList());

        postReactiveMongoRepository.insert(postToBeRetrieved).block();

        Post lastPost = webTestClient.get().uri("/posts")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .returnResult(Post.class)
                .getResponseBody()
                .blockLast();

        assertThat(lastPost, Matchers.is(post));

        postReactiveMongoRepository.delete(postToBeRetrieved).block();
    }

    @Test
    public void insertPost() {
        Post postToBeSaved = new Post("anId", "an awesome post", Collections.emptyList());

        webTestClient.post().uri("/posts")
                .body(Mono.just(postToBeSaved), Post.class)
                .exchange()
                .expectStatus().isCreated()
                .returnResult(Post.class);

        MongoPost postToBeDeleted = new MongoPost("anId", "an awesome post", Collections.emptyList());
        postReactiveMongoRepository.delete(postToBeDeleted).block();
    }


    @Test
    public void commentPost() {
        MongoPost postToBeSaved = new MongoPost("anId", "an awesome post", Collections.emptyList());
        postReactiveMongoRepository.insert(postToBeSaved).block();

        Comment commentBeSaved = new Comment("An Author Name", "A comment message");

        Post lastPost = webTestClient.post().uri("/posts/anId/comments")
                .body(Mono.just(commentBeSaved), Comment.class)
                .exchange()
                .expectStatus().isCreated()
                .returnResult(Post.class)
                .getResponseBody()
                .blockLast();

        assertThat(lastPost.getComments(), Matchers.is(List.of(commentBeSaved)));

        postReactiveMongoRepository.delete(postToBeSaved).block();
    }
}

