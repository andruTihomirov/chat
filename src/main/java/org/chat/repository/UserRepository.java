package org.chat.repository;

import org.chat.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author atsikhamirau on 24.05.2019
 */
@Repository("userProjectionRepository")
public interface UserRepository extends MongoRepository<User, String> {
}
