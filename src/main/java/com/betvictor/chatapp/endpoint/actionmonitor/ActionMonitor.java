package com.betvictor.chatapp.endpoint.actionmonitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class ActionMonitor {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    protected void afterChange(int id, String status) {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        String message = "timestamp="+timestamp+" a row with ID="+id+" was "+status;
        messagingTemplate.convertAndSendToUser(
                "actionmonitor","/queue/messages", message);
    }
}
