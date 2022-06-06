/*
 * To change this license header("36"), choose License Headers in Project Properties.
 * To change this template file("36"), choose Tools | Templates
 * and open the template in the editor.
 */
package com.tienda.sanjuan.Enums;

/**
 *
 * @author carop
 */
public enum Pantalones {
    T38("38"), T40("40"), T42("42"), T44("44"),
    T46("46"), T48("48"),    T50("50"),;

    private String nombreVista;

    private Pantalones(String nombreVista) {
        this.nombreVista = nombreVista;
    }

    public String getNombreVista() {
        return nombreVista;
    }
}
