package com.emu.apps.qcm.services.dtos;

import java.util.List;

/**
 * Created by eric on 05/06/2017.
 */
public class QuestionDto {

    private Long id;

    private String question;

    private CategoryDto category;

    private List<ResponseDto> responses;

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

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    public List<ResponseDto> getResponses() {
        return responses;
    }

    public void setResponses(List<ResponseDto> responses) {
        this.responses = responses;
    }
}