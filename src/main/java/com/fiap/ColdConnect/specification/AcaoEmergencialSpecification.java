package com.fiap.ColdConnect.specification;

import com.fiap.ColdConnect.model.AcaoEmergencial;
import com.fiap.ColdConnect.model.filter.AcaoEmergencialFilter;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

public class AcaoEmergencialSpecification {

    public static Specification<AcaoEmergencial> filtroDinamico(AcaoEmergencialFilter filtro) {
        return (root, query, cb) -> {
            Predicate p = cb.conjunction();

            if (filtro.getTipo() != null && !filtro.getTipo().isEmpty()) {
                p = cb.and(p, cb.like(cb.lower(root.get("tipo")), "%" + filtro.getTipo().toLowerCase() + "%"));
            }
            if (filtro.getStatus() != null) {
                p = cb.and(p, cb.equal(root.get("status"), filtro.getStatus()));
            }
            if (filtro.getAlertaFrioId() != null) {
                // Associação ManyToOne alertaFrio
                Join<Object, Object> alertaJoin = root.join("alertaFrio");
                p = cb.and(p, cb.equal(alertaJoin.get("id"), filtro.getAlertaFrioId()));
            }

            return p;
        };
    }
}
