package sMM;

public class Formato {

	int idFormato;
	String nombreFormato;
	
	Formato (int id, String nombre){
		idFormato = id;
		nombreFormato = nombre;
	}

	Formato (String nombre){
		idFormato = 0;
		nombreFormato = nombre;
	}

	public void setIdentificador(int id) {
		idFormato = id;
	}

	public int getIdentificador() {
		return idFormato;
	}

	public void setNombre(String nombre) {
		nombreFormato = nombre;
	}

	public String getNombre() {
		return nombreFormato;
	}

	@Override
	public String toString() {
		return nombreFormato;
	}
	
}
