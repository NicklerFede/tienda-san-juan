package com.tienda.sanjuan.Enums;

public enum Plazas {
    P1("1 Plaza"),P1_5("1 1/2 Plazas"),P2("2 Plazas"), P2_5("2 1/2 Plazas"),P3("Queen"),P3_5("King");
        
        private String nombreVista;
    
        private Plazas(String nombreVista) {
            this.nombreVista = nombreVista;
        }
        
        public String getNombreVista() {
            return nombreVista;
        } 
}
