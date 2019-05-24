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
import org.chat.model.events.UserCreateEvent;

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
    public User(UserCreateCommand command) {
        UserCreateEvent event = new UserCreateEvent()
                .setId(command.getId())
                .setFirstName(command.getFirstName())
                .setLastName(command.getLastName())
                .setEmail(command.getEmail())
                .setPassword(command.getPassword());

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(UserCreateEvent event) {
        this.id = event.getId();
        this.firstName = event.getFirstName();
        this.lastName = event.getLastName();
        this.email = event.getEmail();
        this.password = event.getPassword();
    }

}
