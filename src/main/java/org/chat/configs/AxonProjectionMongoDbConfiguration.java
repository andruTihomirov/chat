package org.chat.configs;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author atsikhamirau on 22.05.2019
 */

@Configuration
@EnableMongoRepositories(
        mongoTemplateRef = "projectionMongoTemplate")
public class AxonProjectionMongoDbConfiguration {

    @Bean(name = "projectionMongoProperties")
    @ConfigurationProperties(prefix = "org.chat.projections.store.mongo")
    public MongoProperties mongoProperties() {
        return new MongoProperties();
    }

    @Bean(name = "projectionMongoDbFactory")
    public MongoDbFactory mongoDbFactory(@Qualifier("projectionMongoProperties") MongoProperties mongoProperties) {
        MongoClientURI mongoClientURI = new MongoClientURI(mongoProperties.getUri());
        MongoClient mongoClient = new MongoClient(mongoClientURI);
        return new SimpleMongoDbFactory(mongoClient, mongoClientURI.getDatabase());
    }

    @Bean(name = "projectionMappingMongoConverter")
    public MappingMongoConverter mappingMongoConverter(
            @Qualifier("projectionMongoDbFactory") MongoDbFactory mongoFactory,
            MongoMappingContext mongoMappingContext) {
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoFactory);
        return new MappingMongoConverter(dbRefResolver, mongoMappingContext);
    }

    @Bean(name = "projectionMongoTemplate")
    public MongoTemplate mongoTemplate(
            @Qualifier("projectionMongoDbFactory") MongoDbFactory mongoFactory,
            @Qualifier("projectionMappingMongoConverter") MappingMongoConverter mappingMongoConverter) {
        return new MongoTemplate(mongoFactory, mappingMongoConverter);
    }

}
