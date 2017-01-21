package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {
	private static final String URL = "jdbc:postgresql://190.107.176.160:5432/marcas";
	private static final String USER = "marcas";
	private static final String PASS = "marcas.2016";

	public static Connection crearConexion() throws ClassNotFoundException, SQLException{
		Class.forName("org.postgresql.Driver");
		Connection conexion = DriverManager.getConnection(URL, USER, PASS);
		if (conexion != null){
			System.out.print("\nConexion establecida...\n");
			return conexion;
		}
		return null;
	}
	
/*
	public conexion() throws ClassNotFoundException, SQLException{
		Class.forName("org.postgresql.Driver");
		Connection conexion = DriverManager.getConnection(URL, USER, PASS);
		if (conexion != null){
			System.out.println("Conexion Establecida con el servidor: " + URL+ "como el usuario:"+USER);
			return;
		}
		return;
	}

*/


}