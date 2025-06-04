package com.fiap.ColdConnect.mapper;

import com.fiap.ColdConnect.dto.SolicitacaoRecursoDTO;
import com.fiap.ColdConnect.model.Abrigo;
import com.fiap.ColdConnect.model.AlertaFrio;
import com.fiap.ColdConnect.model.SolicitacaoRecurso;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface SolicitacaoRecursoMapper {

    SolicitacaoRecursoDTO toDTO(SolicitacaoRecurso entity);

    @Mapping(target = "abrigo", ignore = true)
    @Mapping(target = "alerta", ignore = true)
    SolicitacaoRecurso toEntity(SolicitacaoRecursoDTO dto);

    default Long mapAbrigoToId(Abrigo abrigo) {
        return (abrigo != null) ? abrigo.getId() : null;
    }

    default Long mapAlertaToId(AlertaFrio alerta) {
        return (alerta != null) ? alerta.getId() : null;
    }

    @AfterMapping
    default void mapAbrigoAlertaIds(@MappingTarget SolicitacaoRecursoDTO dto, SolicitacaoRecurso entity) {
        dto.setAbrigoId(mapAbrigoToId(entity.getAbrigo()));
        dto.setAlertaId(mapAlertaToId(entity.getAlerta()));
    }
}
