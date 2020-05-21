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
		List<Categoria> listCategorias = accesoBD.listaCategorias();
		List<Discografica> listDiscograficas = accesoBD.listaDiscograficas();
		List<Formato> listFormatos = accesoBD.listaFormatos();
		List<Genero> listGeneros = accesoBD.listaGeneros();
		List<Tienda> listTiendas = accesoBD.listaTiendas();
		List<Ubicacion> listUbicaciones = accesoBD.listaUbicaciones();
		
		//Autor nuevoAutor = new Autor("ROBERT REED","Reino Unido");
		//int id = accesoBD.a�adirAutor(nuevoAutor);
		//accesoBD.modificarAutor(id, "U2", "Reino Unido");
		//accesoBD.eliminarAutor(7);
		//accesoBD.eliminarAutor(9);
		//listAutores = accesoBD.listaAutores();
		//int id=1;
		//accesoBD.modificarAmigo(id, "Pepe El Caras 2");
		//id = accesoBD.a�adirAmigo(nuevoAmigo);
		//Categoria nuevaCategoria = new Categoria("Discograf�a");
		//int id = accesoBD.a�adirCategoria(nuevaCategoria);
		//int id=1;
		//accesoBD.modificarCategoria(id, "Discograf�a");
		//Discografica nuevaDiscografica = new Discografica("MCA ReCords");
		//int id = accesoBD.a�adirDiscografica(nuevaDiscografica);
		//int id=1;
		//accesoBD.modificarDiscografica(id, "MCA Records");
		//Formato nuevoFormato = new Formato("Compact Disc");
		//int id = accesoBD.a�adirFormato(nuevoFormato);
		//int id=1;
		//accesoBD.modificarFormato(id, "CD");
		//Genero nuevoGenero = new Genero("Rockkkk");
		//int id = accesoBD.a�adirGenero(nuevoGenero);
		//int id=1;
		//accesoBD.modificarGenero(id, "Rock");
		//Tienda nuevaTienda = new Tienda("FNACCC");
		//int id = accesoBD.a�adirTienda(nuevaTienda);
		//int id=1;
		//accesoBD.modificarTienda(id, "Amazon.es");
		//Ubicacion nuevaUbicacion = new Ubicacion("Billy Sal�nnnn");
		//int id = accesoBD.a�adirUbicacion(nuevaUbicacion);
		int id=1;
		accesoBD.modificarUbicacion(id, "Billy Sal�n");
	}

}
