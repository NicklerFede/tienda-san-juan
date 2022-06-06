package com.tienda.sanjuan.servicios;

import java.util.List;

import com.tienda.sanjuan.entidades.Articulo;
import com.tienda.sanjuan.entidades.Orden;
import com.tienda.sanjuan.entidades.Usuario;
import com.tienda.sanjuan.repositorios.ArticuloRepositorio;
import com.tienda.sanjuan.repositorios.OrdenRepositorio;
import com.tienda.sanjuan.repositorios.UsuarioRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdenServicio {

    @Autowired
    private OrdenRepositorio ordenRepositorio;

    @Autowired
    private ArticuloRepositorio articuloRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public Orden guardar(Orden orden) {
        return ordenRepositorio.save(orden);
    }

    public Orden buscarPorId(String id) {
        return ordenRepositorio.getById(id);
    }

    public void descontarStock(String id, Integer vendidos) {
        Articulo articulo = articuloRepositorio.getById(id);
        articulo.setStock(articulo.getStock() - vendidos);
        articuloRepositorio.save(articulo);
    }

    public List<Orden> buscarPorUsuario(Usuario usuario) {
        return ordenRepositorio.buscarPorUsuario(usuario);
        
    }

}
