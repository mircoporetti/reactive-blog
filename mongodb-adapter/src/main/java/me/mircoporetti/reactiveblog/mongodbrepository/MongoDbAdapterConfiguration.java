package me.mircoporetti.reactiveblog.mongodbrepository;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@ComponentScan
@EnableReactiveMongoRepositories
public class MongoDbAdapterConfiguration {
}
