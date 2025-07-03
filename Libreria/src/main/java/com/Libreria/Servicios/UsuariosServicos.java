
package com.Libreria.Servicios;

import com.Libreria.Modelos.Usuarios;
import com.Libreria.DAO.UsuariosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.*;



@Service
public class UsuariosServicos implements UserDetailsService {

    @Autowired
    private UsuariosRepositorio repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuarios usu = repo.findByEmail(email);
        if (usu != null) {
            return User.withUsername(usu.getEmail())
                    .password(usu.getContraseña())
                    .authorities(usu.getRole()) // ✅ Aquí es authorities, no roles
                    .build();
        }
        throw new UsernameNotFoundException("Usuario no encontrado");
    }

    public Usuarios buscarPorEmail(String email) {
        return repo.findByEmail(email);
    }
}