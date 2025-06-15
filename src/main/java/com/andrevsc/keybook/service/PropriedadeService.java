package com.andrevsc.keybook.service;

import com.andrevsc.keybook.dto.propriedade.PropriedadeCreateDTO;
import com.andrevsc.keybook.dto.propriedade.PropriedadeResponseDTO;
import com.andrevsc.keybook.model.Item;
import com.andrevsc.keybook.model.Propriedade;
import com.andrevsc.keybook.repository.ItemRepository;
import com.andrevsc.keybook.repository.PropriedadeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropriedadeService {

    @Autowired
    private PropriedadeRepository propriedadeRepository;

    @Autowired
    private ItemRepository itemRepository;

    public PropriedadeResponseDTO createPropriedade(PropriedadeCreateDTO dto, Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException("Item não encontrado com o id: " + itemId));

        Propriedade novaPropriedade = new Propriedade();
        novaPropriedade.setNome(dto.nome());
        novaPropriedade.setValor(dto.valor());
        novaPropriedade.setItem(item);

        Propriedade savedPropriedade = propriedadeRepository.save(novaPropriedade);
        return new PropriedadeResponseDTO(savedPropriedade);
    }

    public List<PropriedadeResponseDTO> getPropriedadesByItemId(Long itemId) {
        if (!itemRepository.existsById(itemId)) {
            throw new EntityNotFoundException("Item não encontrado com o id: " + itemId);
        }
        return propriedadeRepository.findByItemId(itemId)
                .stream()
                .map(PropriedadeResponseDTO::new)
                .collect(Collectors.toList());
    }
    
    @Transactional
    public PropriedadeResponseDTO updatePropriedade(Long propriedadeId, PropriedadeCreateDTO dto) {
        Propriedade propriedadeExistente = propriedadeRepository.findById(propriedadeId)
                .orElseThrow(() -> new EntityNotFoundException("Propriedade não encontrada com o id: " + propriedadeId));

        propriedadeExistente.setNome(dto.nome());
        propriedadeExistente.setValor(dto.valor());

        Propriedade updatedPropriedade = propriedadeRepository.save(propriedadeExistente);
        return new PropriedadeResponseDTO(updatedPropriedade);
    }

    public void deletePropriedade(Long propriedadeId) {
        if (!propriedadeRepository.existsById(propriedadeId)) {
            throw new EntityNotFoundException("Propriedade não encontrada com o id: " + propriedadeId);
        }
        propriedadeRepository.deleteById(propriedadeId);
    }
}