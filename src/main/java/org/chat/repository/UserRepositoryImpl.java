package org.chat.repository;

import org.chat.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author atsikhamirau on 24.05.2019
 */
@Component
public class UserRepositoryImpl implements UserRepository {

    private MongoOperations mongoOperations;

    @Autowired
    public UserRepositoryImpl(@Qualifier("projectionMongoTemplate") MongoOperations mongoOperations) {
        Assert.notNull(mongoOperations, "MongoOperations was not autowired.");
        this.mongoOperations = mongoOperations;
    }

    @Override
    public User getById(String id) {
        return mongoOperations.findById(id, User.class);
    }

    @Override
    public List<User> getAll() {
        return mongoOperations.findAll(User.class);
    }

    @Override
    public User save(User user) {
        return this.mongoOperations.save(user);
    }

    public void deleteById(String id) {
        this.mongoOperations.remove(Query.query(Criteria.where("_id").is(id)), User.class);
    }
}
