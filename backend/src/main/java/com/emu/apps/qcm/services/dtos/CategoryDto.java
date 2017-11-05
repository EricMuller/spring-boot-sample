package com.emu.apps.qcm.services.dtos;


/**
 * Created by eric on 05/06/2017.
 */

public class CategoryDto {

    private String id;

    private String libelle;

    public CategoryDto() {
    }

    public CategoryDto(String id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
