package com.andrevsc.keybook.dto.item;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.andrevsc.keybook.model.Item;

public record ItemDetailResponseDTO(
    Long id,
    String nome,
    LocalDateTime dataCriacao,
    Long tabelaId,
    String transponder,
    String tipoServico,
    String anoVeiculo,
    Double valorCobrado,
    String marcaVeiculo,
    String modeloVeiculo,
    String tipoChave,
    String maquina,
    String fornecedor,
    LocalDate dataConstrucao,
    String corteMecanico,
    String sistemaImobilizador,
    String observacoes
) {
    public ItemDetailResponseDTO(Item item) {
        this(
            item.getId(),
            item.getNome(),
            item.getDataCriacao(),
            item.getTabela().getId(),
            item.getTransponder(),
            item.getTipoServico(),
            item.getAnoVeiculo(),
            item.getValorCobrado(),
            item.getMarcaVeiculo(),
            item.getModeloVeiculo(),
            item.getTipoChave(),
            item.getMaquina(),
            item.getFornecedor(),
            item.getDataConstrucao(),
            item.getCorteMecanico(),
            item.getSistemaImobilizador(),
            item.getObservacoes()
        );
    }
}