/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tienda.sanjuan.entidades;

import com.tienda.sanjuan.Enums.Pantalones;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Equipo6
 */
@Entity
public class Medida {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @Enumerated(EnumType.STRING)
    private Pantalones sdm;
    private String medida;

    public Medida() {
    }

    public Medida(String id, Pantalones sdm, String medida) {
        this.id = id;
        this.sdm = sdm;
        this.medida = medida;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Pantalones getSdm() {
        return sdm;
    }

    public void setSdm(Pantalones sdm) {
        this.sdm = sdm;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }
    
}
