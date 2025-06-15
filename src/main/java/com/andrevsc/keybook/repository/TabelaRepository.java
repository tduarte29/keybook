package com.andrevsc.keybook.repository;

import com.andrevsc.keybook.model.Tabela;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TabelaRepository extends JpaRepository<Tabela, Long> {
    List<Tabela> findByUserId(Long userId);
}