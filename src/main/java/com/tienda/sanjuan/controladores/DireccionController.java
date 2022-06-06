/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tienda.sanjuan.controladores;

import com.tienda.sanjuan.entidades.Direccion;
import com.tienda.sanjuan.servicios.DireccionServicio;
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
@RequestMapping("/")
public class DireccionController {

    @Autowired
    private DireccionServicio direccionServicio;

    @PostMapping("/guardar")
    private String guardarDireccion(@ModelAttribute("direccion") Direccion direccion, Model modelo) {
        try {
            direccionServicio.guardarDireccion(direccion);
            modelo.addAttribute("success", "Direccion guardada con exito");
        } catch (Exception ex) {
            modelo.addAttribute("error", ex.getMessage());
        }
        return "formulario-direccion";
    }


    @GetMapping("/editar")
    private String editarDireccion() {
        return "";
    }

    @GetMapping("/eliminar")
    private String eliminarDireccion(@RequestParam("direccionId") String id) {
        
        try {
            direccionServicio.eliminarDireccion(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "formulario-direccion";
    }

}
