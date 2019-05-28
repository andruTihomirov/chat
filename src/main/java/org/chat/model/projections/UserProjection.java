package org.chat.model.projections;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.chat.model.User;
import org.chat.model.events.user.UserCreateEvent;
import org.chat.model.events.user.UserDeleteEvent;
import org.chat.model.events.user.UserUpdateEvent;
import org.chat.model.queries.UserGetAllQuery;
import org.chat.model.queries.UserGetByIdQuery;
import org.chat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author atsikhamirau on 24.05.2019
 */
@Component
public class UserProjection {

    private UserRepository userRepository;

    @Autowired
    public UserProjection(UserRepository userRepository) {
        Assert.notNull(userRepository, "UserRepository was not autowired.");
        this.userRepository = userRepository;
    }

    @EventHandler
    public void on(UserCreateEvent userCreateEvent) {
        User user = new User()
                .setId(userCreateEvent.getId())
                .setFirstName(userCreateEvent.getFirstName())
                .setLastName(userCreateEvent.getLastName())
                .setEmail(userCreateEvent.getEmail())
                .setPassword(userCreateEvent.getPassword());
        userRepository.save(user);
    }

    @EventHandler
    public void on(UserUpdateEvent userUpdateEvent) {
        User user = new User()
                .setId(userUpdateEvent.getId())
                .setFirstName(userUpdateEvent.getFirstName())
                .setLastName(userUpdateEvent.getLastName())
                .setEmail(userUpdateEvent.getEmail())
                .setPassword(userUpdateEvent.getPassword());
        userRepository.save(user);
    }

    @EventHandler
    public void on(UserDeleteEvent userDeleteEvent) {
        userRepository.deleteById(userDeleteEvent.getId());
    }

    @QueryHandler
    public User handle(UserGetByIdQuery query) {
        return userRepository.getById(query.getId());
    }

    @QueryHandler
    public List<User> handle(UserGetAllQuery userGetAllQuery) {
        return userRepository.getAll();
    }

}
