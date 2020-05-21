package sMM;

public class Amigo {

	int idAmigo;
	String nombreAmigo;

	Amigo (int id, String nombre){
		idAmigo = id;
		nombreAmigo = nombre;
	}
	
	Amigo (String nombre){
		idAmigo = 0;
		nombreAmigo = nombre;
	}
	
	public void setIdentificador(int id) {
		idAmigo = id;
	}
	
	public int getIdentificador() {
		return idAmigo;
	}
	
	public void setNombre(String nombre) {
		nombreAmigo = nombre;
	}
	
	public String getNombre() {
		return nombreAmigo;
	}
	
	@Override
	public String toString() {
		return nombreAmigo;
	}



}
