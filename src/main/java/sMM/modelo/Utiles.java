/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sMM.modelo;

import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author Eloy
 */
public class Utiles {
    public static boolean colleccionAutoresContieneString(Collection<Autor> c, String s) {
        boolean res = false;
        Iterator<Autor> iter = c.iterator();
        while(iter.hasNext() && !res) {
            res = (s.compareTo(iter.next().getNombre()) == 0);
        }
        return res;
    }
    
    public static boolean colleccionTiendasContieneString(Collection<Tienda> c, String s) {
        boolean res = false;
        Iterator<Tienda> iter = c.iterator();
        while(iter.hasNext() && !res) {
            res = (s.compareTo(iter.next().getNombre()) == 0);
        }
        return res;
        
    }
     
     public static boolean colleccionUbicacionesContieneString(Collection<Ubicacion> c, String s) {
        boolean res = false;
        Iterator<Ubicacion> iter = c.iterator();
        while(iter.hasNext() && !res) {
            res = (s.compareTo(iter.next().getNombre()) == 0);
        }
        return res;
        
    }
     
        public static boolean colleccionDiscograficasContieneString(Collection<Discografica> c, String s) {
        boolean res = false;
        Iterator<Discografica> iter = c.iterator();
        while(iter.hasNext() && !res) {
            res = (s.compareTo(iter.next().getNombre()) == 0);
        }
        return res;
        
    }
    
    public static boolean colleccionGenerosContieneString(Collection<Genero> c, String s) {
        boolean res = false;
        Iterator<Genero> iter = c.iterator();
        while(iter.hasNext() && !res) {
            res = (s.compareTo(iter.next().getNombre()) == 0);
        }
        return res;
    }
    
    public static boolean colleccionCategoriasContieneString(Collection<Categoria> c, String s) {
        boolean res = false;
        Iterator<Categoria> iter = c.iterator();
        while(iter.hasNext() && !res) {
            res = (s.compareTo(iter.next().getNombre()) == 0);
        }
        return res;
    }
}
