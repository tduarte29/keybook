package com.andrevsc.keybook.model;

import java.time.LocalDateTime;
import java.util.Objects;

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
@Table(name = "ITEM")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Item")
    private Long id;

    @Column(name = "Nome_Item", nullable = false)
    private String nome;

    @CreationTimestamp
    @Column(name = "Data_Criacao_Item", updatable = false)
    private LocalDateTime dataCriacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Tabela_FK", nullable = false)
    private Tabela tabela;

    // @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    // private List<Propriedade> propriedades;

    // @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    // private List<Comentario> comentarios;

    public Item() {
    }

    public Item(Long id, String nome, LocalDateTime dataCriacao, Tabela tabela) {
        this.id = id;
        this.nome = nome;
        this.dataCriacao = dataCriacao;
        this.tabela = tabela;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Tabela getTabela() {
        return tabela;
    }

    public void setTabela(Tabela tabela) {
        this.tabela = tabela;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", dataCriacao=" + dataCriacao +
                ", tabelaId=" + (tabela != null ? tabela.getId() : "null") +
                '}';
    }
}