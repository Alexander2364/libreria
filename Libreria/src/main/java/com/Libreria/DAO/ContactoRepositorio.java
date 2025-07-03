package com.Libreria.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Libreria.Modelos.Contacto;

public interface ContactoRepositorio extends JpaRepository<Contacto, Long> {
}