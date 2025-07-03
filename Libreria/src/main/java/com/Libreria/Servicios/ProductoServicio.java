
package com.Libreria.Servicios;

import com.Libreria.Modelos.Productos;
import java.util.List;

public interface ProductoServicio {
    public List<Productos> get();
    public Productos get (int id);
    public void save(Productos productos);
    public void update(Productos productos);
    public void delete(int id);
}
