/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sMM.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author aaron
 */
public class DatosUsuarioTest {
    
    public DatosUsuarioTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getGenerosDiscos method, of class DatosUsuario.
     */
    /*@Test
    public void testGetGenerosDiscos() {
        System.out.println("getGenerosDiscos");
        DatosUsuario instance = new DatosUsuario();
        ArrayList<Pair<Integer, Integer>> expResult;
        expResult = new ArrayList<Pair<Integer, Integer>>();
        Pair<Integer,Integer> objeto = new Pair(9,99);
        expResult.add(objeto);
        ArrayList<Pair<Integer, Integer>> result = instance.getGenerosDiscos();
        assertEquals(expResult, result);
    }*/

    /**
     * Test of existeTiendaIgnorando method, of class DatosUsuario.
     */
    @Test
    public void testExisteTiendaIgnorando() {
        System.out.println("existeTienda");
        String nombre = "Ebay.es";
        int id = 0;
        DatosUsuario instance = new DatosUsuario();
        Tienda t = new Tienda("Discos Pepe");
        HashMap<Integer,Tienda > mapa = new HashMap<Integer,Tienda>();
        mapa.put(0, t);
        boolean expResult = false;
        boolean result = instance.existeTiendaIgnorando(nombre,id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
    @Test
    public void testExisteAutor() {
        System.out.println("existeAutor");
        String nombre = "Luis Fonsi";
        Autor a = new Autor("David Bisbal","España");
        HashMap<Integer,Autor> mapa = new HashMap<Integer,Autor>();
        mapa.put(0, a);
        DatosUsuario instance = new DatosUsuario();
        instance.setAutores(mapa);
        boolean expResult = false;
        boolean result = instance.existeAutor(nombre);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of existeCategoria method, of class DatosUsuario.
     */
    
    @Test
    public void testExisteCategoria() {
        System.out.println("existeCategoriaIgnorando");
        String nombre = "Hola";
        Categoria cat = new Categoria("Concierto");
        HashMap<Integer,Categoria> mapa = new HashMap<Integer,Categoria>();
        mapa.put(0, cat);
        DatosUsuario instance = new DatosUsuario();
        instance.setCategorias(mapa);
        boolean expResult = false;
        boolean result = instance.existeCategoria(nombre);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
    public void testExisteUbicacion() {
        System.out.println("existeUbicacion");
        String nombre = "Estanteria del salon";
        Ubicacion ub = new Ubicacion("Mueble del pasillo");
        HashMap<Integer,Ubicacion> mapa = new HashMap<Integer,Ubicacion>();
        DatosUsuario instance = new DatosUsuario();
        mapa.put(0,ub);
        instance.setUbicaciones(mapa);
        boolean expResult = false;
        boolean result = instance.existeUbicacion(nombre);
        assertEquals(expResult, result);
    }

    
    /**
     * Test of existeTienda method, of class DatosUsuario.
     */
    @Test
    public void testExisteTienda() {
        System.out.println("existeTienda");
        String nombre = "Ebay.es";
        DatosUsuario instance = new DatosUsuario();
        Tienda t = new Tienda("Discos Pepe");
        HashMap<Integer,Tienda > mapa = new HashMap<Integer,Tienda>();
        mapa.put(0, t);
        instance.setTiendas(mapa);
        boolean expResult = false;
        boolean result = instance.existeTienda(nombre);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of existeGenero method, of class DatosUsuario.
     */
    @Test
    public void testExisteGenero() {
        System.out.println("existeGenero");
        String nombre = "Future Bass";
        Genero gen = new Genero(0,"Drumstep");
        DatosUsuario instance = new DatosUsuario();
        HashMap<Integer,Genero> mapa = new HashMap<Integer,Genero>();
        mapa.put(0, gen);
        instance.setGeneros(mapa);
        boolean expResult = false;
        boolean result = instance.existeGenero(nombre);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of existeCategoriaIgnorando method, of class DatosUsuario.
     */
    @Test
    public void testExisteCategoriaIgnorando() {
        System.out.println("existeCategoriaIgnorando");
        String nombre = "Hola";
        int id = 0;
        Categoria cat = new Categoria("Concierto");
        HashMap<Integer,Categoria> mapa = new HashMap<Integer,Categoria>();
        mapa.put(0, cat);
        DatosUsuario instance = new DatosUsuario();
        instance.setCategorias(mapa);
        boolean expResult = false;
        boolean result = instance.existeCategoriaIgnorando(nombre, id);
        assertEquals(expResult, result);
    }

    /**
     * Test of existeGeneroIgnorando method, of class DatosUsuario.
     */
    @Test
    public void testExisteGeneroIgnorando() {
        System.out.println("existeGeneroIgnorando");
        String nombre = "J-Pop";
        int id = 3;
        Genero gen = new Genero("Drumstep");
        DatosUsuario instance = new DatosUsuario();
        HashMap<Integer,Genero> mapa = new HashMap<Integer,Genero>();
        mapa.put(0, gen);
        instance.setGeneros(mapa);
        boolean expResult = false;
        boolean result = instance.existeGeneroIgnorando(nombre, id);
        assertEquals(expResult, result);
    }

    /**
     * Test of existeAutorIgnorando method, of class DatosUsuario.
     */
    @Test
    public void testExisteAutorIgnorando() {
        System.out.println("existeAutorIgnorando");
        String nombre = "Fonsi";
        int id = 33;
        DatosUsuario instance = new DatosUsuario();
        boolean expResult = false;
        Autor a = new Autor(33,"Daddy Yankee","Puerto Rico");
        HashMap<Integer,Autor> mapa = new HashMap<Integer,Autor>();
        mapa.put(33, a);
        instance.setAutores(mapa);
        boolean result = instance.existeAutorIgnorando(nombre, id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getUbicaciones method, of class DatosUsuario.
     */  
    
        @Test
    public void testExisteUbicacionIgnorando() {
        System.out.println("existeUbicacionIgnorando");
        String nombre = "Estanteria del salon";
        int id = 0;
        Ubicacion ub = new Ubicacion("Mueble del pasillo");
        HashMap<Integer,Ubicacion> mapa = new HashMap<Integer,Ubicacion>();
        DatosUsuario instance = new DatosUsuario();
        mapa.put(0,ub);
        instance.setUbicaciones(mapa);
        boolean expResult = false;
        boolean result = instance.existeUbicacionIgnorando(nombre, id);
        assertEquals(expResult, result);
    }
    
        @Test
    public void testExisteDiscografica() {
        System.out.println("existeDiscografica");
        String nombre = "Sony Music Entertainment";
        Discografica dis = new Discografica("MCA Records");
        HashMap<Integer,Discografica> mapa = new HashMap<Integer,Discografica>();
        DatosUsuario instance = new DatosUsuario();
        instance.setDiscograficas(mapa);
        boolean expResult = false;
        boolean result = instance.existeDiscografica(nombre);
        assertEquals(expResult, result);
    }

}
