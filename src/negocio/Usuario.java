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
public class Usuario {

    private ObjectId id;
    private String nombre;
    private int edad;
    private boolean sexo;
    private Fecha fechaNacimiento;
    private List generosMusicales;
    private List peliculas;

    public Usuario(ObjectId id, String nombre, int edad, boolean sexo, Fecha fechaNacimiento, List generosMusicales, List peliculas) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.generosMusicales = generosMusicales;
        this.peliculas = peliculas;
    }

    public Usuario(String nombre, int edad, boolean sexo, Fecha fechaNacimiento, List generosMusicales, List peliculas) {
        this.id=new ObjectId();
        this.nombre = nombre;
        this.edad = edad;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.generosMusicales = generosMusicales;
        this.peliculas = peliculas;
    }

    public Usuario() {
        this.id = new ObjectId();
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public boolean getSexo() {
        return sexo;
    }

    public void setSexo(boolean sexo) {
        this.sexo = sexo;
    }

    public Fecha getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Fecha fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public List getGenerosMusicales() {
        return generosMusicales;
    }

    public void setGenerosMusicales(List generosMusicales) {
        this.generosMusicales = generosMusicales;
    }

    public List getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(List peliculas) {
        this.peliculas = peliculas;
    }

    @Override
    public String toString() {
        String s, generos = "", peliculas = "";
        if (sexo) {
            s = "Mujer";
        } else {
            s = "Hombre";
        }

        for (Object genero : this.generosMusicales) {
            generos += genero + ", ";
        }
        for (Object pelicula : this.peliculas) {
            peliculas += pelicula + ", ";
        }
        return String.format("Usuario\n%s\nEdad:%d\nSexo:%s\nFecha de nacimiento:%s\nGénero de películas favoritas: %s\nGéneros musicales favoritos: %s\n",
                this.nombre, this.edad, s, this.fechaNacimiento.toString(), peliculas, generos);
    }

}
