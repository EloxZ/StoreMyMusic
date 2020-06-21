package sMM.modelo;

public class Autor {
    int idAutor;
    String nombreAutor;
    String nacionalidad;
    public Autor (int id, String nombre, String pais){
		idAutor = id;
		nombreAutor = nombre;
		nacionalidad = pais;
		
	}

    public Autor (String nombre, String pais){
            idAutor = 0;
            nombreAutor = nombre;
            nacionalidad = pais;
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

    public void setNacionalidad(String pais) {
            nacionalidad = pais;
    }

    public String getNacionalidad() {
            return nacionalidad;
    }

    @Override
    public String toString() {
            return "["+idAutor+"] "+nombreAutor+" ("+nacionalidad+")";
    }
}

