package com.fiap.ColdConnect.mapper;

import com.fiap.ColdConnect.dto.LeituraTemperaturaDTO;
import com.fiap.ColdConnect.model.LeituraTemperatura;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LeituraTemperaturaMapper {

    LeituraTemperaturaMapper INSTANCE = Mappers.getMapper(LeituraTemperaturaMapper.class);

    LeituraTemperaturaDTO toDTO(LeituraTemperatura entity);

    LeituraTemperatura toEntity(LeituraTemperaturaDTO dto);
}
