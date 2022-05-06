package com.betvictor.chatapp.endpoint;


import com.betvictor.chatapp.dto.ApiResponse;
import com.betvictor.chatapp.dto.JwtAuthenticationResponse;
import com.betvictor.chatapp.dto.LoginRequest;
import com.betvictor.chatapp.dto.SignUpRequest;
import com.betvictor.chatapp.exception.BadRequestException;
import com.betvictor.chatapp.exception.EmailAlreadyExistsException;
import com.betvictor.chatapp.exception.UsernameAlreadyExistsException;
import com.betvictor.chatapp.model.Role;
import com.betvictor.chatapp.model.User;
import com.betvictor.chatapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@Slf4j
public class AuthEndpoint {

    private final UserService userService;

    @Autowired
    public AuthEndpoint(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        String token = userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }

    @PostMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@Valid @RequestBody SignUpRequest payload) {
        log.info("creating user {}", payload.getUsername());

        User user = User
                .builder()
                .username(payload.getUsername())
                .email(payload.getEmail())
                .password(payload.getPassword())
                .profilePictureUrl(payload.getProfilePicUrl())
                .build();

        try {
            userService.registerUser(user, Role.USER);
        } catch (UsernameAlreadyExistsException | EmailAlreadyExistsException e) {
            throw new BadRequestException(e.getMessage());
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(user.getUsername()).toUri();

        return ResponseEntity
                .created(location)
                .body(new ApiResponse(true,"User registered successfully"));
    }
}
