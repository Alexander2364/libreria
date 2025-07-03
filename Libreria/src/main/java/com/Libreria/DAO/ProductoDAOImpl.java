
package com.Libreria.DAO;

import com.Libreria.Modelos.Productos;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class ProductoDAOImpl implements ProductoDAO {
    
    @Autowired
    ProductoRepositorio repo;

    @Override
    public List<Productos> get() {
        return repo.findAll();
    }

    @Override
    public Productos get(int id) {
        return repo.findById(id).get();
    }

    @Override
    public void save(Productos productos) {
        repo.save(productos);
    }

    @Override
    public void update(Productos productos) {
       repo.save(productos);
    }

    @Override
    public void delete(int id) {
        Productos productosObj=repo.findById(id).get();
        repo.delete(productosObj);
    }
    
}
