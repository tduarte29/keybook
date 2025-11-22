package com.andrevsc.keybook.dto.historic;


import java.time.LocalDateTime;
import com.andrevsc.keybook.model.HistoricoExportacao;

public record HistoricoResponseDTO(
    Long id,
    String nomeItem,
    String nomeArquivo,
    LocalDateTime dataExportacao
) {
    public HistoricoResponseDTO(HistoricoExportacao historico) {
        this(
            historico.getId(),
            historico.getItem().getNome(),
            historico.getNomeArquivo(),
            historico.getDataExportacao()
        );
    }
}