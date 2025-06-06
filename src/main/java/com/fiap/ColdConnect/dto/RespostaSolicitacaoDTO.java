package com.fiap.ColdConnect.dto;

import com.fiap.ColdConnect.model.enums.StatusSolicitacao;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RespostaSolicitacaoDTO {

    private Long id;

    @NotNull
    private Long solicitacaoId;

    @NotNull
    private String atendidoPor;

    private String observacao;

    private StatusSolicitacao statusRespostaAnterior;

    @NotNull
    private StatusSolicitacao novoStatus;
}
