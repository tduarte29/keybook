package com.andrevsc.keybook.service;

import com.andrevsc.keybook.dto.tabela.TabelaCreateDTO;
import com.andrevsc.keybook.dto.tabela.TabelaResponseDTO;
import com.andrevsc.keybook.model.Tabela;
import com.andrevsc.keybook.model.User;
import com.andrevsc.keybook.repository.TabelaRepository;
import com.andrevsc.keybook.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TabelaService {

    @Autowired
    private TabelaRepository tabelaRepository;

    @Autowired
    private UserRepository userRepository;

    public TabelaResponseDTO createTabela(TabelaCreateDTO tabelaCreateDTO, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com o id: " + userId));

        Tabela novaTabela = new Tabela();
        novaTabela.setNome(tabelaCreateDTO.nome());
        novaTabela.setUser(user);

        Tabela savedTabela = tabelaRepository.save(novaTabela);
        return new TabelaResponseDTO(savedTabela);
    }

    public List<TabelaResponseDTO> getTabelasByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new EntityNotFoundException("Usuário não encontrado com o id: " + userId);
        }
        return tabelaRepository.findByUserId(userId)
                .stream()
                .map(TabelaResponseDTO::new)
                .collect(Collectors.toList());
    }

    public TabelaResponseDTO getTabelaById(Long tabelaId) {
        Tabela tabela = tabelaRepository.findById(tabelaId)
                .orElseThrow(() -> new EntityNotFoundException("Tabela não encontrada com o id: " + tabelaId));
        return new TabelaResponseDTO(tabela);
    }

    @Transactional
    public TabelaResponseDTO updateTabela(Long tabelaId, TabelaCreateDTO tabelaCreateDTO) {
        Tabela tabelaExistente = tabelaRepository.findById(tabelaId)
            .orElseThrow(() -> new EntityNotFoundException("Tabela não encontrada com o id: " + tabelaId));

        tabelaExistente.setNome(tabelaCreateDTO.nome());
        
        Tabela updatedTabela = tabelaRepository.save(tabelaExistente);
        return new TabelaResponseDTO(updatedTabela);
    }

    public void deleteTabela(Long tabelaId) {
        if (!tabelaRepository.existsById(tabelaId)) {
            throw new EntityNotFoundException("Tabela não encontrada com o id: " + tabelaId);
        }
        tabelaRepository.deleteById(tabelaId);
    }
}