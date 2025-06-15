package com.andrevsc.keybook.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Propriedade> propriedades;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comentario> comentarios;

    public Item() {
    }

    public Item(Long id, String nome, LocalDateTime dataCriacao, Tabela tabela, List<Propriedade> propriedades, List<Comentario> comentarios) {
        this.id = id;
        this.nome = nome;
        this.dataCriacao = dataCriacao;
        this.tabela = tabela;
        this.propriedades = propriedades;
        this.comentarios = comentarios;
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

    public List<Propriedade> getPropriedades() {
        return propriedades;
    }

    public void setPropriedades(List<Propriedade> propriedades) {
        this.propriedades = propriedades;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
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