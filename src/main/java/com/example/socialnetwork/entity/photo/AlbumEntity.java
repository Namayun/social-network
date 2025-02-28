package com.example.socialnetwork.entity.photo;

import java.time.LocalDateTime;
import java.util.List;

import com.example.socialnetwork.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "album")
public class AlbumEntity {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String albumName;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "owner_id",nullable = false)
    private UserEntity owner;


    @ManyToMany
    @JoinTable(name = "m2m_album_picture",
            joinColumns = @JoinColumn(name = "album_id"),
            inverseJoinColumns = @JoinColumn(name = "picture_id")
    )
    private List<PictureEntity> photoList;

}
