package com.andrevsc.keybook.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "TABELA")
public class Tabela {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Tabela")
    private Long id;

    @Column(name = "Nome_Tabela", nullable = false)
    private String nome;

    @CreationTimestamp
    @Column(name = "Data_Criacao", updatable = false)
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    @Column(name = "Data_Ultima_Modificacao")
    private LocalDateTime dataUltimaModificacao;
    
    @Column(name = "Numero_Items", nullable = false, columnDefinition = "INT DEFAULT 0")
    private int numeroItems;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Usuario_FK", nullable = false)
    private User user;

    @OneToMany(mappedBy = "tabela", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Item> items;
    
    public Tabela() {
    }

    public Tabela(Long id, String nome, LocalDateTime dataCriacao, LocalDateTime dataUltimaModificacao, int numeroItems, User user, List<Item> items) {
        this.id = id;
        this.nome = nome;
        this.dataCriacao = dataCriacao;
        this.dataUltimaModificacao = dataUltimaModificacao;
        this.numeroItems = numeroItems;
        this.user = user;
        this.items = items;
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

    public LocalDateTime getDataUltimaModificacao() {
        return dataUltimaModificacao;
    }

    public void setDataUltimaModificacao(LocalDateTime dataUltimaModificacao) {
        this.dataUltimaModificacao = dataUltimaModificacao;
    }

    public int getNumeroItems() {
        return numeroItems;
    }

    public void setNumeroItems(int numeroItems) {
        this.numeroItems = numeroItems;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user2) {
        this.user = user2;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tabela tabela = (Tabela) o;
        return Objects.equals(id, tabela.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Tabela{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", dataCriacao=" + dataCriacao +
                ", dataUltimaModificacao=" + dataUltimaModificacao +
                ", numeroItems=" + numeroItems +
                ", userId=" + (user != null ? user.getId() : "null") +
                '}';
    }
}