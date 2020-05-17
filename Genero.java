package sMM;

public class Genero {

	int idGenero;
	String nombreGenero;
	
	Genero (int id, String nombre){
		idGenero = id;
		nombreGenero = nombre;
	}

	Genero (String nombre){
		idGenero = 0;
		nombreGenero = nombre;
	}

	public void setIdentificador(int id) {
		idGenero = id;
	}

	public int getIdentificador() {
		return idGenero;
	}

	public void setNombre(String nombre) {
		nombreGenero = nombre;
	}

	public String getNombre() {
		return nombreGenero;
	}

	@Override
	public String toString() {
		return nombreGenero;
	}

}
