package com.example.socialnetwork.entity.comment;

import com.example.socialnetwork.entity.photo.PictureEntity;
import com.example.socialnetwork.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "picture_comment")
public class PictureCommentEntity {
    @Id
    private Long id;
    private String text;
    @ManyToOne
    @JoinColumn(name = "picture_id", nullable = false)
    private PictureEntity picture;
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private UserEntity author;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt = LocalDateTime.now();
}
