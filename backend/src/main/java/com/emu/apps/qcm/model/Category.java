package com.emu.apps.qcm.model;

import javax.persistence.Entity;

/**
 * Created by eric on 05/06/2017.
 */
@Entity
//@Cacheable
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "entityCache")
//@Cache(usage= CacheConcurrencyStrategy.READ_ONLY, region="Category")
public class Category extends RefEntity {

    private String libelle;

    public Category() {
    }

    public Category(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
