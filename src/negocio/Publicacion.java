/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.util.List;
import objetosServicio.Fecha;
import org.bson.types.ObjectId;

/**
 *
 * @author Invitado
 */
public class Publicacion {
    private ObjectId id;
    private Usuario autor;
    private Fecha fechaHora;
    private String mensaje;
    private List<String> etiquetas;
    private List<Comentario> comentarios;
    

    public Publicacion() {
        this.id=new ObjectId();
    }

    public Publicacion(Usuario autor, Fecha fechaHora, String mensaje, List<String> etiquetas, List<Comentario> comentarios) {
        this.id=new ObjectId();
        this.autor = autor;
        this.fechaHora = fechaHora;
        this.mensaje = mensaje;
        this.etiquetas = etiquetas;
        this.comentarios = comentarios;
    }
    
    public Publicacion(ObjectId id, Usuario autor, Fecha fechaHora, String mensaje, List<String> etiquetas, List<Comentario> comentarios) {
        this.id = id;
        this.autor = autor;
        this.fechaHora = fechaHora;
        this.mensaje = mensaje;
        this.etiquetas = etiquetas;
        this.comentarios=comentarios;
    }


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Fecha getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Fecha fechaHora) {
        this.fechaHora = fechaHora;
    }
    
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public List<String> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(List<String> etiquetas) {
        this.etiquetas = etiquetas;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    @Override
    public String toString() {
        
        String comments="", tags="";
        
        for (String etiqueta : etiquetas) {
            tags+=etiqueta+", ";
        }
        
        for (Comentario comentario : comentarios) {
            comments+=comentario+"\n";
        }
        
        return String.format("\n----\n%s:%s\nEl %s a las %s\nComentarios:\n%sEtiquetas: %s", this.autor.getNombre(), this.mensaje, 
                this.fechaHora, this.fechaHora.getCadenaHora(), comments, tags);
    }
}
