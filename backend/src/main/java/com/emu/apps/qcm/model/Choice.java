package com.emu.apps.qcm.model;

import com.emu.apps.qcm.model.converters.BooleanTFConverter;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
public class Choice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The database generated Choice ID")
    private Long id;

    @Column(name = "MESSAGE", nullable = false, length = 1024)
    private String message;

    @Column(name = "NUMBER", nullable = false)
    private Long number;

    @Convert(converter = BooleanTFConverter.class)
    private Boolean isTrue;

    public Choice(String message, Long number, Boolean isTrue) {
        this.message = message;
        this.number = number;
        this.isTrue = isTrue;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
}
