package com.andrevsc.keybook.service;

import com.andrevsc.keybook.dto.user.UserCreateDTO;
import com.andrevsc.keybook.dto.user.UserResponseDTO;
import com.andrevsc.keybook.model.User;
import com.andrevsc.keybook.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserResponseDTO createUser(UserCreateDTO userCreateDTO) {
        if (userRepository.findByEmail(userCreateDTO.email()).isPresent()) {
            throw new IllegalStateException("Email já cadastrado.");
        }
        
        User newUser = new User();
        newUser.setNome(userCreateDTO.nome());
        newUser.setEmail(userCreateDTO.email());
        newUser.setPassword(passwordEncoder.encode(userCreateDTO.password()));
        
        User savedUser = userRepository.save(newUser);
        
        return new UserResponseDTO(savedUser);
    }

    public UserResponseDTO getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com o id: " + userId));
        return new UserResponseDTO(user);
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserResponseDTO::new)
                .collect(Collectors.toList());
    }

    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new EntityNotFoundException("Usuário não encontrado com o id: " + userId);
        }
        userRepository.deleteById(userId);
    }
}