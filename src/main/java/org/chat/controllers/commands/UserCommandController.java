package org.chat.controllers.commands;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.GenericCommandMessage;
import org.chat.model.commands.UserCreateCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
        this.commandBus = commandBus;
    }

    @PostMapping
    public ResponseEntity<UserCreateCommand> create(@RequestBody UserCreateCommand command) {
        command.setId(UUID.randomUUID().toString());
        commandBus.dispatch(new GenericCommandMessage<>(command));
        return new ResponseEntity<>(command, HttpStatus.ACCEPTED);
    }

}
