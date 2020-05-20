package presentacion;

import java.util.Arrays;
import java.util.List;
import objetosServicio.Fecha;
import repositorios.*;
import negocio.*;

/**
 *
 * @author Invitado
 */
public class pres {

    public static void main(String[] args) {
        MongoDb db = new MongoDb("localhost", "faceboot");
        UsuarioRepositoryImpl usuarios = new UsuarioRepositoryImpl(db);
        PublicacionRepositoryImpl publicaciones=new PublicacionRepositoryImpl(db);
        
        db.getDb().drop();

        System.out.println("--------USUARIOS-----------");
        
        Usuario usuario = new Usuario("Alfonso Felix", 
                19, 
                false, 
                new Fecha("2000/08/22"), 
                Arrays.asList("Pop", "Alternativa"), 
                Arrays.asList("Terror", "Comedia"));
        
        Usuario usuario2 = new Usuario("Kenia Villegas", 
                20, 
                true, 
                new Fecha("2000/01/26"), 
                Arrays.asList("Alternativa", "Reggaeton"), 
                Arrays.asList("Ciencia ficción", "Romance"));

        usuarios.insertar(usuario);
        usuarios.insertar(usuario2);

        for (Usuario usuario1 : usuarios.buscar()) {
            System.out.println(usuario1);
        }
        
        
        usuario2.setNombre("Kenia V. E.");
        
        usuarios.actualizar(usuario2);
        
        System.out.println("--------USUARIOS ACTUALIZADOS-----------");
        
        for (Usuario usuario1 : usuarios.buscar()) {
            System.out.println(usuario1);
        }
        
        Comentario comentario1 = new Comentario(usuario2, new Fecha(), "Hola Alfonso");
        Comentario comentario2 = new Comentario(usuario, new Fecha(), "Hola Kenia");
        Comentario comentario3 = new Comentario(usuario, new Fecha(), "Muy buena publicación");
        Comentario comentario4 = new Comentario(usuario2, new Fecha(), "Muchas gracias Alfonso");
        Comentario comentario5 = new Comentario(usuario, new Fecha(), "Por nada");
        
        
        Publicacion publicacion=new Publicacion(usuario, 
                new Fecha("2020/05/18 05:30"), 
                "PRIMERA PUBLICACIÓN EN FACEBOOT", 
                Arrays.asList("primera", "faceboot", "first"),
                Arrays.asList(comentario1,comentario2));
        
        Publicacion publicacion2=new Publicacion(usuario2, 
                new Fecha("2020/05/19 08:30"), 
                "Esta es otra publicación en Faceboot", 
                Arrays.asList("primera", "faceboot", "first"),
                Arrays.asList(comentario3,comentario4,comentario5));
        
        publicaciones.insertar(publicacion);
        publicaciones.insertar(publicacion2);
        
        System.out.println("--------PUBLICACIONES-----------");
        
        for (Publicacion pub : publicaciones.buscar()) {
            System.out.println(pub);
            
        }

        publicaciones.eliminarComentario(publicacion.getId(), comentario1.getId());
        publicaciones.eliminarComentario(publicacion.getId(), comentario2.getId());
        
        
        System.out.println("--------PUBLICACIONES CON COMENTARIOS ELIMINADOS-----------");
        for (Publicacion pub : publicaciones.buscar()) {
            System.out.println(pub);
        }
        
        usuarios.eliminar(usuario.getId());
        
        System.out.println("--------USUARIOS-----------");
        
        for (Usuario usuario1 : usuarios.buscar()) {
            System.out.println(usuario1);
        }

    }

}
