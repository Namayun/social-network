package com.example.socialnetwork.repository;

import com.example.socialnetwork.dto.response.UserFriendshipDto;
import com.example.socialnetwork.entity.user.FriendshipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendshipEntityRepository extends JpaRepository<FriendshipEntity, Long> {
    Optional<FriendshipEntity> findFriendshipEntitiesByFriend_UsernameAndUser_Username(String friendUsername, String username);

    @Query(value = ("""
            select
                   uf.username as username,
                   fs.status as status,
                   fs.created_at as createdAt
            from users u
            join friendship fs on u.id = fs.friend_id
            join users uf on uf.id = fs.user_id
            where u.username = :username and fs.status = 'PENDING'
            """), nativeQuery = true)
    List<UserFriendshipDto> showAllFriendsRequests(@Param("username") String username);


    @Query(value = """
            with first_query as (
                        select uf.username as username,
                                 fs.status as status,
                         fs.created_at as createdAt
                                 from users u
                                          join friendship fs on u.id = fs.friend_id
                                          join users uf on uf.id = fs.user_id
                                 where u.username = :username
                                   and fs.status like 'ACCEPTED')
            select * from  first_query
            union all
            select uf.username as username,
                   fs.status as status,
                   fs.created_at as createdAt
            from users u
                     join friendship fs on u.id = fs.user_id
                     join users uf on uf.id = fs.friend_id
            where u.username = :username and fs.status like 'ACCEPTED'
              and not exists (select 1 from first_query)
            """, nativeQuery = true)
    List<UserFriendshipDto> showAllFriends(@Param("username") String username);
}
