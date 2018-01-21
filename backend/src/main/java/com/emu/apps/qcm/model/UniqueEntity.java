package com.emu.apps.qcm.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.util.UUID;

@SuppressWarnings("serial")
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public abstract class UniqueEntity {

    @Column(unique = true, name = "uuid", nullable = false)
    protected String uuid = UUID.randomUUID().toString().toUpperCase();


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @PrePersist
    public void autofill() {
        this.setUuid(UUID.randomUUID().toString());
    }
}