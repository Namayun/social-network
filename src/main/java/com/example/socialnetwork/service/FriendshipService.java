package com.example.socialnetwork.service;

import com.example.socialnetwork.dto.response.UserFriendshipDto;
import com.example.socialnetwork.entity.user.UserEntity;


import java.util.List;


public interface FriendshipService {
    String acceptFriendship(String friendLogin);

    String declineFriendship(String friendLogin);

    String deleteFriend(String friendLogin);

    UserEntity findFriendByLogin(String friendLogin);

    String sendFriendshipRequest(String friendLogin);

    List<UserFriendshipDto> listFriendshipRequests();
    List<UserFriendshipDto> listFriends();

}
