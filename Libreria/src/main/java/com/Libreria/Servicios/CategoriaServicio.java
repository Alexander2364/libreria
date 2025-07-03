package com.Libreria.Servicios;

import java.util.List;

import com.Libreria.Modelos.Categoria;

public interface CategoriaServicio {
   public List<Categoria> get();

   public Categoria get(int id);

   public void save(Categoria categoria);

   public void update(Categoria categoria);

   public void delete(int id);

}