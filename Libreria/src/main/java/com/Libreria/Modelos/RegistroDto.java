
package com.Libreria.Modelos;

import jakarta.validation.constraints.*;

public class RegistroDto {
    @NotEmpty(message="El nombre no puede ser vacio")
    private String nombre;
    @NotEmpty(message="El apellido no puede ser vacio")
    private String apellido;
    @NotEmpty(message="El email no puede ser vacio")
    @Email(message="El email debe tener un @")
    private String email;
    @Size(min=6,message="El tamaño de caracteres debe ser 6")
    private String contraseña;
    private String confirmacion;
    private String celular;
    private String direccion;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String Nombre) {
        this.nombre = Nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String Apellido) {
        this.apellido = Apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getConfirmacion() {
        return confirmacion;
    }

    public void setConfirmacion(String confirmacion) {
        this.confirmacion = confirmacion;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    
}
