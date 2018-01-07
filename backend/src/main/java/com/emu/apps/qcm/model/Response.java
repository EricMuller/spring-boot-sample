package com.emu.apps.qcm.model;

import com.emu.apps.qcm.model.converters.BooleanTFConverter;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Response {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "response", nullable = false, length = 32672)
    private String response;

    @Version
    private Long  version;

    @Column(name = "CREATED_DATE")
    private Date date;

    @Column(name = "number")
    private Long number;

    @Convert(converter=BooleanTFConverter.class)
    private Boolean isTrue;

    public Response() {
        //
    }

    public Response(String response, Boolean isTrue, Date date) {
        this.response = response;
        this.isTrue = isTrue;
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

    public Boolean getTrue() {
        return isTrue;
    }

    public void setTrue(Boolean aTrue) {
        isTrue = aTrue;
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
}
