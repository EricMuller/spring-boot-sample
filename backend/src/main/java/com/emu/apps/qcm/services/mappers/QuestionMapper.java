package com.emu.apps.qcm.services.mappers;

import com.emu.apps.qcm.model.Question;
import com.emu.apps.qcm.model.Response;
import com.emu.apps.qcm.services.dtos.QuestionDto;
import com.emu.apps.qcm.services.dtos.ResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface QuestionMapper {

    Question dtoToModel(QuestionDto questionDto);

    QuestionDto modelToDto(Question question);

    Iterable<QuestionDto> modelToDtos(Iterable<Question> questions);

    @Named("responseWithOutResponse")
    @Mapping(target = "response", ignore = true)
    ResponseDto modelToDtoWithoutStringResponse(Response response);

    @Named("questionWithOutResponse")
    @Mapping(target = "responses", qualifiedByName = "responseWithOutResponse")
    QuestionDto modelToDtoWithoutStringResponse(Question question);

}