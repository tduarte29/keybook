package com.andrevsc.keybook.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.andrevsc.keybook.model.HistoricoExportacao; // Importação nova

@Repository
public interface HistoricoRepository extends JpaRepository<HistoricoExportacao, Long> {
    
    // MUDANÇA 1: Adicionei "JOIN FETCH h.item"
    // Isso obriga o banco a trazer os dados do Item junto com o histórico
    @Query("SELECT h FROM HistoricoExportacao h JOIN FETCH h.item ORDER BY h.dataExportacao DESC")
    List<HistoricoExportacao> findRecentHistory(Pageable pageable);
}