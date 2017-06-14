package com.emu.apps.sample.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by eric on 05/06/2017.
 */
@Entity
public class Question {
    /*@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUST_SEQ")
    @SequenceGenerator(sequenceName = "customer_seq", allocationSize = 1, name = "CUST_SEQ")*/

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;


    transient private Long number;

    @Column(name = "middle_name", nullable = false, length = 1024)
    private String question;

    @Column(name = "response", nullable = false, length = 32672)
    private String response;

    transient private String categorie;

    @Column(name = "CREATED_DATE")
    private Date date;

    @ManyToOne
    private Category category;

    public Question() {
    }

    public Question(String question, String response, Date date) {
        this.question = question;
        this.response = response;
        this.date = date;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
