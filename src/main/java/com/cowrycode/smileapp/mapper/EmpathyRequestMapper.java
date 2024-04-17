package com.cowrycode.smileapp.mapper;

import com.cowrycode.smileapp.domains.EmpathyRequestEntity;
import com.cowrycode.smileapp.models.EmpathyRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmpathyRequestMapper {

    EmpathyRequestMapper INSTANCE = Mappers.getMapper(EmpathyRequestMapper.class);

    EmpathyRequestDTO entityToDTO(EmpathyRequestEntity empathyRequestEntity);
   // EmpathyRequestEntity DTOtoEntity(EmpathyRequestDTO empathyRequestDTO);
}
