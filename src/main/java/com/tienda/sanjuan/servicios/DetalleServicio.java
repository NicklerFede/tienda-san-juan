package com.tienda.sanjuan.servicios;
import java.util.ArrayList;
import com.tienda.sanjuan.entidades.Detalle;
import com.tienda.sanjuan.entidades.Orden;
import com.tienda.sanjuan.repositorios.DetalleRepositorio;
import com.tienda.sanjuan.repositorios.OrdenRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalleServicio {
    @Autowired
    private DetalleRepositorio detalleRepositorio;

    @Autowired
    private OrdenRepositorio ordenRepositorio;

    public Detalle guardar(Detalle detalle) {
        return detalleRepositorio.save(detalle);
    }

    public void editar() {
        
    }

    public void eliminar(String id) {
        detalleRepositorio.delete(detalleRepositorio.getById(id));
    }

    public ArrayList<Detalle> listarPorCompra(String id) {
        Orden orden = ordenRepositorio.getById(id)        ;
        return detalleRepositorio.buscarPorCompra(orden);
    }
}
