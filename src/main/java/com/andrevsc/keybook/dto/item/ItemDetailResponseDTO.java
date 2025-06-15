package com.andrevsc.keybook.dto.item;

import com.andrevsc.keybook.dto.comentario.ComentarioDTO;
import com.andrevsc.keybook.dto.propriedade.PropriedadeDTO;
import com.andrevsc.keybook.model.Item;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record ItemDetailResponseDTO(
    Long id,
    String nome,
    LocalDateTime dataCriacao,
    Long tabelaId,
    List<PropriedadeDTO> propriedades,
    List<ComentarioDTO> comentarios
) {
    public ItemDetailResponseDTO(Item item) {
        this(
            item.getId(),
            item.getNome(),
            item.getDataCriacao(),
            item.getTabela().getId(),
            item.getPropriedades().stream().map(PropriedadeDTO::new).collect(Collectors.toList()),
            item.getComentarios().stream().map(ComentarioDTO::new).collect(Collectors.toList())
        );
    }
}