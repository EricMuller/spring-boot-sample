package com.emu.apps.qcm.rest.mappers;

import com.emu.apps.qcm.model.Question;
import com.emu.apps.qcm.model.Response;
import com.emu.apps.qcm.rest.dtos.QuestionDto;
import com.emu.apps.qcm.rest.dtos.ResponseDto;
import com.emu.apps.qcm.services.projections.QuestionProjection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface QuestionMapper {

    Question dtoToModel(QuestionDto questionDto);

    QuestionDto modelToDto(Question question);

    Iterable<QuestionDto> projectionsToDtos(Iterable<QuestionProjection> questions);

    Iterable<QuestionDto> modelToDtos(Iterable<Question> questions);

    @Named("responseWithOutResponse")
    @Mapping(target = "response", ignore = true)
    ResponseDto modelToDtoWithoutStringResponse(Response response);

    @Named("questionWithOutResponse")
    @Mapping(target = "responses", qualifiedByName = "responseWithOutResponse")
    QuestionDto modelToDtoWithoutStringResponse(QuestionProjection question);



}