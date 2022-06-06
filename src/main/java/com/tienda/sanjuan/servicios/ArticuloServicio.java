/*
Articulo change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tienda.sanjuan.servicios;

import com.tienda.sanjuan.DTOs.FiltroArticulo;
import com.tienda.sanjuan.Filters.ArticuloFilter;
import com.tienda.sanjuan.entidades.Articulo;
import com.tienda.sanjuan.entidades.Foto;
import com.tienda.sanjuan.Enums.Bebes;
import com.tienda.sanjuan.Enums.Ninios;
import com.tienda.sanjuan.Enums.Pantalones;
import com.tienda.sanjuan.Enums.Plazas;
import com.tienda.sanjuan.Enums.RopaNormal;
import com.tienda.sanjuan.Enums.Seccion;
import com.tienda.sanjuan.Enums.TipoMedida;
import com.tienda.sanjuan.repositorios.ArticuloRepositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Equipo6
 */
@Service
public class ArticuloServicio {

    @Autowired
    private ArticuloRepositorio articuloRepositorio;

    @Autowired
    private FotoServicio fotoServicio;
    
    @Autowired
    private ArticuloFilter  articuloFilter;

    public Articulo guardarArticulo(Articulo articulo, MultipartFile file) throws Exception {
        if (articulo.getTitle().isEmpty()) {
            throw new Exception("El titulo del articulo no puede estar vacio");
        }
        if(articuloRepositorio.buscarPorTitle(articulo.getTitle()) != null){
            throw new Exception("Ya existe el articulo");
        }
        if (articulo.getPrice() == 0 || articulo.getPrice() == null) {
            throw new Exception("El precio del articulo no puede ser 0 o vacio");
        }
        if (articulo.getColor() == null) {
            throw new Exception("El color del articulo no puede estar vacio");
        }
        if (articulo.getMaterial() == null) {
            throw new Exception("El material del articulo no puede estar vacio");
        }
        
        if (articulo.getStock() == 0 || articulo.getStock() == null) {
            throw new Exception("El stock del articulo no puede ser 0 o vacio");
        }
        
        if (file != null) {
            Foto foto = fotoServicio.guardar(file);            
        }

        return articuloRepositorio.save(articulo);
    }

    public Articulo modificarArticulo(Articulo articulo) throws Exception {
                if (articulo.getTitle().isEmpty()) {
                    throw new Exception("El nuevo titulo del articulo no puede estar vacio");
                }
                if (articulo.getPrice() == 0 || articulo.getPrice()== null) {
                    throw new Exception("El nuevo preciodel articulo no puede estar vacio o nulo");
                }
                if (articulo.getColor() == null) {
                    throw new Exception("El nuevo color no puede estar vacio");
                }
                if (articulo.getMaterial() == null) {
                    throw new Exception("El nuevo material del articulo no puede estar vacio");
                }
                
        return articuloRepositorio.save(articulo);
    }

    public List<Articulo> listarArticulos() {
        return articuloRepositorio.findAll();
    }

    public Articulo buscarPorTitle(String title) {
        return articuloRepositorio.buscarPorTitle(title);
    }

     public void articuloDestacado(String id) throws Error {
        Optional<Articulo> respuesta = articuloRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Articulo articulo = respuesta.get();
            articulo.setDestacado(true);
            articuloRepositorio.save(articulo);
        } else {
            throw new Error("No se encontró un articulo con ese nombre");

        }
    }
     public Articulo articuloNoDestacado(Articulo articulo) {
        articulo.setDestacado(false);
        return articuloRepositorio.save(articulo);
    }
     
     public List<Articulo> listardestacados(){
         return articuloRepositorio.findDestacados();
     }
    
    public Articulo buscarPorId(String id) {
        return articuloRepositorio.getById(id);
    }
    
    public List<Articulo> filtrarArticulos(FiltroArticulo filtroArticulo) {
        return  articuloFilter.filtrar(filtroArticulo); 
    }

    
    public void altaArticulo(String id) throws Error {
        Optional<Articulo> respuesta = articuloRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Articulo articulo = respuesta.get();
            articulo.setAlta(true);
            articuloRepositorio.save(articulo);
        } else {
            throw new Error("No se encontró un articulo con ese nombre");

        }
    }

    public Articulo darBaja(Articulo articulo) {
        articulo.setAlta(false);
        return articuloRepositorio.save(articulo);
    }
    
    public void bajaArticulo(String id) throws Error {
        Optional<Articulo> respuesta = articuloRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Articulo articulo = respuesta.get();
            articulo.setAlta(false);
            articuloRepositorio.save(articulo);
        } else {
            throw new Error("No se encontró una libro con ese nombre");
        }
    }

    public void eliminarArticulo(String id) throws Error {
        Articulo articulo = articuloRepositorio.getById(id);
        if (articulo == null) {
            throw new Error("El autor no existe en la base de datos");
        } else {
            articuloRepositorio.delete(articulo);
        }
    }
    
    public List<Articulo> buscarPorCategoria(String categoria){
        List<Articulo> articulos = new ArrayList<>();
        for (Seccion var : Seccion.values()) {
            if (var.toString().equals(categoria.toUpperCase())) {
                articulos = articuloRepositorio.buscarPorCategoria(var);                
            }
        }
        /* switch (categoria) {
            case value:
                
                break;
        
            default:
                break;
        } */
        List<Articulo> listaCorta = new ArrayList<Articulo>();

        for (Articulo articulo : articulos) {
            for (Articulo articulo2 : listaCorta) {
                if (articulo2.getTitle().isEmpty() ||  articulo2.getTitle() == null) {
                    listaCorta.add(articulo);
                }else if (!articulo2.getTitle().equals(articulo.getTitle())) {
                    listaCorta.add(articulo);
                }       
            }
        }
        return articulos;
    }


    public List<Articulo> listaIndex() {
        return articuloRepositorio.listaIndex();
    }

}
