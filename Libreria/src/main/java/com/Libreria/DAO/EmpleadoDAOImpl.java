
package com.Libreria.DAO;

import com.Libreria.Modelos.Empleados;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmpleadoDAOImpl implements EmpleadoDAO {
    
    @Autowired
    EmpleadoRepositorio repo;

    @Override
    public List<Empleados> get() {
         return repo.findAll();
    }

    @Override
    public Empleados get(int id) {
        return repo.findById(id).get();
    }

    @Override
    public void save(Empleados empleados) {
         repo.save(empleados);
    }

    @Override
    public void update(Empleados empleados) {
         repo.save(empleados);
    }

    @Override
    public void delete(int id) {
        Empleados empleadosObj=repo.findById(id).get();
        repo.delete(empleadosObj);
    }

    

    
    
}
