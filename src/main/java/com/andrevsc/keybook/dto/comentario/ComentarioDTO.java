package com.andrevsc.keybook.dto.comentario;

import com.andrevsc.keybook.model.Comentario;

public record ComentarioDTO(String texto) {
    public ComentarioDTO(Comentario comentario) {
        this(comentario.getTexto());
    }
}