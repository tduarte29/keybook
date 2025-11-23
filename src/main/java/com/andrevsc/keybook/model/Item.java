package com.andrevsc.keybook.model;

import java.time.LocalDate;
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

    @Column(name = "Transponder")
    private String transponder;

    @Column(name = "Tipo_Servico")
    private String tipoServico;

    @Column(name = "Ano_Veiculo")
    private String anoVeiculo;

    @Column(name = "Valor_Cobrado")
    private Double valorCobrado;

    @Column(name = "Marca_Veiculo")
    private String marcaVeiculo;

    @Column(name = "Modelo_Veiculo")
    private String modeloVeiculo;

    @Column(name = "Tipo_Chave")
    private String tipoChave;

    @Column(name = "Maquina")
    private String maquina;

    @Column(name = "Fornecedor")
    private String fornecedor;

    @Column(name = "Data_Construcao")
    private LocalDate dataConstrucao;

    @Column(name = "Corte_Mecanico")
    private String corteMecanico;

    @Column(name = "Sistema_Imobilizador")
    private String sistemaImobilizador;

    @Column(name = "Observacoes", columnDefinition = "TEXT")
    private String observacoes;


    public Item() {
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

    public String getTransponder() {
        return transponder;
    }

    public void setTransponder(String transponder) {
        this.transponder = transponder;
    }

    public String getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(String tipoServico) {
        this.tipoServico = tipoServico;
    }

    public String getAnoVeiculo() {
        return anoVeiculo;
    }

    public void setAnoVeiculo(String anoVeiculo) {
        this.anoVeiculo = anoVeiculo;
    }

    public Double getValorCobrado() {
        return valorCobrado;
    }

    public void setValorCobrado(Double valorCobrado) {
        this.valorCobrado = valorCobrado;
    }

    public String getMarcaVeiculo() {
        return marcaVeiculo;
    }

    public void setMarcaVeiculo(String marcaVeiculo) {
        this.marcaVeiculo = marcaVeiculo;
    }

    public String getModeloVeiculo() {
        return modeloVeiculo;
    }

    public void setModeloVeiculo(String modeloVeiculo) {
        this.modeloVeiculo = modeloVeiculo;
    }

    public String getTipoChave() {
        return tipoChave;
    }

    public void setTipoChave(String tipoChave) {
        this.tipoChave = tipoChave;
    }

    public String getMaquina() {
        return maquina;
    }

    public void setMaquina(String maquina) {
        this.maquina = maquina;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public LocalDate getDataConstrucao() {
        return dataConstrucao;
    }

    public void setDataConstrucao(LocalDate dataConstrucao) {
        this.dataConstrucao = dataConstrucao;
    }

    public String getCorteMecanico() {
        return corteMecanico;
    }

    public void setCorteMecanico(String corteMecanico) {
        this.corteMecanico = corteMecanico;
    }

    public String getSistemaImobilizador() {
        return sistemaImobilizador;
    }

    public void setSistemaImobilizador(String sistemaImobilizador) {
        this.sistemaImobilizador = sistemaImobilizador;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
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
                ", tabela=" + (tabela != null ? tabela.getId() : "null") +
                '}';
    }
}