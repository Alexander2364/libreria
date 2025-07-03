package com.Libreria.Servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Libreria.DAO.DetallePedidoRepositorio;
import com.Libreria.Modelos.DetallePedido;

@Service
public class DetallePedidoServicio {

    @Autowired
    private DetallePedidoRepositorio detalleRepo;

    public void guardar(DetallePedido detalle) {
        detalleRepo.save(detalle);
    }

    public List<DetallePedido> listar() {
        return detalleRepo.findAll();
    }
}
