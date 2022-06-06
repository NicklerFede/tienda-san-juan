package com.tienda.sanjuan.controladores;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ErrorsController implements ErrorController {

    @RequestMapping(value = "/error", method = { RequestMethod.GET, RequestMethod.POST })
    public String error(Model modelo, HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        switch (statusCode) {
            case 404:
                modelo.addAttribute("explicacion", "Not Found");
                break;
            case 403:
                modelo.addAttribute("explicacion", "No tiene los permisos necesarios para acceder a este recurso.");
                break;
            case 500:
                modelo.addAttribute("explicacion", "La solicitud no ha podido ser procesada.");
                break;
                case 400:
                modelo.addAttribute("explicacion", "La solicitud contiene informacion incorrecta.");
                break;

            default:
                break;
        }
        modelo.addAttribute("codigo", statusCode);
        return "404";
    }
}
