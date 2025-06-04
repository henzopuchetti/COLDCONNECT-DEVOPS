package com.fiap.ColdConnect.mapper;

import com.fiap.ColdConnect.dto.AcaoEmergencialDTO;
import com.fiap.ColdConnect.model.AcaoEmergencial;
import com.fiap.ColdConnect.model.AlertaFrio;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AcaoEmergencialMapper {

    AcaoEmergencialMapper INSTANCE = Mappers.getMapper(AcaoEmergencialMapper.class);

    AcaoEmergencialDTO toDTO(AcaoEmergencial entity);

    AcaoEmergencial toEntity(AcaoEmergencialDTO dto);

    @AfterMapping
    default void mapAlertaFrioId(@MappingTarget AcaoEmergencialDTO dto, AcaoEmergencial entity) {
        AlertaFrio alerta = entity.getAlertaFrio();
        if (alerta != null) {
            dto.setAlertaFrioId(alerta.getId());
        }
    }
}
