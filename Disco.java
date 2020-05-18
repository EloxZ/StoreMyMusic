package sMM;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Disco {
	private int idDisco;
	private String titulo;
	private int anoSalida;
	private int anoEdicion;
	private String numeroCatalogo;
	private String codigoBarras;
	private Date fechaCompra;
	private double precioCompra;
	private String notas;
	private double valoracion;
	private String paisEdicion;
	private String posicionEnUbicacion;
	private int idCategoria;
	private int idDiscografica;
	private int idUbicacion;
	private int idTienda;
	private List<Genero> generos;
	private List<Autor> autores;
	private List<Soporte> soportes;
	private List<Prestamo> prestamos;
	private Estado estado;
	
	public Disco(String st) {
		titulo = st;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public int getID() {
		return idDisco;
	}
}
