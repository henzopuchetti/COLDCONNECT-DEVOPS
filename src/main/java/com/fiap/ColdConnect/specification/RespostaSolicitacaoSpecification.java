package com.fiap.ColdConnect.specification;

import com.fiap.ColdConnect.model.RespostaSolicitacao;
import com.fiap.ColdConnect.model.filter.RespostaSolicitacaoFilter;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class RespostaSolicitacaoSpecification {

    public static Specification<RespostaSolicitacao> comFiltros(RespostaSolicitacaoFilter filtro) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filtro.getSolicitacaoId() != null) {
                predicates.add(builder.equal(root.get("solicitacao").get("id"), filtro.getSolicitacaoId()));
            }

            if (filtro.getAtendidoPor() != null && !filtro.getAtendidoPor().isBlank()) {
                predicates.add(builder.like(builder.lower(root.get("atendidoPor")), "%" + filtro.getAtendidoPor().toLowerCase() + "%"));
            }

            if (filtro.getStatus() != null) {
                predicates.add(builder.equal(root.get("status"), filtro.getStatus()));
            }

            if (filtro.getNovoStatus() != null) {
                predicates.add(builder.equal(root.get("novoStatus"), filtro.getNovoStatus()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
