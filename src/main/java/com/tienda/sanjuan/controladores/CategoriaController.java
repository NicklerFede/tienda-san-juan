/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tienda.sanjuan.controladores;

import com.tienda.sanjuan.DTOs.FiltroArticulo;
import com.tienda.sanjuan.entidades.Articulo;
import com.tienda.sanjuan.entidades.Categoria;
import com.tienda.sanjuan.servicios.ArticuloServicio;
import com.tienda.sanjuan.servicios.CategoriaServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Equipo6
 */
@Controller
@RequestMapping("/categoria")
public class CategoriaController {
    
    @Autowired
    CategoriaServicio categoriaServicio;
    @Autowired
    ArticuloServicio articuloServicio;
    
    @GetMapping("")
    public String formularioCategoria(Model modelo) {
	modelo.addAttribute("categoria", new Categoria());
	return "formulario-categoria";
    }

    @PostMapping("/save")
    public String guardarCategoria(@ModelAttribute("categoria") Categoria categoria, Model modelo) {
	try {
	    modelo.addAttribute("categoria", categoria);
	     if(categoria.getId()!= null && ! categoria.getId().isEmpty()) {
		categoriaServicio.modificarCategoria(categoria);
	    }
             else{
                 categoriaServicio.guardarCategoria(categoria);
             }
             modelo.addAttribute("exito", "articulo guardado correctamente");

	    return "formulario-categoria";

	} catch (Exception ex) {
	    ex.printStackTrace();
	    modelo.addAttribute("categoria", categoria);
	    modelo.addAttribute("error", ex.getMessage());
	    return "formulario-categoria";
	}
    }
    @GetMapping("/modificar")
    public String modificarCategoria(@RequestParam(name = "id", required = true) String id, Model modelo) {
	Categoria categoria = categoriaServicio.buscarPorId(id);
	modelo.addAttribute("categoria", categoria);
	return "formulario-categoria";
    }

    @GetMapping("/listar")
    public String listAll(Model modelo) {
	List<Categoria> categorias = categoriaServicio.listarCategorias();
	modelo.addAttribute("listaDeCategorias", categorias);
	return "listar-categorias";
    }
    
    @GetMapping("/listararticulos")
    public String listarArticulos(@RequestParam("categoria")String categoria, Model modelo) {
        FiltroArticulo filtroArticulo = new FiltroArticulo();
        filtroArticulo.setCategoria(categoria);
       List<Articulo> articulos = articuloServicio.filtrarArticulos(filtroArticulo);
       modelo.addAttribute("categoria", categoria);
       return "shop";
    }
    
}
