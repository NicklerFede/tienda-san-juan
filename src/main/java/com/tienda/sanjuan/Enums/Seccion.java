package com.tienda.sanjuan.Enums;

public enum Seccion {
    HOMBRE("Hombre"), MUJER("Mujer"), NINIOS("Niños"), BEBES("Bebes"), COLEGIAL("Colegial"), BANIO("Baño"),
    COCINA("Cocina & Bazar"), DORMITORIO("Dormitorio"), DECORACION("Deco&Co");

    private String nombreVista;

    private Seccion(String nombreVista) {
        this.nombreVista = nombreVista;
    }

    public String getNombreVista() {
        return nombreVista;
    }
}
