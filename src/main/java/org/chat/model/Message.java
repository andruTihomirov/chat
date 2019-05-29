package org.chat.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author atsikhamirau on 28.05.2019
 */
@Getter
@Setter
@Accessors(chain = true)
public class Message {

    private String id;

    private String fromUserId;

    private String body;

}
