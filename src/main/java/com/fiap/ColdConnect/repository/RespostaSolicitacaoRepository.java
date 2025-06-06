package com.fiap.ColdConnect.repository;

import com.fiap.ColdConnect.model.RespostaSolicitacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RespostaSolicitacaoRepository extends JpaRepository<RespostaSolicitacao, Long>,
        JpaSpecificationExecutor<RespostaSolicitacao> {
}
