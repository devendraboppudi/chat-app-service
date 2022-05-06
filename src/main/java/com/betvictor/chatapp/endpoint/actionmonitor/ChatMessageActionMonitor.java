package com.betvictor.chatapp.endpoint.actionmonitor;

import com.betvictor.chatapp.model.ChatMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;

@Slf4j
@Component
public class ChatMessageActionMonitor extends ActionMonitor {

    @PostUpdate
    private void afterUpdate(ChatMessage chatMessage) {
        afterChange(chatMessage.getId(), "updated");
    }

    @PostPersist
    private void afterPersist(ChatMessage chatMessage) {
        afterChange(chatMessage.getId(), "inserted");
    }


}
