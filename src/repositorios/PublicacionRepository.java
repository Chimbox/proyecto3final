/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorios;

import java.util.List;
import negocio.Publicacion;
import org.bson.types.ObjectId;

/**
 *
 * @author Invitado
 */
public interface PublicacionRepository extends Repository<Publicacion>{
    List<Publicacion> buscarPorTag(String tag);
    void eliminarComentario(ObjectId idPublicacion, ObjectId idComentario);
}
