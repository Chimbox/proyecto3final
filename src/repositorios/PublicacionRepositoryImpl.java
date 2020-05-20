package repositorios;

import com.mongodb.DBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.util.JSON;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import negocio.Publicacion;
import negocio.Comentario;
import objetosServicio.Fecha;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

public class PublicacionRepositoryImpl extends RepositoryBase<Publicacion> implements PublicacionRepository {

    UsuarioRepositoryImpl usuarios;

    public PublicacionRepositoryImpl(MongoDb mongo) {
        super(mongo);
        usuarios = new UsuarioRepositoryImpl(mongo);
    }

    @Override
    public List<Publicacion> buscar() {
        List lstPublicaciones = new ArrayList<Publicacion>();
        List lstComentarios;
        MongoCollection<Document> collection = this.getDatabase().getCollection("publicaciones");
        for (Document doc : collection.find()) {
            lstComentarios= new ArrayList<Comentario>();
            for (Document docComentario : doc.getList("comentarios", Document.class)) {
                lstComentarios.add(
                        new Comentario(
                                docComentario.getObjectId("_id"),
                                usuarios.buscarPorId(docComentario.getObjectId("autor")),
                                new Fecha(docComentario.getString("fecha")),
                                docComentario.getString("mensaje")
                        )
                );
            }

            lstPublicaciones.add(
                    new Publicacion(
                            doc.getObjectId("_id"),
                            usuarios.buscarPorId(doc.getObjectId("autor")),
                            new Fecha(doc.getString("fecha")),
                            doc.getString("mensaje"),
                            doc.getList("tags", String.class),
                            lstComentarios
                    )
            );
        }
        return lstPublicaciones;
    }

    @Override
    public List<Publicacion> buscarPorTag(String tag) {
        List lstPublicaciones = new ArrayList<Publicacion>();
        List lstComentarios;
        for (Document doc : this.getDatabase().getCollection("publicaciones").find(Filters.regex("tags", Pattern.compile(tag, Pattern.CASE_INSENSITIVE)))) {
            lstComentarios= new ArrayList<Comentario>();
            for (Document docComentario : doc.getList("comentarios", Document.class)) {
                lstComentarios.add(
                        new Comentario(
                                docComentario.getObjectId("_id"),
                                usuarios.buscarPorId(docComentario.getObjectId("autor")),
                                new Fecha(docComentario.getString("fecha")),
                                docComentario.getString("mensaje")
                        )
                );
            }
            lstPublicaciones.add(
                    new Publicacion(
                            doc.getObjectId("_id"),
                            usuarios.buscarPorId(doc.getObjectId("autor")),
                            new Fecha(doc.getString("fecha")),
                            doc.getString("mensaje"),
                            doc.getList("tags", String.class),
                            lstComentarios
                    )
            );
        }
        return lstPublicaciones;
    }

    @Override
    public void insertar(Publicacion publicacion) {
        try {
            List<Document> lstDocComentarios = new ArrayList<>();
            for (Comentario comentario : publicacion.getComentarios()) {
                lstDocComentarios.add(
                        new Document()
                                .append("_id", comentario.getId())
                                .append("autor", comentario.getAutor().getId())
                                .append("fecha", comentario.getFechaHora().toString() + " " + comentario.getFechaHora().getCadenaHora())
                                .append("mensaje", comentario.getMensaje())
                );
            }
            this.getDatabase().getCollection("publicaciones").insertOne(
                    new Document()
                            .append("_id", publicacion.getId())
                            .append("autor", publicacion.getAutor().getId())
                            .append("fecha", publicacion.getFechaHora().toString() + " " + publicacion.getFechaHora().getCadenaHora())
                            .append("mensaje", publicacion.getMensaje())
                            .append("tags", publicacion.getEtiquetas())
                            .append("comentarios", lstDocComentarios)
            );
        } catch (Exception e) {

        }
    }

    @Override
    public void actualizar(Publicacion publicacion) {
        MongoCollection collection = this.getDatabase().getCollection("publicaciones");
        collection.updateOne(
                Filters.eq("_id", publicacion.getId()),
                Updates.addToSet("usuario", publicacion.getAutor().getId()));
        collection.updateOne(
                Filters.eq("_id", publicacion.getId()),
                Updates.addToSet("fecha", publicacion.getFechaHora().toString() + " " + publicacion.getFechaHora().getCadenaHora()));
        collection.updateOne(
                Filters.eq("_id", publicacion.getId()),
                Updates.addToSet("mensaje", publicacion.getMensaje()));
        collection.updateOne(
                Filters.eq("_id", publicacion.getId()),
                Updates.addToSet("tags", publicacion.getEtiquetas()));
    }

    @Override
    public void eliminar(ObjectId id) {
        this.getDatabase().getCollection("publicaciones").deleteOne(Filters.eq("_id", id));
    }

    @Override
    public void eliminarComentario(ObjectId idPublicacion, ObjectId idComentario) {
        this.getDatabase().getCollection("publicaciones").updateOne(
               Filters.eq("_id",idPublicacion),
               Updates.pull("comentarios",Filters.eq("_id",idComentario)));
    }
}
