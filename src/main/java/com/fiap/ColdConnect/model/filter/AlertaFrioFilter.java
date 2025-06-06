package com.fiap.ColdConnect.model.filter;

import com.fiap.ColdConnect.model.enums.GrauAlerta;
import lombok.Data;

@Data
public class AlertaFrioFilter {
    private GrauAlerta grau;
    private String regiao;
    private String dataHoraDe;
    private String dataHoraAte;
}
