package sMM;

import java.sql.*;


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
}
