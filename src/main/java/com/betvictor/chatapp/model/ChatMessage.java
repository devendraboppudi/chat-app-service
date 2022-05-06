package com.betvictor.chatapp.model;

import com.betvictor.chatapp.endpoint.actionmonitor.ChatMessageActionMonitor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(ChatMessageActionMonitor.class)
@Entity
@Table(name="chatmessage")
public class ChatMessage {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private int id;

   private String chatId;
   private String senderId;
   private String recipientId;
   private String senderName;
   private String recipientName;
   private String content;
   private Date timestamp;
   private MessageStatus status;

}
