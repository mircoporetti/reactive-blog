package me.mircoporetti.reactiveblog.rest;

import me.mircoporetti.reactiveblog.domain.post.usecase.GetAllPosts;
import me.mircoporetti.reactiveblog.domain.post.usecase.CreateNewPost;
import me.mircoporetti.reactiveblog.domain.post.PostPersistencePort;
import me.mircoporetti.reactiveblog.mongodbrepository.MongoDbAdapterConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({MongoDbAdapterConfiguration.class})
public class BlogConfiguration {

    @Bean
    public CreateNewPost newPostUseCase(PostPersistencePort postPersistencePort){
        return new CreateNewPost(postPersistencePort);
    }

    @Bean
    public GetAllPosts getAllPostsUseCase(PostPersistencePort postPersistencePort){
        return new GetAllPosts(postPersistencePort);
    }

}
