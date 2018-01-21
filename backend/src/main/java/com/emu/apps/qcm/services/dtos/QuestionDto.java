package com.emu.apps.qcm.services.dtos;

import com.emu.apps.qcm.model.Type;
import com.emu.apps.qcm.services.UniqueDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by eric on 05/06/2017.
 */
@ApiModel(value = "Question")
public class QuestionDto extends UniqueDto {

    @ApiModelProperty(notes = "The database generated product ID")
    private Long id;

    private Long version;

    private Long number;

    private String question;

    private List<ResponseDto> responses;

    private Type type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
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
