package org.chat;

import org.axonframework.boot.autoconfig.AxonAutoConfiguration;
import org.chat.configs.AxonProjectionMongoDbConfiguration;
import org.chat.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Import({
        AxonProjectionMongoDbConfiguration.class
})
@SpringBootApplication(exclude = {
        MongoAutoConfiguration.class,
        MongoDataAutoConfiguration.class,
        AxonAutoConfiguration.class
})
@EnableMongoRepositories(basePackageClasses = UserRepository.class)
public class ChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatApplication.class, args);
    }

}
