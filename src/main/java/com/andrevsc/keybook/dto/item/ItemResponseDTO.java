package com.andrevsc.keybook.dto.item;

import com.andrevsc.keybook.model.Item;
import java.time.LocalDateTime;

public record ItemResponseDTO(
    Long id,
    String nome,
    LocalDateTime dataCriacao,
    Long tabelaId
) {
    public ItemResponseDTO(Item item) {
        this(
            item.getId(),
            item.getNome(),
            item.getDataCriacao(),
            item.getTabela().getId()
        );
    }
}