package com.Libreria.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.Libreria.Modelos.Categoria;

import com.Libreria.Servicios.CategoriaServicio;

import org.springframework.web.bind.annotation.*;

@Controller
public class CategoriaControlador {

    @Autowired
    private CategoriaServicio categoriaServicio;

    @GetMapping("/categoria")
    public String get(Model model) {
        model.addAttribute("lista", categoriaServicio.get());
        model.addAttribute("categoria", new Categoria());
        return "categoria";
    }

    @PostMapping("/categoria/guardar") // <- CORREGIDO
    public String guardarCategoria(@ModelAttribute("categoria") Categoria categoria) {
        categoriaServicio.save(categoria);
        return "redirect:/categoria";
    }

    @GetMapping("/categoria/eliminar/{id}")
    public String eliminarCategoria(@PathVariable Integer id) {
        categoriaServicio.delete(id);
        return "redirect:/categoria";
    }

    @PostMapping("/categoria/actualizar/{id}")
    public String actualizarCategoria(@PathVariable Integer id, @ModelAttribute Categoria categoria) {
        categoria.setId(id); // asegurarse que se actualice el correcto
        categoriaServicio.save(categoria);
        return "redirect:/categoria";
    }
}
