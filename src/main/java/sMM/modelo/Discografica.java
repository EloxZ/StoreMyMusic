package sMM.modelo;

public class Discografica {

	int idDiscografica;
	String nombreDiscografica;

	public Discografica (int id, String nombre){
		idDiscografica = id;
		nombreDiscografica = nombre;
	}

	public Discografica (String nombre){
		idDiscografica = 0;
		nombreDiscografica = nombre;
	}

	public void setIdentificador(int id) {
		idDiscografica = id;
	}

	public int getIdentificador() {
		return idDiscografica;
	}

	public void setNombre(String nombre) {
		nombreDiscografica = nombre;
	}

	public String getNombre() {
		return nombreDiscografica;
	}

	@Override
	public String toString() {
		return nombreDiscografica;
	}

}
