package com.example.socialnetwork.repository;

import com.example.socialnetwork.entity.photo.AlbumEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumEntityRepository extends JpaRepository<AlbumEntity,Long> {
}
