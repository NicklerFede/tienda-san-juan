package com.tienda.sanjuan.Filters;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.tienda.sanjuan.DTOs.FiltroArticulo;
import com.tienda.sanjuan.entidades.Articulo;

import org.springframework.stereotype.Component;

@Component
public class ArticuloFilter {
    @PersistenceContext
    private EntityManager em;
    private final String PRECIO_MINIMO = "precioMinimo";
    private final String PRECIO_MAXIMO = "precioMaximo";
    private final String CATEGORIA = "categoria";
    


    public List<Articulo> filtrar(FiltroArticulo filtroArticulo) {
        Query query = em.createQuery(crearQuery(filtroArticulo));
        setearParametros(query, filtroArticulo);
        return query.getResultList();
    }

    private String crearQuery(FiltroArticulo filtroArticulo) {
        String query = "Select a from Articulo a where a.alta = true ";
        
        if (filtroArticulo.getPrecioMinimo() != null) {
            query = query.concat("and a.precio > :precioMinimo");
        }
        if (filtroArticulo.getPrecioMaximo() != null) {
            query = query.concat("and a.precio < :precioMaximo");
        }
        if (filtroArticulo.getCategoria()!= null) {
            query = query.concat("and a.categoria.categoria < :categoria");
        }
        
        return query;
    }

    private void setearParametros(Query query, FiltroArticulo filtroArticulo) {
        if (filtroArticulo.getPrecioMinimo() != null) {
            query.setParameter(PRECIO_MINIMO, filtroArticulo.getPrecioMinimo() );
        }
        if (filtroArticulo.getPrecioMaximo() != null) {
            query.setParameter(PRECIO_MAXIMO, filtroArticulo.getPrecioMaximo() );
        }
        if (filtroArticulo.getCategoria()!= null) {
            query.setParameter(CATEGORIA, filtroArticulo.getCategoria());
        }
    }
}
