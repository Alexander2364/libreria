package com.Libreria.Modelos;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="categorias")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column
    private String nombre;
    @Column
    private String descripcion;
    
    //relacion con la tabla productos (una categoria puede tener varios productos)
   @OneToMany(mappedBy = "categoria")
   private List<Productos> productos;

   public int getId() {
    return id;
   }

   public void setId(int id) {
    this.id = id;
   }

   public String getNombre() {
    return nombre;
   }

   public void setNombre(String nombre) {
    this.nombre = nombre;
   }

   public List<Productos> getProductos() {
    return productos;
   }

   public void setProductos(List<Productos> productos) {
    this.productos = productos;
   }

   public String getDescripcion() {
    return descripcion;
   }

   public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
   }

   
}
