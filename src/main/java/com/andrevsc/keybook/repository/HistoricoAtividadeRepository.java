package com.andrevsc.keybook.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.andrevsc.keybook.model.HistoricoAtividade;

@Repository
public interface HistoricoAtividadeRepository extends JpaRepository<HistoricoAtividade, Long> {
    
    // LEFT JOIN FETCH permite trazer o histórico mesmo se o Item for NULL (deletado)
    @Query("SELECT h FROM HistoricoAtividade h LEFT JOIN FETCH h.item ORDER BY h.dataHora DESC")
    List<HistoricoAtividade> findRecentActivity(Pageable pageable);
}