package com.tienda.sanjuan.repositorios;

import com.tienda.sanjuan.entidades.Detalle;
import com.tienda.sanjuan.entidades.Orden;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleRepositorio extends JpaRepository<Detalle, String> {
    
    @Query("SELECT d from Detalle d where d.orden = :orden")
    public ArrayList<Detalle> buscarPorCompra(@Param("orden") Orden orden);
}
