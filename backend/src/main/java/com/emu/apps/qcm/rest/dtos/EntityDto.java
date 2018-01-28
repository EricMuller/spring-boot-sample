package com.emu.apps.qcm.rest.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public abstract class EntityDto {

    @ApiModelProperty(notes = "The database generated product ID")
    private Long id;

    @JsonProperty("uuid")
    private  String uuid;

    @ApiModelProperty(notes = "The database generated version record")
    @JsonProperty("version")
    private Long version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
