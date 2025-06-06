package com.fiap.ColdConnect.model.filter;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class LeituraTemperaturaFilter {

    private Double temperaturaMin;
    private Double temperaturaMax;
    private Double latitude;
    private Double longitude;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dataHoraInicio;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dataHoraFim;
}