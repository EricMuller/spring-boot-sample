package com.emu.apps.qcm.services.dtos;

import com.fasterxml.jackson.annotation.*;
import io.swagger.annotations.*;

/**
 * Created by eric on 05/06/2017.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "Response")
public class ResponseDto {

    private Long id;

    private String response;

    private Boolean isTrue;

    private Long version;

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

    public Boolean getTrue() {
        return isTrue;
    }

    public void setTrue(Boolean aTrue) {
        isTrue = aTrue;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
