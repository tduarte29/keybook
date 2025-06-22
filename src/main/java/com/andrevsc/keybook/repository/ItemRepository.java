package com.andrevsc.keybook.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.andrevsc.keybook.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByTabelaId(Long tabelaId);

    @Query("SELECT i FROM Item i WHERE i.id = :itemId")
    Optional<Item> findByIdWithDetails(@Param("itemId") Long itemId);

    // Métodos atualizados para cada campo
    @Query("SELECT DISTINCT i.marcaVeiculo FROM Item i WHERE LOWER(i.marcaVeiculo) LIKE LOWER(CONCAT('%', :query, '%')) AND i.marcaVeiculo IS NOT NULL AND i.marcaVeiculo != ''")
    List<String> findDistinctMarcaVeiculo(String query);

    @Query("SELECT DISTINCT i.modeloVeiculo FROM Item i WHERE LOWER(i.modeloVeiculo) LIKE LOWER(CONCAT('%', :query, '%')) AND i.modeloVeiculo IS NOT NULL AND i.modeloVeiculo != ''")
    List<String> findDistinctModeloVeiculo(String query);

    @Query("SELECT DISTINCT i.tipoChave FROM Item i WHERE LOWER(i.tipoChave) LIKE LOWER(CONCAT('%', :query, '%')) AND i.tipoChave IS NOT NULL AND i.tipoChave != ''")
    List<String> findDistinctTipoChave(String query);

    @Query("SELECT DISTINCT i.fornecedor FROM Item i WHERE LOWER(i.fornecedor) LIKE LOWER(CONCAT('%', :query, '%')) AND i.fornecedor IS NOT NULL AND i.fornecedor != ''")
    List<String> findDistinctFornecedor(String query);

    @Query("SELECT DISTINCT i.tipoServico FROM Item i WHERE LOWER(i.tipoServico) LIKE LOWER(CONCAT('%', :query, '%')) AND i.tipoServico IS NOT NULL AND i.tipoServico != ''")
    List<String> findDistinctTipoServico(String query);

    @Query("SELECT DISTINCT i.transponder FROM Item i WHERE LOWER(i.transponder) LIKE LOWER(CONCAT('%', :query, '%')) AND i.transponder IS NOT NULL AND i.transponder != ''")
    List<String> findDistinctTransponder(String query);
}