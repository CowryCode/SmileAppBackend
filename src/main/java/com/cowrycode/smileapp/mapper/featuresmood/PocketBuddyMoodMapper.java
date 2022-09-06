package com.cowrycode.smileapp.mapper.featuresmood;

import com.cowrycode.smileapp.domains.featuresmood.PocketBuddyMoodEntity;
import com.cowrycode.smileapp.models.featuresmood.PocketBuddyMoodDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PocketBuddyMoodMapper {
    PocketBuddyMoodMapper INSTANCE = Mappers.getMapper(PocketBuddyMoodMapper.class);

    PocketBuddyMoodDTO EntityToDTO(PocketBuddyMoodEntity pocketBuddyMoodEntity);

    PocketBuddyMoodEntity DTOtoEntity(PocketBuddyMoodDTO pocketBuddyMoodDTO);
}
