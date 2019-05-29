package org.chat.controllers.commands;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.GenericCommandMessage;
import org.chat.model.commands.conversation.ConversationCreateCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author atsikhamirau on 28.05.2019
 */
@RestController
@RequestMapping("conversations")
public class ConversationController {

    private final CommandBus commandBus;

    @Autowired
    public ConversationController(CommandBus commandBus) {
        Assert.notNull(commandBus, "CommandBus was not autowired.");
        this.commandBus = commandBus;
    }

    @PostMapping
    public ResponseEntity<ConversationCreateCommand> create() {
        ConversationCreateCommand conversationCreateCommand = new ConversationCreateCommand();
        conversationCreateCommand.setId(UUID.randomUUID().toString());
        conversationCreateCommand.setInitiatorId(UUID.randomUUID().toString()); // TODO: get current user later
        commandBus.dispatch(new GenericCommandMessage<>(conversationCreateCommand));
        return new ResponseEntity<>(conversationCreateCommand, HttpStatus.ACCEPTED);
    }
}
