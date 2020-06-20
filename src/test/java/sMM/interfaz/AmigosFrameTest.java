/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sMM.interfaz;

import java.awt.Point;
import java.awt.event.MouseEvent;
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
public class AmigosFrameTest {
    
    public AmigosFrameTest() {
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
     * Test of getScreenLocation method, of class AmigosFrame.
     */
    @Test
    public void testGetScreenLocation() {
        System.out.println("getScreenLocation");
        MouseEvent e = null;
        AmigosFrame instance = null;
        Point expResult = null;
        Point result = instance.getScreenLocation(e);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cargarDatos method, of class AmigosFrame.
     */
    @Test
    public void testCargarDatos() {
        System.out.println("cargarDatos");
        AmigosFrame instance = null;
        instance.cargarDatos();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of configVentana method, of class AmigosFrame.
     */
    @Test
    public void testConfigVentana() {
        System.out.println("configVentana");
        AmigosFrame instance = null;
        instance.configVentana();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of configComponentes method, of class AmigosFrame.
     */
    @Test
    public void testConfigComponentes() {
        System.out.println("configComponentes");
        AmigosFrame instance = null;
        instance.configComponentes();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class AmigosFrame.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        AmigosFrame.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
