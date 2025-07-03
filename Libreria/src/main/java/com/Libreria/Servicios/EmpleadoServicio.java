
package com.Libreria.Servicios;

import com.Libreria.Modelos.Empleados;
import java.util.List;


public interface EmpleadoServicio {
     public List<Empleados> get();
    public Empleados get (int id);
    public void save(Empleados empleados);
    public void update(Empleados empleados);
    public void delete(int id);
}
