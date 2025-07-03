package com.Libreria.Controlador;

import com.Libreria.Modelos.CarritoItem;
import com.Libreria.Modelos.DetallePedido;
import com.Libreria.Modelos.Pedido;
import com.Libreria.Modelos.Productos;
import com.Libreria.Modelos.Usuarios;
import com.Libreria.Servicios.PedidoServicio;
import com.Libreria.Servicios.ProductoServicio;
import com.Libreria.Servicios.UsuariosServicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("carrito") // <- guarda el carrito en la sesión
public class CarritoControlador {
    @Autowired
private ProductoServicio productoServicio;

    @Autowired
    private PedidoServicio pedidoServicio;

@Autowired
private UsuariosServicos usuariosServicos;

    // Crea carrito si no existe
    @ModelAttribute("carrito")
    public List<CarritoItem> crearCarrito() {
        return new ArrayList<>();
    }
    @GetMapping("/carrito")
public String verCarrito(@ModelAttribute("carrito") List<CarritoItem> carrito, Model model) {
    double total = carrito.stream().mapToDouble(CarritoItem::getSubtotal).sum();
    model.addAttribute("total", total);
    return "carrito";
}

@PostMapping("/carrito/confirmar")
public String confirmarCompra(@ModelAttribute("carrito") List<CarritoItem> carrito,
                              SessionStatus status,
                              Authentication auth,
                              Model model) {
    String email = auth.getName();
    Usuarios usuario = usuariosServicos.buscarPorEmail(email);

    Pedido pedido = new Pedido();
    pedido.setFecha(LocalDateTime.now());
    pedido.setUsuario(usuario);

    List<DetallePedido> detalles = new ArrayList<>();
    double total = 0;

    for (CarritoItem item : carrito) {
        DetallePedido detalle = new DetallePedido();
        detalle.setProducto(item.getProducto());
        detalle.setCantidad(item.getCantidad());
        detalle.setPrecioUnitario(item.getProducto().getPrecio());
        detalle.setSubtotal(item.getSubtotal());
        detalle.setPedido(pedido);
        detalles.add(detalle);
        total += item.getSubtotal();
    }

    pedido.setDetalles(detalles);
    pedido.setTotal(total);

    // Guarda el pedido por primera vez para obtener el ID
    pedido = pedidoServicio.guardar(pedido);

    // Ahora que tiene ID, generamos el código y asignamos estado
    pedido.setCodigo("Pdo" + pedido.getId());
    pedido.setEstado("En proceso");

    // Guardamos nuevamente con código y estado
    pedido = pedidoServicio.guardar(pedido);

    // Limpiamos la sesión del carrito
    status.setComplete();

    // Enviamos datos a la vista
    model.addAttribute("pedido", pedido);
    model.addAttribute("detalles", detalles);
    model.addAttribute("total", total);

    return "confirmacion";
}




@PostMapping("/carrito/agregar")
public String agregarAlCarrito(@RequestParam("productoId") int id,
                               @RequestParam("cantidad") int cantidad,
                               @ModelAttribute("carrito") List<CarritoItem> carrito,
                               RedirectAttributes redirect) {
    Productos producto = productoServicio.get(id);

    for (CarritoItem item : carrito) {
        if (item.getProducto().getId() == id) {
            item.setCantidad(item.getCantidad() + cantidad);
            redirect.addFlashAttribute("success", "Cantidad actualizada en el carrito.");
            return "redirect:/productos";
        }
    }

    carrito.add(new CarritoItem(producto, cantidad));
    redirect.addFlashAttribute("success", "Producto añadido al carrito.");
    return "redirect:/productos";
}


@GetMapping("/carrito/eliminar/{id}")
public String eliminarDelCarrito(@PathVariable("id") int id,
                                 @ModelAttribute("carrito") List<CarritoItem> carrito) {
    carrito.removeIf(item -> item.getProducto().getId() == id);
    return "redirect:/carrito";
}
@GetMapping("/carrito/aumentar/{id}")
public String aumentarCantidad(@PathVariable("id") int id,
                                @ModelAttribute("carrito") List<CarritoItem> carrito) {
    for (CarritoItem item : carrito) {
        if (item.getProducto().getId() == id) {
            item.setCantidad(item.getCantidad() + 1);
            break;
        }
    }
    return "redirect:/carrito";
}

@GetMapping("/carrito/disminuir/{id}")
public String disminuirCantidad(@PathVariable("id") int id,
                                 @ModelAttribute("carrito") List<CarritoItem> carrito) {
    for (CarritoItem item : carrito) {
        if (item.getProducto().getId() == id && item.getCantidad() > 1) {
            item.setCantidad(item.getCantidad() - 1);
            break;
        }
    }
    return "redirect:/carrito";
}
@GetMapping("/historial")
public String verHistorialCompras(Model model, Authentication auth) {
    String email = auth.getName();
    Usuarios usuario = usuariosServicos.buscarPorEmail(email);
    List<Pedido> historial = pedidoServicio.obtenerPedidosPorUsuario(usuario);
    model.addAttribute("historial", historial);
    return "historial";
}
}
