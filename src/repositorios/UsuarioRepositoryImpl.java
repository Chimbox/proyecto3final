/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorios;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import java.util.ArrayList;
import java.util.List;
import negocio.Usuario;
import objetosServicio.Fecha;
import org.bson.Document;
import org.bson.types.ObjectId;

public class UsuarioRepositoryImpl extends RepositoryBase<Usuario> implements UsuarioRepository {

    private static Usuario post;

    public UsuarioRepositoryImpl(MongoClient mongo) {
        super(mongo);
    }

    @Override
    public List<Usuario> buscar() {
        List lstUsuarios = new ArrayList<Usuario>();
        for (Document doc : this.getDatabase().getCollection("usuarios").find()) {
            lstUsuarios.add(
                    UsuarioRepositoryImpl.fromDocument(doc)
            );
        }
        return lstUsuarios;
    }

    @Override
    public Usuario buscarPorId(ObjectId id) {

        Document doc = this.getDatabase().getCollection("usuarios").find(Filters.eq("_id", id)).first();
        return UsuarioRepositoryImpl.fromDocument(doc);

    }

    @Override
    public void insertar(Usuario usuario) {
        try {
            this.getDatabase().getCollection("usuarios").insertOne(
                    UsuarioRepositoryImpl.toDocument(usuario)
            );
        } catch (Exception e) {

        }
    }

    @Override
    public void actualizar(Usuario usuario) {
        MongoCollection collection = this.getDatabase().getCollection("usuarios");
        collection.updateOne(
                Filters.eq("_id", usuario.getId()),
                Updates.set("nombre", usuario.getNombre()));
        collection.updateOne(
                Filters.eq("_id", usuario.getId()),
                Updates.set("edad", usuario.getEdad()));
        collection.updateOne(
                Filters.eq("_id", usuario.getId()),
                Updates.set("sexo", usuario.getSexo()));
        collection.updateOne(
                Filters.eq("_id", usuario.getId()),
                Updates.set("fecha_nac", usuario.getFechaNacimiento().toString()));
        collection.updateOne(
                Filters.eq("_id", usuario.getId()),
                Updates.set("peliculas", usuario.getPeliculas()));
        collection.updateOne(
                Filters.eq("_id", usuario.getId()),
                Updates.set("musica", usuario.getGenerosMusicales()));

    }

    @Override
    public void eliminar(ObjectId id) {
        this.getDatabase().getCollection("usuarios").deleteOne(
                Filters.eq("_id", id)
        );
    }

    private static Usuario fromDocument(Document doc) {
        try {
            post=new Usuario(
                    doc.getObjectId("id"),
                    doc.getString("nombre"),
                    doc.getInteger("edad"),
                    doc.getBoolean("sexo"),
                    new Fecha(doc.getString("fecha_nac")),
                    doc.getList("peliculas", String.class),
                    doc.getList("musica", String.class)
            );
        } catch (Exception e) {

        }

        return post;
    }

    private static Document toDocument(Usuario usuario) {
        try {
            return new Document()
                    .append("_id", usuario.getId())
                    .append("nombre", usuario.getNombre())
                    .append("edad", usuario.getEdad())
                    .append("sexo", usuario.getSexo())
                    .append("fecha_nac", usuario.getFechaNacimiento().toString())
                    .append("peliculas", usuario.getPeliculas())
                    .append("musica", usuario.getGenerosMusicales());
            
        } catch (Exception e) {

        }
        return null;

    }

}
