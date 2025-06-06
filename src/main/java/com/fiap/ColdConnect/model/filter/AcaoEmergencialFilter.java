package com.fiap.ColdConnect.model.filter;

import com.fiap.ColdConnect.model.enums.StatusAcao;
import lombok.Data;

@Data
public class AcaoEmergencialFilter {
    private String tipo;
    private StatusAcao status;
    private Long alertaFrioId;
}
