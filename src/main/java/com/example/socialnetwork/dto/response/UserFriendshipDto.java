package com.example.socialnetwork.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.sql.Timestamp;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFriendshipDto {
    private String username;
    private String status;
    private Timestamp createdAt;
    
}
