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
	
	public int añadirDisco(Disco dis) {
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
}
	

