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
    private HashMap<Integer,Autor> autores; // Mapa de autores
    private HashMap<Integer,Discografica> discograficas;
    private HashMap<Integer,Categoria> categorias;
    private HashMap<Integer,Ubicacion> ubicaciones;
    private HashMap<Integer,Tienda> tiendas;
    private HashMap<Integer,Genero> generos;

    public HashMap<Integer, Categoria> getCategorias() {
        return categorias;
    }
    
    public HashMap<Integer, Genero> getGeneros() {
        return generos;
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
}
