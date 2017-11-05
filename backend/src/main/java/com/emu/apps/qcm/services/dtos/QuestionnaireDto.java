package com.emu.apps.qcm.services.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
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
