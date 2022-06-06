/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tienda.sanjuan.controladores;

import com.tienda.sanjuan.entidades.Orden;
import com.tienda.sanjuan.entidades.Usuario;
import com.tienda.sanjuan.servicios.OrdenServicio;
import com.tienda.sanjuan.servicios.UsuarioServicio;
import java.util.List;
import javax.servlet.http.HttpSession;
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
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private OrdenServicio ordenServicio;

    @GetMapping("")
    private String formularioUsuario(Model modelo) {
        Usuario usuario = new Usuario();
        modelo.addAttribute("usuario", usuario);
        return "signin";
    }

    // guardar usuario
    @PostMapping("/registro")
    private String guardarUsuario(@ModelAttribute("usuario") Usuario usuario,
            @RequestParam(name = "password", required = false) String password,
            @RequestParam(name = "password2", required = false) String password2, Model modelo) {
        try {
            if (usuario.getId() == null || usuario.getId().isEmpty()) {
                usuarioServicio.registrarUsuario(usuario, password, password2);
            } else {
                usuarioServicio.modificarUsuario(usuario);
            }
            modelo.addAttribute("success", "Registrado con exito!");
        } catch (Exception ex) {
            ex.printStackTrace();
            modelo.addAttribute("error", ex.getMessage());
        }
        return "signin";
    }

    @GetMapping("/listar")
    private String listarUsuarios(Model modelo) {
        List<Usuario> usuarios = usuarioServicio.findAll();
        modelo.addAttribute("usuarios", usuarios);
        return "";
    }

    // eliminar usuario - ADMIN
    @GetMapping("/eliminar")
    private String eliminarUsuario(@RequestParam("usuarioId") String id) {

        try {
            usuarioServicio.eliminarUsuario(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    // modificar usuario
    @GetMapping("/modificar")
    private String modificarUsuario(Model modelo, @RequestParam("id") String id) {
        Usuario usuario = usuarioServicio.buscarUsuario(id);
        modelo.addAttribute("usuario", usuario);

        return "signin";
    }

    // mostrar perfil
    @GetMapping("/perfil")
    private String mostrarPerfil() {
        return "profile-details";
    }

    @GetMapping("/direccion")
    private String direcciones() {
        return "address";
    }

    @GetMapping("/compras")
    private String compras(Model modelo, HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        List<Orden> ordenes = ordenServicio.buscarPorUsuario(usuario);

        if (ordenes.isEmpty() || ordenes == null) {
            modelo.addAttribute("status", "Aun no hiciste ninguna compra.");
        } else {
            modelo.addAttribute("ordenes", ordenes);
            modelo.addAttribute("status", null);
        }

        return "dashboard";
    }

}
