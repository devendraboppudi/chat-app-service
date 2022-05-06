package com.betvictor.chatapp.repository;

import com.betvictor.chatapp.model.ChatMessage;
import com.betvictor.chatapp.model.MessageStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository
        extends JpaRepository<ChatMessage, Integer> {

    long countBySenderIdAndRecipientIdAndStatus(
            String senderId, String recipientId, MessageStatus status);

    List<ChatMessage> findByChatId(String chatId);

    List<ChatMessage> findBySenderIdAndRecipientId(String senderId, String recipientId);
}