package com.example.socialnetwork.repository;

import com.example.socialnetwork.entity.comment.PictureCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureCommentEntityRepository extends JpaRepository<PictureCommentEntity, Long> {
}
