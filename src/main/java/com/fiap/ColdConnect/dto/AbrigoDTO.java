package com.fiap.ColdConnect.dto;

import com.fiap.ColdConnect.model.enums.StatusAbrigo;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class AbrigoDTO {

    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String localizacao;

    @NotNull
    private Integer capacidadeTotal;

    @NotNull
    private Integer capacidadeAtual;

    @NotNull
    private StatusAbrigo status;  // <<<<< alterado de Boolean para enum
}
