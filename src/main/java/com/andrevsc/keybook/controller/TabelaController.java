package com.andrevsc.keybook.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.andrevsc.keybook.dto.tabela.TabelaCreateDTO;
import com.andrevsc.keybook.dto.tabela.TabelaResponseDTO;
import com.andrevsc.keybook.model.User;
import com.andrevsc.keybook.service.TabelaService;

@RestController
@RequestMapping
public class TabelaController {

    @Autowired
    private TabelaService tabelaService;

    // ALTERADO: Recebe TabelaCreateDTO e retorna TabelaResponseDTO
    @PostMapping("/users/{userId}/tables")
    public ResponseEntity<TabelaResponseDTO> createTabela(
            @PathVariable Long userId,
            @RequestBody TabelaCreateDTO tabelaCreateDTO,
            UriComponentsBuilder uriBuilder) {

        // Recupera o usuário autenticado do contexto de segurança
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User authenticatedUser = (User) authentication.getPrincipal();

        // Garante que o usuário só pode criar tabela para si mesmo
        if (!authenticatedUser.getId().equals(userId)) {
            return ResponseEntity.status(403).build(); // Forbidden
        }

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