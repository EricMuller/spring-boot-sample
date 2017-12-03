package com.emu.apps.qcm.model;

import io.swagger.annotations.*;

import javax.persistence.*;

/**
 * Created by eric on 05/06/2017.
 */
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @ApiModelProperty(notes = "The database generated Category ID")
    private Long id;

    private String libelle;

    public Category() {
    }

    public Category(String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
