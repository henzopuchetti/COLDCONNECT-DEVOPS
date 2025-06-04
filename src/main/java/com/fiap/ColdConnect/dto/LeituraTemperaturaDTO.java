package com.fiap.ColdConnect.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class LeituraTemperaturaDTO {

    private Long id;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    @NotNull
    private Double temperatura;

    private String dataHora; // ISO string para exibição
}
