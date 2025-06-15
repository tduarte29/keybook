package com.andrevsc.keybook.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "COMENTARIO")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Comentario")
    private Long id;

    @Column(name = "Texto_Comentario", nullable = false, columnDefinition = "TEXT")
    private String texto;

    @CreationTimestamp
    @Column(name = "Data_Comentario", updatable = false)
    private LocalDateTime data;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Item_FK", nullable = false)
    private Item item;

    public Comentario() {
    }

    public Comentario(Long id, String texto, LocalDateTime data, Item item) {
        this.id = id;
        this.texto = texto;
        this.data = data;
        this.item = item;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comentario that = (Comentario) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Comentario{" +
                "id=" + id +
                ", texto='" + texto + '\'' +
                ", data=" + data +
                ", itemId=" + (item != null ? item.getId() : "null") +
                '}';
    }
}