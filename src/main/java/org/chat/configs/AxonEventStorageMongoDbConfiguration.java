package org.chat.configs;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.axonframework.mongo.DefaultMongoTemplate;
import org.axonframework.mongo.MongoTemplate;
import org.axonframework.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
import org.axonframework.mongo.eventsourcing.eventstore.documentperevent.DocumentPerEventStorageStrategy;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.json.JacksonSerializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author atsikhamirau on 21.05.2019
 */
@Configuration
public class AxonEventStorageMongoDbConfiguration {

    @Bean(name = "eventsMongoProperties")
    @ConfigurationProperties(prefix = "org.chat.events.store.mongo")
    public MongoProperties mongoProperties() {
        return new MongoProperties();
    }

    @Bean(name = "eventsMongoClient")
    public MongoClient mongoClient(@Qualifier("eventsMongoProperties") MongoProperties mongoProperties) {
        MongoClientURI mongoClientURI = new MongoClientURI(mongoProperties.getUri());
        return new MongoClient(mongoClientURI);
    }

    @Bean
    public Serializer eventSerializer() {
        return new JacksonSerializer();
    }

    @Bean(name = "eventsMongoTemplate")
    public MongoTemplate mongoTemplate(@Qualifier("eventsMongoClient") MongoClient mongoClient,
                                       @Qualifier("eventsMongoProperties") MongoProperties mongoProperties) {
        MongoClientURI mongoClientURI = new MongoClientURI(mongoProperties.getUri());

        return new DefaultMongoTemplate(mongoClient, mongoClientURI.getDatabase())
                .withDomainEventsCollection("Events")
                .withSnapshotCollection("Snapshots")
                .withSagasCollection("Sagas")
                .withTrackingTokenCollection("TrackingTokens");
    }

    @Bean
    @Primary
    public MongoEventStorageEngine mongoEventStorageEngine(
            @Qualifier("eventsMongoTemplate")MongoTemplate mongoTemplate, Serializer eventSerializer) {
        return new MongoEventStorageEngine(eventSerializer, null, mongoTemplate, new DocumentPerEventStorageStrategy());
    }

}
