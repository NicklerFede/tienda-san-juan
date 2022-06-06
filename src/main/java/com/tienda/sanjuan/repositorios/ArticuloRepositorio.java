package com.tienda.sanjuan.repositorios;

import com.tienda.sanjuan.entidades.Articulo;
import com.tienda.sanjuan.Enums.Seccion;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Equipo6
 */
@Repository
public interface ArticuloRepositorio extends JpaRepository<Articulo, String> {

    @Query("select a from Articulo a where a.title= :title")
    public Articulo buscarPorTitle(@Param("title") String title);

    @Query("select a from Articulo a where a.destacado= true")
    public List<Articulo> findDestacados();

    @Query("select a from Articulo a where a.categoria= :cat")
    public List<Articulo> buscarPorCategoria(@Param("cat") Seccion categoria);

    @Query("select a from Articulo a group by a.title")
    public List<Articulo> listaIndex();

}
