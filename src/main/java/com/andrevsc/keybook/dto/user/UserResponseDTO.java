package com.andrevsc.keybook.dto.user;

import com.andrevsc.keybook.model.User;

public record UserResponseDTO(
    Long id,
    String nome,
    String email
) {
    public UserResponseDTO(User user) {
        this(user.getId(), user.getNome(), user.getEmail());
    }
}