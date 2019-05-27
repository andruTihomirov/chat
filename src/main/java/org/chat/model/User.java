package org.chat.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.chat.model.commands.UserCreateCommand;
import org.chat.model.commands.UserUpdateCommand;
import org.chat.model.events.user.UserCreateEvent;
import org.chat.model.events.user.UserUpdateEvent;

/**
 * @author atsikhamirau on 23.05.2019
 */
@Getter
@Setter
@Accessors(chain = true)
@Aggregate
public class User {

    @AggregateIdentifier
    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    public User() {
    }

    @CommandHandler
    public User(UserCreateCommand userCreateCommand) {
        UserCreateEvent userCreateEvent = new UserCreateEvent();
        userCreateEvent.setId(userCreateCommand.getId());
        userCreateEvent.setFirstName(userCreateCommand.getFirstName());
        userCreateEvent.setLastName(userCreateCommand.getLastName());
        userCreateEvent.setEmail(userCreateCommand.getEmail());
        userCreateEvent.setPassword(userCreateCommand.getPassword());

        AggregateLifecycle.apply(userCreateEvent);
    }

    @CommandHandler
    public void handle(UserUpdateCommand userUpdateCommand) {
        UserUpdateEvent userUpdateEvent = new UserUpdateEvent();
        userUpdateEvent.setId(userUpdateCommand.getId());
        userUpdateEvent.setFirstName(userUpdateCommand.getFirstName());
        userUpdateEvent.setLastName(userUpdateCommand.getLastName());
        userUpdateEvent.setEmail(userUpdateCommand.getEmail());
        userUpdateEvent.setPassword(userUpdateCommand.getPassword());

        AggregateLifecycle.apply(userUpdateEvent);
    }

    @EventSourcingHandler
    public void on(UserCreateEvent userCreateEvent) {
        this.id = userCreateEvent.getId();
        this.firstName = userCreateEvent.getFirstName();
        this.lastName = userCreateEvent.getLastName();
        this.email = userCreateEvent.getEmail();
        this.password = userCreateEvent.getPassword();
    }

    @EventSourcingHandler
    public void on(UserUpdateEvent userUpdateEvent) {
        this.id = userUpdateEvent.getId();
        this.firstName = userUpdateEvent.getFirstName();
        this.lastName = userUpdateEvent.getLastName();
        this.email = userUpdateEvent.getEmail();
        this.password = userUpdateEvent.getPassword();
    }


}
