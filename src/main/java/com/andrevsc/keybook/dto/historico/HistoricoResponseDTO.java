package com.andrevsc.keybook.dto.historico;

import java.time.LocalDateTime;

import com.andrevsc.keybook.model.HistoricoAtividade;

public record HistoricoResponseDTO(
    Long id,
    String nomeItem, // Agora pegamos do snapshot
    String tipoEvento,
    String descricao,
    LocalDateTime dataHora
) {
    public HistoricoResponseDTO(HistoricoAtividade h) {
        this(
            h.getId(),
            h.getNomeItemSnapshot(), // Usa o snapshot que é seguro contra deleção
            h.getTipoEvento().toString(),
            h.getDescricao(),
            h.getDataHora()
        );
    }
}


// package com.andrevsc.keybook.dto.historic;


// import java.time.LocalDateTime;
// import com.andrevsc.keybook.model.HistoricoExportacao;

// public record HistoricoResponseDTO(
//     Long id,
//     String nomeItem,
//     String nomeArquivo,
//     LocalDateTime dataExportacao
// ) {
//     public HistoricoResponseDTO(HistoricoExportacao historico) {
//         this(
//             historico.getId(),
//             historico.getItem().getNome(),
//             historico.getNomeArquivo(),
//             historico.getDataExportacao()
//         );
//     }
// }