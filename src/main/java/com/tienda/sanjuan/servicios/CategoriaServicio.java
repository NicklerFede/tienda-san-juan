/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tienda.sanjuan.servicios;

import com.tienda.sanjuan.entidades.Categoria;
import com.tienda.sanjuan.repositorios.CategoriaRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carop
 */
@Service
public class CategoriaServicio{
    
    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    public Categoria guardarCategoria(Categoria categoria) throws Exception {
        if (categoria.getCategoria().isEmpty()) {
            throw new Exception("El titulo del categoria no puede estar vacio");
        }
       
        return categoriaRepositorio.save(categoria);
    }

    public Categoria modificarCategoria(Categoria categoria) throws Exception {
                if (categoria.getCategoria().isEmpty()) {
                    throw new Exception("El nuevo titulo del categoria no puede estar vacio");
                }
        return categoriaRepositorio.save(categoria);
    }

    public List<Categoria> listarCategorias() {
        return categoriaRepositorio.findAll();
    }   
    
     public Categoria buscarCategoria(String categoria) {
        return categoriaRepositorio.buscarCategoria(categoria);
    }
     
     public Categoria buscarPorId(String id) {
        return categoriaRepositorio.getById(id);
    }
}
