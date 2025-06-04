package com.fiap.ColdConnect.model;

import com.fiap.ColdConnect.model.enums.StatusAcao;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AcaoEmergencial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;

    @Enumerated(EnumType.STRING)
    private StatusAcao status;

    @ManyToOne
    @JoinColumn(name = "alerta_frio_id")
    private AlertaFrio alertaFrio;

    @CreationTimestamp
    private LocalDateTime dataHora;
}