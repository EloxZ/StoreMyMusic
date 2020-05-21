/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sMM;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringJoiner;
import javafx.util.Pair;


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

    public HashMap<Integer, Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(HashMap<Integer, Categoria> categorias) {
        this.categorias = categorias;
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
}
