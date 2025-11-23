package com.andrevsc.keybook.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.andrevsc.keybook.dto.auth.LoginDTO;
import com.andrevsc.keybook.dto.auth.LoginResponseDTO;
import com.andrevsc.keybook.dto.user.UserCreateDTO;
import com.andrevsc.keybook.dto.user.UserResponseDTO;
import com.andrevsc.keybook.model.User;
import com.andrevsc.keybook.service.UserService;
import com.andrevsc.keybook.service.auth.TokenService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserService userService;

    public AuthenticationController(AuthenticationManager authenticationManager, 
                                  TokenService tokenService, 
                                  UserService userService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        var authToken = new UsernamePasswordAuthenticationToken(loginDTO.email(), loginDTO.password());
        var authentication = authenticationManager.authenticate(authToken);
        var user = (User) authentication.getPrincipal();
        var token = tokenService.generateToken(user);
        return ResponseEntity.ok(new LoginResponseDTO(token, user.getId()));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserCreateDTO userCreateDTO, 
                                                  UriComponentsBuilder uriBuilder) {
        var createdUser = userService.createUser(userCreateDTO);
        var uri = uriBuilder.path("/users/{id}").buildAndExpand(createdUser.id()).toUri();
        return ResponseEntity.created(uri).body(createdUser);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
    return ResponseEntity.noContent().build();
}
}