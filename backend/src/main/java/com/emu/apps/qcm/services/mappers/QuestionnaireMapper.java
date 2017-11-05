package com.emu.apps.qcm.services.mappers;

import com.emu.apps.qcm.model.Questionnaire;
import com.emu.apps.qcm.services.dtos.QuestionnaireDto;
import org.mapstruct.*;


@Mapper(componentModel = "spring")
public interface QuestionnaireMapper {

    @Mapping(target = "questions", ignore = true)
    QuestionnaireDto modelToDto(Questionnaire fiche);

    Iterable<QuestionnaireDto> modelToDtos(Iterable<Questionnaire> questionnaires);

    /*
    Iterable<QuestionnaireDto> modelToDtosWithOutStringResponse(Iterable<Questionnaire> questionnaires);

    @IterableMapping(qualifiedByName="questionnnaireWithOutResponse")
    Iterable<QuestionnaireDto> modelToDtosWithOutStringResponse(Iterable<Questionnaire> questionnaires);

    @Named( "questionnnaireWithOutResponse" )
    @Mapping(target = "questions", qualifiedByName = "questionWithOutResponse")
    QuestionnaireDto modelToDtoWithOutStringResponse(Questionnaire fiche);
    */


}
