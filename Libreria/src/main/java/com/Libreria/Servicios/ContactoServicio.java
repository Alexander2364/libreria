// com.Libreria.Servicios.ContactoServicio
package com.Libreria.Servicios;

import com.Libreria.Modelos.Contacto;
import java.util.List;

public interface ContactoServicio {
    void guardarMensaje(Contacto contacto);
    List<Contacto> listarMensajes();
}
