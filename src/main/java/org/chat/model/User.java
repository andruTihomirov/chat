package org.chat.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.chat.mappers.UserMapper;
import org.chat.model.commands.user.UserCreateCommand;
import org.chat.model.commands.user.UserUpdateCommand;
import org.chat.model.events.user.BaseUserEvent;
import org.chat.model.events.user.UserCreateEvent;
import org.chat.model.events.user.UserUpdateEvent;
import org.mapstruct.factory.Mappers;

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

    private UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    public User() {
    }

    @CommandHandler
    public User(UserCreateCommand userCreateCommand) {
        UserCreateEvent userCreateEvent = userMapper.commandToEvent(userCreateCommand);
        AggregateLifecycle.apply(userCreateEvent);
    }

    @CommandHandler
    public void handle(UserUpdateCommand userUpdateCommand) {
        UserUpdateEvent userUpdateEvent = userMapper.commandToEvent(userUpdateCommand);
        AggregateLifecycle.apply(userUpdateEvent);
    }

    @EventSourcingHandler
    public void on(UserCreateEvent userCreateEvent) {
       doEvent(userCreateEvent);
    }

    @EventSourcingHandler
    public void on(UserUpdateEvent userUpdateEvent) {
        doEvent(userUpdateEvent);
    }

    private void doEvent(BaseUserEvent event) {
        this.id = event.getId();
        this.firstName = event.getFirstName();
        this.lastName = event.getLastName();
        this.email = event.getEmail();
        this.password = event.getPassword();
    }

}
