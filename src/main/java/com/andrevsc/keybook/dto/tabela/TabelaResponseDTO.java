package com.andrevsc.keybook.dto.tabela;

import com.andrevsc.keybook.model.Tabela;

import java.time.LocalDateTime;

public record TabelaResponseDTO(
    Long id,
    String nome,
    LocalDateTime dataCriacao,
    LocalDateTime dataUltimaModificacao,
    int numeroItems,
    Long userId
) {
    public TabelaResponseDTO(Tabela tabela) {
        this(
            tabela.getId(),
            tabela.getNome(),
            tabela.getDataCriacao(),
            tabela.getDataUltimaModificacao(),
            tabela.getNumeroItems(),
            tabela.getUser().getId()
        );
    }
}