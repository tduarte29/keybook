package com.andrevsc.keybook.controller;

import com.andrevsc.keybook.dto.propriedade.PropriedadeCreateDTO;
import com.andrevsc.keybook.dto.propriedade.PropriedadeResponseDTO;
import com.andrevsc.keybook.service.PropriedadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class PropriedadeController {

    @Autowired
    private PropriedadeService propriedadeService;

    // ALTERADO: Usa os DTOs
    @PostMapping("/items/{itemId}/properties")
    public ResponseEntity<PropriedadeResponseDTO> createPropriedade(@PathVariable Long itemId, @RequestBody PropriedadeCreateDTO dto) {
        PropriedadeResponseDTO createdPropriedade = propriedadeService.createPropriedade(dto, itemId);
        return ResponseEntity.status(201).body(createdPropriedade);
    }

    // ALTERADO: Retorna lista de DTOs
    @GetMapping("/items/{itemId}/properties")
    public ResponseEntity<List<PropriedadeResponseDTO>> getPropriedadesByItemId(@PathVariable Long itemId) {
        List<PropriedadeResponseDTO> propriedades = propriedadeService.getPropriedadesByItemId(itemId);
        return ResponseEntity.ok(propriedades);
    }

    // ALTERADO: Usa DTO no update
    @PutMapping("/properties/{propriedadeId}")
    public ResponseEntity<PropriedadeResponseDTO> updatePropriedade(@PathVariable Long propriedadeId, @RequestBody PropriedadeCreateDTO dto) {
        PropriedadeResponseDTO updatedPropriedade = propriedadeService.updatePropriedade(propriedadeId, dto);
        return ResponseEntity.ok(updatedPropriedade);
    }

    @DeleteMapping("/properties/{propriedadeId}")
    public ResponseEntity<Void> deletePropriedade(@PathVariable Long propriedadeId) {
        propriedadeService.deletePropriedade(propriedadeId);
        return ResponseEntity.noContent().build();
    }
}