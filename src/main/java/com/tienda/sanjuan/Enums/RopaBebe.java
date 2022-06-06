/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tienda.sanjuan.Enums;

/**
 *
 * @author carop
 */
public enum RopaBebe {
   M0("0m"),M3("3m"),M6("6m"),M9("9m"),M12("12m"),M18("18m"),M36("36m");
   
     private RopaBebe(String text) {
        this.text = text;
    }
    
    private String text;
    
    public String getText() {
        return text;
    }
   
}
