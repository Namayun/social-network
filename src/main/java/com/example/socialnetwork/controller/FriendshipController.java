package com.example.socialnetwork.controller;

import com.example.socialnetwork.dto.response.UserFriendshipDto;
import com.example.socialnetwork.entity.user.FriendshipEntity;
import com.example.socialnetwork.entity.user.UserEntity;
import com.example.socialnetwork.service.FriendshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/friendships")
@RequiredArgsConstructor
public class FriendshipController {
    private final FriendshipService friendshipService;

    @PostMapping("/send-request")
    public ResponseEntity<String> sendFriendshipRequest(
            @RequestParam String login
    ) {
        return ResponseEntity.ok(friendshipService.sendFriendshipRequest(login));
    }


    @GetMapping("/requests")
    public ResponseEntity<List<UserFriendshipDto>> listFriendshipRequests() {
        return ResponseEntity.ok(friendshipService.listFriendshipRequests());
    }

    @PostMapping("/accept")
    public ResponseEntity<String> acceptFriend(
            @RequestParam String login
    ) {
        return ResponseEntity.ok(friendshipService.acceptFriendship(login));
    }

    @PostMapping("/decline")
    public ResponseEntity<String> declineFriend(
            @RequestParam String login
    ) {
        return ResponseEntity.ok(friendshipService.declineFriendship(login));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserFriendshipDto>> listFriends() {
        return ResponseEntity.ok(friendshipService.listFriends());
    }
}
