package sMM;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ConexionBD accesoBD;

		accesoBD = ConexionJDBC.getInstance();
		//		accesoBD = ConexionBaseDatosHibernate.getInstance();
		List<Autor> listAutores = accesoBD.listaAutores();
		
	}

}
