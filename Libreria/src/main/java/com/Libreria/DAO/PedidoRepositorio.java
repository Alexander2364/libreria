package com.Libreria.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Libreria.Modelos.Pedido;
import com.Libreria.Modelos.Usuarios;

public interface PedidoRepositorio extends JpaRepository<Pedido,Integer>{
     List<Pedido> findByUsuarioOrderByFechaDesc(Usuarios usuario);
     @Query("SELECT p FROM Pedido p WHERE " +
       "LOWER(p.codigo) LIKE %:filtro% OR " +
       "LOWER(p.usuario.nombre) LIKE %:filtro% OR " +
       "CAST(p.fecha AS string) LIKE %:filtro%")
List<Pedido> buscarPorCodigoNombreFecha(@Param("filtro") String filtro);

@Query("SELECT DATE(p.fecha), COUNT(p.id) FROM Pedido p GROUP BY DATE(p.fecha) ORDER BY DATE(p.fecha)")
List<Object[]> obtenerPedidosPorDia();




}
