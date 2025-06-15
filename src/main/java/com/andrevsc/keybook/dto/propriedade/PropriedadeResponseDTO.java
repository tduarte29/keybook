package com.andrevsc.keybook.dto.propriedade;

import com.andrevsc.keybook.model.Propriedade;

public record PropriedadeResponseDTO(
    Long id,
    String nome,
    String valor,
    Long itemId
) {
    public PropriedadeResponseDTO(Propriedade propriedade) {
        this(
            propriedade.getId(),
            propriedade.getNome(),
            propriedade.getValor(),
            propriedade.getItem().getId()
        );
    }
}