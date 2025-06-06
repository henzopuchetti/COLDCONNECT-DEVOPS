package com.fiap.ColdConnect.model.filter;

import com.fiap.ColdConnect.model.enums.StatusResposta;
import com.fiap.ColdConnect.model.enums.StatusSolicitacao;
import lombok.Data;

@Data
public class RespostaSolicitacaoFilter {

    private Long solicitacaoId;
    private String atendidoPor;
    private StatusResposta status;
    private StatusSolicitacao novoStatus;
}
