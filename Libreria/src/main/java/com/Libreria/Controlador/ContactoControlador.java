// com.Libreria.Controladores.ContactoControlador
package com.Libreria.Controlador;

import com.Libreria.DAO.ContactoRepositorio;
import com.Libreria.Modelos.Contacto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ContactoControlador {

    @Autowired
    private ContactoRepositorio contactoRepositorio;

    // Mostrar el formulario de contacto
    @GetMapping("/contacto")
    public String mostrarFormulario(Model model) {
        model.addAttribute("contacto", new Contacto());
        return "contacto"; // nombre del archivo .html en /templates
    }

    // Procesar formulario
    @PostMapping("/enviar-contacto")
    public String procesarFormulario(@ModelAttribute("contacto") Contacto contacto) {
        try {
            contactoRepositorio.save(contacto);
            return "redirect:/contacto?exito";
        } catch (Exception e) {
            e.printStackTrace(); // imprime error en consola si ocurre
            return "redirect:/contacto?error";
        }
    }
}

