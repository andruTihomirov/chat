package org.chat.model.commands.conversation;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.chat.model.commands.Command;

/**
 * @author atsikhamirau on 28.05.2019
 */
@Getter
@Setter
@Accessors(chain = true)
public class ConversationCreateCommand extends Command {

    private String initiatorId;

}
