package com.fiap.ColdConnect.model;

import com.fiap.ColdConnect.model.enums.StatusAbrigo;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Abrigo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String localizacao;

    @NotNull
    @Min(1)
    private Integer capacidadeTotal;

    @NotNull
    @Min(0)
    private Integer capacidadeAtual;

    @Enumerated(EnumType.STRING)
    private StatusAbrigo status;
}
