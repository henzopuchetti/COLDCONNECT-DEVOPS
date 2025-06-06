package com.fiap.ColdConnect.model.filter;

import com.fiap.ColdConnect.model.enums.StatusAbrigo;
import lombok.Data;

@Data
public class AbrigoFilter {

    private String nome;
    private String localizacao;
    private Integer capacidadeMinima;
    private Integer capacidadeMaxima;
    private StatusAbrigo status;
}
