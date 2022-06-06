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
public enum RopaNinio {
   T4("4"),T6("6"),T8("8"),T10("10"),T12("12"),T14("14"),T16("16");
   
    private RopaNinio(String text) {
        this.text = text;
    }
    
    private String text;
    
    public String getText() {
        return text;
    }
}
