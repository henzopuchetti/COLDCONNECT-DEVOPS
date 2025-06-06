package com.fiap.ColdConnect.specification;

import com.fiap.ColdConnect.model.SolicitacaoRecurso;
import com.fiap.ColdConnect.model.filter.SolicitacaoRecursoFilter;
import org.springframework.data.jpa.domain.Specification;

public class SolicitacaoRecursoSpecification {

    public static Specification<SolicitacaoRecurso> comFiltros(SolicitacaoRecursoFilter filtro) {
        return (root, query, builder) -> {
            var predicate = builder.conjunction();

            if (filtro.getTipoRecurso() != null && !filtro.getTipoRecurso().isEmpty()) {
                predicate = builder.and(predicate,
                        builder.like(builder.lower(root.get("tipoRecurso")), "%" + filtro.getTipoRecurso().toLowerCase() + "%"));
            }

            if (filtro.getStatus() != null) {
                predicate = builder.and(predicate,
                        builder.equal(root.get("status"), filtro.getStatus()));
            }

            if (filtro.getAbrigoId() != null) {
                predicate = builder.and(predicate,
                        builder.equal(root.get("abrigo").get("id"), filtro.getAbrigoId()));
            }

            if (filtro.getAlertaId() != null) {
                predicate = builder.and(predicate,
                        builder.equal(root.get("alerta").get("id"), filtro.getAlertaId()));
            }

            return predicate;
        };
    }
}