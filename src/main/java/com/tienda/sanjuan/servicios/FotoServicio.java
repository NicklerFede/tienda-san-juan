package com.tienda.sanjuan.servicios;

import java.io.IOException;

import com.tienda.sanjuan.entidades.Foto;
import com.tienda.sanjuan.repositorios.FotoRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FotoServicio {

    @Autowired
    private FotoRepositorio fotoRepositorio;
    
    public Foto guardar(MultipartFile file) throws Exception {
        if (file != null) {
                try {
                Foto foto = new Foto();
                foto.setMime(file.getContentType());
                foto.setName(file.getName());
                foto.setContenido(file.getBytes());
                return fotoRepositorio.save(foto);
            } catch (IOException ex) {
                throw new Exception("No se pudo guardar la foto");
            }
        } else {
            return null;
        }
    }
}