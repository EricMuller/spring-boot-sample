package com.emu.apps.qcm.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Response {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    private Long version;

    @Column(name = "RESPONSE", nullable = false, length = 32672)
    private String response;

    @Column(name = "CREATED_DATE")
    private Date date;

    @Column(name = "NUMBER")
    private Long number;

    @Column(name = "NUMBER")

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Choice> choices;

    public Response() {
        //
    }

    public Response(Type type, String response, Date date) {
        this.response = response;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return String.format("Response[id=%d,  response='%s']", id, response);
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }
}
