package com.fiap.ColdConnect.mapper;

import com.fiap.ColdConnect.dto.AbrigoDTO;
import com.fiap.ColdConnect.model.Abrigo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AbrigoMapper {

    AbrigoMapper INSTANCE = Mappers.getMapper(AbrigoMapper.class);

    AbrigoDTO toDTO(Abrigo entity);
    Abrigo toEntity(AbrigoDTO dto);
}
