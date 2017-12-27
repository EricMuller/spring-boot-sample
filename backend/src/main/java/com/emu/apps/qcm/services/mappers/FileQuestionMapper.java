package com.emu.apps.qcm.services.mappers;

import com.emu.apps.qcm.model.*;
import com.emu.apps.qcm.services.dtos.FileQuestionDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface FileQuestionMapper {
    Question dtoToModel(FileQuestionDto fileQuestionJson );
}