package com.cowrycode.smileapp.mapper.easyfeed;

import com.cowrycode.smileapp.domains.EmpathyRequestEntity;
import com.cowrycode.smileapp.domains.easyfeed.BreastMilkData;
import com.cowrycode.smileapp.models.EmpathyRequestDTO;
import com.cowrycode.smileapp.models.easyfeed.BreastMilkDataDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BreastMilkDataMapper {
    BreastMilkDataMapper INSTANCE = Mappers.getMapper(BreastMilkDataMapper.class);
    BreastMilkDataDTO entityToDTO(BreastMilkData breastMilkData);
    BreastMilkData DTOtoEntity(BreastMilkDataDTO breastMilkDataDTO);
}
