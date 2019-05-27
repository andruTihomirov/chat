package org.chat.repository;

import org.chat.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

/**
 * @author atsikhamirau on 24.05.2019
 */
@Component
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    @Qualifier("projectionMongoTemplate")
    private MongoOperations mongoOperations;

    @Override
    public User getById(String id) {
        return mongoOperations.findById(id, User.class);
    }

    @Override
    public User save(User user) {
        return this.mongoOperations.save(user);
    }
}
