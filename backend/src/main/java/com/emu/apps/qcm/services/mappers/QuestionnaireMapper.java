package com.emu.apps.qcm.services.mappers;

import com.emu.apps.qcm.model.Questionnaire;
import com.emu.apps.qcm.services.dtos.QuestionnaireDto;
import org.mapstruct.*;


@Mapper(componentModel = "spring", uses = { CategoryMapper.class })
public interface QuestionnaireMapper {

    @Mapping(target = "questions", ignore = true)
    QuestionnaireDto modelToDto(Questionnaire questionnaire);

    @Mapping(target = "questions", ignore = true)
    Questionnaire dtoToModel(QuestionnaireDto questionnaireDto);

    Iterable<QuestionnaireDto> modelToDtos(Iterable<Questionnaire> questionnaires);


}
