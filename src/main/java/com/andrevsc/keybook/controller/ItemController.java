package com.andrevsc.keybook.controller;

import com.andrevsc.keybook.dto.item.ItemCreateDTO;
import com.andrevsc.keybook.dto.item.ItemDetailResponseDTO;
import com.andrevsc.keybook.dto.item.ItemResponseDTO;
import com.andrevsc.keybook.dto.item.ItemUpdateRequestDTO;
import com.andrevsc.keybook.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping
public class ItemController {

    @Autowired
    private ItemService itemService;

    // Adicione este novo endpoint na classe ItemController
    // Add this new endpoint to the ItemController class

    @PutMapping("/items/{itemId}/details")
    public ResponseEntity<ItemDetailResponseDTO> updateItemWithDetails(@PathVariable Long itemId,
            @RequestBody ItemUpdateRequestDTO dto) {
        ItemDetailResponseDTO updatedItem = itemService.updateItemWithDetails(itemId, dto);
        return ResponseEntity.ok(updatedItem);
    }

    // Adicione este novo endpoint na classe ItemController
    // Add this new endpoint to the ItemController class

    @GetMapping("/items/{itemId}/details")
    public ResponseEntity<ItemDetailResponseDTO> getItemWithDetails(@PathVariable Long itemId) {
        ItemDetailResponseDTO itemDetails = itemService.getItemWithDetails(itemId);
        return ResponseEntity.ok(itemDetails);
    }

    // ALTERADO: Usa os DTOs de Item
    @PostMapping("/tables/{tabelaId}/items")
    public ResponseEntity<ItemResponseDTO> createItem(@PathVariable Long tabelaId,
            @RequestBody ItemCreateDTO itemCreateDTO, UriComponentsBuilder uriBuilder) {
        ItemResponseDTO createdItem = itemService.createItem(itemCreateDTO, tabelaId);
        URI uri = uriBuilder.path("/items/{id}").buildAndExpand(createdItem.id()).toUri();
        return ResponseEntity.created(uri).body(createdItem);
    }

    // ALTERADO: Retorna lista de DTOs
    @GetMapping("/tables/{tabelaId}/items")
    public ResponseEntity<List<ItemResponseDTO>> getItemsByTabelaId(@PathVariable Long tabelaId) {
        List<ItemResponseDTO> items = itemService.getItemsByTabelaId(tabelaId);
        return ResponseEntity.ok(items);
    }

    // ALTERADO: Retorna um DTO
    @GetMapping("/items/{itemId}")
    public ResponseEntity<ItemResponseDTO> getItemById(@PathVariable Long itemId) {
        ItemResponseDTO item = itemService.getItemById(itemId);
        return ResponseEntity.ok(item);
    }

    // ALTERADO: Usa DTO no update
    @PutMapping("/items/{itemId}")
    public ResponseEntity<ItemResponseDTO> updateItem(@PathVariable Long itemId,
            @RequestBody ItemCreateDTO itemCreateDTO) {
        ItemResponseDTO updatedItem = itemService.updateItem(itemId, itemCreateDTO);
        return ResponseEntity.ok(updatedItem);
    }

    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long itemId) {
        itemService.deleteItem(itemId);
        return ResponseEntity.noContent().build();
    }
}