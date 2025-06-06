package com.fiap.ColdConnect.model.filter;

import com.fiap.ColdConnect.model.enums.StatusSolicitacao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SolicitacaoRecursoFilter {
    private String tipoRecurso;
    private StatusSolicitacao status;
    private Long abrigoId;
    private Long alertaId;
}
