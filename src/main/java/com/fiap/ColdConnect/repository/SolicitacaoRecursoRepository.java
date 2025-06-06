package com.fiap.ColdConnect.repository;

import com.fiap.ColdConnect.model.SolicitacaoRecurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SolicitacaoRecursoRepository extends JpaRepository<SolicitacaoRecurso, Long>, JpaSpecificationExecutor<SolicitacaoRecurso> {
}
