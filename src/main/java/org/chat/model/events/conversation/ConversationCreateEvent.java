package org.chat.model.events.conversation;

import lombok.Getter;
import lombok.Setter;
import org.chat.model.events.Event;

/**
 * @author atsikhamirau on 28.05.2019
 */
@Getter
@Setter
public class ConversationCreateEvent extends Event {

    private String initiatorId;

}
