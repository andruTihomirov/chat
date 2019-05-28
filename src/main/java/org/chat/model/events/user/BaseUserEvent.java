package org.chat.model.events.user;

import lombok.Getter;
import lombok.Setter;
import org.chat.model.events.Event;

/**
 * @author atsikhamirau on 27.05.2019
 */
@Getter
@Setter
public abstract class BaseUserEvent extends Event {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

}
