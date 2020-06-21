/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sMM.modelo;

import java.util.Collection;
import java.util.Iterator;
import java.util.StringJoiner;

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
    
    //Inicio David 21/06
    public static String segundos2String(int t){
        StringJoiner joiner = new StringJoiner("");
        int hor = t / 3600;
        if (hor!=0){
            if (hor<10){
                joiner.add("0");
            }
            joiner.add(String.valueOf(hor));
            joiner.add(":");
        }
        int min = (t-hor*3600)/60;
        System.out.println(min);
        if (min<10){
            joiner.add("0");
        }
        joiner.add(String.valueOf(min));
        joiner.add(":");
        int seg = t-(hor*3600+min*60);
        if (seg<10){
            joiner.add("0");
        }
        joiner.add(String.valueOf(seg));
        return String.valueOf(joiner);
    }
    //Fin David 21/06
    
}
