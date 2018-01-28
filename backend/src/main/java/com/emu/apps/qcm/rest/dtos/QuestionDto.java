package com.emu.apps.qcm.rest.dtos;

import com.emu.apps.qcm.model.Type;
import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 * Created by eric on 05/06/2017.
 */
@ApiModel(value = "Question")
public class QuestionDto extends EntityDto {

    private Long number;

    private String question;

    private List<ResponseDto> responses;

    private Type type;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<ResponseDto> getResponses() {
        return responses;
    }

    public void setResponses(List<ResponseDto> responses) {
        this.responses = responses;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
