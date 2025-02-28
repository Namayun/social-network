package com.example.socialnetwork.entity.message;
import com.example.socialnetwork.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class ChatEntity {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private UserEntity user1;
    @ManyToOne
    private UserEntity user2;

    @OneToMany(mappedBy = "chat")
    private List<MessageEntity> messageList;
}
