/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sMM.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.StringJoiner;


/**
 *
 * @author Eloy
 */
public class DatosUsuario {
    private HashMap<Integer,Disco> discos; // Mapa de discos <Id Disco, Disco> (Para optimizar busquedas)
    private ArrayList<Pair<Integer,Integer>>  autoresDiscos; // Lista de tuplas (autor, disco)
    private ArrayList<Pair<Integer,Integer>>  generosDiscos;
    private HashMap<Integer,Autor> autores; // Mapa de autores
    private HashMap<Integer,Discografica> discograficas;
    private HashMap<Integer,Categoria> categorias;
    private HashMap<Integer,Ubicacion> ubicaciones;
    private HashMap<Integer,Tienda> tiendas;
    private HashMap<Integer,Genero> generos;
    private HashMap<Integer,Amigo> amigos;
    private HashMap<Integer,Soporte> soportes;
    private HashMap<Integer,Formato> formatos;
  
    
    private HashMap<Integer,Cancion> canciones;
    
    
    public HashMap<Integer, Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(HashMap<Integer, Cancion> canciones) {
        this.canciones = canciones;
    }
    
   
    public ArrayList<Pair<Integer, Integer>> getGenerosDiscos() {
        return generosDiscos;
    }

    public void setGenerosDiscos(ArrayList<Pair<Integer, Integer>> generosDiscos) {
        this.generosDiscos = generosDiscos;
    }
    
    public HashMap<Integer, Formato> getFormatos() {
        return formatos;
    }

    public void setFormatos(HashMap<Integer, Formato> formatos) {
        this.formatos = formatos;
    }

    public HashMap<Integer, Soporte> getSoportes() {
        return soportes;
    }

    public void setSoportes(HashMap<Integer, Soporte> soportes) {
        this.soportes = soportes;
    }
    
    public HashMap<Integer, Amigo> getAmigos() {
        return amigos;
    }

    public void setAmigos(HashMap<Integer, Amigo> amigos) {
        this.amigos = amigos;
    }
    

    public HashMap<Integer, Categoria> getCategorias() {
        return categorias;
    }
    
    public HashMap<Integer, Genero> getGeneros() {
        return generos;
    }
    
    public void setGeneros(HashMap<Integer, Genero> generos) {
        this.generos = generos;
    }
    
    public boolean existeTienda(String nombre) {
        boolean res = false;
        if (tiendas != null && !tiendas.isEmpty()) {
            Collection<Tienda> setTiendas = tiendas.values();
            res = Utiles.colleccionTiendasContieneString(setTiendas, nombre);
        }
        return res;
    }
    
    public boolean existeTiendaIgnorando(String nombre, int id) {
        boolean res = false;
        if (tiendas != null && !tiendas.isEmpty()) {
            HashMap<Integer,Tienda> copia = (HashMap<Integer,Tienda>) tiendas.clone();
            copia.remove(id);
            if (copia != null && !copia.isEmpty()) {
                Collection<Tienda> setTiendas = copia.values();
                res = Utiles.colleccionTiendasContieneString(setTiendas, nombre);
            }
            
        }
        return res;
    }

    public void setCategorias(HashMap<Integer, Categoria> categorias) {
        this.categorias = categorias;
    }
    
    public void addAutor(Autor a) {
        autores.put(a.getIdentificador(),a);
    }
    
    public void modificarAutor(int id, String n, String nac) {
        Autor a = autores.getOrDefault(id, null);
        if (a != null) {
            a.setNacionalidad(nac);
            a.setNombre(n);
        }
    }
    
    public boolean existeAutor(String nombre) {
        boolean res = false;
        if (autores != null && !autores.isEmpty()) {
            Collection<Autor> setAutores = autores.values();
            res = Utiles.colleccionAutoresContieneString(setAutores, nombre);
        }
        return res;
    }
    
    public boolean existeCategoria(String nombre) {
        boolean res = false;
        if (categorias != null && !categorias.isEmpty()) {
            Collection<Categoria> setCategorias = categorias.values();
            res = Utiles.colleccionCategoriasContieneString(setCategorias, nombre);
        }
        return res;
    }
    
    public boolean existeGenero(String nombre) {
        boolean res = false;
        if (generos != null && !generos.isEmpty()) {
            Collection<Genero> setGeneros = generos.values();
            res = Utiles.colleccionGenerosContieneString(setGeneros, nombre);
        }
        return res;
    }
    
    public boolean existeCategoriaIgnorando(String nombre, int id) {
    boolean res = false;
    if (categorias != null && !categorias.isEmpty()) {
        HashMap<Integer,Categoria> copia = (HashMap<Integer,Categoria>) categorias.clone();
        copia.remove(id);
        if (copia != null && !copia.isEmpty()) {
            Collection<Categoria> setCategorias = copia.values();
            res = Utiles.colleccionCategoriasContieneString(setCategorias, nombre);
        }

    }
    return res;
    }
    
