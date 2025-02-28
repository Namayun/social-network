package com.example.socialnetwork.repository;

import com.example.socialnetwork.entity.message.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatEntityRepository extends JpaRepository<ChatEntity, Long> {
}
