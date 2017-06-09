/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AnimeList.model;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;
import java.io.*;

/**
 * Created by nfdar on 24/05/2017.
 */
public class Anime implements Serializable, Comparable<Anime>, Comparator<Anime>{
    /**
     * Iniciamos las variables
     * El serializable fue un sufrimiento :)
     */
    private static final long serialVersionUID = 582657575207694850L;

    private String titulo;
    private Genero genero;
    private int puntuacion;
    private int capitulos;
    
      /**
     * Metodos Comparators se encargan de comparar
     */
    public static Comparator<Anime> compareGeneroTitulo = new Comparator<Anime>() {
        @Override
        public int compare(Anime anime1, Anime anime2) {
            int res = anime1.getGenero().compareTo(anime2.getGenero());
            if( res != 0) { return res; }

            return anime1.getTitulo().compareTo(anime2.getTitulo());
        }
    };
    
    public static Comparator<Anime> compareTituloGenero = new Comparator<Anime>() {
        @Override
        public int compare(Anime anime1, Anime anime2) {
            int res = anime1.getTitulo().compareTo(anime2.getTitulo());
            if( res != 0) { return res; }

            return anime1.getGenero().compareTo(anime2.getGenero());
        }
    };


    public static Comparator<Anime> compareCapitulo = new Comparator<Anime>() {
        @Override
        public int compare(Anime anime1, Anime anime2) {
            return  anime2.getCapitulos() - anime1.getCapitulos();
        }
    };

    public static Comparator<Anime> comparePuntuacion = new Comparator<Anime>() {
        @Override
        public int compare(Anime anime1, Anime anime2) {
            return anime2.getPuntuacion() - anime1.getPuntuacion();
        }
    };

    public Anime(){
        
    }
/**
 * Constructor utilizado para buscar un Anime por su titulo
 * @param titulo 
 */
    public Anime(String titulo) {
        this.titulo = titulo;
    }
    
/**
 * Constructor General
 * @param titulo
 * @param genero
 * @param puntuacion
 * @param capitulos 
 */
    public Anime(String titulo, Genero genero, int puntuacion, int capitulos) {
       try {
            this.setTitulo(titulo);
        } catch (TituloVacioException e) {
            e.printStackTrace();
        }
       this.setGenero(genero);
        this.setPuntuacion(puntuacion);

        this.setCapitulos(capitulos);
    }

    
/**
 * Metodos getters and setters
 * @return 
 */
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) throws TituloVacioException { 

        if ( titulo.equals("")){
            throw new TituloVacioException("Este campo no puede estar vacio");
        }else {
            this.titulo = titulo;
        }
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }
    

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
    
    public int getCapitulos() {
        return capitulos;
    }

    public void setCapitulos(int capitulos) {
        this.capitulos = capitulos;
    }
    
    /**
     * toString nos permite imprimir por la consola las variables.
     * @return 
     */
    @Override
    public String toString() {
         return "titulo='" + titulo + ", genero=" + genero  + " capitulos=" + capitulos + "puntuacion=" + puntuacion;
        
    }
    /**
     * Metodo equals para realizar las busquedas por medio de indexOf para poder Borrar y Actualizar
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        // Compruebo si es el mismo objeto
        if ( this == obj ) { return true; }

        // Compruebo si el objeto es nulo
        if ( obj == null) { return false; }

        // Comprobamos si son de la misma clase
        if( this.getClass() != obj.getClass() ){ return false; }

        Anime al = (Anime) obj;

        return Objects.equals( this.getTitulo(), al.getTitulo() );
    }
    /**
     * Metodo que compara Titulos
     * @param anime
     * @return 
     */
    @Override
    public int compareTo(Anime anime) {
        return this.getTitulo().compareTo(anime.getTitulo());
    }
    
    /**
     * 
     * @param anime1
     * @param anime2
     * @return 
     */

    @Override
    public int compare(Anime anime1, Anime anime2) {
        int res;

        res = anime1.getTitulo().compareToIgnoreCase(anime2.getTitulo()) ;

        if (res != 0){
            return res;
        }
        return (int) (anime1.getCapitulos() - anime2.getCapitulos());
    }
}
    /**
     * Excepcion para prevenir que metan un titulo vacio en CrearAnime, Borrar o Actualizar
     */
class TituloVacioException extends IOException {public TituloVacioException (String message) { super(message);  }}
     
