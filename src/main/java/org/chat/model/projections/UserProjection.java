package org.chat.model.projections;

import org.axonframework.eventhandling.EventHandler;
import org.chat.model.User;
import org.chat.model.events.UserCreateEvent;
import org.chat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author atsikhamirau on 24.05.2019
 */
@Component
public class UserProjection {

    @Autowired
    private UserRepository userRepository;

    @EventHandler
    public void on(UserCreateEvent event) {
        User user = new User()
                .setId(event.getId())
                .setFirstName(event.getFirstName())
                .setLastName(event.getLastName())
                .setEmail(event.getEmail())
                .setPassword(event.getPassword());
        userRepository.save(user);
    }

}
