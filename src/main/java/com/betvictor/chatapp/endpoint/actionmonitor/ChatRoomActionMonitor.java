package com.betvictor.chatapp.endpoint.actionmonitor;

import com.betvictor.chatapp.model.ChatRoom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;

@Slf4j
@Component
public class ChatRoomActionMonitor extends ActionMonitor {

    @PostUpdate
    private void afterUpdate(ChatRoom chatRoom) {
        afterChange(chatRoom.getId(), "updated");
    }

    @PostPersist
    private void afterPersist(ChatRoom chatRoom) {
        afterChange(chatRoom.getId(), "inserted");
    }

}
