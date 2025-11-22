package com.andrevsc.keybook.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.andrevsc.keybook.model.enums.TipoEvento;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "HISTORICO_ATIVIDADE")
public class HistoricoAtividade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Historico")
    private Long id;

    @CreationTimestamp
    @Column(name = "Data_Hora", updatable = false)
    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    @Column(name = "Tipo_Evento")
    private TipoEvento tipoEvento;

    @Column(name = "Descricao")
    private String descricao;

    @Column(name = "Nome_Item_Snapshot")
    private String nomeItemSnapshot;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Item_FK")
    private Item item;

    public HistoricoAtividade() {}

    public HistoricoAtividade(Item item, TipoEvento tipo, String descricao) {
        this.item = item;
        this.nomeItemSnapshot = (item != null) ? item.getNome() : "Item Removido";
        this.tipoEvento = tipo;
        this.descricao = descricao;
    }
    
    // Construtor especial para deleção (quando não temos mais o objeto Item persistido ou queremos desvincular)
    public HistoricoAtividade(String nomeSnapshot, TipoEvento tipo, String descricao) {
        this.item = null;
        this.nomeItemSnapshot = nomeSnapshot;
        this.tipoEvento = tipo;
        this.descricao = descricao;
    }

    // Getters (Gere os Getters padrão aqui ou use Lombok)
    public Long getId() { return id; }
    public LocalDateTime getDataHora() { return dataHora; }
    public TipoEvento getTipoEvento() { return tipoEvento; }
    public String getDescricao() { return descricao; }
    public String getNomeItemSnapshot() { return nomeItemSnapshot; }
    public Item getItem() { return item; }
}