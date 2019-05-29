package org.chat.model.projections;

import org.axonframework.eventhandling.EventHandler;
import org.chat.model.Conversation;
import org.chat.model.events.conversation.ConversationCreateEvent;
import org.chat.repository.ConversationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author atsikhamirau on 28.05.2019
 */
@Component
public class ConversationProjection {

    private ConversationRepository conversationRepository;

    @Autowired
    public ConversationProjection(@Qualifier("conversationProjectionRepository") ConversationRepository conversationRepository) {
        this.conversationRepository = conversationRepository;
    }

    @EventHandler
    public void on(ConversationCreateEvent conversationCreateEvent) {
        Conversation conversation = new Conversation();
        conversation.setId(conversationCreateEvent.getId());
        conversation.setInitiatorId(conversationCreateEvent.getInitiatorId());
        conversationRepository.save(conversation);
    }

}
