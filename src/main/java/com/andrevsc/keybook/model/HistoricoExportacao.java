package com.andrevsc.keybook.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "HISTORICO_EXPORTACAO")
public class HistoricoExportacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Historico")
    private Long id;

    @CreationTimestamp
    @Column(name = "Data_Exportacao", updatable = false)
    private LocalDateTime dataExportacao;

    @Column(name = "Nome_Arquivo_Gerado")
    private String nomeArquivo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Item_FK", nullable = false)
    private Item item;

    // Construtores
    public HistoricoExportacao() {}

    public HistoricoExportacao(Item item, String nomeArquivo) {
        this.item = item;
        this.nomeArquivo = nomeArquivo;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDateTime getDataExportacao() { return dataExportacao; }
    public String getNomeArquivo() { return nomeArquivo; }
    public void setNomeArquivo(String nomeArquivo) { this.nomeArquivo = nomeArquivo; }
    public Item getItem() { return item; }
    public void setItem(Item item) { this.item = item; }
}