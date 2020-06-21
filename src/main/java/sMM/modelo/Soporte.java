package sMM.modelo;

import java.util.ArrayList;
import java.util.List;

public class Soporte {
	int idSoporte;
	String nombreSoporte;
	List<Cancion> canciones;
        int idFormato;
        int idDisco;
	
        public Soporte(int id, String nombre,int idDis,int idFor){
		idSoporte = id;
		nombreSoporte = nombre;
		canciones = new ArrayList<>();
                idDisco = idDis;
                idFormato = idFor;
	}
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
        public Soporte(String nombre, int formato)
        {
            idSoporte = 0;
            nombreSoporte = nombre;
            idFormato = formato;
        }
        public int getID()
        {
            return idSoporte;
        }
        public void setID(int ID)
        {
            this.idSoporte=ID;
        }
        public String getNombre()
        {
            return nombreSoporte;
        }
        public void setNombre(String nom)
        {
            this.nombreSoporte=nom;
        }
        public List<Cancion> getCanciones()
        {
            return canciones;
        }
        public void setCanciones(List<Cancion> can)
        {
            this.canciones=can;
        }
        public int getIDDisco()
        {
                    return idDisco;
        }
        public void setIDDisco(int iddis)
        {
            this.idDisco=iddis;
        }
        public int getIDFormato()
        {
            return idFormato;
        }
        public void setIDFormato(int idfor)
        {
            this.idFormato=idfor;
        }
	
}
