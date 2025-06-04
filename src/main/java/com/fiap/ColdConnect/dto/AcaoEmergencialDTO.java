package com.fiap.ColdConnect.dto;

import com.fiap.ColdConnect.model.enums.StatusAcao;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class AcaoEmergencialDTO {

    private Long id;

    @NotBlank
    private String tipo;

    private StatusAcao status;

    private Long alertaFrioId;
}
