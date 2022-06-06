/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tienda.sanjuan.controladores;

import java.util.List;

import com.tienda.sanjuan.entidades.Articulo;
import com.tienda.sanjuan.servicios.ArticuloServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 *
 * @author carop
 */
@Controller
@RequestMapping("")
public class MainController {
    @Autowired
    private ArticuloServicio articuloServicio;

    @GetMapping("")
    public String index(Model modelo){
        List<Articulo> lista = articuloServicio.listaIndex();
        modelo.addAttribute("articulos", lista);
	return "index";
    }
    
    @GetMapping("/about")
    public String conocenos(){
        return "about";
    }
}
