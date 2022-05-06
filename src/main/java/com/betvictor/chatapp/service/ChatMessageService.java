package com.betvictor.chatapp.service;

import com.betvictor.chatapp.exception.ResourceNotFoundException;
import com.betvictor.chatapp.model.ChatMessage;
import com.betvictor.chatapp.model.MessageStatus;
import com.betvictor.chatapp.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatMessageService {

    private final ChatMessageRepository repository;

    private final ChatRoomService chatRoomService;

    @Autowired
    public ChatMessageService(ChatMessageRepository repository, ChatRoomService chatRoomService) {
        this.repository = repository;
        this.chatRoomService = chatRoomService;
    }

    @Transactional
    public ChatMessage save(ChatMessage chatMessage) {
        chatMessage.setStatus(MessageStatus.RECEIVED);
        repository.save(chatMessage);
        return chatMessage;
    }


    public long countNewMessages(String senderId, String recipientId) {
        return repository.countBySenderIdAndRecipientIdAndStatus(
                senderId, recipientId, MessageStatus.RECEIVED);
    }

    public List<ChatMessage> findChatMessages(String senderId, String recipientId) {
        var chatId = chatRoomService.getChatId(senderId, recipientId, false);

        var messages =
                chatId.map(cId -> repository.findByChatId(cId)).orElse(new ArrayList<>());

        if(messages.size() > 0) {
            updateStatuses(senderId, recipientId, MessageStatus.DELIVERED);
        }

        return messages;
    }

    public ChatMessage findById(Integer id) {
        return repository
                .findById(id)
                .map(chatMessage -> {
                    chatMessage.setStatus(MessageStatus.DELIVERED);
                    return repository.save(chatMessage);
                })
                .orElseThrow(() ->
                        new ResourceNotFoundException("can't find message (" + id + ")"));
    }

    @Transactional
    public void updateStatuses(String senderId, String recipientId, MessageStatus status) {

        List<ChatMessage> chatMessages = repository.findBySenderIdAndRecipientId(senderId, recipientId);

        if (chatMessages != null) {
            for (ChatMessage chatMessage : chatMessages) {
                chatMessage.setStatus(status);
            }

        }
        repository.saveAll(chatMessages);
    }
}
