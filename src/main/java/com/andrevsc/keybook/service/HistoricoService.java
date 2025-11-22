package com.andrevsc.keybook.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest; // Importação nova
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andrevsc.keybook.dto.historic.HistoricoResponseDTO;
import com.andrevsc.keybook.model.HistoricoExportacao;
import com.andrevsc.keybook.model.Item;
import com.andrevsc.keybook.repository.HistoricoRepository;
import com.andrevsc.keybook.repository.ItemRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class HistoricoService {

    @Autowired
    private HistoricoRepository historicoRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Transactional
    public void registrarExportacao(Long itemId, String nomeArquivo) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException("Item não encontrado: " + itemId));

        HistoricoExportacao historico = new HistoricoExportacao(item, nomeArquivo);
        historicoRepository.save(historico);
    }

    // MUDANÇA: Adicionado @Transactional(readOnly = true) para manter a sessão aberta
    @Transactional(readOnly = true)
    public List<HistoricoResponseDTO> getHistoricoRecente() {
        // MUDANÇA: Usando PageRequest.of(0, 20) para pegar os top 20
        return historicoRepository.findRecentHistory(PageRequest.of(0, 20)).stream()
                .map(HistoricoResponseDTO::new)
                .collect(Collectors.toList());
    }
}