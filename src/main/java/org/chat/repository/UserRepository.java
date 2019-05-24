package org.chat.repository;

import org.chat.model.User;
import org.springframework.data.repository.Repository;

/**
 * @author atsikhamirau on 24.05.2019
 */
public interface UserRepository extends Repository<User, String> {

    User save(User user);

}
