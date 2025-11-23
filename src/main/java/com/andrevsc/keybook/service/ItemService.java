package com.andrevsc.keybook.service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andrevsc.keybook.dto.item.ItemCreateDTO;
import com.andrevsc.keybook.dto.item.ItemDetailResponseDTO;
import com.andrevsc.keybook.dto.item.ItemResponseDTO;
import com.andrevsc.keybook.dto.item.ItemUpdateRequestDTO;
import com.andrevsc.keybook.model.Item;
import com.andrevsc.keybook.model.Tabela;
import com.andrevsc.keybook.model.enums.TipoEvento;
import com.andrevsc.keybook.repository.ItemRepository;
import com.andrevsc.keybook.repository.TabelaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private HistoricoService historicoService;

    @Autowired
    private TabelaRepository tabelaRepository;

    @Transactional
    public ItemDetailResponseDTO updateItemWithDetails(Long itemId, ItemUpdateRequestDTO dto) {
        Item itemExistente = itemRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException("Item não encontrado com o id: " + itemId));

        updateItemFields(itemExistente, dto);

        Item itemAtualizado = itemRepository.save(itemExistente);
        return new ItemDetailResponseDTO(itemAtualizado);
    }

    public ItemDetailResponseDTO getItemWithDetails(Long itemId) {
    Item item = itemRepository.findById(itemId)
            .orElseThrow(() -> new EntityNotFoundException("Item não encontrado com o id: " + itemId));
    return new ItemDetailResponseDTO(item);
}

    @Transactional
    public ItemResponseDTO createItem(ItemCreateDTO itemCreateDTO, Long tabelaId) {
        Tabela tabela = tabelaRepository.findById(tabelaId)
                .orElseThrow(() -> new EntityNotFoundException("Tabela não encontrada com o id: " + tabelaId));

        Item novoItem = new Item();
        novoItem.setNome(itemCreateDTO.nome());
        novoItem.setTabela(tabela);
        setAdditionalFields(novoItem, itemCreateDTO);

        tabela.setNumeroItems(tabela.getNumeroItems() + 1);

        Item savedItem = itemRepository.save(novoItem);
        
        historicoService.registrar(savedItem, TipoEvento.CRIACAO, "Chave criada no sistema.");
        
        return new ItemResponseDTO(savedItem);
    }

    public List<ItemResponseDTO> getItemsByTabelaId(Long tabelaId) {
        if (!tabelaRepository.existsById(tabelaId)) {
            throw new EntityNotFoundException("Tabela não encontrada com o id: " + tabelaId);
        }
        return itemRepository.findByTabelaId(tabelaId)
                .stream()
                .map(ItemResponseDTO::new)
                .collect(Collectors.toList());
    }

    public ItemResponseDTO getItemById(Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException("Item não encontrado com o id: " + itemId));
        return new ItemResponseDTO(item);
    }

    @Transactional
    public ItemResponseDTO updateItem(Long itemId, ItemUpdateRequestDTO dto) {
        Item itemExistente = itemRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException("Item não encontrado com o id: " + itemId));

        updateItemFields(itemExistente, dto);

        Item updatedItem = itemRepository.save(itemExistente);
        
        historicoService.registrar(updatedItem, TipoEvento.EDICAO, "Informações da chave atualizadas.");
        
        return new ItemResponseDTO(updatedItem);
    }

    @Transactional
    public void deleteItem(Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException("Item não encontrado"));

        String nomeBackup = item.getNome(); // Salva o nome para o histórico

        // 1. PRIMEIRO: Remove o vínculo do histórico (Isso resolve o erro SQL)
        historicoService.desvincularHistoricoDoItem(itemId);

        // 2. SEGUNDO: Deleta o item (Agora o banco vai deixar)
        itemRepository.delete(item);

        // 3. TERCEIRO: Registra que foi deletado
        historicoService.registrarDelecao(nomeBackup, "Chave removida permanentemente.");
    }

    private void updateItemFields(Item item, ItemUpdateRequestDTO dto) {
        item.setNome(dto.nome());
        item.setTransponder(dto.transponder());
        item.setTipoServico(dto.tipoServico());
        item.setAnoVeiculo(dto.anoVeiculo());
        item.setValorCobrado(dto.valorCobrado());
        item.setMarcaVeiculo(dto.marcaVeiculo());
        item.setModeloVeiculo(dto.modeloVeiculo());
        item.setTipoChave(dto.tipoChave());
        item.setMaquina(dto.maquina());
        item.setFornecedor(dto.fornecedor());
        item.setDataConstrucao(dto.dataConstrucao());
        item.setCorteMecanico(dto.corteMecanico());
        item.setSistemaImobilizador(dto.sistemaImobilizador());
        item.setObservacoes(dto.observacoes());
    }

    private void setAdditionalFields(Item item, ItemCreateDTO dto) {
        item.setTransponder(dto.transponder());
        item.setTipoServico(dto.tipoServico());
        item.setAnoVeiculo(dto.anoVeiculo());
        item.setValorCobrado(dto.valorCobrado());
        item.setMarcaVeiculo(dto.marcaVeiculo());
        item.setModeloVeiculo(dto.modeloVeiculo());
        item.setTipoChave(dto.tipoChave());
        item.setMaquina(dto.maquina());
        item.setFornecedor(dto.fornecedor());
        item.setDataConstrucao(dto.dataConstrucao());
        item.setCorteMecanico(dto.corteMecanico());
        item.setSistemaImobilizador(dto.sistemaImobilizador());
        item.setObservacoes(dto.observacoes());
    }

    public List<String> getFieldSuggestions(String fieldName, String query) {
        if (query == null || query.trim().isEmpty()) {
            return Collections.emptyList();
        }
        
        String searchQuery = query.toLowerCase();
        System.out.println("🔍 Buscando sugestões para '" + fieldName + "' com termo: '" + searchQuery + "'");
        
        List<String> results = switch (fieldName) {
            case "marcaVeiculo" -> itemRepository.findDistinctMarcaVeiculo(searchQuery);
            case "modeloVeiculo" -> itemRepository.findDistinctModeloVeiculo(searchQuery);
            case "tipoChave" -> itemRepository.findDistinctTipoChave(searchQuery);
            case "fornecedor" -> itemRepository.findDistinctFornecedor(searchQuery);
            case "tipoServico" -> itemRepository.findDistinctTipoServico(searchQuery);
            case "transponder" -> itemRepository.findDistinctTransponder(searchQuery);
            default -> throw new IllegalArgumentException("Campo inválido para sugestões: " + fieldName);
        };
        
        results = results.stream()
            .filter(Objects::nonNull)
            .filter(s -> !s.isEmpty())
            .collect(Collectors.toList());
        
        System.out.println("✅ " + results.size() + " sugestões encontradas para '" + fieldName + "': " + results);
        return results;
    }

}