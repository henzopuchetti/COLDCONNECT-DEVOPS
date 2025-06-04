package com.fiap.ColdConnect.model;

import com.fiap.ColdConnect.model.enums.StatusSolicitacao;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SolicitacaoRecurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String tipoRecurso;

    @NotNull
    @Min(1)
    private Integer quantidade;

    @Enumerated(EnumType.STRING)
    private StatusSolicitacao status;

    @ManyToOne
    @JoinColumn(name = "abrigo_id", nullable = false)
    private Abrigo abrigo;

    @ManyToOne
    @JoinColumn(name = "alerta_id", nullable = false)
    private AlertaFrio alerta;
}
