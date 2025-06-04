package com.fiap.ColdConnect.model;

import com.fiap.ColdConnect.model.enums.GrauAlerta;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlertaFrio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private GrauAlerta grau;

    private String regiao;

    @CreationTimestamp
    private LocalDateTime dataHora;
}