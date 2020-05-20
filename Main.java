package sMM;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ConexionBD accesoBD;

		accesoBD = ConexionJDBC.getInstance();
		//		accesoBD = ConexionBaseDatosHibernate.getInstance();
		List<Autor> listAutores = accesoBD.listaAutores();
		List<Amigo> listAmigos = accesoBD.listaAmigos();
		
		//Autor nuevoAutor = new Autor("ROBERT REED","Reino Unido");
		//int id = accesoBD.añadirAutor(nuevoAutor);
		//accesoBD.modificarAutor(id, "U2", "Reino Unido");
		//accesoBD.eliminarAutor(7);
		//accesoBD.eliminarAutor(9);
		//listAutores = accesoBD.listaAutores();
		int id=1;
		accesoBD.modificarAmigo(id, "Pepe El Caras 2");
		//id = accesoBD.añadirAmigo(nuevoAmigo);
	}

}
