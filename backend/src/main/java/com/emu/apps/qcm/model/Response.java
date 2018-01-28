package com.emu.apps.qcm.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Response extends BasicEntity {

    @Column(name = "RESPONSE", nullable = false, length = 32672)
    private String response;

    @Column(name = "NUMBER")
    private Long number;

    @Column(name = "NUMBER")

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Choice> choices;

    public Response() {
        //
    }

    public Response(Type type, String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return String.format("Response[id=%d,  response='%s']", getId(), response);
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }
}
