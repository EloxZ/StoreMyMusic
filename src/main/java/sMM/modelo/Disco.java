/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sMM.modelo;

/**
 *
 * @author David
 */
import sMM.modelo.Genero;
import sMM.modelo.Soporte;
import sMM.modelo.Prestamo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Disco {
private int idDisco;
	private String titulo;
	private int anoSalida = 0;
	private int anoEdicion = 0;
	private String numeroCatalogo;
	private String codigoBarras;
	private String codigoColeccion;
	private String fechaCompra;
	private float precioCompra;
	private String notas;
	private float valoracion;
	private String paisEdicion;
	private String posicionEnUbicacion;
	private int idCategoria;
	private int idDiscografica;
	private int idUbicacion;
	private int idTienda;
        private String portada;
	private List<Genero> generos;
	private List<Autor> autores;
	private List<Soporte> soportes;
        private boolean enListaDeseos;
        private boolean favorito;
        private boolean prestado;
	//private List<Prestamo> prestamos;
	private Estado estado;
	
	public Disco(String st) {
		titulo = st;
                valoracion = -1f;
                precioCompra = -1f;
	}
	
	public Disco(int id) {
        idDisco = id;
                valoracion = -1f;
                precioCompra = -1f;
	}
	
	public String getTitulo() {
		return titulo;
	}
        
        public String getPortada() {
            return portada;
        }
        
        public void setPortada(String p) {
            portada = p;
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

	public String getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(String fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public float getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(float precioCompra) {
		this.precioCompra = precioCompra;
	}

	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

	public float getValoracion() {
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



	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getCodigoColeccion() {
		return codigoColeccion;
	}

	public void setCodigoColeccion(String codigoColeccion) {
		this.codigoColeccion = codigoColeccion;
	}

    public int getIdDisco() {
        return idDisco;
    }

    public boolean getEnListaDeseos() {
        return enListaDeseos;
    }

    public boolean getFavorito() {
        return favorito;
    }

    public boolean getPrestado() {
        return prestado;
    }

    public void setIdDisco(int idDisco) {
        this.idDisco = idDisco;
    }

    public void setEnListaDeseos(boolean enListaDeseos) {
        this.enListaDeseos = enListaDeseos;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

    public void setPrestado(boolean prestado) {
        this.prestado = prestado;
    }
        
        
}
