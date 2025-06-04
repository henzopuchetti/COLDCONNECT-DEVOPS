package com.fiap.ColdConnect.mapper;

import com.fiap.ColdConnect.dto.AlertaFrioDTO;
import com.fiap.ColdConnect.model.AlertaFrio;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AlertaFrioMapper {

    AlertaFrioMapper INSTANCE = Mappers.getMapper(AlertaFrioMapper.class);

    AlertaFrioDTO toDTO(AlertaFrio entity);

    AlertaFrio toEntity(AlertaFrioDTO dto);
}
