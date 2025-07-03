
package com.Libreria.DAO;

import com.Libreria.Modelos.*;
import java.util.List;



public interface EmpleadoDAO {
  List<Empleados> get();
    Empleados get (int id);
    void save(Empleados empleados);
    void update(Empleados empleados);
    void delete(int id);  
}
