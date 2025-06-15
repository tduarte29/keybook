package com.andrevsc.keybook.repository;

import com.andrevsc.keybook.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByTabelaId(Long tabelaId);

    @Query("SELECT i FROM Item i LEFT JOIN FETCH i.propriedades LEFT JOIN FETCH i.comentarios WHERE i.id = :itemId")
    Optional<Item> findByIdWithDetails(@Param("itemId") Long itemId);
}