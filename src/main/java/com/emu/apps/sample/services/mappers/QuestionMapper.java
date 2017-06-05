package com.emu.apps.sample.services.mappers;

import com.emu.apps.sample.model.Question;
import com.emu.apps.sample.services.dtos.QuestionDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface  QuestionMapper {
    QuestionDto modelToDto(Question question);

    Iterable<QuestionDto> modelToDtos(Iterable<Question> questions);
}