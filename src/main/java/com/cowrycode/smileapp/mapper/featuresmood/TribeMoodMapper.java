package com.cowrycode.smileapp.mapper.featuresmood;

import com.cowrycode.smileapp.domains.featuresmood.TribeMoodEntity;
import com.cowrycode.smileapp.models.featuresmood.TribeMoodDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TribeMoodMapper {
    TribeMoodMapper INSTANCE = Mappers.getMapper(TribeMoodMapper.class);

    TribeMoodEntity DTOtoEntity(TribeMoodDTO tribeMoodDTO);

    TribeMoodDTO EntitytoDTO(TribeMoodEntity tribeMoodEntity);
}
