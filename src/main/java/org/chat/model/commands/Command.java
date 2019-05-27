package org.chat.model.commands;

import lombok.Getter;
import lombok.Setter;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 * @author atsikhamirau on 27.05.2019
 */
@Getter
@Setter
public abstract class Command {

    @TargetAggregateIdentifier
    private String id;
}
