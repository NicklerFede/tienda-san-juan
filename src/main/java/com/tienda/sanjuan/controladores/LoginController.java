/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tienda.sanjuan.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author usuario_2
 */

@Controller
@RequestMapping("/login")
public class LoginController {
/* 
    @GetMapping("")
    public String login(@RequestParam(name = "error", required = false) String error, Model modelo) {
        if (error != null && !error.isEmpty()) {
            modelo.addAttribute("error", "Usuario o contraseña incorrectos");
        }
        return "login";
    } */
    @GetMapping("")
    public String login() {
        return "login";
    }
}
