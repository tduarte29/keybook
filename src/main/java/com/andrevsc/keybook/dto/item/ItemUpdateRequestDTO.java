package com.andrevsc.keybook.dto.item;

import com.andrevsc.keybook.dto.comentario.ComentarioDTO;
import com.andrevsc.keybook.dto.propriedade.PropriedadeDTO;

import java.util.List;

public record ItemUpdateRequestDTO(
    String nome,
    List<PropriedadeDTO> propriedades,
    List<ComentarioDTO> comentarios
) {}