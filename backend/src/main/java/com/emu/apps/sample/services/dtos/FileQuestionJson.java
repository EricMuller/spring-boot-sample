package com.emu.apps.sample.services.dtos;

import com.emu.apps.sample.model.Category;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by eric on 05/06/2017.
 */

public class FileQuestionJson {

    private Long id;

    private Long number;

    private String question;

    private String response;

    private String categorie;

    public FileQuestionJson() {
    }

    public FileQuestionJson(String question, String response, Date date) {
        this.question = question;
        this.response = response;
    }


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

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return String.format("Question[id=%d, question='%s', response='%s']", id, question, response);
    }
}
