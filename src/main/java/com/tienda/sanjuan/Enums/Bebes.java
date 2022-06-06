package com.tienda.sanjuan.Enums;

public enum Bebes {
    M00("0 meses"), M03("3 meses"), M6("6 meses"), M09("9 meses"), M12("12 meses");

    private String nombreVista;

    private Bebes(String nombreVista) {
        this.nombreVista = nombreVista;
    }

    public String getNombreVista() {
        return nombreVista;
    }
}
