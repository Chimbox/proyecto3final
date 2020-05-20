/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorios;

import java.util.List;
import org.bson.types.ObjectId;

public interface Repository<T> {
    List<T> buscar();
    void insertar(T objeto);
    void actualizar(T objeto);
    void eliminar(ObjectId id);
}
