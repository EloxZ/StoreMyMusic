package sMM.modelo;

public class Ubicacion {
	int idUbicacion;
	String nombreUbicacion;
	
	public Ubicacion (int id, String nombre){
		idUbicacion = id;
		nombreUbicacion = nombre;
	}

	public Ubicacion (String nombre){
		idUbicacion = 0;
		nombreUbicacion = nombre;
	}

	public void setIdentificador(int id) {
		idUbicacion = id;
	}

	public int getIdentificador() {
		return idUbicacion;
	}

	public void setNombre(String nombre) {
		nombreUbicacion = nombre;
	}

	public String getNombre() {
		return nombreUbicacion;
	}

	@Override
	public String toString() {
		return nombreUbicacion;
	}
}
