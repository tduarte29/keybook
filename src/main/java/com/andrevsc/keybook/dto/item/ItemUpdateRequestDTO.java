package com.andrevsc.keybook.dto.item;

import java.time.LocalDate;

public record ItemUpdateRequestDTO(
    String nome,
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
) {}