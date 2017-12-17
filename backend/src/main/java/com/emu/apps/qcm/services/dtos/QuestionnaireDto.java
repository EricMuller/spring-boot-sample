package com.emu.apps.qcm.services.dtos;

import com.fasterxml.jackson.annotation.*;
import io.swagger.annotations.*;

import java.util.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "Questionnaire")
public class QuestionnaireDto {

    private Long id;

    private String name;

    private List<QuestionDto> questions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<QuestionDto> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDto> questions) {
        this.questions = questions;
    }
}
