package sMM;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
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
	
	/*public int añadirDisco(Disco dis) {
		int discoID = 0;
		String insertBody = "INSERT INTO Discos(Titulo) VALUES(?)";
		try {
			PreparedStatement texto = conn.prepareStatement(insertBody,PreparedStatement.RETURN_GENERATED_KEYS);
			texto.setString(1, dis.getTitulo());
			int res = texto.executeUpdate();
			ResultSet rs = texto.getGeneratedKeys();
			while (rs.next()) {
				discoID = rs.getInt(1);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return discoID;
	}*/
	
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
            texto.setString(5,dis.getCodigoColeccion());
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

	public List<Autor> listaAutores() {
		ArrayList<Autor> lAutores = new ArrayList<>();
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
					lAutores.add(new Autor(id, name, pais));
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
	                    int yearEdicion = rs.getInt(4);
	                    String numCatalogo = rs.getString(5);
	                    String codigoBarras = rs.getString(6);
	                    int precio = rs.getInt(9);
	                    float valoracion = rs.getFloat(11);
	                    
	                    d.setTitulo(titulo);
	                    d.setAnoEdicion(yearEdicion);
	                    d.setNumeroCatalogo(numCatalogo);
	                    d.setCodigoBarras(codigoBarras);
	                    d.setPrecioCompra(precio);
	                    d.setValoracion(valoracion);
	                    
	                    discos.put(id,d);
			}
	            }
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        return discos;
	}
	
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

	public List<Amigo> listaAmigos() {
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

	public List<Categoria> listaCategorias() {
		ArrayList<Categoria> lCategorias = new ArrayList<>();
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
					lCategorias.add(new Categoria(id, name));
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

	public List<Discografica> listaDiscograficas() {
		ArrayList<Discografica> lDiscograficas = new ArrayList<>();
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
					lDiscograficas.add(new Discografica(id, name));
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
		int discograficaID = 0;
		String insertBody = "INSERT INTO Discográficas (NombreDiscografica) VALUES(?)";
		try {
			PreparedStatement texto = conn.prepareStatement(insertBody,PreparedStatement.RETURN_GENERATED_KEYS);
			texto.setString(1, d.getNombre());
			int res = texto.executeUpdate();
			ResultSet rs = texto.getGeneratedKeys();
			while (rs.next()) {
				discograficaID = rs.getInt(1);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return discograficaID;
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

	public List<Formato> listaFormatos() {
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
		int formatoID = 0;
		String insertBody = "INSERT INTO Formatos (NombreFormato) VALUES(?)";
		try {
			PreparedStatement texto = conn.prepareStatement(insertBody,PreparedStatement.RETURN_GENERATED_KEYS);
			texto.setString(1, f.getNombre());
			int res = texto.executeUpdate();
			ResultSet rs = texto.getGeneratedKeys();
			while (rs.next()) {
				formatoID = rs.getInt(1);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return formatoID;
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

	public List<Genero> listaGeneros() {
		ArrayList<Genero> lGeneros = new ArrayList<>();
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
					lGeneros.add(new Genero(id, name));
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
		int generoID = 0;
		String insertBody = "INSERT INTO Géneros (NombreGenero) VALUES(?)";
		try {
			PreparedStatement texto = conn.prepareStatement(insertBody,PreparedStatement.RETURN_GENERATED_KEYS);
			texto.setString(1, g.getNombre());
			int res = texto.executeUpdate();
			ResultSet rs = texto.getGeneratedKeys();
			while (rs.next()) {
				generoID = rs.getInt(1);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return generoID;
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

	public List<Tienda> listaTiendas() {
		ArrayList<Tienda> lTiendas = new ArrayList<>();
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
					lTiendas.add(new Tienda(id, name));
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
		int tiendaID = 0;
		String insertBody = "INSERT INTO Tiendas (NombreTienda) VALUES(?)";
		try {
			PreparedStatement texto = conn.prepareStatement(insertBody,PreparedStatement.RETURN_GENERATED_KEYS);
			texto.setString(1, t.getNombre());
			int res = texto.executeUpdate();
			ResultSet rs = texto.getGeneratedKeys();
			while (rs.next()) {
				tiendaID = rs.getInt(1);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return tiendaID;
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

	public List<Ubicacion> listaUbicaciones() {
		ArrayList<Ubicacion> lUbicaciones = new ArrayList<>();
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
					lUbicaciones.add(new Ubicacion(id, name));
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
		int ubicacionID = 0;
		String insertBody = "INSERT INTO Ubicaciones (NombreUbicacion) VALUES(?)";
		try {
			PreparedStatement texto = conn.prepareStatement(insertBody,PreparedStatement.RETURN_GENERATED_KEYS);
			texto.setString(1, u.getNombre());
			int res = texto.executeUpdate();
			ResultSet rs = texto.getGeneratedKeys();
			while (rs.next()) {
				ubicacionID = rs.getInt(1);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return ubicacionID;
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

	/***************************************************************************************/
	
}
