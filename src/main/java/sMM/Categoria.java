package sMM;

public class Categoria {

	int idCategoria;
	String nombreCategoria;

	Categoria (int id, String nombre){
		idCategoria = id;
		nombreCategoria = nombre;
	}

	Categoria (String nombre){
		idCategoria = 0;
		nombreCategoria = nombre;
	}

	public void setIdentificador(int id) {
		idCategoria = id;
	}

	public int getIdentificador() {
		return idCategoria;
	}

	public void setNombre(String nombre) {
		nombreCategoria = nombre;
	}

	public String getNombre() {
		return nombreCategoria;
	}

	@Override
	public String toString() {
		return nombreCategoria;
	}
	
}
