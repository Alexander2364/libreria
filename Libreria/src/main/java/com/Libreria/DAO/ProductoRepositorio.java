
package com.Libreria.DAO;

import com.Libreria.Modelos.Productos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepositorio extends JpaRepository<Productos,Integer> {
    
}
