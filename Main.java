package sMM;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ConexionBD accesoBD;

		accesoBD = ConexionJDBC.getInstance();
		//		accesoBD = ConexionBaseDatosHibernate.getInstance();
		List<Autor> listAutores = accesoBD.listaAutores();
		
		Autor nuevoAutor = new Autor("SIMPLE MINDS","Reino Unido");
		int id = accesoBD.añadirAutor(nuevoAutor);
		//accesoBD.modificarAutor(id, "U2", "Reino Unido");
		//accesoBD.eliminarAutor(7);
		//accesoBD.eliminarAutor(9);
	}

}
