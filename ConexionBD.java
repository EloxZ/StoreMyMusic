package sMM;

import java.util.List;

public abstract class ConexionBD {
		static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		static final String DB_URL = "jdbc:mysql://database-iis.cobadwnzalab.eu-central-1.rds.amazonaws.com";
		static final String DB_SCHEMA = "storemymusicdb";

		//  Database credentials
		static final String USER = "santana";
		static final String PASS = "beyonce";
		
		public abstract int añadirDisco(Disco dis);
		public abstract void insertarDatosDisco (Disco dis, int id, String fecha);
		public abstract void eliminarDisco(int id);
		public abstract List<Autor> listaAutores();
		public abstract int añadirAutor(Autor a);
		public abstract void modificarAutor(int id, String nombre, String pais);
		public abstract void eliminarAutor(int id);
		public abstract List<Amigo> listaAmigos();
		public abstract int añadirAmigo(Amigo a);
		public abstract void modificarAmigo(int id, String nombre);
		public abstract void eliminarAmigo(int id);
		public abstract List<Categoria> listaCategorias();
		public abstract int añadirCategoria(Categoria c);
		public abstract void modificarCategoria(int id, String nombre);
		public abstract void eliminarCategoria(int id);
		public abstract List<Discografica> listaDiscograficas();
		public abstract int añadirDiscografica(Discografica d);
		public abstract void modificarDiscografica(int id, String nombre);
		public abstract void eliminarDiscografica(int id);
		public abstract List<Formato> listaFormatos();
		public abstract int añadirFormato(Formato f);
		public abstract void modificarFormato(int id, String nombre);
		public abstract void eliminarFormato(int id);
		public abstract List<Genero> listaGeneros();
		public abstract int añadirGenero(Genero g);
		public abstract void modificarGenero(int id, String nombre);
		public abstract void eliminarGenero(int id);
		public abstract List<Tienda> listaTiendas();
		public abstract int añadirTienda(Tienda t);
		public abstract void modificarTienda(int id, String nombre);
		public abstract void eliminarTienda(int id);
		public abstract List<Ubicacion> listaUbicaciones();
		public abstract int añadirUbicacion(Ubicacion u);
		public abstract void modificarUbicacion(int id, String nombre);
		public abstract void eliminarUbicacion(int id);
		public abstract void añadirDatosAdquisicion(int id, String fecha, int precio);
		public abstract void añadirNotasValoracion(int id, String nota, float valoracion);
}
