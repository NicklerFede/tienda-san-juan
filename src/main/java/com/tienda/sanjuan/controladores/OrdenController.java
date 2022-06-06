
package com.tienda.sanjuan.controladores;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;

import com.tienda.sanjuan.entidades.Detalle;
import com.tienda.sanjuan.entidades.Orden;
import com.tienda.sanjuan.entidades.Usuario;
import com.tienda.sanjuan.servicios.DetalleServicio;
import com.tienda.sanjuan.servicios.OrdenServicio;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/orden")
public class OrdenController {
    @Autowired
    private OrdenServicio ordenServicio;

    @Autowired
    private DetalleServicio detalleServicio;

    //@PreAuthorize("hasAnyRole('ROLE_USUARIO','ROLE_ADMINISTRADOR')")
    @GetMapping("/checkout")
    public String checkout(Model modelo, HttpSession session) {
        /*
         * ------agregar al modelo una entidad (Metodo de pago) vacio
         * -----y rellenar la direccion del usuario y el detalle de la compra
         */
        Double total = 0.0;
        ArrayList<Detalle> carrito = (ArrayList<Detalle>) session.getAttribute("carrito");
        System.out.println("carrito: " + carrito.toString());
        for (Detalle item : carrito) {
            total += item.getSubTotal();
        }
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        modelo.addAttribute("total", total);

        return "checkout";
    }

    @GetMapping("/realizarCompra")
    public String hacerPedido(Model modelo, HttpSession session) throws Exception {
        // --------se genera la orden y se la persiste
        Double total = 0.0;

        ArrayList<Detalle> carrito = (ArrayList<Detalle>) session.getAttribute("carrito");

        for (Detalle item : carrito) {
            total += item.getSubTotal();
        }
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        try {
            Orden orden = new Orden();
            // SimpleDateFormat parseador = new SimpleDateFormat("yyyy-MM-dd");
            orden.setPurchaseDate(new Date());
            orden.setUser(usuario);
            orden.setTotal(total);
            orden = ordenServicio.guardar(orden);
            for (Detalle item : carrito) {
                item.setOrden(orden);
                detalleServicio.guardar(item);
                ordenServicio.descontarStock(item.getArticulo().getId(), item.getQuantity());
            }
            session.removeAttribute("carrito");
            
            return "confirmation";
        } catch (Exception e) {
            e.printStackTrace();
            modelo.addAttribute("error", "No se pudo hacer la compra.");
            return "404";// ------ deberia devolver a una pagina de error
        }
    }

    @GetMapping("/listar")
    public String listar(Model modelo, @RequestParam("idUsuario") String idUsuario) {
        // ---- listar las ordenes por el id del usuario
        // ArrayList<Orden> ordenes = ordenServicio.buscarPorUsuario(idUsuario);
        // modelo.addAttribute("listaOrdenes", ordenes);
        return "order";
    }
}
