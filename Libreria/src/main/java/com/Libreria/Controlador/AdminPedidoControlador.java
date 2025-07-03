package com.Libreria.Controlador;

import com.Libreria.Modelos.Pedido;
import com.Libreria.Servicios.PedidoServicio;

import java.time.LocalDate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminPedidoControlador {

    @Autowired
    private PedidoServicio pedidoServicio;

    
    @GetMapping("/pedidos")
    public String verPedidos(Model model) {
        model.addAttribute("pedidos", pedidoServicio.listarPedidos());
        return "pedidos";
    }

    
    @PostMapping("/pedidos/estado")
    public String actualizarEstado(@RequestParam("id") int id,
                                   @RequestParam("estado") String estado) {
        Pedido pedido = pedidoServicio.obtenerPorId(id);
        if (pedido != null) {
            pedido.setEstado(estado);
            pedidoServicio.guardar(pedido);
        }
        return "redirect:/pedidos";
    }

@GetMapping("/pedidos/buscar")
public String buscarPedidos(@RequestParam("filtro") String filtro, Model model) {
    List<Pedido> pedidosFiltrados;

    if (filtro == null || filtro.trim().isEmpty()) {
        pedidosFiltrados = pedidoServicio.listarPedidos(); // Muestra todo si no hay filtro
    } else {
        pedidosFiltrados = pedidoServicio.buscarPorCodigoNombreFecha(filtro);
    }

    model.addAttribute("pedidos", pedidosFiltrados);
    return "pedidos";
}

@GetMapping("/dashboard-pedidos")
public String verDashboard(Model model) {
    List<Object[]> pedidosPorDia = pedidoServicio.obtenerPedidosPorDia(); // [fecha, cantidad]

    int totalPedidos = pedidosPorDia.stream()
        .mapToInt(row -> ((Number) row[1]).intValue())
        .sum();

    LocalDate hoy = LocalDate.now();
    int pedidosHoy = pedidosPorDia.stream()
        .filter(row -> hoy.equals(LocalDate.parse(row[0].toString())))
        .mapToInt(row -> ((Number) row[1]).intValue())
        .findFirst()
        .orElse(0);

    model.addAttribute("pedidosPorDia", pedidosPorDia);
    model.addAttribute("totalPedidos", totalPedidos);
    model.addAttribute("pedidosHoy", pedidosHoy);

    return "dashboard_pedidos";
}





}
