package org.chat.model.commands;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 * @author atsikhamirau on 23.05.2019
 */
@Getter
@Setter
@Accessors(chain = true)
public class UserCreateCommand {

    @TargetAggregateIdentifier
    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;
}
