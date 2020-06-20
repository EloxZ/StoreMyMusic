/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sMM.basededatos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import sMM.modelo.Amigo;
import sMM.modelo.Autor;
import sMM.modelo.Categoria;
import sMM.modelo.Disco;
import sMM.modelo.Discografica;
import sMM.modelo.Formato;
import sMM.modelo.Genero;
import sMM.modelo.Pair;
import sMM.modelo.Soporte;
import sMM.modelo.Tienda;
import sMM.modelo.Ubicacion;

/**
 *
 * @author aaron
 */
public class ConexionJDBCTest {
    
    public ConexionJDBCTest() {
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
     * Test of eliminarDisco method, of class ConexionJDBC.
     */
    @Test
    public void testEliminarDisco() {
        System.out.println("eliminarDisco");
        int id = 0;
        ConexionJDBC instance = new ConexionJDBC();
        instance.eliminarDisco(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertarPortada method, of class ConexionJDBC.
     */
    @Test
    public void testInsertarPortada() {
        System.out.println("insertarPortada");
        int id = 0;
        String portada = "";
        ConexionJDBC instance = new ConexionJDBC();
        instance.insertarPortada(id, portada);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertarDatosAdquisicion method, of class ConexionJDBC.
     */
    @Test
    public void testInsertarDatosAdquisicion() {
        System.out.println("insertarDatosAdquisicion");
        int id = 0;
        String codCol = "";
        String fechaCompra = "";
        float precio = 0.0F;
        int tienda = 0;
        ConexionJDBC instance = new ConexionJDBC();
        instance.insertarDatosAdquisicion(id, codCol, fechaCompra, precio, tienda);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertarDatosDisco method, of class ConexionJDBC.
     */
    @Test
    public void testInsertarDatosDisco_3args() {
        System.out.println("insertarDatosDisco");
        Disco dis = null;
        int id = 0;
        String fecha = "";
        ConexionJDBC instance = new ConexionJDBC();
        instance.insertarDatosDisco(dis, id, fecha);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listaAutores method, of class ConexionJDBC.
     */
    @Test
    public void testListaAutores() {
        System.out.println("listaAutores");
        ConexionJDBC instance = new ConexionJDBC();
        HashMap<Integer, Autor> expResult = null;
        HashMap<Integer, Autor> result = instance.listaAutores();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of añadirAutor method, of class ConexionJDBC.
     */
    @Test
    public void testAñadirAutor() {
        System.out.println("a\u00f1adirAutor");
        Autor a = null;
        ConexionJDBC instance = new ConexionJDBC();
        int expResult = 0;
        int result = instance.añadirAutor(a);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modificarAutor method, of class ConexionJDBC.
     */
    @Test
    public void testModificarAutor() {
        System.out.println("modificarAutor");
        int id = 0;
        String nombre = "";
        String pais = "";
        ConexionJDBC instance = new ConexionJDBC();
        instance.modificarAutor(id, nombre, pais);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminarAutor method, of class ConexionJDBC.
     */
    @Test
    public void testEliminarAutor() {
        System.out.println("eliminarAutor");
        int id = 0;
        ConexionJDBC instance = new ConexionJDBC();
        instance.eliminarAutor(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of añadirDatosAdquisicion method, of class ConexionJDBC.
     */
    @Test
    public void testAñadirDatosAdquisicion() {
        System.out.println("a\u00f1adirDatosAdquisicion");
        int id = 0;
        String fecha = "";
        int precio = 0;
        ConexionJDBC instance = new ConexionJDBC();
        instance.añadirDatosAdquisicion(id, fecha, precio);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of añadirNotasValoracion method, of class ConexionJDBC.
     */
    @Test
    public void testAñadirNotasValoracion() {
        System.out.println("a\u00f1adirNotasValoracion");
        int id = 0;
        String nota = "";
        float valoracion = 0.0F;
        ConexionJDBC instance = new ConexionJDBC();
        instance.añadirNotasValoracion(id, nota, valoracion);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listaAmigos method, of class ConexionJDBC.
     */
    @Test
    public void testListaAmigos() {
        System.out.println("listaAmigos");
        ConexionJDBC instance = new ConexionJDBC();
        HashMap<Integer, Amigo> expResult = null;
        HashMap<Integer, Amigo> result = instance.listaAmigos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of añadirAmigo method, of class ConexionJDBC.
     */
    @Test
    public void testAñadirAmigo() {
        System.out.println("a\u00f1adirAmigo");
        Amigo a = null;
        ConexionJDBC instance = new ConexionJDBC();
        int expResult = 0;
        int result = instance.añadirAmigo(a);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modificarAmigo method, of class ConexionJDBC.
     */
    @Test
    public void testModificarAmigo() {
        System.out.println("modificarAmigo");
        int id = 0;
        String nombre = "";
        ConexionJDBC instance = new ConexionJDBC();
        instance.modificarAmigo(id, nombre);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminarAmigo method, of class ConexionJDBC.
     */
    @Test
    public void testEliminarAmigo() {
        System.out.println("eliminarAmigo");
        int id = 0;
        ConexionJDBC instance = new ConexionJDBC();
        instance.eliminarAmigo(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listaCategorias method, of class ConexionJDBC.
     */
    @Test
    public void testListaCategorias() {
        System.out.println("listaCategorias");
        ConexionJDBC instance = new ConexionJDBC();
        HashMap<Integer, Categoria> expResult = null;
        HashMap<Integer, Categoria> result = instance.listaCategorias();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of añadirCategoria method, of class ConexionJDBC.
     */
    @Test
    public void testAñadirCategoria() {
        System.out.println("a\u00f1adirCategoria");
        Categoria c = null;
        ConexionJDBC instance = new ConexionJDBC();
        int expResult = 0;
        int result = instance.añadirCategoria(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modificarCategoria method, of class ConexionJDBC.
     */
    @Test
    public void testModificarCategoria() {
        System.out.println("modificarCategoria");
        int id = 0;
        String nombre = "";
        ConexionJDBC instance = new ConexionJDBC();
        instance.modificarCategoria(id, nombre);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminarCategoria method, of class ConexionJDBC.
     */
    @Test
    public void testEliminarCategoria() {
        System.out.println("eliminarCategoria");
        int id = 0;
        ConexionJDBC instance = new ConexionJDBC();
        instance.eliminarCategoria(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listaDiscograficas method, of class ConexionJDBC.
     */
    @Test
    public void testListaDiscograficas() {
        System.out.println("listaDiscograficas");
        ConexionJDBC instance = new ConexionJDBC();
        HashMap<Integer, Discografica> expResult = null;
        HashMap<Integer, Discografica> result = instance.listaDiscograficas();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of añadirDiscografica method, of class ConexionJDBC.
     */
    @Test
    public void testAñadirDiscografica() {
        System.out.println("a\u00f1adirDiscografica");
        Discografica d = null;
        ConexionJDBC instance = new ConexionJDBC();
        int expResult = 0;
        int result = instance.añadirDiscografica(d);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modificarDiscografica method, of class ConexionJDBC.
     */
    @Test
    public void testModificarDiscografica() {
        System.out.println("modificarDiscografica");
        int id = 0;
        String nombre = "";
        ConexionJDBC instance = new ConexionJDBC();
        instance.modificarDiscografica(id, nombre);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminarDiscografica method, of class ConexionJDBC.
     */
    @Test
    public void testEliminarDiscografica() {
        System.out.println("eliminarDiscografica");
        int id = 0;
        ConexionJDBC instance = new ConexionJDBC();
        instance.eliminarDiscografica(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listaFormatos method, of class ConexionJDBC.
     */
    @Test
    public void testListaFormatos() {
        System.out.println("listaFormatos");
        ConexionJDBC instance = new ConexionJDBC();
        HashMap<Integer, Formato> expResult = null;
        HashMap<Integer, Formato> result = instance.listaFormatos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of añadirFormato method, of class ConexionJDBC.
     */
    @Test
    public void testAñadirFormato() {
        System.out.println("a\u00f1adirFormato");
        Formato f = null;
        ConexionJDBC instance = new ConexionJDBC();
        int expResult = 0;
        int result = instance.añadirFormato(f);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modificarFormato method, of class ConexionJDBC.
     */
    @Test
    public void testModificarFormato() {
        System.out.println("modificarFormato");
        int id = 0;
        String nombre = "";
        ConexionJDBC instance = new ConexionJDBC();
        instance.modificarFormato(id, nombre);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminarFormato method, of class ConexionJDBC.
     */
    @Test
    public void testEliminarFormato() {
        System.out.println("eliminarFormato");
        int id = 0;
        ConexionJDBC instance = new ConexionJDBC();
        instance.eliminarFormato(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listaGeneros method, of class ConexionJDBC.
     */
    @Test
    public void testListaGeneros() {
        System.out.println("listaGeneros");
        ConexionJDBC instance = new ConexionJDBC();
        HashMap<Integer, Genero> expResult = null;
        HashMap<Integer, Genero> result = instance.listaGeneros();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of añadirGenero method, of class ConexionJDBC.
     */
    @Test
    public void testAñadirGenero() {
        System.out.println("a\u00f1adirGenero");
        Genero g = null;
        ConexionJDBC instance = new ConexionJDBC();
        int expResult = 0;
        int result = instance.añadirGenero(g);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modificarGenero method, of class ConexionJDBC.
     */
    @Test
    public void testModificarGenero() {
        System.out.println("modificarGenero");
        int id = 0;
        String nombre = "";
        ConexionJDBC instance = new ConexionJDBC();
        instance.modificarGenero(id, nombre);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminarGenero method, of class ConexionJDBC.
     */
    @Test
    public void testEliminarGenero() {
        System.out.println("eliminarGenero");
        int id = 0;
        ConexionJDBC instance = new ConexionJDBC();
        instance.eliminarGenero(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listaTiendas method, of class ConexionJDBC.
     */
    @Test
    public void testListaTiendas() {
        System.out.println("listaTiendas");
        ConexionJDBC instance = new ConexionJDBC();
        HashMap<Integer, Tienda> expResult = null;
        HashMap<Integer, Tienda> result = instance.listaTiendas();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of añadirTienda method, of class ConexionJDBC.
     */
    @Test
    public void testAñadirTienda() {
        System.out.println("a\u00f1adirTienda");
        Tienda t = null;
        ConexionJDBC instance = new ConexionJDBC();
        int expResult = 0;
        int result = instance.añadirTienda(t);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modificarTienda method, of class ConexionJDBC.
     */
    @Test
    public void testModificarTienda() {
        System.out.println("modificarTienda");
        int id = 0;
        String nombre = "";
        ConexionJDBC instance = new ConexionJDBC();
        instance.modificarTienda(id, nombre);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminarTienda method, of class ConexionJDBC.
     */
    @Test
    public void testEliminarTienda() {
        System.out.println("eliminarTienda");
        int id = 0;
        ConexionJDBC instance = new ConexionJDBC();
        instance.eliminarTienda(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listaUbicaciones method, of class ConexionJDBC.
     */
    @Test
    public void testListaUbicaciones() {
        System.out.println("listaUbicaciones");
        ConexionJDBC instance = new ConexionJDBC();
        HashMap<Integer, Ubicacion> expResult = null;
        HashMap<Integer, Ubicacion> result = instance.listaUbicaciones();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of añadirUbicacion method, of class ConexionJDBC.
     */
    @Test
    public void testAñadirUbicacion() {
        System.out.println("a\u00f1adirUbicacion");
        Ubicacion u = null;
        ConexionJDBC instance = new ConexionJDBC();
        int expResult = 0;
        int result = instance.añadirUbicacion(u);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modificarUbicacion method, of class ConexionJDBC.
     */
    @Test
    public void testModificarUbicacion() {
        System.out.println("modificarUbicacion");
        int id = 0;
        String nombre = "";
        ConexionJDBC instance = new ConexionJDBC();
        instance.modificarUbicacion(id, nombre);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminarUbicacion method, of class ConexionJDBC.
     */
    @Test
    public void testEliminarUbicacion() {
        System.out.println("eliminarUbicacion");
        int id = 0;
        ConexionJDBC instance = new ConexionJDBC();
        instance.eliminarUbicacion(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertarDatosDisco method, of class ConexionJDBC.
     */
    @Test
    public void testInsertarDatosDisco_Disco_int() {
        System.out.println("insertarDatosDisco");
        Disco dis = null;
        int id = 0;
        ConexionJDBC instance = new ConexionJDBC();
        instance.insertarDatosDisco(dis, id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listaSoportes method, of class ConexionJDBC.
     */
    @Test
    public void testListaSoportes() {
        System.out.println("listaSoportes");
        ConexionJDBC instance = new ConexionJDBC();
        HashMap<Integer, Soporte> expResult = null;
        HashMap<Integer, Soporte> result = instance.listaSoportes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of añadirSoporte method, of class ConexionJDBC.
     */
    @Test
    public void testAñadirSoporte() {
        System.out.println("a\u00f1adirSoporte");
        Soporte s = null;
        ConexionJDBC instance = new ConexionJDBC();
        int expResult = 0;
        int result = instance.añadirSoporte(s);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modificarSoporte method, of class ConexionJDBC.
     */
    @Test
    public void testModificarSoporte() {
        System.out.println("modificarSoporte");
        int id = 0;
        String nombre = "";
        int idDisco = 0;
        int idFor = 0;
        ConexionJDBC instance = new ConexionJDBC();
        instance.modificarSoporte(id, nombre, idDisco, idFor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminarSoporte method, of class ConexionJDBC.
     */
    @Test
    public void testEliminarSoporte() {
        System.out.println("eliminarSoporte");
        int id = 0;
        ConexionJDBC instance = new ConexionJDBC();
        instance.eliminarSoporte(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
