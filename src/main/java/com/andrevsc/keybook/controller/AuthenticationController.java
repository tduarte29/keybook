package com.andrevsc.keybook.controller;

import com.andrevsc.keybook.dto.auth.LoginDTO;
import com.andrevsc.keybook.dto.auth.TokenDTO;
import com.andrevsc.keybook.dto.user.UserCreateDTO;
import com.andrevsc.keybook.dto.user.UserResponseDTO;
import com.andrevsc.keybook.model.User;
import com.andrevsc.keybook.service.UserService;
import com.andrevsc.keybook.service.auth.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody LoginDTO loginDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginDTO.email(), loginDTO.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new TokenDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserCreateDTO userCreateDTO, UriComponentsBuilder uriBuilder) {
        UserResponseDTO createdUser = userService.createUser(userCreateDTO);
        return ResponseEntity.status(201).body(createdUser);
    }
}