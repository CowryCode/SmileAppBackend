package com.cowrycode.smileapp.mapper.easyfeed;

import com.cowrycode.smileapp.domains.easyfeed.EasyFeedUserProfileDAO;
import com.cowrycode.smileapp.models.easyfeed.EasyFeedUserprofileDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EasyFeedUserProfileMapper {
    EasyFeedUserProfileMapper INSTANCE = Mappers.getMapper(EasyFeedUserProfileMapper.class);

    EasyFeedUserprofileDTO DAOtoDTO(EasyFeedUserProfileDAO userProfiledao);
    EasyFeedUserProfileDAO DTOtoDAO(EasyFeedUserprofileDTO easyFeedUserprofileDTO);
}
