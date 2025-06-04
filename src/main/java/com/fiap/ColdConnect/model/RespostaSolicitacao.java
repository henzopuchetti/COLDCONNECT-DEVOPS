package com.fiap.ColdConnect.model;

import com.fiap.ColdConnect.model.enums.StatusResposta;
import com.fiap.ColdConnect.model.enums.StatusSolicitacao;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RespostaSolicitacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "solicitacao_id", nullable = false)
    private SolicitacaoRecurso solicitacao;

    @NotBlank
    private String atendidoPor;

    private String observacao;

    @Enumerated(EnumType.STRING)
    private StatusResposta status;

    @Enumerated(EnumType.STRING)
    private StatusSolicitacao novoStatus;
}
