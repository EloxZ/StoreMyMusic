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
	private float valoracion;
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
	
	public Disco(int id) {
        idDisco = id;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public int getID() {
		return idDisco;
	}
	
	public void setID(int id) {
		idDisco = id;
	}

	public int getAnoSalida() {
		return anoSalida;
	}

	public void setAnoSalida(int anoSalida) {
		this.anoSalida = anoSalida;
	}

	public int getAnoEdicion() {
		return anoEdicion;
	}

	public void setAnoEdicion(int anoEdicion) {
		this.anoEdicion = anoEdicion;
	}

	public String getNumeroCatalogo() {
		return numeroCatalogo;
	}

	public void setNumeroCatalogo(String numeroCatalogo) {
		this.numeroCatalogo = numeroCatalogo;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public Date getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public double getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(double precioCompra) {
		this.precioCompra = precioCompra;
	}

	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

	public double getValoracion() {
		return valoracion;
	}

	public void setValoracion(float valoracion) {
		this.valoracion = valoracion;
	}

	public String getPaisEdicion() {
		return paisEdicion;
	}

	public void setPaisEdicion(String paisEdicion) {
		this.paisEdicion = paisEdicion;
	}

	public String getPosicionEnUbicacion() {
		return posicionEnUbicacion;
	}

	public void setPosicionEnUbicacion(String posicionEnUbicacion) {
		this.posicionEnUbicacion = posicionEnUbicacion;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public int getIdDiscografica() {
		return idDiscografica;
	}

	public void setIdDiscografica(int idDiscografica) {
		this.idDiscografica = idDiscografica;
	}

	public int getIdUbicacion() {
		return idUbicacion;
	}

	public void setIdUbicacion(int idUbicacion) {
		this.idUbicacion = idUbicacion;
	}

	public int getIdTienda() {
		return idTienda;
	}

	public void setIdTienda(int idTienda) {
		this.idTienda = idTienda;
	}

	public List<Genero> getGeneros() {
		return generos;
	}

	public void setGeneros(List<Genero> generos) {
		this.generos = generos;
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

	public List<Soporte> getSoportes() {
		return soportes;
	}

	public void setSoportes(List<Soporte> soportes) {
		this.soportes = soportes;
	}

	public List<Prestamo> getPrestamos() {
		return prestamos;
	}

	public void setPrestamos(List<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	
}
