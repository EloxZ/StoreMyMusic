package sMM;

import java.util.List;

public abstract class ConexionBD {
		static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		static final String DB_URL = "jdbc:mysql://database-iis.cobadwnzalab.eu-central-1.rds.amazonaws.com";
		static final String DB_SCHEMA = "storemymusicdb";

		//  Database credentials
		static final String USER = "santana";
		static final String PASS = "beyonce";
		
		public abstract int a�adirDisco(Disco dis);
		public abstract void insertarDatosDisco (Disco dis, int id, String fecha);
		public abstract void eliminarDisco(int id);
		public abstract List<Autor> listaAutores();
		public abstract int a�adirAutor(Autor a);
		public abstract void modificarAutor(int id, String nombre, String pais);
		public abstract void eliminarAutor(int id);
		public abstract List<Amigo> listaAmigos();
		public abstract int a�adirAmigo(Amigo a);
		public abstract void modificarAmigo(int id, String nombre);
		public abstract void eliminarAmigo(int id);
		public abstract List<Categoria> listaCategorias();
		public abstract int a�adirCategoria(Categoria c);
		public abstract void modificarCategoria(int id, String nombre);
		public abstract void eliminarCategoria(int id);
		public abstract List<Discografica> listaDiscograficas();
		public abstract int a�adirDiscografica(Discografica d);
		public abstract void modificarDiscografica(int id, String nombre);
		public abstract void eliminarDiscografica(int id);
		public abstract List<Formato> listaFormatos();
		public abstract int a�adirFormato(Formato f);
		public abstract void modificarFormato(int id, String nombre);
		public abstract void eliminarFormato(int id);
		public abstract List<Genero> listaGeneros();
		public abstract int a�adirGenero(Genero g);
		public abstract void modificarGenero(int id, String nombre);
		public abstract void eliminarGenero(int id);
		public abstract List<Tienda> listaTiendas();
		public abstract int a�adirTienda(Tienda t);
		public abstract void modificarTienda(int id, String nombre);
		public abstract void eliminarTienda(int id);
		public abstract List<Ubicacion> listaUbicaciones();
		public abstract int a�adirUbicacion(Ubicacion u);
		public abstract void modificarUbicacion(int id, String nombre);
		public abstract void eliminarUbicacion(int id);
		public abstract void a�adirDatosAdquisicion(int id, String fecha, int precio);
		public abstract void a�adirNotasValoracion(int id, String nota, float valoracion);
}
