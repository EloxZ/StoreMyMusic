package sMM;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Disco {
  int idDisco;
  String titulo;
  int anoSalida;
  int anoEdicion;
  String numeroCatalogo;
  String codigoBarras;
  Date fechaCompra;
  double precioCompra;
  String notas;
  double valoracion;
  String paisEdicion;
  String posicionEnUbicacion;
  int idCategoria;
  int idDiscografica;
  int idUbicacion;
  int idTienda;
  List<Genero> generos;
  List<Autor> autores;
  List<Soporte> soportes;
  List<Prestamo> prestamos;
  Estado estado;
}
