package org.chat.mappers;

import org.chat.model.commands.user.UserCreateCommand;
import org.chat.model.commands.user.UserUpdateCommand;
import org.chat.model.events.user.UserCreateEvent;
import org.chat.model.events.user.UserUpdateEvent;
import org.mapstruct.Mapper;

/**
 * @author atsikhamirau on 27.05.2019
 */
@Mapper
public interface UserMapper {

    UserCreateEvent commandToEvent(UserCreateCommand command);
    UserUpdateEvent commandToEvent(UserUpdateCommand command);

}
