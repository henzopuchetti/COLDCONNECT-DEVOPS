package com.fiap.ColdConnect.specification;

import com.fiap.ColdConnect.model.AlertaFrio;
import com.fiap.ColdConnect.model.filter.AlertaFrioFilter;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class AlertaFrioSpecification {

    public static Specification<AlertaFrio> filtroDinamico(AlertaFrioFilter filtro) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filtro.getGrau() != null) {
                predicates.add(cb.equal(root.get("grau"), filtro.getGrau()));
            }

            if (filtro.getRegiao() != null && !filtro.getRegiao().isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("regiao")), "%" + filtro.getRegiao().toLowerCase() + "%"));
            }

            try {
                if (filtro.getDataHoraDe() != null && !filtro.getDataHoraDe().isEmpty()) {
                    LocalDateTime dataDe = LocalDateTime.parse(filtro.getDataHoraDe());
                    predicates.add(cb.greaterThanOrEqualTo(root.get("dataHora"), dataDe));
                }

                if (filtro.getDataHoraAte() != null && !filtro.getDataHoraAte().isEmpty()) {
                    LocalDateTime dataAte = LocalDateTime.parse(filtro.getDataHoraAte());
                    predicates.add(cb.lessThanOrEqualTo(root.get("dataHora"), dataAte));
                }
            } catch (DateTimeParseException e) {
                // opcional: logar erro ou ignorar filtro inválido
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
