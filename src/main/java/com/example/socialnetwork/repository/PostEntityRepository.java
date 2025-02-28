package com.example.socialnetwork.repository;

import com.example.socialnetwork.entity.post.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostEntityRepository extends JpaRepository<PostEntity, Long> {
    Optional<PostEntity> findPostEntitiesById(Long id);
}
