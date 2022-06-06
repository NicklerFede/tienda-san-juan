/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tienda.sanjuan.repositorios;

import com.tienda.sanjuan.entidades.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Equipo6
 */
@Repository
public interface CategoriaRepositorio extends JpaRepository<Categoria, String> {
    
     @Query("select cat from Categoria cat where cat.categoria= :categoria")
    public Categoria buscarCategoria(@Param("categoria") String categoria);
}
