package com.fiap.ColdConnect.dto;

import com.fiap.ColdConnect.model.enums.StatusSolicitacao;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class SolicitacaoRecursoDTO {

    private Long id;

    @NotBlank
    private String tipoRecurso;

    @NotNull
    private Integer quantidade;

    private StatusSolicitacao status;

    private Long abrigoId;

    private Long alertaId;
}
