package com.example.socialnetwork.entity.photo;

import com.example.socialnetwork.entity.comment.PictureCommentEntity;
import com.example.socialnetwork.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "picture")
public class PictureEntity {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String url;
    private String name;
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt = LocalDateTime.now();
    private Long likes;

    @ManyToMany(mappedBy = "photoList")
    private List<AlbumEntity> albumList;




}
