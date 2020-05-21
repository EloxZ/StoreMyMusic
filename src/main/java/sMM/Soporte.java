package sMM;

import java.util.ArrayList;
import java.util.List;

public class Soporte {
	int idSoporte;
	String nombreSoporte;
	List<Cancion> canciones;

	Soporte(int id, String nombre){
		idSoporte = id;
		nombreSoporte = nombre;
		canciones = new ArrayList<>();
	}

	Soporte(String nombre){
		idSoporte = 0;
		nombreSoporte = nombre;
		canciones = new ArrayList<>();
	}
	
}
