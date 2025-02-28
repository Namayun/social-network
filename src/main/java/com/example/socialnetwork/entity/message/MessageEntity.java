package com.example.socialnetwork.entity.message;

import java.time.LocalDateTime;

import com.example.socialnetwork.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "message")
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String content;
    @ManyToOne
    @JoinColumn(name = "chat_id",nullable = false)
    private ChatEntity chat;
    @ManyToOne
    @JoinColumn(name = "sender_id",nullable = false)
    private UserEntity sender;
    @ManyToOne
    @JoinColumn(name = "receiver_id",nullable = false)
    private UserEntity receiver;
    private String status = "SENT";
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime sentAt = LocalDateTime.now();
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime deliveredAt;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime readAt;
}
