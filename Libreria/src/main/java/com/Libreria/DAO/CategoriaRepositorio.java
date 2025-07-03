package com.Libreria.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Libreria.Modelos.Categoria;

@Repository
public interface CategoriaRepositorio extends JpaRepository<Categoria,Integer> {

    
} 