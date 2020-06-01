/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sMM.basededatos;

import java.util.HashMap;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import sMM.modelo.Disco;

/**
 *
 * @author Ivanchu
 */
public class PruebaMetodosBasededatos {

    public PruebaMetodosBasededatos() {
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    private final ConexionJDBC conexion = new ConexionJDBC();

    @Test
    public void testGetDiscos() 
    {
        HashMap<Integer, Disco> mapa = conexion.getDiscos();

        
        Disco d = new Disco(33);
        d.setTitulo("OK Computer");
        d.setAnoSalida(1997);
        d.setAnoEdicion(2009);
        d.setNumeroCatalogo("23");
        d.setCodigoBarras("AB12345678");

        d.setPrecioCompra(-1);
        d.setNotas("Regalo del tío Tom");
        d.setValoracion(8);
        d.setPaisEdicion("Inglaterra");
        d.setPosicionEnUbicacion("Caja 23");
        d.setIdCategoria(1);
        d.setIdDiscografica(4);
        d.setIdUbicacion(3);

        d.setPortada("wtmepxnlfydsh0tq6zan");
        Disco d2 = mapa.get(99);
        System.out.println(d.getCodigoColeccion());
        System.out.println(d.getFechaCompra());
        System.out.println(d.getIdTienda());

        assertEquals(d.getTitulo(), mapa.get(99).getTitulo());
        assertEquals(d.getAnoEdicion(), mapa.get(99).getAnoEdicion());
        assertEquals(d.getAnoSalida(), mapa.get(99).getAnoSalida());
        assertEquals(d.getNumeroCatalogo(), mapa.get(99).getNumeroCatalogo());
        assertEquals(d.getCodigoBarras(), mapa.get(99).getCodigoBarras());
        assertEquals(d.getCodigoColeccion(), mapa.get(99).getCodigoColeccion());
        assertEquals(d.getIdDiscografica(),mapa.get(99).getIdDiscografica());
        
    }

    @Test
    public void testListaDicograficas() {
        assertEquals("MCA Records", conexion.listaDiscograficas().get(1).getNombre());
    }
    @Test
    public void testListaCategorias() {
        assertEquals("Concierto", conexion.listaCategorias().get(2).getNombre());
    }
    @Test
    public void testListaAutores() {
        assertEquals("Reino Unido", conexion.listaAutores().get(19).getNacionalidad());
    }
    @Test
    public void testListaSoportes() {
        assertEquals(1, conexion.listaSoportes().get(7).getIDFormato());
    }
   
    
  
    
}
