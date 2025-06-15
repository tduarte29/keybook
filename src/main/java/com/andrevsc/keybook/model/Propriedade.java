// package com.andrevsc.keybook.model;

// import jakarta.persistence.*;
// import java.util.Objects;

// @Entity
// @Table(name = "PROPRIEDADE")
// public class Propriedade {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     @Column(name = "ID_Propriedade")
//     private Long id;

//     @Column(name = "Nome_Propriedade", nullable = false)
//     private String nome;

//     @Column(name = "Valor_Propriedade", columnDefinition = "TEXT")
//     private String valor;

//     @ManyToOne(fetch = FetchType.LAZY)
//     @JoinColumn(name = "ID_Item_FK", nullable = false)
//     private Item item;

//     public Propriedade() {
//     }

//     public Propriedade(Long id, String nome, String valor, Item item) {
//         this.id = id;
//         this.nome = nome;
//         this.valor = valor;
//         this.item = item;
//     }

//     public Long getId() {
//         return id;
//     }

//     public void setId(Long id) {
//         this.id = id;
//     }

//     public String getNome() {
//         return nome;
//     }

//     public void setNome(String nome) {
//         this.nome = nome;
//     }

//     public String getValor() {
//         return valor;
//     }

//     public void setValor(String valor) {
//         this.valor = valor;
//     }

//     public Item getItem() {
//         return item;
//     }

//     public void setItem(Item item) {
//         this.item = item;
//     }

//     @Override
//     public boolean equals(Object o) {
//         if (this == o) return true;
//         if (o == null || getClass() != o.getClass()) return false;
//         Propriedade that = (Propriedade) o;
//         return Objects.equals(id, that.id);
//     }

//     @Override
//     public int hashCode() {
//         return Objects.hash(id);
//     }

//     @Override
//     public String toString() {
//         return "Propriedade{" +
//                 "id=" + id +
//                 ", nome='" + nome + '\'' +
//                 ", valor='" + valor + '\'' +
//                 ", itemId=" + (item != null ? item.getId() : "null") +
//                 '}';
//     }
// }