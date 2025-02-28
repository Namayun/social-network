package com.example.socialnetwork.entity.user;

import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "friendship", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "friend_id"}))
public class FriendshipEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
    @ManyToOne
    @JoinColumn(name = "friend_id", nullable = false)
    private UserEntity friend;
    @Column(nullable = false)
    private String status = "PENDING";
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt = LocalDateTime.now();
}
