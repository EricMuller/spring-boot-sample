package com.emu.apps.qcm.model;


import com.emu.apps.qcm.model.converters.BooleanTFConverter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

/**
 * Created by eric on 05/06/2017.
 */

@Entity
public class Question extends BasicEntity {


    @Convert(converter = BooleanTFConverter.class)
    private Boolean mandatory;

    @Column(name = "NUMBER", nullable = false)
    private Long number;

    @ManyToOne
    private Questionnaire questionnaire;

    @Column(name = "question", nullable = false, length = 1024)
    private String question;

    @OneToMany(cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    private List<Response> responses;

    @Enumerated(EnumType.STRING)
    private Type type;


    public Question() {
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return String.format("Question[id=%d, question='%s']", getId(), question);
    }

    public List<Response> getResponses() {
        return responses;
    }

    public void setResponses(List<Response> responses) {
        this.responses = responses;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Boolean getMandatory() {
        return mandatory;
    }

    public void setMandatory(Boolean mandatory) {
        this.mandatory = mandatory;
    }

}
