package com.andrevsc.keybook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.andrevsc.keybook.dto.historic.HistoricoResponseDTO;
import com.andrevsc.keybook.service.HistoricoService;

@RestController
@RequestMapping("/historico")
public class HistoricoController {

    @Autowired
    private HistoricoService historicoService;

    // Endpoint para o Flutter AVISAR que gerou um PDF
    @PostMapping("/{itemId}")
    public ResponseEntity<Void> registrarExportacao(
            @PathVariable Long itemId, 
            @RequestParam String nomeArquivo) {
        
        historicoService.registrarExportacao(itemId, nomeArquivo);
        return ResponseEntity.ok().build();
    }

    // Endpoint para o Flutter PEGAR a lista de históricos
    @GetMapping
    public ResponseEntity<List<HistoricoResponseDTO>> getHistorico() {
        List<HistoricoResponseDTO> historico = historicoService.getHistoricoRecente();
        return ResponseEntity.ok(historico);
    }
}