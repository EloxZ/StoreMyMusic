/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sMM.basededatos;

import sMM.modelo.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Aaron
 */
public abstract class ConexionBD {
		static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		static final String DB_URL = "jdbc:mysql://database-iis.cobadwnzalab.eu-central-1.rds.amazonaws.com";
		static final String DB_SCHEMA = "storemymusicdb";

		//  Database credentials
		static final String USER = "santana";
		static final String PASS = "beyonce";
		
                public abstract HashMap<Integer,Disco> getDiscos();
                public abstract ArrayList<Pair<Integer,Integer>>  getAutoresDiscos();
                public abstract int añadirDisco(Disco dis);
		public abstract void insertarDatosDisco (Disco dis, int id);
		public abstract void eliminarDisco(int id);
		public abstract HashMap<Integer,Autor> listaAutores();
		public abstract int añadirAutor(Autor a);
		public abstract void modificarAutor(int id, String nombre, String pais);
		public abstract void eliminarAutor(int id);
		public abstract HashMap<Integer,Amigo> listaAmigos();
		public abstract int añadirAmigo(Amigo a);
		public abstract void modificarAmigo(int id, String nombre);
		public abstract void eliminarAmigo(int id);
		public abstract HashMap<Integer,Categoria> listaCategorias();
		public abstract int añadirCategoria(Categoria c);
		public abstract void modificarCategoria(int id, String nombre);
		public abstract void eliminarCategoria(int id);
		public abstract HashMap<Integer,Discografica> listaDiscograficas();
		public abstract int añadirDiscografica(Discografica d);
		public abstract void modificarDiscografica(int id, String nombre);
		public abstract void eliminarDiscografica(int id);
		public abstract HashMap<Integer,Formato> listaFormatos();
		public abstract int añadirFormato(Formato f);
		public abstract void modificarFormato(int id, String nombre);
		public abstract void eliminarFormato(int id);
		public abstract HashMap<Integer,Genero> listaGeneros();
		public abstract int añadirGenero(Genero g);
		public abstract void modificarGenero(int id, String nombre);
		public abstract void eliminarGenero(int id);
		public abstract HashMap<Integer,Tienda> listaTiendas();
		public abstract int añadirTienda(Tienda t);
		public abstract void modificarTienda(int id, String nombre);
		public abstract void eliminarTienda(int id);
		public abstract HashMap<Integer,Ubicacion> listaUbicaciones();
		public abstract int añadirUbicacion(Ubicacion u);
		public abstract void modificarUbicacion(int id, String nombre);
		public abstract void eliminarUbicacion(int id);
		public abstract void añadirDatosAdquisicion(int id, String fecha, int precio);
		public abstract void añadirNotasValoracion(int id, String nota, float valoracion);
                public abstract HashMap<Integer,Soporte> listaSoportes();
                public abstract int añadirSoporte(Soporte s);
                public abstract void modificarSoporte(int id, String nombre, int idDis, int idFor);
                public abstract void eliminarSoporte (int id);
                public abstract void insertarPortada (int id, String p);
                public abstract ArrayList<Pair<Integer,Integer>>  getGenerosDiscos();
                public abstract void añadirGenerosDisco(int id, Set<Integer> generos);
                public abstract void añadirAutoresDisco(int id, Set<Integer> autores);
                public abstract void insertarDatosAdquisicion(int id, String codCol, String fechaCompra, float precio, int tienda);
                public abstract void modificarDisco(Disco dis);
                public abstract void eliminarGenerosAutoresDisco(int id);
                public abstract HashMap<Integer,Cancion> listaCanciones();
                public abstract int añadirCancion (Cancion c);
                public abstract void modificarCancion(int id, int tr, String titulo, String aut, int dur, String not, double val, int idSop);
                public abstract void eliminarCancion(int id);
}