    public boolean existeGeneroIgnorando(String nombre, int id) {
    boolean res = false;
    if (generos != null && !generos.isEmpty()) {
        HashMap<Integer,Genero> copia = (HashMap<Integer,Genero>) generos.clone();
        copia.remove(id);
        if (copia != null && !copia.isEmpty()) {
            Collection<Genero> setGeneros = copia.values();
            res = Utiles.colleccionGenerosContieneString(setGeneros, nombre);
        }

    }
    return res;
    }
    
    public boolean existeAutorIgnorando(String nombre, int id) {
        boolean res = false;
        if (autores != null && !autores.isEmpty()) {
            HashMap<Integer,Autor> copia = (HashMap<Integer,Autor>) autores.clone();
            copia.remove(id);
            if (copia != null && !copia.isEmpty()) {
                Collection<Autor> setAutores = copia.values();
                res = Utiles.colleccionAutoresContieneString(setAutores, nombre);
            }
            
        }
        return res;
    }

    public HashMap<Integer, Ubicacion> getUbicaciones() {
        return ubicaciones;
    }

    public void setUbicaciones(HashMap<Integer, Ubicacion> ubicaciones) {
        this.ubicaciones = ubicaciones;
    }
    
    public Ubicacion getUbicacion(int id) {
        return ubicaciones.get(id);
    }

    public HashMap<Integer, Tienda> getTiendas() {
        return tiendas;
    }

    public void setTiendas(HashMap<Integer, Tienda> tiendas) {
        this.tiendas = tiendas;
    }

    public HashMap<Integer, Discografica> getDiscograficas() {
        return discograficas;
    }

    public void setDiscograficas(HashMap<Integer, Discografica> discograficas) {
        this.discograficas = discograficas;
    }
    
    public void addDisco(Disco d) {
        discos.put(d.getID(),d);
    }
    
    public String stringAutoresDisco(int idDisco) {
        StringJoiner joiner = new StringJoiner(", ");
        for (Pair<Integer,Integer> x : autoresDiscos) {
            if (x.getValue() == idDisco) {
                joiner.add(autores.get(x.getKey()).getNombre());
            } 
        }
        return joiner.toString();
    }
    
        public String stringGenerosDisco(int idDisco) {
        StringJoiner joiner = new StringJoiner(", ");
        for (Pair<Integer,Integer> x : generosDiscos) {
            if (x.getValue() == idDisco) {
                joiner.add(generos.get(x.getKey()).getNombre());
            } 
        }
        return joiner.toString();
    }
    
    public void setDiscos(HashMap<Integer,Disco> ds) {
        discos = ds;
    }
    
     public HashMap<Integer, Autor> getAutores() {
        return autores;
    }
     
      public void setAutores(HashMap<Integer,Autor> a) {
        autores = a;
    }
    
    public void setAutoresDiscos(ArrayList<Pair<Integer,Integer>> ad) {
        autoresDiscos = ad;
    }

    public HashMap<Integer, Disco> getDiscos() {
        return discos;
    }
    
    public ArrayList<Pair<Integer,Integer>> getAutoresDiscos() {
        return autoresDiscos;
    }

    public void eliminarAutor(int id) {
        autores.remove(id);
    }
    
    public boolean existeUbicacion(String nombre) {
        boolean res = false;
        if (ubicaciones != null && !ubicaciones.isEmpty()) {
            Collection<Ubicacion> setUbicaciones = ubicaciones.values();
            res = Utiles.colleccionUbicacionesContieneString(setUbicaciones, nombre);
        }
        return res;
    }
    
    public boolean existeUbicacionIgnorando(String nombre, int id) {
        boolean res = false;
        if (ubicaciones != null && !ubicaciones.isEmpty()) {
            HashMap<Integer,Ubicacion> copia = (HashMap<Integer,Ubicacion>) ubicaciones.clone();
            copia.remove(id);
            if (copia != null && !copia.isEmpty()) {
                Collection<Ubicacion> setUbicaciones = copia.values();
                res = Utiles.colleccionUbicacionesContieneString(setUbicaciones, nombre);
            }
            
        }
        return res;
    }   
    
       public boolean existeDiscografica(String nombre) {
        boolean res = false;
        if (discograficas != null && !discograficas.isEmpty()) {
            Collection<Discografica> setDiscograficas = discograficas.values();
            res = Utiles.colleccionDiscograficasContieneString(setDiscograficas, nombre);
        }
        return res;
    }
    
    public boolean existeDiscograficaIgnorando(String nombre, int id) {
        boolean res = false;
        if (discograficas != null && !discograficas.isEmpty()) {
            HashMap<Integer,Discografica> copia = (HashMap<Integer,Discografica>) discograficas.clone();
            copia.remove(id);
            if (copia != null && !copia.isEmpty()) {
                Collection<Discografica> setDiscograficas = copia.values();
                res = Utiles.colleccionDiscograficasContieneString(setDiscograficas, nombre);
            }
            
        }
        return res;
    }  
    
}
