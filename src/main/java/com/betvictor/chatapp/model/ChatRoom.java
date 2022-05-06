package com.betvictor.chatapp.model;

import com.betvictor.chatapp.endpoint.actionmonitor.ChatRoomActionMonitor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(ChatRoomActionMonitor.class)
@Entity
@Table(name="chatroom")
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String chatId;
    private String senderId;
    private String recipientId;
}
