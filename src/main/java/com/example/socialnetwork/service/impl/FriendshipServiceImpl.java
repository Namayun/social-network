package com.example.socialnetwork.service.impl;

import com.example.socialnetwork.dto.response.UserFriendshipDto;
import com.example.socialnetwork.entity.user.FriendshipEntity;
import com.example.socialnetwork.entity.user.UserEntity;
import com.example.socialnetwork.repository.FriendshipEntityRepository;
import com.example.socialnetwork.repository.UserEntityRepository;
import com.example.socialnetwork.service.AuthenticationService;
import com.example.socialnetwork.service.FriendshipService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class FriendshipServiceImpl implements FriendshipService {
    private final FriendshipEntityRepository friendshipEntityRepository;
    private final AuthenticationService authenticationService;
    private final UserEntityRepository userEntityRepository;


    @Transactional
    @Modifying
    @Override
    public String acceptFriendship(String friendLogin) {
        FriendshipEntity friendship = findFriendship(friendLogin);
        if (friendship.getStatus().equals("PENDING")) {
            friendship.setStatus("ACCEPTED");
        } else {
            throw new IllegalArgumentException("Friendship already accepted");
        }
        friendshipEntityRepository.save(friendship);

        return "Friendship accepted";
    }

    @Transactional
    @Modifying
    @Override
    public String declineFriendship(String friendLogin) {
        FriendshipEntity friendship = findFriendship(friendLogin);
        if (!friendship.getStatus().equals("PENDING")) {
            throw new IllegalArgumentException("Friendship already declined or accepted");
        }
        friendship.setStatus("DECLINED");
        friendshipEntityRepository.delete(friendship);

        return "Friendship declined";
    }

    @Override
    public String deleteFriend(String friendLogin) {
        friendshipEntityRepository.delete(findFriendship(friendLogin));
        return "Friendship deleted";
    }

    @Override
    public UserEntity findFriendByLogin(String friendLogin) {
        return findFriendship(friendLogin).getFriend();
    }

    @Transactional
    @Modifying
    @Override
    public String sendFriendshipRequest(String friendLogin) {
        String formattedFriendLogin = friendLogin.toLowerCase().trim();
        if (formattedFriendLogin.isBlank()) {
            throw new IllegalArgumentException("Login cannot be blank");
        }
        if (findFriendship(formattedFriendLogin) != null) {
            throw new IllegalArgumentException("Friendship already exists");
        }

        if (userEntityRepository.findByUsername(formattedFriendLogin).isEmpty()) {
            throw new EntityNotFoundException("User with login " + formattedFriendLogin + " not found");
        }
        if (formattedFriendLogin.equals(authenticationService.getAuthenticatedUser().getUsername())) {
            throw new IllegalArgumentException("You cannot send request to yourself");
        }
        UserEntity authenticatedUser = authenticationService.getAuthenticatedUser();
        UserEntity friend = userEntityRepository.findByUsername(formattedFriendLogin).get();
        FriendshipEntity friendship = new FriendshipEntity();
        friendship.setUser(authenticatedUser);
        friendship.setFriend(friend);
        friendshipEntityRepository.save(friendship);

        return "Request sent";
    }


    @Override
    public List<UserFriendshipDto> listFriendshipRequests() {
        String login = authenticationService.getAuthenticatedUser().getUsername();
        return friendshipEntityRepository.showAllFriendsRequests(login);
    }

    @Override
    public List<UserFriendshipDto> listFriends() {
        String login = authenticationService.getAuthenticatedUser().getUsername();
        return friendshipEntityRepository.showAllFriends(login);
    }

    private FriendshipEntity findFriendship(String friendLogin) {
        String formattedFriendLogin = friendLogin.toLowerCase().trim();
        if (formattedFriendLogin.isBlank()) {
            throw new IllegalArgumentException("Login cannot be blank");
        }
        UserEntity authenticatedUser = authenticationService.getAuthenticatedUser();
        return friendshipEntityRepository.findFriendshipEntitiesByFriend_UsernameAndUser_Username(
                authenticatedUser.getUsername(), formattedFriendLogin).orElseThrow(
                () -> new EntityNotFoundException("Friendship not found")
        );
    }
}
