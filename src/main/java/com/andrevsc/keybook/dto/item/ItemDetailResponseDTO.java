package com.andrevsc.keybook.dto.item;

import java.time.LocalDateTime;

import com.andrevsc.keybook.model.Item;

public record ItemDetailResponseDTO(
    Long id,
    String nome,
    LocalDateTime dataCriacao,
    Long tabelaId
) {
    public ItemDetailResponseDTO(Item item) {
        this(
            item.getId(),
            item.getNome(),
            item.getDataCriacao(),
            item.getTabela().getId()
        );
    }
}