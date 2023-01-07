package com.cowrycode.smileapp.mapper;

import com.cowrycode.smileapp.domains.QuestionnaireBMIScaleEntity;
import com.cowrycode.smileapp.models.QuestionnaireBMIScaleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface QuestionnaireBMIScaleMapper {
    QuestionnaireBMIScaleMapper INSTANCE = Mappers.getMapper(QuestionnaireBMIScaleMapper.class);

    QuestionnaireBMIScaleDTO entityToDTO(QuestionnaireBMIScaleEntity questionnaireBMIScaleEntity);
    QuestionnaireBMIScaleEntity dtoToEntity(QuestionnaireBMIScaleDTO questionnaireBMIScaleDTO);
}
