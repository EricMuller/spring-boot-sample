package com.emu.apps.qcm.model;

public enum Type {
    TEXTE_LIBRE("Texte Libre"),
    //CHECK_BOX("check box"),
    CHECK_BOX("Case a cocher") ;

    private String libelle;

    Type(String libelle) {
        this.libelle = libelle;
    }
}
