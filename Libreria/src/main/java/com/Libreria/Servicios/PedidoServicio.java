
package com.Libreria.Servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Libreria.DAO.PedidoRepositorio;
import com.Libreria.Modelos.Pedido;
import com.Libreria.Modelos.Usuarios;

@Service
public class PedidoServicio {

     @Autowired
    private PedidoRepositorio pedidoRepo;

     
     public Pedido guardar(Pedido pedido) {
        return pedidoRepo.save(pedido);
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepo.findAll();
    }
    public List<Pedido> obtenerPedidosPorUsuario(Usuarios usuario) {
    return pedidoRepo.findByUsuarioOrderByFechaDesc(usuario);
}

    public Pedido obtenerPorId(int id) {
      return pedidoRepo.findById(id).orElse(null);
       
    }

   public List<Pedido> buscarPorCodigoNombreFecha(String filtro) {
    return pedidoRepo.buscarPorCodigoNombreFecha(filtro.toLowerCase());
}
public List<Object[]> obtenerPedidosPorDia() {
    return pedidoRepo.obtenerPedidosPorDia();
}

}
