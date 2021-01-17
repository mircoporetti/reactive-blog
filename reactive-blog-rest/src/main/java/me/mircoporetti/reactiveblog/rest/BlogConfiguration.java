package me.mircoporetti.reactiveblog.rest;

import me.mircoporetti.reactiveblog.domain.post.GetAllPostsUseCase;
import me.mircoporetti.reactiveblog.domain.post.NewPostUseCase;
import me.mircoporetti.reactiveblog.domain.post.PostPersistencePort;
import me.mircoporetti.reactiveblog.mongodbrepository.MongoDbAdapterConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({MongoDbAdapterConfiguration.class})
public class BlogConfiguration {

    @Bean
    public NewPostUseCase newPostUseCase(PostPersistencePort postPersistencePort){
        return new NewPostUseCase(postPersistencePort);
    }

    @Bean
    public GetAllPostsUseCase getAllPostsUseCase(PostPersistencePort postPersistencePort){
        return new GetAllPostsUseCase(postPersistencePort);
    }

}
