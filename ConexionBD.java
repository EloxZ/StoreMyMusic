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
		public abstract void eliminarDisco(int id);
		protected abstract List<Autor> listaAutores();
		public abstract void a�adirDatosAdquisicion(int id, String fecha, int precio);
		public abstract void a�adirNotasValoracion(int id, String nota, float valoracion);
}
