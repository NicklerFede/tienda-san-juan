package com.tienda.sanjuan.Enums;

public enum Ninios {
    T4("4"), T6("6"), T8("8"), T10("10"), T12("12"), T14("14"), T16("16"), T18("18"), T20("20");

    private String nombreVista;

    private Ninios(String nombreVista) {
        this.nombreVista = nombreVista;
    }
    
    public String getNombreVista() {
        return nombreVista;
    }
}
