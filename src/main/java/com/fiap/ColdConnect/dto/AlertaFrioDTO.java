package com.fiap.ColdConnect.dto;

import com.fiap.ColdConnect.model.enums.GrauAlerta;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class AlertaFrioDTO {

    private Long id;

    private GrauAlerta grau;

    private String regiao;

    private String dataHora; // ISO string para exibição
}
