/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AnimeList.App;

import AnimeList.model.Anime;
import AnimeList.model.Genero;
import java.util.ArrayList;
import java.io.*;
import java.util.Collections;
import javax.swing.table.DefaultTableModel;

/**
 *Clase principal de la aplicacion que contiene todos los metodos de esta
 * 
 * @author nfdar
 */
public class App {
    /**
     * Creamos la arraylist que va a ser nuestra lista de animes
     */
    ArrayList<Anime> animes = new ArrayList();
/**
 * Constructor para cargar la lista de animes al inicializar en los JFrame.
 */
    public App() {
//     animes.add(new Anime("Evangelion", Genero.MECHA, 9.0));
//     guardarAnimes();
        loadAnimes();
    
    }
    /**
     * Getter para poder acceder a animes desde otras clases
     * @return 
     */
    public ArrayList<Anime> getAnimes() {
        return animes;
    }
  
    /**
     * Metodo que añade un nuevo Anime a la lista 
     * 
     * El string genero lo pasamos a un ENUM Genero mediante un UpperCase 
     * Despues comprobamos que el Titulo no sea nulo porque es el unico campo que puede serlo
     * añadimos a la lista y guardamos mediante el metodo guardarAnime.
     * @param cad1
     * @param cad2
     * @param puntua
     * @param capi 
     */
    public void crearAnime(String cad1, String cad2, int puntua, int capi){  
        Genero genero;

            genero = Genero.valueOf(cad2.toUpperCase());
            
            Anime anime = new Anime(cad1, genero, puntua, capi);
            if ( (cad1 != null) && (!cad1.equals("")) ) {
                animes.add(anime); 
                guardarAnimes();
            }else{
                 System.out.println("No has introducido un anime valido.");
            }
                                   
    }
    /**
     * Metodo que utilizamos para guardar el anime en un fichero.
     */
    public void guardarAnimes(){
        try {
            ObjectOutputStream listaAnimes = new ObjectOutputStream(
                    new FileOutputStream("animes.txt"));
            
            
            listaAnimes.writeObject( animes );
            listaAnimes.close();
            } catch(IOException e){
                e.printStackTrace();
            }
    }

   
/**
 * Cargamos el anime desde el fichero
 */
public void loadAnimes(){
    try{
        ObjectInputStream listaAnimes = new ObjectInputStream(
        new FileInputStream("animes.txt"));
    
    animes = (ArrayList<Anime>) listaAnimes.readObject();
    listaAnimes.close();
    } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    
     
            
        
    }
/**
 * Creamos las columnas que va a tener el jTable y le asignamos los valores.
 * @param modelo 
 */
public void verAnimes(DefaultTableModel modelo){ 
    
    
         
         Object[] fila = new Object[modelo.getColumnCount()];
         for(int i = 0; i<animes.size(); i++){
             fila[0]= animes.get(i).getTitulo();
             fila[1]=animes.get(i).getGenero();
             fila[2]=animes.get(i).getCapitulos();
             fila[3]=animes.get(i).getPuntuacion();
             modelo.addRow(fila);
         }
    
    }
/**
 * Eliminamos un Anime de la lista obteniendo el index de este mediante el nombre y 
 * la funcion indexOf.
 * @param nombre 
 */
public void DeleteAnime(String nombre){

        int index = animes.indexOf( new Anime(nombre) );
    
        if( index !=-1){
            animes.get(index);
            animes.remove(index);
            guardarAnimes(); 
        }else{
            System.out.println("No ha introducido un Titulo correcto");
        }
        
        
        
        
        

}
/**
 * Cambiamos un Anime basandonos en el codigo de los metodos crear y borrar anime solo que recibimos mas parametros
 * tambien comprobamos que ese titulo nuevo no sea null.
 * @param nombre
 * @param generos
 * @param tituloNuevo
 * @param puntua
 * @param capi 
 */
public void updateAnime(String nombre, String generos, String tituloNuevo, int puntua, int capi){
    
    Genero genero;
    
    genero = Genero.valueOf(generos.toUpperCase());
    
    int index = animes.indexOf( new Anime(nombre) );
    
    Anime anime = new Anime( tituloNuevo,  genero,  puntua, capi);
    
        if( index !=-1){
            animes.get(index);
            animes.remove(index);
             if ( (tituloNuevo != null) && (!tituloNuevo.equals("")) ) {
            animes.add(index,anime);
            guardarAnimes(); 
            System.out.println(animes);
            }else{
                System.out.println("El nuevo Titulo tiene que tener minimo un caracter"); 
            }
        }else{
            System.out.println("No encontramos el anime introducido cambia el Titulo para que sea valido.");
        }
        
}
/**
 * Metodos que ordenan por la lista
 * utilizando el collection.sort y los compare de la clase Anime
 */
public void ordenarPuntuacion(){
    Collections.sort ( animes, Anime.comparePuntuacion );
}
public void ordenarCapitulo(){
    Collections.sort( animes, Anime.compareCapitulo);
}
public void ordenarGenero(){
    Collections.sort(animes, Anime.compareGeneroTitulo);
}
public void ordenarTitulo(){
    Collections.sort(animes, Anime.compareTituloGenero);
}



}
    