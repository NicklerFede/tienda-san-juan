package com.tienda.sanjuan.repositorios;

import com.tienda.sanjuan.entidades.Orden;
import com.tienda.sanjuan.entidades.Usuario;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenRepositorio extends JpaRepository<Orden, String> {

    @Query("SELECT o from Orden o where o.user = :usuario")
    public List<Orden> buscarPorUsuario(@Param("usuario") Usuario usuario);
}
