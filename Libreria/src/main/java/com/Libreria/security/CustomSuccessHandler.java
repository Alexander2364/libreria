
package com.Libreria.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
      for (GrantedAuthority auth : authentication.getAuthorities()) {
            String role = auth.getAuthority();
            if (role.equals("ROLE_admin")) {
                response.sendRedirect("/dashboard-pedidos");
                return;
            } else if (role.equals("ROLE_cliente")) {
                response.sendRedirect("/");
                return;
            }
        }

        // Redirección por defecto
        response.sendRedirect("/"); 
    }
    
}
