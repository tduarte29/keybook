package com.andrevsc.keybook.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.andrevsc.keybook.dto.item.ItemCreateDTO;
import com.andrevsc.keybook.dto.item.ItemDetailResponseDTO;
import com.andrevsc.keybook.dto.item.ItemResponseDTO;
import com.andrevsc.keybook.dto.item.ItemUpdateRequestDTO;
import com.andrevsc.keybook.service.ItemService;

@RestController
@RequestMapping
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PutMapping("/items/{itemId}/details")
    public ResponseEntity<ItemDetailResponseDTO> updateItemWithDetails(
        @PathVariable Long itemId,
        @RequestBody ItemUpdateRequestDTO dto) {
            ItemDetailResponseDTO updatedItem = itemService.updateItemWithDetails(itemId, dto);
        return ResponseEntity.ok(updatedItem);
    }

    @GetMapping("/items/{itemId}/details")
    public ResponseEntity<ItemDetailResponseDTO> getItemWithDetails(
            @PathVariable Long itemId) {
        ItemDetailResponseDTO itemDetails = itemService.getItemWithDetails(itemId);
        return ResponseEntity.ok(itemDetails);
    }

    @PostMapping("/tables/{tabelaId}/items")
    public ResponseEntity<ItemResponseDTO> createItem(
            @PathVariable Long tabelaId,
            @RequestBody ItemCreateDTO itemCreateDTO,
            UriComponentsBuilder uriBuilder) {
        ItemResponseDTO createdItem = itemService.createItem(itemCreateDTO, tabelaId);
        URI uri = uriBuilder.path("/items/{id}").buildAndExpand(createdItem.id()).toUri();
        return ResponseEntity.created(uri).body(createdItem);
    }

    @GetMapping("/tables/{tabelaId}/items")
    public ResponseEntity<List<ItemResponseDTO>> getItemsByTabelaId(
            @PathVariable Long tabelaId) {
        List<ItemResponseDTO> items = itemService.getItemsByTabelaId(tabelaId);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/items/{itemId}")
    public ResponseEntity<ItemResponseDTO> getItemById(
            @PathVariable Long itemId) {
        ItemResponseDTO item = itemService.getItemById(itemId);
        return ResponseEntity.ok(item);
    }

    @GetMapping("/items/suggestions")
    public ResponseEntity<List<String>> getFieldSuggestions(
        @RequestParam String field,
        @RequestParam String query) {
        List<String> suggestions = itemService.getFieldSuggestions(field, query);
        return ResponseEntity.ok(suggestions);
    }

    @PutMapping("/items/{itemId}")
    public ResponseEntity<ItemResponseDTO> updateItem(
            @PathVariable Long itemId,
            @RequestBody ItemUpdateRequestDTO dto) {
        ItemResponseDTO updatedItem = itemService.updateItem(itemId, dto);
        return ResponseEntity.ok(updatedItem);
    }
    

    @DeleteMapping("/items/{itemId}")
        public ResponseEntity<Void> deleteItem(
        @PathVariable Long itemId,
        @RequestHeader("Authorization") String authHeader) {
        itemService.deleteItem(itemId);
        return ResponseEntity.noContent().build();
    }

}