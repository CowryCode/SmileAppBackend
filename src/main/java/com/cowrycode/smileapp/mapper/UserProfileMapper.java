package com.cowrycode.smileapp.mapper;

import com.cowrycode.smileapp.domains.UserProfileEntity;
import com.cowrycode.smileapp.models.UserProfileDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserProfileMapper {
    UserProfileMapper INSTANCE = Mappers.getMapper(UserProfileMapper.class);

    UserProfileDTO EntitytoDTO(UserProfileEntity userProfileEntity);
    UserProfileEntity DTOtoEntity(UserProfileDTO userProfileDTO);
}
