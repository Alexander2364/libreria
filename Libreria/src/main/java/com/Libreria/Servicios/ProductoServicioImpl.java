
package com.Libreria.Servicios;

import com.Libreria.DAO.ProductoDAO;
import com.Libreria.Modelos.Productos;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoServicioImpl implements ProductoServicio{
    @Autowired
    private ProductoDAO productoDAO;
    
    @Transactional
    @Override
    public List<Productos> get() {
       return productoDAO.get();
    }
    @Transactional
    @Override
    public Productos get(int id) {
        return productoDAO.get(id);
    }
    @Transactional
    @Override
    public void save(Productos productos) {
        productoDAO.save(productos);
    }
    @Transactional
    @Override
    public void update(Productos productos) {
        productoDAO.update(productos);
    }
    @Transactional
    @Override
    public void delete(int id) {
        productoDAO.delete(id);
    }
    
}
