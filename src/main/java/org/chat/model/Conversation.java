package org.chat.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.chat.model.commands.conversation.ConversationCreateCommand;
import org.chat.model.events.conversation.ConversationCreateEvent;

import java.util.List;

/**
 * @author atsikhamirau on 28.05.2019
 */
@Getter
@Setter
@Accessors(chain = true)
@Aggregate
public class Conversation {

    @AggregateIdentifier
    private String id;

    private String initiatorId;

    private List<String> participantIds;

    private List<Message> messages;

    public Conversation() {
    }

    @CommandHandler
    public Conversation(ConversationCreateCommand conversationCreateCommand) {
        ConversationCreateEvent conversationCreateEvent = new ConversationCreateEvent();
        conversationCreateEvent.setId(conversationCreateCommand.getId());
        conversationCreateEvent.setInitiatorId(conversationCreateCommand.getInitiatorId());
        AggregateLifecycle.apply(conversationCreateEvent);
    }

    @EventSourcingHandler
    public void on(ConversationCreateEvent conversationCreateEvent) {
        this.id = conversationCreateEvent.getId();
        this.initiatorId = conversationCreateEvent.getInitiatorId();
    }

}
