package org.chat.controllers.commands;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.GenericCommandMessage;
import org.chat.model.commands.user.UserCreateCommand;
import org.chat.model.commands.user.UserUpdateCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author atsikhamirau on 23.05.2019
 */
@RestController
@RequestMapping("users")
public class UserCommandController {

    private final CommandBus commandBus;

    @Autowired
    public UserCommandController(CommandBus commandBus) {
        Assert.notNull(commandBus, "CommandBus was not autowired.");
        this.commandBus = commandBus;
    }

    @PostMapping
    public ResponseEntity<UserCreateCommand> create(@RequestBody UserCreateCommand userCreateCommand) {
        userCreateCommand.setId(UUID.randomUUID().toString());
        commandBus.dispatch(new GenericCommandMessage<>(userCreateCommand));
        return new ResponseEntity<>(userCreateCommand, HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserUpdateCommand> update(@PathVariable String id, @RequestBody UserUpdateCommand userUpdateCommand) {
        userUpdateCommand.setId(id);
        commandBus.dispatch(new GenericCommandMessage<>(userUpdateCommand));
        return new ResponseEntity<>(userUpdateCommand, HttpStatus.ACCEPTED);
    }

}
