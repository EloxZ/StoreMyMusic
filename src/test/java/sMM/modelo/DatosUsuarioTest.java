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
 * @author Eloy
 */
public class DatosUsuarioTest {
    
    public DatosUsuarioTest() {
    }

    @org.junit.jupiter.api.BeforeAll
    public static void setUpClass() throws Exception {
    }

    @org.junit.jupiter.api.AfterAll
    public static void tearDownClass() throws Exception {
    }

    @org.junit.jupiter.api.BeforeEach
    public void setUp() throws Exception {
    }

    @org.junit.jupiter.api.AfterEach
    public void tearDown() throws Exception {
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
     * Test of getCategorias method, of class DatosUsuario.
     */
    @org.junit.jupiter.api.Test
    public void testGetCategorias() {
        System.out.println("getCategorias");
        DatosUsuario instance = new DatosUsuario();
        HashMap<Integer, Categoria> expResult = null;
        HashMap<Integer, Categoria> result = instance.getCategorias();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCategorias method, of class DatosUsuario.
     */
    @org.junit.jupiter.api.Test
    public void testSetCategorias() {
        System.out.println("setCategorias");
        HashMap<Integer, Categoria> categorias = null;
        DatosUsuario instance = new DatosUsuario();
        instance.setCategorias(categorias);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of existeAutor method, of class DatosUsuario.
     */
    @org.junit.jupiter.api.Test
    public void testExisteAutor() {
        System.out.println("existeAutor");
        String nombre = "AutorDePrueba";
        DatosUsuario instance = new DatosUsuario();
        HashMap<Integer,Autor> hmAut = new HashMap<Integer,Autor>();
        hmAut.put(4, new Autor(nombre,"USA"));
        instance.setAutores(hmAut);
        boolean expResult = true;
        boolean result = instance.existeAutor(nombre);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getUbicaciones method, of class DatosUsuario.
     */
    @org.junit.jupiter.api.Test
    public void testGetUbicaciones() {
        System.out.println("getUbicaciones");
        DatosUsuario instance = new DatosUsuario();
        HashMap<Integer, Ubicacion> expResult = null;
        HashMap<Integer, Ubicacion> result = instance.getUbicaciones();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUbicaciones method, of class DatosUsuario.
     */
    @org.junit.jupiter.api.Test
    public void testSetUbicaciones() {
        System.out.println("setUbicaciones");
        HashMap<Integer, Ubicacion> ubicaciones = null;
        DatosUsuario instance = new DatosUsuario();
        instance.setUbicaciones(ubicaciones);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUbicacion method, of class DatosUsuario.
     */
    @org.junit.jupiter.api.Test
    public void testGetUbicacion() {
        System.out.println("getUbicacion");
        int id = 0;
        DatosUsuario instance = new DatosUsuario();
        Ubicacion expResult = null;
        Ubicacion result = instance.getUbicacion(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTiendas method, of class DatosUsuario.
     */
    @org.junit.jupiter.api.Test
    public void testGetTiendas() {
        System.out.println("getTiendas");
        DatosUsuario instance = new DatosUsuario();
        HashMap<Integer, Tienda> expResult = null;
        HashMap<Integer, Tienda> result = instance.getTiendas();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTiendas method, of class DatosUsuario.
     */
    @org.junit.jupiter.api.Test
    public void testSetTiendas() {
        System.out.println("setTiendas");
        HashMap<Integer, Tienda> tiendas = null;
        DatosUsuario instance = new DatosUsuario();
        instance.setTiendas(tiendas);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDiscograficas method, of class DatosUsuario.
     */
    @org.junit.jupiter.api.Test
    public void testGetDiscograficas() {
        System.out.println("getDiscograficas");
        DatosUsuario instance = new DatosUsuario();
        HashMap<Integer, Discografica> expResult = null;
        HashMap<Integer, Discografica> result = instance.getDiscograficas();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDiscograficas method, of class DatosUsuario.
     */
    @org.junit.jupiter.api.Test
    public void testSetDiscograficas() {
        System.out.println("setDiscograficas");
        HashMap<Integer, Discografica> discograficas = null;
        DatosUsuario instance = new DatosUsuario();
        instance.setDiscograficas(discograficas);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addDisco method, of class DatosUsuario.
     */
    @org.junit.jupiter.api.Test
    public void testAddDisco() {
        System.out.println("addDisco");
        Disco d = null;
        DatosUsuario instance = new DatosUsuario();
        instance.addDisco(d);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of stringAutoresDisco method, of class DatosUsuario.
     */
    @org.junit.jupiter.api.Test
    public void testStringAutoresDisco() {
        System.out.println("stringAutoresDisco");
        int idDisco = 0;
        DatosUsuario instance = new DatosUsuario();
        String expResult = "";
        String result = instance.stringAutoresDisco(idDisco);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDiscos method, of class DatosUsuario.
     */
    @org.junit.jupiter.api.Test
    public void testSetDiscos() {
        System.out.println("setDiscos");
        HashMap<Integer, Disco> ds = null;
        DatosUsuario instance = new DatosUsuario();
        instance.setDiscos(ds);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAutores method, of class DatosUsuario.
     */
    @org.junit.jupiter.api.Test
    public void testGetAutores() {
        System.out.println("getAutores");
        DatosUsuario instance = new DatosUsuario();
        HashMap<Integer, Autor> expResult = null;
        HashMap<Integer, Autor> result = instance.getAutores();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAutores method, of class DatosUsuario.
     */
    @org.junit.jupiter.api.Test
    public void testSetAutores() {
        System.out.println("setAutores");
        HashMap<Integer, Autor> a = null;
        DatosUsuario instance = new DatosUsuario();
        instance.setAutores(a);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAutoresDiscos method, of class DatosUsuario.
     */
    @org.junit.jupiter.api.Test
    public void testSetAutoresDiscos() {
        System.out.println("setAutoresDiscos");
        ArrayList<Pair<Integer, Integer>> ad = null;
        DatosUsuario instance = new DatosUsuario();
        instance.setAutoresDiscos(ad);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDiscos method, of class DatosUsuario.
     */
    @org.junit.jupiter.api.Test
    public void testGetDiscos() {
        System.out.println("getDiscos");
        DatosUsuario instance = new DatosUsuario();
        HashMap<Integer, Disco> expResult = null;
        HashMap<Integer, Disco> result = instance.getDiscos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAutoresDiscos method, of class DatosUsuario.
     */
    @org.junit.jupiter.api.Test
    public void testGetAutoresDiscos() {
        System.out.println("getAutoresDiscos");
        DatosUsuario instance = new DatosUsuario();
        ArrayList<Pair<Integer, Integer>> expResult = null;
        ArrayList<Pair<Integer, Integer>> result = instance.getAutoresDiscos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
