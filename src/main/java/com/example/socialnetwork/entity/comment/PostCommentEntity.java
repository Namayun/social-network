package com.example.socialnetwork.entity.comment;
import com.example.socialnetwork.entity.post.PostEntity;
import com.example.socialnetwork.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "post_comment")
public class PostCommentEntity {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String text;

    @ManyToOne
    @JoinColumn(name = "post_id",nullable = false)
    private PostEntity post;

    @ManyToOne
    @JoinColumn(name = "author_id",nullable = false)
    private UserEntity author;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt = LocalDateTime.now();


}
