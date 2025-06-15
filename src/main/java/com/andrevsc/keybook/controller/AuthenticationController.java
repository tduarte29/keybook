package com.andrevsc.keybook.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.andrevsc.keybook.dto.auth.LoginDTO;
import com.andrevsc.keybook.dto.user.UserCreateDTO;
import com.andrevsc.keybook.dto.user.UserResponseDTO;
import com.andrevsc.keybook.model.User;
import com.andrevsc.keybook.service.UserService;
import com.andrevsc.keybook.service.auth.TokenService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    

    @Autowired
    private UserService userService;

    // @PostMapping("/login")
    // public ResponseEntity<TokenDTO> login(@RequestBody LoginDTO loginDTO) {
    //     var usernamePassword = new UsernamePasswordAuthenticationToken(loginDTO.email(), loginDTO.password());
    //     var auth = this.authenticationManager.authenticate(usernamePassword);
    //     var token = tokenService.generateToken((User) auth.getPrincipal());
    //     return ResponseEntity.ok(new TokenDTO(token));
    // }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginDTO loginDTO) {
    var usernamePassword = new UsernamePasswordAuthenticationToken(loginDTO.email(), loginDTO.password());
    var auth = this.authenticationManager.authenticate(usernamePassword);
    User user = (User) auth.getPrincipal();
    String token = tokenService.generateToken(user);
    
    Map<String, String> response = new HashMap<>();
    response.put("token", token);
    response.put("userId", user.getId().toString()); // Adiciona o userId
    
    return ResponseEntity.ok(response);
}

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserCreateDTO userCreateDTO, UriComponentsBuilder uriBuilder) {
        UserResponseDTO createdUser = userService.createUser(userCreateDTO);
        return ResponseEntity.status(201).body(createdUser);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
    String authHeader = request.getHeader("Authorization");
    
    if (authHeader != null && authHeader.startsWith("Bearer ")) {
        String token = authHeader.substring(7);
    }
    
    return ResponseEntity.noContent().build();
}
}