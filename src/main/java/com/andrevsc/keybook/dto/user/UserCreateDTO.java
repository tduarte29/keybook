package com.andrevsc.keybook.dto.user;

public record UserCreateDTO(
    String nome,
    String email,
    String password
) {}