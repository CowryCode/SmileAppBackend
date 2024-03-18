package com.cowrycode.smileapp.mapper.easyfeed;

import com.cowrycode.smileapp.domains.easyfeed.JournalData;
import com.cowrycode.smileapp.models.easyfeed.JournalDataDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface JournalDataMapper {
    JournalDataMapper INSTANCE = Mappers.getMapper(JournalDataMapper.class);

    JournalDataDTO DAOtoDTO(JournalData journalData);
    JournalData DTOtoDAO(JournalDataDTO journalDataDTO);

}
