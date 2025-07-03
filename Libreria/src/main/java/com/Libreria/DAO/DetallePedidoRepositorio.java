package com.Libreria.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Libreria.Modelos.DetallePedido;

public interface DetallePedidoRepositorio extends JpaRepository<DetallePedido, Integer> {
}
