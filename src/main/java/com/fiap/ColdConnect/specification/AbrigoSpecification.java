package com.fiap.ColdConnect.specification;

import com.fiap.ColdConnect.model.Abrigo;
import com.fiap.ColdConnect.model.filter.AbrigoFilter;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class AbrigoSpecification {

    public static Specification<Abrigo> comFiltros(AbrigoFilter filtro) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filtro.getNome() != null && !filtro.getNome().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("nome")), "%" + filtro.getNome().toLowerCase() + "%"));
            }

            if (filtro.getLocalizacao() != null && !filtro.getLocalizacao().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("localizacao")), "%" + filtro.getLocalizacao().toLowerCase() + "%"));
            }

            if (filtro.getCapacidadeMinima() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("capacidadeAtual"), filtro.getCapacidadeMinima()));
            }

            if (filtro.getCapacidadeMaxima() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("capacidadeAtual"), filtro.getCapacidadeMaxima()));
            }

            if (filtro.getStatus() != null) {
                predicates.add(cb.equal(root.get("status"), filtro.getStatus()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
