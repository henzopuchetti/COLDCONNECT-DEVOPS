package com.fiap.ColdConnect.specification;

import com.fiap.ColdConnect.model.LeituraTemperatura;
import com.fiap.ColdConnect.model.filter.LeituraTemperaturaFilter;
import org.springframework.data.jpa.domain.Specification;


public class LeituraTemperaturaSpecification {

    public static Specification<LeituraTemperatura> filtrar(LeituraTemperaturaFilter filtro) {
        return (root, query, cb) -> {
            var predicates = cb.conjunction();

            if (filtro.getTemperaturaMin() != null) {
                predicates.getExpressions().add(cb.greaterThanOrEqualTo(root.get("temperatura"), filtro.getTemperaturaMin()));
            }

            if (filtro.getTemperaturaMax() != null) {
                predicates.getExpressions().add(cb.lessThanOrEqualTo(root.get("temperatura"), filtro.getTemperaturaMax()));
            }

            if (filtro.getLatitude() != null) {
                predicates.getExpressions().add(cb.equal(root.get("latitude"), filtro.getLatitude()));
            }

            if (filtro.getLongitude() != null) {
                predicates.getExpressions().add(cb.equal(root.get("longitude"), filtro.getLongitude()));
            }

            if (filtro.getDataHoraInicio() != null) {
                predicates.getExpressions().add(cb.greaterThanOrEqualTo(root.get("dataHora"), filtro.getDataHoraInicio()));
            }

            if (filtro.getDataHoraFim() != null) {
                predicates.getExpressions().add(cb.lessThanOrEqualTo(root.get("dataHora"), filtro.getDataHoraFim()));
            }

            return predicates;
        };
    }
}
