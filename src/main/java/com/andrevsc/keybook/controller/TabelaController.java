package com.andrevsc.keybook.controller;

import com.andrevsc.keybook.dto.tabela.TabelaCreateDTO;
import com.andrevsc.keybook.dto.tabela.TabelaResponseDTO;
import com.andrevsc.keybook.service.TabelaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping
public class TabelaController {

    @Autowired
    private TabelaService tabelaService;

    // ALTERADO: Recebe TabelaCreateDTO e retorna TabelaResponseDTO
    @PostMapping("/users/{userId}/tables")
    public ResponseEntity<TabelaResponseDTO> createTabela(@PathVariable Long userId, @RequestBody TabelaCreateDTO tabelaCreateDTO, UriComponentsBuilder uriBuilder) {
        TabelaResponseDTO createdTabela = tabelaService.createTabela(tabelaCreateDTO, userId);
        URI uri = uriBuilder.path("/tables/{id}").buildAndExpand(createdTabela.id()).toUri();
        return ResponseEntity.created(uri).body(createdTabela);
    }

    // ALTERADO: Retorna lista de TabelaResponseDTO
    @GetMapping("/users/{userId}/tables")
    public ResponseEntity<List<TabelaResponseDTO>> getTabelasByUserId(@PathVariable Long userId) {
        List<TabelaResponseDTO> tabelas = tabelaService.getTabelasByUserId(userId);
        return ResponseEntity.ok(tabelas);
    }

    // ALTERADO: Retorna TabelaResponseDTO
    @GetMapping("/tables/{tabelaId}")
    public ResponseEntity<TabelaResponseDTO> getTabelaById(@PathVariable Long tabelaId) {
        TabelaResponseDTO tabela = tabelaService.getTabelaById(tabelaId);
        return ResponseEntity.ok(tabela);
    }

    // ALTERADO: Recebe TabelaCreateDTO para o update
    @PutMapping("/tables/{tabelaId}")
    public ResponseEntity<TabelaResponseDTO> updateTabela(@PathVariable Long tabelaId, @RequestBody TabelaCreateDTO tabelaCreateDTO) {
        TabelaResponseDTO updatedTabela = tabelaService.updateTabela(tabelaId, tabelaCreateDTO);
        return ResponseEntity.ok(updatedTabela);
    }

    @DeleteMapping("/tables/{tabelaId}")
    public ResponseEntity<Void> deleteTabela(@PathVariable Long tabelaId) {
        tabelaService.deleteTabela(tabelaId);
        return ResponseEntity.noContent().build();
    }
}