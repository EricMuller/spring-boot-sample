package com.emu.apps.qcm.services;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class UniqueDto {


    @JsonProperty("uuid")
    private  String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
