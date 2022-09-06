package com.cowrycode.smileapp.mapper.featuresmood;

import com.cowrycode.smileapp.domains.featuresmood.SmileGramMoodEntity;
import com.cowrycode.smileapp.models.featuresmood.SmileGramMoodDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SmileGramMoodMapper {
    SmileGramMoodMapper INSTANCE = Mappers.getMapper(SmileGramMoodMapper.class);

    SmileGramMoodDTO EntityToDTO(SmileGramMoodEntity smileGramMoodEntity);

    SmileGramMoodEntity DTOtoEntity(SmileGramMoodDTO smileGramMoodDTO);
}
