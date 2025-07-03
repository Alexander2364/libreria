
package com.Libreria.DAO;

import com.Libreria.Modelos.Empleados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepositorio extends JpaRepository <Empleados,Integer> {
    
}
