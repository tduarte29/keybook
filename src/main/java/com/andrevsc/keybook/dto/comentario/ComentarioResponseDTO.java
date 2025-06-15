package com.andrevsc.keybook.dto.comentario;

import com.andrevsc.keybook.model.Comentario;

import java.time.LocalDateTime;

public record ComentarioResponseDTO(
    Long id,
    String texto,
    LocalDateTime data,
    Long itemId
) {
    public ComentarioResponseDTO(Comentario comentario) {
        this(
            comentario.getId(),
            comentario.getTexto(),
            comentario.getData(),
            comentario.getItem().getId()
        );
    }
}