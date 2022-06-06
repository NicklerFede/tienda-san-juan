/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tienda.sanjuan.controladores;

import com.tienda.sanjuan.entidades.Articulo;
import com.tienda.sanjuan.servicios.ArticuloServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Equipo6
 */

@Controller
@RequestMapping("/articulo")
public class ArticuloController {

    @Autowired
    ArticuloServicio articuloServicio;

    @GetMapping("")
    public String formularioArticulo(Model modelo) {
        modelo.addAttribute("articulo", new Articulo());
        return "formulario-articulo";
    }

    @PostMapping("/save")
    public String formularioData(@ModelAttribute("articulo") Articulo articulo, Model modelo, MultipartFile file) {
        try {
            modelo.addAttribute("articulo", articulo);
            if (articulo.getId() != null && !articulo.getId().isEmpty()) {
                articuloServicio.modificarArticulo(articulo);
            } else {
                articuloServicio.guardarArticulo(articulo, file);
            }
            modelo.addAttribute("exito", "articulo guardado correctamente");

            return "formulario-articulo";

        } catch (Exception ex) {
            ex.printStackTrace();
            modelo.addAttribute("articulo", articulo);
            modelo.addAttribute("error", ex.getMessage());
            return "formulario-articulo";
        }
    }

    @GetMapping("/producto")
    public String mostrarProducto(@RequestParam("idArticulo") String id, Model modelo) {
        Articulo articulo = articuloServicio.buscarPorId(id);
        List<Articulo> articulos = articuloServicio.buscarPorCategoria(articulo.getCategoria().toString());

        modelo.addAttribute("articulo", articulo);
        modelo.addAttribute("articulos", articulos);
        return "product-single";
    }


    @GetMapping("/modificar")
    public String formulario(@RequestParam(name = "id", required = true) String id, Model modelo) {
        Articulo articulo = articuloServicio.buscarPorId(id);
        modelo.addAttribute("articulo", articulo);
        return "formulario-articulo";
    }

    @GetMapping("/listar")
    public String listAll(Model modelo) {
        List<Articulo> articulos = articuloServicio.listarArticulos();
        modelo.addAttribute("listaDeArticulos", articulos);
        return "shop";
    }

    @GetMapping("/alta")
    public String alta(@RequestParam("id") String id) {
        try {
            articuloServicio.altaArticulo(id);
            return "redirect:/articulo/listar";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/articulo/listar";
        }
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") String id) {
        try {
            articuloServicio.bajaArticulo(id);
            return "redirect:/articulo/listar";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/articulo/listar";
        }
    }

    @GetMapping("/eliminar")
    public String eliminarArticulo(@RequestParam("id") String id) {
        try {
            articuloServicio.eliminarArticulo(id);
            return "redirect:/articulo/listar";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/articulo/listar";
        }
    }

    /* @GetMapping("/compra")
    public String prestamo(@RequestParam("id") String id, RedirectAttributes attributes) {
        try {
            articuloServicio.compra(id);
            attributes.addFlashAttribute("exito", "compra realizada");
            return "redirect:/articulo/listar";
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/articulo/listar";
        }
    } */

    @GetMapping("/destacado")
    public String destacado(@RequestParam("id") String id) {
        try {
            articuloServicio.articuloDestacado(id);
            return "redirect:/articulo/listar";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/articulo/listar";
        }

        
    }

}
