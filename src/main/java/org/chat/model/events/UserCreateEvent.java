package org.chat.model.events;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author atsikhamirau on 23.05.2019
 */
@Getter
@Setter
@Accessors(chain = true)
public class UserCreateEvent {

    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

}
