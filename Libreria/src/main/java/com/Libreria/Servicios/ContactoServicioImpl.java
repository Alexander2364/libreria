// com.Libreria.Servicios.ContactoServicioImpl
package com.Libreria.Servicios;

import com.Libreria.Modelos.Contacto;
import com.Libreria.DAO.ContactoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactoServicioImpl implements ContactoServicio {

    @Autowired
    private ContactoRepositorio contactoRepositorio;

    @Override
    public void guardarMensaje(Contacto contacto) {
        contactoRepositorio.save(contacto);
    }

    @Override
    public List<Contacto> listarMensajes() {
        return contactoRepositorio.findAll();
    }
}
