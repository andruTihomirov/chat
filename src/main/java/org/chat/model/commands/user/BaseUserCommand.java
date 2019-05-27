package org.chat.model.commands.user;

import lombok.Getter;
import lombok.Setter;
import org.chat.model.commands.Command;

/**
 * @author atsikhamirau on 27.05.2019
 */
@Getter
@Setter
abstract class BaseUserCommand extends Command {

    private String firstName;

    private String lastName;

    private String email;

    private String password;
}
