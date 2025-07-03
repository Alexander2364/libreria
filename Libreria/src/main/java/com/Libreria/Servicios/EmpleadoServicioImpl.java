
package com.Libreria.Servicios;

import com.Libreria.DAO.EmpleadoDAO;
import com.Libreria.Modelos.Empleados;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpleadoServicioImpl implements EmpleadoServicio{
    
    @Autowired
    private EmpleadoDAO empleadoDAO;
    
    @Transactional
    @Override
    public List<Empleados> get() {
        return empleadoDAO.get();
    }
    @Transactional
    @Override
    public Empleados get(int id) {
        return empleadoDAO.get(id);
    }
    
    @Transactional
    @Override
    public void save(Empleados empleados) {
         empleadoDAO.save(empleados);
    }
    
    @Transactional
    @Override
    public void update(Empleados empleados) {
       empleadoDAO.update(empleados);
    }
    @Transactional
    @Override
    public void delete(int id) {
        empleadoDAO.delete(id);
    }
    
}
