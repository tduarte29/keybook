package com.andrevsc.keybook.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andrevsc.keybook.dto.historico.HistoricoResponseDTO;
import com.andrevsc.keybook.model.HistoricoAtividade;
import com.andrevsc.keybook.model.Item;
import com.andrevsc.keybook.model.enums.TipoEvento;
import com.andrevsc.keybook.repository.HistoricoAtividadeRepository;
import com.andrevsc.keybook.repository.ItemRepository;

@Service
public class HistoricoService {

    @Autowired
    private HistoricoAtividadeRepository repo;

    @Autowired
    private ItemRepository itemRepository;

    @Transactional
    public void registrar(Long itemId, TipoEvento tipo, String descricao) {
        // Se for REMOCAO, o item pode não existir mais no findById, 
        // então o chamador deve passar null ou tratamos diferente.
        // Para simplificar, vamos assumir que na REMOCAO passamos o objeto antes de deletar.
        
        Item item = null;
        if (itemId != null) {
             item = itemRepository.findById(itemId).orElse(null);
        }
        
        HistoricoAtividade log = new HistoricoAtividade(item, tipo, descricao);
        repo.save(log);
    }
    
    // Sobrecarga para quando já temos o objeto Item em mãos (mais performático)
    @Transactional
    public void registrar(Item item, TipoEvento tipo, String descricao) {
        HistoricoAtividade log = new HistoricoAtividade(item, tipo, descricao);
        repo.save(log);
    }

    // Sobrecarga específica para DELEÇÃO (quando o item vai deixar de existir)
    @Transactional
    public void registrarDelecao(String nomeItem, String descricao) {
        HistoricoAtividade log = new HistoricoAtividade(nomeItem, TipoEvento.REMOCAO, descricao);
        repo.save(log);
    }

    @Transactional(readOnly = true)
    public List<HistoricoResponseDTO> getHistoricoRecente() {
        return repo.findRecentActivity(PageRequest.of(0, 50)) // Aumentei pra 50
                .stream()
                .map(HistoricoResponseDTO::new)
                .collect(Collectors.toList());
    }
}