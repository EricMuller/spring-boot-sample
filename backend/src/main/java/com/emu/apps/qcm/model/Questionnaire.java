package com.emu.apps.qcm.model;

import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.List;

@Entity
public class Questionnaire {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    private Long version;

    private String name;

    @OneToMany(cascade=CascadeType.ALL)
    @BatchSize(size = 20)
    private List<Question> questions;

    public Questionnaire(String name) {
        this.name = name;
    }

    public Questionnaire() {
    }

    public Questionnaire(List<Question> questionList) {
        this.questions = questionList;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
