
package com.Libreria.DAO;

//ACCESO A LOS DATOS

import com.Libreria.Modelos.Productos;
import java.util.List;
public interface ProductoDAO {
    List<Productos> get();
    Productos get (int id);
    void save(Productos productos);
    void update(Productos productos);
    void delete(int id);
}
