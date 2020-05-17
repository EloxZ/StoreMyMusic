package sMM;

public class Autor {
	int idAutor;
	String nombreAutor;
	
	Autor (int id, String nombre){
		idAutor = id;
		nombreAutor = nombre;
	}

	Autor (String nombre){
		idAutor = 0;
		nombreAutor = nombre;
	}

	public void setIdentificador(int id) {
		idAutor = id;
	}

	public int getIdentificador() {
		return idAutor;
	}

	public void setNombre(String nombre) {
		nombreAutor = nombre;
	}

	public String getNombre() {
		return nombreAutor;
	}

	@Override
	public String toString() {
		return nombreAutor;
	}
}

