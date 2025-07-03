
package com.Libreria.Controlador;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class inicio_Controlador {
    @GetMapping({"","/"})
    public String inicio(){
    return "index";
}
   
    @GetMapping("/login")
   public String login(){       
       return "login";
   }
   @GetMapping("/intranet")
   public String intranet(){       
       return "intranet";
   }
}
