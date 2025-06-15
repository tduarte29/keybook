package com.andrevsc.keybook.service;

import java.util.List;
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
import com.andrevsc.keybook.repository.ItemRepository;
import com.andrevsc.keybook.repository.TabelaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Transactional
    public ItemDetailResponseDTO updateItemWithDetails(Long itemId, ItemUpdateRequestDTO dto) {
        Item itemExistente = itemRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException("Item não encontrado com o id: " + itemId));

        itemExistente.setNome(dto.nome());

        // itemExistente.getPropriedades().clear();
        // dto.propriedades().forEach(propDTO -> {
        //     Propriedade novaPropriedade = new Propriedade();
        //     novaPropriedade.setNome(propDTO.nome());
        //     novaPropriedade.setValor(propDTO.valor());
        //     novaPropriedade.setItem(itemExistente);
        //     itemExistente.getPropriedades().add(novaPropriedade);
        // });

        // itemExistente.getComentarios().clear();
        // dto.comentarios().forEach(comentarioDTO -> {
        //     Comentario novoComentario = new Comentario();
        //     novoComentario.setTexto(comentarioDTO.texto());
        //     novoComentario.setItem(itemExistente);
        //     itemExistente.getComentarios().add(novoComentario);
        // });

        Item itemAtualizado = itemRepository.save(itemExistente);

        return new ItemDetailResponseDTO(itemAtualizado);
    }

    public ItemDetailResponseDTO getItemWithDetails(Long itemId) {
        Item item = itemRepository.findByIdWithDetails(itemId)
                .orElseThrow(() -> new EntityNotFoundException("Item não encontrado com o id: " + itemId));
        return new ItemDetailResponseDTO(item);
    }

    @Autowired
    private TabelaRepository tabelaRepository;

    @Transactional
    public ItemResponseDTO createItem(ItemCreateDTO itemCreateDTO, Long tabelaId) {
        Tabela tabela = tabelaRepository.findById(tabelaId)
                .orElseThrow(() -> new EntityNotFoundException("Tabela não encontrada com o id: " + tabelaId));

        Item novoItem = new Item();
        novoItem.setNome(itemCreateDTO.nome());
        novoItem.setTabela(tabela);

        tabela.setNumeroItems(tabela.getNumeroItems() + 1);

        Item savedItem = itemRepository.save(novoItem);
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
    public ItemResponseDTO updateItem(Long itemId, ItemCreateDTO itemCreateDTO) {
        Item itemExistente = itemRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException("Item não encontrado com o id: " + itemId));

        itemExistente.setNome(itemCreateDTO.nome());

        Item updatedItem = itemRepository.save(itemExistente);
        return new ItemResponseDTO(updatedItem);
    }

    @Transactional
    public void deleteItem(Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException("Item não encontrado com o id: " + itemId));

        Tabela tabela = item.getTabela();

        itemRepository.delete(item);

        tabela.setNumeroItems(tabela.getNumeroItems() - 1);
        tabelaRepository.save(tabela);
    }
}