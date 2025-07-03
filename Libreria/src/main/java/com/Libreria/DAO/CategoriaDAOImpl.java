package com.Libreria.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Libreria.Modelos.Categoria;

@Repository
public class CategoriaDAOImpl implements CategoriaDAO {

    @Autowired
    CategoriaRepositorio repo;

    @Override
    public List<Categoria> get() {
        return repo.findAll();
    }

    @Override
    public Categoria get(int id) {
       return repo.findById(id).get();
    }

    @Override
    public void save(Categoria categoria) {
       repo.save(categoria);
    }

    @Override
    public void update(Categoria categoria) {
        repo.save(categoria);
    }

    @Override
    public void delete(int id) {
        Categoria categoriaObj=repo.findById(id).get();
        repo.delete(categoriaObj);
       
    }
    
}
