package com.Libreria.Controlador;


import com.Libreria.Modelos.Empleados;
import com.Libreria.Servicios.EmpleadoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class EmpleadoControlador {
    @Autowired
    private EmpleadoServicio empleadoservicio;
    
    @GetMapping("/listaempleados")
    public String get(Model model){
   model.addAttribute("listas", empleadoservicio.get());
   model.addAttribute("empleados", new Empleados());
    return "listaempleados";
   }
    
        @GetMapping("/guardaremp")
   public String GuardarEmp(@ModelAttribute("empleado")Empleados empleados){
   empleadoservicio.save(empleados);
   return "redirect:/listaempleados";
   
   }
   

   
   @GetMapping("/empleado/eliminar/{id}")
   public String EliminarEmp(@PathVariable Integer id){
   empleadoservicio.delete(id);
   return "redirect:/listaempleados";
   }
}
