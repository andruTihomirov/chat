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
import org.chat.model.events.UserCreateEvent;
import org.chat.model.events.UserUpdateEvent;

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
        UserCreateEvent event = new UserCreateEvent()
                .setId(userCreateCommand.getId())
                .setFirstName(userCreateCommand.getFirstName())
                .setLastName(userCreateCommand.getLastName())
                .setEmail(userCreateCommand.getEmail())
                .setPassword(userCreateCommand.getPassword());

        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(UserUpdateCommand userUpdateCommand) {
        UserUpdateEvent event = new UserUpdateEvent()
                .setId(userUpdateCommand.getId())
                .setFirstName(userUpdateCommand.getFirstName())
                .setLastName(userUpdateCommand.getLastName())
                .setEmail(userUpdateCommand.getEmail())
                .setPassword(userUpdateCommand.getPassword());

        AggregateLifecycle.apply(event);
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
