package com.example.socialnetwork.repository;

import com.example.socialnetwork.entity.photo.PictureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureEntityRepository extends JpaRepository<PictureEntity, Long> {
}
