package com.example.socialnetwork.repository;


import com.example.socialnetwork.entity.comment.PostCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostCommentEntityRepository extends JpaRepository<PostCommentEntity, Long> {
}
