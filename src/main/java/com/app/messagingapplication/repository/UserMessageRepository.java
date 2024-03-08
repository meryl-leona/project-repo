package com.app.messagingapplication.repository;

import com.app.messagingapplication.entity.UserMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMessageRepository extends JpaRepository<UserMessage, Long> {

    List<UserMessage> findAllBySender(String sender);
    List<UserMessage> findAllByReceiver(String receiver);
    List<UserMessage> findAllBySenderAndReceiver(String sender, String receiver);

}
