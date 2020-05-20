/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorios;

import negocio.Usuario;
import org.bson.types.ObjectId;

/**
 *
 * @author Invitado
 */
public interface UsuarioRepository extends Repository<Usuario>{
    Usuario buscarPorId(ObjectId id);
}
