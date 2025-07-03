package com.Libreria.Servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Libreria.DAO.CategoriaDAO;
import com.Libreria.Modelos.Categoria;

import jakarta.transaction.Transactional;
@Service
public class CategoriaServicioImpl implements CategoriaServicio {

@Autowired
private CategoriaDAO categoriaDAO;
    

    @Transactional
    @Override
    public List<Categoria> get() {
        return categoriaDAO.get();
    }

    @Override
    public Categoria get(int id) {
       return categoriaDAO.get(id);
    }

    @Override
    public void save(Categoria categoria) {
      categoriaDAO.save(categoria);
    }

    @Override
    public void update(Categoria categoria) {
        categoriaDAO.save(categoria);
    }

    @Override
    public void delete(int id) {
       categoriaDAO.delete(id);
    }
    
}
