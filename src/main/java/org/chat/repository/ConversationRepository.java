package org.chat.repository;

import org.chat.model.Conversation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author atsikhamirau on 28.05.2019
 */
@Repository("conversationProjectionRepository")
public interface ConversationRepository extends MongoRepository<Conversation, String> {
}
