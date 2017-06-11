package com.emu.apps.sample.services.mappers;

import com.emu.apps.sample.model.Question;
import com.emu.apps.sample.services.dtos.FileQuestionJson;
import com.emu.apps.sample.services.dtos.QuestionDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface FileQuestionMapper {
    Question dtoToModel(FileQuestionJson fileQuestionJson );
}