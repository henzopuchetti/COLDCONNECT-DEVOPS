package com.fiap.ColdConnect.mapper;

import com.fiap.ColdConnect.dto.RespostaSolicitacaoDTO;
import com.fiap.ColdConnect.model.RespostaSolicitacao;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RespostaSolicitacaoMapper {

    RespostaSolicitacaoDTO toDTO(RespostaSolicitacao entity);

    @Mapping(target = "solicitacao", ignore = true)
    RespostaSolicitacao toEntity(RespostaSolicitacaoDTO dto);

    @AfterMapping
    default void mapSolicitacaoId(@MappingTarget RespostaSolicitacaoDTO dto, RespostaSolicitacao entity) {
        if (entity.getSolicitacao() != null) {
            dto.setSolicitacaoId(entity.getSolicitacao().getId());
        }
    }
}
