package com.tienda.sanjuan.controladores;

import java.util.List;

import com.tienda.sanjuan.entidades.Articulo;
import com.tienda.sanjuan.servicios.ArticuloServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/seccion")
public class SeccionController {
    @Autowired
    private ArticuloServicio articuloServicio;
    
    @RequestMapping("")
    public String hombre(@RequestParam("categoria") String categoria, Model modelo) {
        List<Articulo> articulos = articuloServicio.buscarPorCategoria(categoria);
        modelo.addAttribute("articulos", articulos);
        return "shop";
    }

    @GetMapping("/about")
    public String about(Model modelo) {
        return "about";
    }
}