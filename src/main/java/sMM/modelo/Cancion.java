package sMM.modelo;

public class Cancion {

	int idCancion;
	int track;  
	String tituloCancion;
	String autor;
	int duracion;
	String notas;
	double valoracion;
<<<<<<< HEAD
=======
        int idSoporte;
        
public Cancion(int id, int tr, String titulo, String aut, int dur, String not, double val, int idSop){
		idCancion = id;
                track = tr;
		tituloCancion = titulo;
		autor = aut;
                duracion = dur;
                notas = not;
                valoracion = val;
                idSoporte = idSop;
}

public Cancion(int tr, String titulo, String aut, int dur, String not, double val, int idSop){
		idCancion=0;
                track = tr;
		tituloCancion = titulo;
		autor = aut;
                duracion = dur;
                notas = not;
                valoracion = val;
                idSoporte = idSop;
}

public Cancion(int tr, String titulo, String aut, int dur, int idSop){
		idCancion = 0;
                track = tr;
		tituloCancion = titulo;
		autor = aut;
                duracion = dur;
                idSoporte = idSop;
}

public int getID(){
    return idCancion;
}

public void setID(int ID){
    this.idCancion=ID;
}

public int getTrack(){
    return track;
}

public void setTrack(int tr){
    this.track=tr;
}
     
public String getTitulo(){
    return tituloCancion;
}

public void setTitulo(String tit){
    this.tituloCancion=tit;
}

public String getAutor(){
    return autor;
}

public void setAutor(String aut){
    this.autor=aut;
}

public int getDuracion(){
    return duracion;
}

public void setDuracion(int tr){
    this.duracion=tr;
}

public String getNotas(){
    return notas;
}

public void setNotas(String not){
    this.notas=not;
}

public double getValoracion(){
    return valoracion;
}

public void setValoracion(double val){
    this.valoracion=val;
}

public int getIDSoporte(){
    return idSoporte;
}

public void setIDSoporte(int idsop){
    this.idSoporte=idsop;
}
>>>>>>> Eloy-Branch
	
}
