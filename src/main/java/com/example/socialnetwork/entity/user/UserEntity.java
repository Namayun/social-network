package com.example.socialnetwork.entity.user;

import com.example.socialnetwork.entity.comment.PictureCommentEntity;
import com.example.socialnetwork.entity.comment.PostCommentEntity;
import com.example.socialnetwork.entity.message.MessageEntity;
import com.example.socialnetwork.entity.photo.AlbumEntity;
import com.example.socialnetwork.entity.photo.PictureEntity;
import com.example.socialnetwork.entity.post.PostEntity;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Entity
@Table(name = "users")
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username",unique = true)
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "email",unique = true)
    private String email;
    private String firstName;
    private String lastName;
    private String role = "USER";

    @Temporal(TemporalType.DATE)
    private LocalDate birthDate;
    private String country;
    private String city;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_picture_id")
    private PictureEntity profilePicture;


    @OneToMany(mappedBy = "owner",cascade = CascadeType.ALL)
    private List<PostEntity> postList;

    @OneToMany(mappedBy = "owner",cascade = CascadeType.ALL)
    private List<AlbumEntity> albumEntities;

    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
    private List<PostCommentEntity> postComments;

    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
    private List<PictureCommentEntity> pictureComments;

    @OneToMany(mappedBy = "sender",cascade = CascadeType.ALL)
    private List<MessageEntity> receivedMessages;

    @OneToMany(mappedBy = "receiver",cascade = CascadeType.ALL)
    private List<MessageEntity> sentMessages;

    @ManyToMany
    @JoinTable(name = "friendship",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id"))
    private Set<UserEntity> friends  = new HashSet<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
