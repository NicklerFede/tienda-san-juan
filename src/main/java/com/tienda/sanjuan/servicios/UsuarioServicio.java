/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tienda.sanjuan.servicios;

import com.tienda.sanjuan.entidades.Usuario;
import com.tienda.sanjuan.repositorios.UsuarioRepositorio;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 *
 * @author Equipo6
 */
@Service
public class UsuarioServicio  implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public Usuario registrarUsuario(Usuario usuario, String password,String password2) throws Exception {

        if (usuario.getUserName().isEmpty()) {
            throw new Exception("Este campo no puede estar vacío");
        }

        if (usuario.getPassword().isEmpty()) {
            throw new Exception("Este campo no puede estar vacío");

        }
        if (usuario.getEmail().isEmpty()) {
            throw new Exception("La contraseña no puede estar vacía");
        }
        if (usuario.getFullName().isEmpty()) {
            throw new Exception("Este campo no puede estar vacío");
        }
        if (usuario.getPhoneNumber().isEmpty()) {
            throw new Exception("Este campo no puede estar vacío");
        }

        Usuario user = usuarioRepositorio.findByUserName(usuario.getUserName());
        if (user != null) {
            throw new Exception("El usuario ya existe, pruebe con otro nombre");
        }
        if (!password.equals(password2)) {
            throw new Exception("Las contraseñas ingresadas deben ser iguales");
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        usuario.setPassword(encoder.encode(password));
        
        return usuarioRepositorio.save(usuario);
    }

    public void modificarUsuario(Usuario usuario) throws Exception {
        if (usuario.getUserName().isEmpty()) {
            throw new Exception("Este campo no puede estar vacío");
        }

        if (usuario.getPassword().isEmpty()) {
            throw new Exception("Este campo no puede estar vacío");

        }
        if (usuario.getEmail().isEmpty()) {
            throw new Exception("La contraseña no puede estar vacía");
        }
        if (usuario.getFullName().isEmpty()) {
            throw new Exception("Este campo no puede estar vacío");
        }
        if (usuario.getPhoneNumber().isEmpty()) {
            throw new Exception("Este campo no puede estar vacío");
        }
        
        usuarioRepositorio.save(usuario);

    }

    public List<Usuario> findAll() {
        return usuarioRepositorio.findAll();
    }

    public void agregarUsuarioALaSesion(Usuario usuario) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attributes.getRequest().getSession(true);
        session.setAttribute("usuario", usuario);
    }

    public void eliminarUsuario(String id) throws Exception {
        Usuario usuario = usuarioRepositorio.getById(id);
        if (usuario == null) {
            throw new Exception("El usuario no existe");
        } else {
            usuarioRepositorio.delete(usuario);
        }

    }

    public Usuario buscarUsuario(String id) {
        return usuarioRepositorio.getById(id);
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Usuario usuario = usuarioRepositorio.findByUserName(username);
            List<GrantedAuthority> authorities = new ArrayList<>();
            agregarUsuarioALaSesion(usuario);
            authorities.add(new SimpleGrantedAuthority("ROLE_" + usuario.getRol()));
            return new User(username, usuario.getPassword(), authorities);
        } catch (Exception e) {
            throw new UsernameNotFoundException("El usuario no existe");
        }
    }
}
