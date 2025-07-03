package com.Libreria.DAO;

import java.util.List;

import com.Libreria.Modelos.Categoria;


public interface CategoriaDAO {
  List<Categoria> get();
    Categoria get (int id);
    void save(Categoria categoria);
    void update(Categoria categoria);
    void delete(int id);
    
}
