package sMM.modelo;

import java.util.ArrayList;
import java.util.List;

public class Soporte {
	int idSoporte;
	String nombreSoporte;
	List<Cancion> canciones;

	public Soporte(int id, String nombre){
		idSoporte = id;
		nombreSoporte = nombre;
		canciones = new ArrayList<>();
	}

	public Soporte(String nombre){
		idSoporte = 0;
		nombreSoporte = nombre;
		canciones = new ArrayList<>();
	}
	
}
