
package com.Libreria.DAO;

import com.Libreria.Modelos.*;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuariosRepositorio extends JpaRepository<Usuarios,Integer> {
    //definir el comportamiento con metodos
    public Usuarios findByEmail(String email);
}
