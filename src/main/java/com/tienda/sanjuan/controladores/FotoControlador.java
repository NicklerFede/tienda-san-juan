package com.tienda.sanjuan.controladores;

import com.tienda.sanjuan.entidades.Articulo;
import com.tienda.sanjuan.servicios.ArticuloServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/foto")
public class FotoControlador {
    @Autowired
    private ArticuloServicio articuloServicio;

    @GetMapping("/articulo")
    public ResponseEntity<byte[]> devolverFotoArticulo(@RequestParam("idArticulo") String id) {
        try {
            Articulo articulo = articuloServicio.buscarPorId(id);
            byte[] foto = articulo.getFoto().getContenido();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(foto, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
