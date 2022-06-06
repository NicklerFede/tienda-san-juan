/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tienda.sanjuan.servicios;

import com.tienda.sanjuan.entidades.Direccion;
import com.tienda.sanjuan.repositorios.DireccionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Equipo6
 */
@Service
public class DireccionServicio {
    
    @Autowired
    private DireccionRepositorio direccionRepositorio;
    
    public Direccion guardarDireccion(Direccion direccion) throws Exception{
        
        if(direccion.getPostalCode().isEmpty()){
            throw new Exception("El campo Codigo Postal no puede estar vacio");
        }
        
        if(direccion.getAddress().isEmpty()){
            throw new Exception("El campo Direccion no puede estar vacio");
        }
        
        if(direccion.getCity().isEmpty()){
            throw new Exception("El campo Ciudad no puede estar vacio");
        }
        
        if(direccion.getProvince().isEmpty()){
            throw new Exception("El campo Provincia no puede estar vacio");
        }
        
        if(direccion.getReceiver().isEmpty()){
            throw new Exception("El campo Receptor no puede estar vacio");
        }      
        
        if(direccion.getDetails().isEmpty()){
            throw new Exception("El campo Detalles no puede estar vacio");
        }
        
        return direccionRepositorio.save(direccion);
    }
    
    public void eliminarDireccion(String id) throws Exception{
        Direccion direccion = direccionRepositorio.getById(id);
        if(direccion == null){
            throw new Exception("La direccion no existe");
        }else{
            direccionRepositorio.delete(direccion);
        }
        
    }
    
    public Direccion mostrarDireccion(String id){
        
        return direccionRepositorio.getById(id);
        
    }
    
    
    
}
