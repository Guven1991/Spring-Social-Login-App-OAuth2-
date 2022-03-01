package com.guven.springsocialloginapp.controller;

import com.guven.springsocialloginapp.exception.ResourceNotFoundException;
import com.guven.springsocialloginapp.model.User;
import com.guven.springsocialloginapp.repository.UserRepository;
import com.guven.springsocialloginapp.security.CurrentUser;
import com.guven.springsocialloginapp.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
}
