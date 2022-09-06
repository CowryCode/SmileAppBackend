package com.cowrycode.smileapp.mapper;

import com.cowrycode.smileapp.domains.MyTribeMessageEntity;
import com.cowrycode.smileapp.models.MyTribeMessageDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MyTribeMessageMapper {
    MyTribeMessageMapper INSTANCE = Mappers.getMapper(MyTribeMessageMapper.class);

    MyTribeMessageDTO EntityToDTO(MyTribeMessageEntity myTribeMessageEntity);
    MyTribeMessageEntity DTOtoEntity(MyTribeMessageDTO myTribeMessageDTO);
}
