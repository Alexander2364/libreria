package com.Libreria.Controlador;


import com.Libreria.Modelos.RegistroDto;
import com.Libreria.Modelos.Usuarios;
import com.Libreria.DAO.UsuariosRepositorio;
import jakarta.validation.Valid;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class cuentaControlador {
    @Autowired
    private UsuariosRepositorio repo;
    
    @GetMapping("/perfil")
public String perfil(Authentication auth, Model model) {
    Usuarios user = repo.findByEmail(auth.getName());
       // Imprimir en consola para depurar
    

    model.addAttribute("appUser", user);

    // Redireccionar a la vista según el rol
    if (user.getRole().equals("ROLE_admin")) {
        return "perfil"; // src/main/resources/templates/admin/perfil.html
    } else if (user.getRole().equals("ROLE_cliente")) {
        return "perfilusuario"; // src/main/resources/templates/cliente/perfil.html
    }

    return "perfil"; // vista por defecto si no hay rol válido
}

    @GetMapping("/registro")
    public String register(Model model){
    if(!model.containsAttribute("registroDto")) {
        model.addAttribute("registroDto", new RegistroDto());
    }
    // No es necesario agregar "success" false porque si no existe, Thymeleaf lo trata como false
    return "registro";
}
    
   @PostMapping("/registro")
public String register(@Valid @ModelAttribute RegistroDto registroDto, BindingResult result,
                       RedirectAttributes redirectAttributes){
    if (!registroDto.getContraseña().equals(registroDto.getConfirmacion())){
        result.addError(new FieldError("registroDto", "confirmacion", "las contraseñas no coinciden"));
    }
    Usuarios usu = repo.findByEmail(registroDto.getEmail());
    if (usu != null){
        result.addError(new FieldError("registroDto", "email", "el email registrado ya esta registrado"));
    }

    if(result.hasErrors()){
        return "registro";
    }
    try{
        var bCryptEncoder = new BCryptPasswordEncoder();
        Usuarios newusuario = new Usuarios();
        newusuario.setNombre(registroDto.getNombre());
        newusuario.setApellido(registroDto.getApellido());
        newusuario.setEmail(registroDto.getEmail());
        newusuario.setCelular(registroDto.getCelular());
        newusuario.setDireccion(registroDto.getDireccion());
        newusuario.setContraseña(bCryptEncoder.encode(registroDto.getContraseña()));
        newusuario.setRole("ROLE_cliente");
        newusuario.setCreaccion(new Date());

        repo.save(newusuario);

        redirectAttributes.addFlashAttribute("success", true);
        return "redirect:/registro";

    } catch(Exception ex) {
        result.addError(new FieldError("registroDto", "nombre", ex.getMessage()));
        return "registro";
    }
}

@PostMapping("/perfil/cambiar-password")
public String cambiarPassword(@RequestParam String contraseñaActual,
                              @RequestParam String nuevaContraseña,
                              @RequestParam String confirmarContraseña,
                              Authentication auth,
                              RedirectAttributes redirect) {

    Usuarios usuario = repo.findByEmail(auth.getName());
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // Verifica la contraseña actual
    if (!encoder.matches(contraseñaActual, usuario.getContraseña())) {
        redirect.addFlashAttribute("error", "La contraseña actual es incorrecta.");
        return "redirect:/perfil";
    }

    // Verifica que la nueva contraseña coincida con la confirmación
    if (!nuevaContraseña.equals(confirmarContraseña)) {
        redirect.addFlashAttribute("error", "Las contraseñas no coinciden.");
        return "redirect:/perfil";
    }

    // Cambiar la contraseña
    usuario.setContraseña(encoder.encode(nuevaContraseña));
    repo.save(usuario);

    redirect.addFlashAttribute("success", "Contraseña actualizada correctamente.");
    return "redirect:/perfil";
}


 

    
    
}//fin
