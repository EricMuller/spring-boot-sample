package com.emu.apps.qcm.model;


import com.emu.apps.qcm.model.converters.BooleanTFConverter;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by eric on 05/06/2017.
 */

@Entity
public class Question extends UniqueEntity {
    /*@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUST_SEQ")
    @SequenceGenerator(sequenceName = "customer_seq", allocationSize = 1, name = "CUST_SEQ")*/

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The database generated Question ID")
    private Long id;

    @Version
    private Long version;

    @Column(name = "CREATED_DATE")
    private Date date;

    @Convert(converter = BooleanTFConverter.class)
    private Boolean mandatory;

    @Column(name = "NUMBER", nullable = false)
    private Long number;

    @ManyToOne
    private Questionnaire questionnaire;

    @Column(name = "question", nullable = false, length = 1024)
    private String question;

    @Enumerated(EnumType.STRING)
    private Type type;

    @OneToMany(cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    private List<Response> responses;

    public Question() {
    }

    public Question(Type type,String question, Date date) {
        this.type = type;
        this.question = question;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return String.format("Question[id=%d, question='%s']", id, question);
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

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
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
