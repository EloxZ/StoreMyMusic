/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sMM.basededatos;

/**
 *
 * @author Aaron
 */

import sMM.modelo.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ConexionJDBC extends ConexionBD{
    private Connection conn;

    private static ConexionJDBC instanciaInterfaz = null;

    public ConexionJDBC() {
        try {
                // create connection for database called DBB_SCHEMA in a server installed in
                // DB_URL, with a user USER with password PASS
                conn = DriverManager.getConnection(DB_URL + "/" + DB_SCHEMA, USER, PASS);
        } catch (SQLException e) {
                System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
                e.printStackTrace();
        }

    }

    public static ConexionJDBC getInstance() {
        if (instanciaInterfaz == null) {
                instanciaInterfaz = new ConexionJDBC();
        }
        return instanciaInterfaz;
    }


    public ArrayList<Pair<Integer,Integer>>  getAutoresDiscos() {
        ArrayList<Pair<Integer,Integer>> ad = new ArrayList<>();
        String selectQueryBody = "SELECT * FROM Autores_has_Discos";
	Statement querySt;
          try {
            querySt = conn.createStatement();
            ResultSet rs = querySt.executeQuery(selectQueryBody);
            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    int idAutor = rs.getInt(1);
                    int idDisco  = rs.getInt(2);
                  
                    Pair<Integer,Integer> tuplaAutorDisco = new Pair<>(idAutor,idDisco);
                    
                    ad.add(tuplaAutorDisco);
                }
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ad;
    }

    public HashMap<Integer, Disco> getDiscos() {
        HashMap<Integer, Disco> discos = new HashMap<>();
        String selectQueryBody = "SELECT * FROM Discos";
	Statement querySt;
        try {
            querySt = conn.createStatement();
            ResultSet rs = querySt.executeQuery(selectQueryBody);
            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    int id = rs.getInt(1);
                    Disco d = new Disco(id);
                    
                    
                    String titulo = rs.getString(2);
                    int yearSalida = rs.getInt(3);
                    int yearEdicion = rs.getInt(4);
                    String numCatalogo = rs.getString(5);
                    String codigoBarras = rs.getString(6);
                    String CodigoColeccion = rs.getString(7);
                    
                    String fechaCompraString = null;
                    try {
                         java.sql.Date fechaCompra = rs.getDate(8);
                        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        if (fechaCompra != null) fechaCompraString = df.format(fechaCompra);
                    } catch (java.sql.SQLException e) {
                        
                    }
                    
                  
                    
                    
                    float precioCompra = rs.getFloat(9);
                    String notas = rs.getString(10);
                    float valoracion = rs.getFloat(11);
                    String PaisEdici = rs.getString(12);
                    String PosEnUbicacion = rs.getString(13);
                    int idCat = rs.getInt(14);
                    int idDiscogra = rs.getInt(15);
                    int idUbi = rs.getInt(16);
                    int idTien = rs.getInt(17);

                    d.setTitulo(titulo);
                    d.setAnoSalida(yearSalida);
                    d.setAnoEdicion(yearEdicion);
                    d.setNumeroCatalogo(numCatalogo);
                    d.setCodigoBarras(codigoBarras);
                    d.setCodigoColeccion(CodigoColeccion);
                    d.setFechaCompra(fechaCompraString);
                    d.setPrecioCompra(precioCompra);
                    d.setNotas(notas);
                    d.setValoracion(valoracion);
                    d.setPaisEdicion(PaisEdici);
                    d.setPosicionEnUbicacion(PosEnUbicacion);
                    d.setIdCategoria(idCat);
                    d.setIdDiscografica(idDiscogra);
                    d.setIdUbicacion(idUbi);
                    d.setIdTienda(idTien);

                    
                    discos.put(id,d);
                }
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return discos;
    }

    public int añadirDisco(Disco dis) {
		int discoID = 0;
		java.sql.Date fecha2 = null;
		if(dis.getFechaCompra() != null) {
			fecha2 = java.sql.Date.valueOf(dis.getFechaCompra());
		}
		 
		String insertBody = "INSERT INTO Discos(Titulo,AñoSalida,AñoEdicion,NumeroCatalogo,CodigoBarras,CodigoColeccion,FechaCompra,PrecioCompra,Notas,Valoracion,PaisEdicion,PosicionEnUbicacion) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement texto = conn.prepareStatement(insertBody,PreparedStatement.RETURN_GENERATED_KEYS);
			texto.setString(1, dis.getTitulo());
			texto.setInt(2, dis.getAnoSalida());
            		texto.setInt(3, dis.getAnoEdicion());
            		texto.setString(4, dis.getNumeroCatalogo());
          	        texto.setString(5,dis.getCodigoBarras());
           	        texto.setString(6,dis.getCodigoColeccion());
                        texto.setDate(7, (fecha2));
                        texto.setFloat(8, dis.getPrecioCompra());
                        texto.setString(9, dis.getNotas());
                        texto.setFloat(10, dis.getValoracion());
                        texto.setString(11, dis.getPaisEdicion());
                        texto.setString(12, dis.getPosicionEnUbicacion());
			int res = texto.executeUpdate();
			ResultSet rs = texto.getGeneratedKeys();
			while (rs.next()) {
				discoID = rs.getInt(1);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return discoID;
	}
	
	public void eliminarDisco(int id) {
		//int discoID = dis.getID();
		//System.out.println(discoID);
		String deleteBody = "DELETE FROM Discos WHERE (idDisco = ?)";
		try {
			PreparedStatement texto = conn.prepareStatement(deleteBody);
			texto.setInt(1, id);
			int res = texto.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertarDatosDisco (Disco dis, int id, String fecha) {
		java.sql.Date fecha2 = java.sql.Date.valueOf(fecha);
        //Le das un disco y modifica todas sus variables auxiliares en la Base de Datos
        String insertBody = "UPDATE Discos SET AñoSalida = ?, AñoEdicion = ?, NumeroCatalogo = ?, CodigoBarras = ?, CodigoColeccion = ?, FechaCompra = ?, PrecioCompra = ?, Notas = ?, Valoracion = ?, PaisEdicion = ?, PosicionEnUbicacion = ?  WHERE idDisco = ?";
        try 
        {
            
            PreparedStatement texto = conn.prepareStatement(insertBody);
            texto.setInt(1, dis.getAnoSalida());
            texto.setInt(2, dis.getAnoEdicion());
            texto.setString(3, dis.getNumeroCatalogo());
            texto.setString(4,dis.getCodigoBarras());
            //texto.setString(5,dis.getCodigoColeccion());
            texto.setDate(6, (fecha2));
            texto.setFloat(7, dis.getPrecioCompra());
            texto.setString(8, dis.getNotas());
            texto.setFloat(9, dis.getValoracion());
            texto.setString(10, dis.getPaisEdicion());
            texto.setString(11, dis.getPosicionEnUbicacion());
            texto.setInt(12, id);
            int res = texto.executeUpdate();
            
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
	
/*AUTORES*********************************************************************************/	

	public HashMap<Integer,Autor> listaAutores() {
		HashMap<Integer,Autor> lAutores = new HashMap<Integer,Autor>();
		String selectQueryBody = "SELECT * FROM Autores";
		Statement querySt;
		try {
			querySt = conn.createStatement();
			ResultSet rs = querySt.executeQuery(selectQueryBody);
			// position result to first
			int cont = 0;
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					int id = rs.getInt(1);
					String name = rs.getString(2);
					String pais = rs.getString(3);
					lAutores.put(id, new Autor(id, name, pais));
					System.out.println(id+" "+name+" "+pais);
					cont++;
				}
			}
			System.out.println(lAutores);
			System.out.println("Importados "+cont+" autores");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lAutores;
	}
	
	public int añadirAutor(Autor a) {
		int autorID = 0;
		String insertBody = "INSERT INTO Autores (NombreAutor,Nacionalidad) VALUES(?,?)";
		try {
			PreparedStatement texto = conn.prepareStatement(insertBody,PreparedStatement.RETURN_GENERATED_KEYS);
			texto.setString(1, a.getNombre());
			texto.setString(2, a.getNacionalidad());
			int res = texto.executeUpdate();
			ResultSet rs = texto.getGeneratedKeys();
			while (rs.next()) {
				autorID = rs.getInt(1);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return autorID;
	}	
	
	public void modificarAutor(int id, String nombre, String pais) {
		String query = "UPDATE Autores SET nombreAutor = ?, nacionalidad = ? WHERE idAutor = ?";
		try {
			PreparedStatement texto = conn.prepareStatement(query);
			texto.setInt(3, id);
			texto.setString(1, nombre);
			texto.setString(2, pais);
			int res = texto.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void eliminarAutor(int id) {
		//int discoID = dis.getID();
		//System.out.println(discoID);
		String deleteBody = "DELETE FROM Autores WHERE (idAutor = ?)";
		try {
			PreparedStatement texto = conn.prepareStatement(deleteBody);
			texto.setInt(1, id);
			int res = texto.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	/***************************************************************************************/
	
	
	public void añadirDatosAdquisicion(int id, String fecha, int precio) {
		//fecha tiene que ser un String de formato YYYY-MM-DD
		String query = "UPDATE Discos SET FechaCompra = ?, PrecioCompra = ? WHERE idDisco = ?";
		java.sql.Date fecha2 = java.sql.Date.valueOf(fecha);
		try {
			PreparedStatement texto = conn.prepareStatement(query);
			texto.setInt(3, id);
			texto.setDate(1, fecha2);
			texto.setInt(2, precio);
			int res = texto.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void añadirNotasValoracion(int id, String nota, float valoracion) {
			String query = "UPDATE Discos SET Notas = ?, Valoracion = ? WHERE idDisco = ?";
		try {
			PreparedStatement texto = conn.prepareStatement(query);
			texto.setString(1, nota);
			texto.setFloat(2, valoracion);
			texto.setInt(3, id);
			int res = texto.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*AMIGOS*********************************************************************************/	

	public ArrayList<Amigo> listaAmigos() {
		ArrayList<Amigo> lAmigos = new ArrayList<>();
		String selectQueryBody = "SELECT * FROM Amigos";
		Statement querySt;
		try {
			querySt = conn.createStatement();
			ResultSet rs = querySt.executeQuery(selectQueryBody);
			// position result to first
			int cont = 0;
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					int id = rs.getInt(1);
					String name = rs.getString(2);
					lAmigos.add(new Amigo(id, name));
					System.out.println(id+" "+name);
					cont++;
				}
			}
			System.out.println(lAmigos);
			System.out.println("Importados "+cont+" amigos");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lAmigos;
	}
	
	public int añadirAmigo(Amigo a) {
		int amigoID = 0;
		String insertBody = "INSERT INTO Amigos (NombreAmigo) VALUES(?)";
		try {
			PreparedStatement texto = conn.prepareStatement(insertBody,PreparedStatement.RETURN_GENERATED_KEYS);
			texto.setString(1, a.getNombre());
			int res = texto.executeUpdate();
			ResultSet rs = texto.getGeneratedKeys();
			while (rs.next()) {
				amigoID = rs.getInt(1);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return amigoID;
	}	
	
	public void modificarAmigo(int id, String nombre) {
		String query = "UPDATE Amigos SET nombreAmigo = ? WHERE idAmigo = ?";
		try {
			PreparedStatement texto = conn.prepareStatement(query);
			texto.setInt(2, id);
			texto.setString(1, nombre);
			int res = texto.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void eliminarAmigo(int id) {
		//int discoID = dis.getID();
		//System.out.println(discoID);
		String deleteBody = "DELETE FROM Amigo WHERE (idAmigo = ?)";
		try {
			PreparedStatement texto = conn.prepareStatement(deleteBody);
			texto.setInt(1, id);
			int res = texto.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	/***************************************************************************************/
	
	/*CATEGORÍAS*********************************************************************************/	

	public HashMap<Integer,Categoria>  listaCategorias() {
		HashMap<Integer,Categoria>  lCategorias = new HashMap<>();
		String selectQueryBody = "SELECT * FROM Categorías";
		Statement querySt;
		try {
			querySt = conn.createStatement();
			ResultSet rs = querySt.executeQuery(selectQueryBody);
			// position result to first
			int cont = 0;
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					int id = rs.getInt(1);
					String name = rs.getString(2);
					lCategorias.put(id,new Categoria(id, name));
					System.out.println(id+" "+name);
					cont++;
				}
			}
			System.out.println(lCategorias);
			System.out.println("Importadas "+cont+" categorías");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lCategorias;
	}
	
	public int añadirCategoria(Categoria c) {
		int categoriaID = 0;
		String insertBody = "INSERT INTO Categorías (NombreCategoria) VALUES(?)";
		try {
			PreparedStatement texto = conn.prepareStatement(insertBody,PreparedStatement.RETURN_GENERATED_KEYS);
			texto.setString(1, c.getNombre());
			int res = texto.executeUpdate();
			ResultSet rs = texto.getGeneratedKeys();
			while (rs.next()) {
				categoriaID = rs.getInt(1);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return categoriaID;
	}	
	
	public void modificarCategoria(int id, String nombre) {
		String query = "UPDATE Categorías SET nombreCategoria = ? WHERE idCategoria = ?";
		try {
			PreparedStatement texto = conn.prepareStatement(query);
			texto.setInt(2, id);
			texto.setString(1, nombre);
			int res = texto.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void eliminarCategoria(int id) {
		//int discoID = dis.getID();
		//System.out.println(discoID);
		String deleteBody = "DELETE FROM Categoria WHERE (idCategoria = ?)";
		try {
			PreparedStatement texto = conn.prepareStatement(deleteBody);
			texto.setInt(1, id);
			int res = texto.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	/***************************************************************************************/
	
	/*DISCOGRÁFICAS*********************************************************************************/	

	public HashMap<Integer,Discografica> listaDiscograficas() {
		HashMap<Integer,Discografica> lDiscograficas = new HashMap<>();
		String selectQueryBody = "SELECT * FROM Discográficas";
		Statement querySt;
		try {
			querySt = conn.createStatement();
			ResultSet rs = querySt.executeQuery(selectQueryBody);
			// position result to first
			int cont = 0;
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					int id = rs.getInt(1);
					String name = rs.getString(2);
					lDiscograficas.put(id, new Discografica(id, name));
					System.out.println(id+" "+name);
					cont++;
				}
			}
			System.out.println(lDiscograficas);
			System.out.println("Importadas "+cont+" discográficas");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lDiscograficas;
	}
	
	public int añadirDiscografica(Discografica d) {
		int categoriaID = 0;
		String insertBody = "INSERT INTO Discográficas (NombreDiscografica) VALUES(?)";
		try {
			PreparedStatement texto = conn.prepareStatement(insertBody,PreparedStatement.RETURN_GENERATED_KEYS);
			texto.setString(1, d.getNombre());
			int res = texto.executeUpdate();
			ResultSet rs = texto.getGeneratedKeys();
			while (rs.next()) {
				categoriaID = rs.getInt(1);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return categoriaID;
	}	
	
	public void modificarDiscografica(int id, String nombre) {
		String query = "UPDATE Discográficas SET nombreDiscografica = ? WHERE idDiscografica = ?";
		try {
			PreparedStatement texto = conn.prepareStatement(query);
			texto.setInt(2, id);
			texto.setString(1, nombre);
			int res = texto.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void eliminarDiscografica(int id) {
		//int discoID = dis.getID();
		//System.out.println(discoID);
		String deleteBody = "DELETE FROM Discográficas WHERE (idDiscografica = ?)";
		try {
			PreparedStatement texto = conn.prepareStatement(deleteBody);
			texto.setInt(1, id);
			int res = texto.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	/***************************************************************************************/
	
	/*FORMATOS*********************************************************************************/	

	public ArrayList<Formato> listaFormatos() {
		ArrayList<Formato> lFormatos = new ArrayList<>();
		String selectQueryBody = "SELECT * FROM Formatos";
		Statement querySt;
		try {
			querySt = conn.createStatement();
			ResultSet rs = querySt.executeQuery(selectQueryBody);
			// position result to first
			int cont = 0;
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					int id = rs.getInt(1);
					String name = rs.getString(2);
					lFormatos.add(new Formato(id, name));
					System.out.println(id+" "+name);
					cont++;
				}
			}
			System.out.println(lFormatos);
			System.out.println("Importados "+cont+" formatos");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lFormatos;
	}
	
	public int añadirFormato(Formato f) {
		int categoriaID = 0;
		String insertBody = "INSERT INTO Formatos (NombreFormato) VALUES(?)";
		try {
			PreparedStatement texto = conn.prepareStatement(insertBody,PreparedStatement.RETURN_GENERATED_KEYS);
			texto.setString(1, f.getNombre());
			int res = texto.executeUpdate();
			ResultSet rs = texto.getGeneratedKeys();
			while (rs.next()) {
				categoriaID = rs.getInt(1);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return categoriaID;
	}	
	
	public void modificarFormato(int id, String nombre) {
		String query = "UPDATE Formatos SET nombreFormato = ? WHERE idFormato = ?";
		try {
			PreparedStatement texto = conn.prepareStatement(query);
			texto.setInt(2, id);
			texto.setString(1, nombre);
			int res = texto.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void eliminarFormato(int id) {
		//int discoID = dis.getID();
		//System.out.println(discoID);
		String deleteBody = "DELETE FROM Formatos WHERE (idFormato = ?)";
		try {
			PreparedStatement texto = conn.prepareStatement(deleteBody);
			texto.setInt(1, id);
			int res = texto.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	/***************************************************************************************/
	
	/*GÉNEROS*********************************************************************************/	

	public HashMap<Integer,Genero> listaGeneros() {
		HashMap<Integer,Genero> lGeneros = new HashMap<Integer,Genero>();
		String selectQueryBody = "SELECT * FROM Géneros";
		Statement querySt;
		try {
			querySt = conn.createStatement();
			ResultSet rs = querySt.executeQuery(selectQueryBody);
			// position result to first
			int cont = 0;
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					int id = rs.getInt(1);
					String name = rs.getString(2);
					lGeneros.put(id, new Genero(id, name));
					System.out.println(id+" "+name);
					cont++;
				}
			}
			System.out.println(lGeneros);
			System.out.println("Importados "+cont+" géneros");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lGeneros;
	}
	
	public int añadirGenero(Genero g) {
		int categoriaID = 0;
		String insertBody = "INSERT INTO Géneros (NombreGenero) VALUES(?)";
		try {
			PreparedStatement texto = conn.prepareStatement(insertBody,PreparedStatement.RETURN_GENERATED_KEYS);
			texto.setString(1, g.getNombre());
			int res = texto.executeUpdate();
			ResultSet rs = texto.getGeneratedKeys();
			while (rs.next()) {
				categoriaID = rs.getInt(1);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return categoriaID;
	}	
	
	public void modificarGenero(int id, String nombre) {
		String query = "UPDATE Géneros SET nombreGenero = ? WHERE idGenero = ?";
		try {
			PreparedStatement texto = conn.prepareStatement(query);
			texto.setInt(2, id);
			texto.setString(1, nombre);
			int res = texto.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void eliminarGenero(int id) {
		//int discoID = dis.getID();
		//System.out.println(discoID);
		String deleteBody = "DELETE FROM Géneros WHERE (idGenero = ?)";
		try {
			PreparedStatement texto = conn.prepareStatement(deleteBody);
			texto.setInt(1, id);
			int res = texto.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	/***************************************************************************************/
	
	/*TIENDAS*********************************************************************************/	

	public HashMap<Integer,Tienda> listaTiendas() {
		HashMap<Integer,Tienda> lTiendas = new HashMap<>();
		String selectQueryBody = "SELECT * FROM Tiendas";
		Statement querySt;
		try {
			querySt = conn.createStatement();
			ResultSet rs = querySt.executeQuery(selectQueryBody);
			// position result to first
			int cont = 0;
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					int id = rs.getInt(1);
					String name = rs.getString(2);
					lTiendas.put(id, new Tienda(id, name));
					System.out.println(id+" "+name);
					cont++;
				}
			}
			System.out.println(lTiendas);
			System.out.println("Importadas "+cont+" tiendas");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lTiendas;
	}
	
	public int añadirTienda(Tienda t) {
		int categoriaID = 0;
		String insertBody = "INSERT INTO Tiendas (NombreTienda) VALUES(?)";
		try {
			PreparedStatement texto = conn.prepareStatement(insertBody,PreparedStatement.RETURN_GENERATED_KEYS);
			texto.setString(1, t.getNombre());
			int res = texto.executeUpdate();
			ResultSet rs = texto.getGeneratedKeys();
			while (rs.next()) {
				categoriaID = rs.getInt(1);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return categoriaID;
	}	
	
	public void modificarTienda(int id, String nombre) {
		String query = "UPDATE Tiendas SET nombreTienda = ? WHERE idTienda = ?";
		try {
			PreparedStatement texto = conn.prepareStatement(query);
			texto.setInt(2, id);
			texto.setString(1, nombre);
			int res = texto.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void eliminarTienda(int id) {
		//int discoID = dis.getID();
		//System.out.println(discoID);
		String deleteBody = "DELETE FROM Tiendas WHERE (idTienda = ?)";
		try {
			PreparedStatement texto = conn.prepareStatement(deleteBody);
			texto.setInt(1, id);
			int res = texto.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	/***************************************************************************************/
	
	/*UBICACIONES*********************************************************************************/	

	public HashMap<Integer,Ubicacion> listaUbicaciones() {
		HashMap<Integer,Ubicacion> lUbicaciones = new HashMap<>();
		String selectQueryBody = "SELECT * FROM Ubicaciones";
		Statement querySt;
		try {
			querySt = conn.createStatement();
			ResultSet rs = querySt.executeQuery(selectQueryBody);
			// position result to first
			int cont = 0;
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					int id = rs.getInt(1);
					String name = rs.getString(2);
					lUbicaciones.put(id, new Ubicacion(id, name));
					System.out.println(id+" "+name);
					cont++;
				}
			}
			System.out.println(lUbicaciones);
			System.out.println("Importadas "+cont+" ubicaciones");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lUbicaciones;
	}
	
	public int añadirUbicacion(Ubicacion u) {
		int categoriaID = 0;
		String insertBody = "INSERT INTO Ubicaciones (NombreUbicacion) VALUES(?)";
		try {
			PreparedStatement texto = conn.prepareStatement(insertBody,PreparedStatement.RETURN_GENERATED_KEYS);
			texto.setString(1, u.getNombre());
			int res = texto.executeUpdate();
			ResultSet rs = texto.getGeneratedKeys();
			while (rs.next()) {
				categoriaID = rs.getInt(1);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return categoriaID;
	}	
	
	public void modificarUbicacion(int id, String nombre) {
		String query = "UPDATE Ubicaciones SET nombreUbicacion = ? WHERE idUbicacion = ?";
		try {
			PreparedStatement texto = conn.prepareStatement(query);
			texto.setInt(2, id);
			texto.setString(1, nombre);
			int res = texto.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void eliminarUbicacion(int id) {
		//int discoID = dis.getID();
		//System.out.println(discoID);
		String deleteBody = "DELETE FROM Ubicaciones WHERE (idUbicacion = ?)";
		try {
			PreparedStatement texto = conn.prepareStatement(deleteBody);
			texto.setInt(1, id);
			int res = texto.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

    @Override
    public void insertarDatosDisco(Disco dis, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	/***************************************************************************************/
}