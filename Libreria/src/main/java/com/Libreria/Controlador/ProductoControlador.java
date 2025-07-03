package com.Libreria.Controlador;

import com.Libreria.Modelos.Productos;
import com.Libreria.Servicios.CategoriaServicio;
import com.Libreria.Servicios.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;


@Controller
public class ProductoControlador {

    @Autowired
    private ProductoServicio productoServicio;
     @Autowired
    private CategoriaServicio categoriaServicio;

    @GetMapping("/listaproductos")
    public String get(Model model) {
        model.addAttribute("lista", productoServicio.get());
        model.addAttribute("producto", new Productos());
        model.addAttribute("categorias", categoriaServicio.get());
        return "listaproductos";
    }

    @PostMapping("/guardarpro")
public String guardarProducto(@ModelAttribute Productos producto, @RequestParam("file") MultipartFile file) {
    if (!file.isEmpty()) {
        String nombreArchivo = file.getOriginalFilename();
        String rutaDestino = System.getProperty("user.dir") + "/src/main/resources/static/imagenes/" + nombreArchivo;

        try {
            file.transferTo(new File(rutaDestino));
            producto.setImagen(nombreArchivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    productoServicio.save(producto);
    // Luego generamos el código y lo volvemos a guardar
        producto.setCodigo("P" + producto.getId());
        productoServicio.update(producto);
    return "redirect:/listaproductos";
}


    @GetMapping("/producto/editar/{id}")
    public String EditarPro(@PathVariable Integer id, Model modelo) {
        Productos producto = productoServicio.get(id);
        modelo.addAttribute("producto", producto);
        return "editarproducto";
    }

@PostMapping("/producto/actualizar/{id}")
public String actualizarProducto(
        @PathVariable Integer id,
        @ModelAttribute Productos producto,
        @RequestParam("file") MultipartFile file) {

    Productos actual = productoServicio.get(id);
    actual.setNombre(producto.getNombre());
    actual.setDescripcion(producto.getDescripcion());
    actual.setPrecio(producto.getPrecio());

    if (!file.isEmpty()) {
        String nombreArchivo = file.getOriginalFilename();
        String rutaDestino = System.getProperty("user.dir") + "/src/main/resources/static/imagenes/" + nombreArchivo;

        try {
            file.transferTo(new File(rutaDestino));
            actual.setImagen(nombreArchivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    productoServicio.update(actual);
    return "redirect:/listaproductos";
}



    @GetMapping("/producto/eliminar/{id}")
    public String EliminarPro(@PathVariable Integer id) {
        productoServicio.delete(id);
        return "redirect:/listaproductos";
    }

    @GetMapping("/productos")
public String verProductos(Model model) {
    model.addAttribute("productos", productoServicio.get());
    return "productos"; // Este es el nombre del archivo HTML
}
}
