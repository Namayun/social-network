package com.example.socialnetwork.service.impl;

import com.example.socialnetwork.dto.update.UpdatePasswordDto;
import com.example.socialnetwork.entity.user.UserEntity;
import com.example.socialnetwork.repository.UserEntityRepository;
import com.example.socialnetwork.service.AuthenticationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserEntityRepository userEntityRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void testUpdatePassword_ShouldUpdatePasswordSuccessfully() {
        UpdatePasswordDto updatePasswordDto = new UpdatePasswordDto();
        updatePasswordDto.setOldPassword("oldPassword");
        updatePasswordDto.setNewPassword("newPassword");

        UserEntity existingUser = new UserEntity();
        existingUser.setPassword("encodedPassword");

        Mockito.when(authenticationService.getAuthenticatedUser()).thenReturn(existingUser);
        Mockito.when(passwordEncoder.matches("oldPassword", "encodedPassword")).thenReturn(true);
        Mockito.when(passwordEncoder.encode("newPassword")).thenReturn("newEncodedPassword");

        String result = userService.updatePassword(updatePasswordDto);

        Mockito.verify(authenticationService, Mockito.times(1)).getAuthenticatedUser();
        Mockito.verify(passwordEncoder, Mockito.times(1)).matches("oldPassword", "encodedPassword");
        Mockito.verify(passwordEncoder, Mockito.times(1)).encode("newPassword");
        Mockito.verifyNoMoreInteractions(authenticationService, passwordEncoder);


        Assertions.assertEquals("Password successfully updated", result);
        Assertions.assertEquals("newEncodedPassword", existingUser.getPassword());


    }


}