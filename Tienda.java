package sMM;

public class Tienda {
	int idTienda;
	String nombreTienda;
	
	Tienda (int id, String nombre){
		idTienda = id;
		nombreTienda = nombre;
	}

	Tienda (String nombre){
		idTienda = 0;
		nombreTienda = nombre;
	}

	public void setIdentificador(int id) {
		idTienda = id;
	}

	public int getIdentificador() {
		return idTienda;
	}

	public void setNombre(String nombre) {
		nombreTienda = nombre;
	}

	public String getNombre() {
		return nombreTienda;
	}

	@Override
	public String toString() {
		return nombreTienda;
	}
}
