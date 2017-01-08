package Connection;


import java.sql.*;
public class TestConn {

	public static void main(String[] args) {
		try {
			//1.- Creamos la conexion con la BD, credenciales estan como private attribute en conexion.java
			Connection conn = conexion.crearConexion();

			//2.- Creamos un Statement Object para ejecutar nuestras query's
			Statement myStmt = conn.createStatement();
			
			//3.- Ejecutamos la Query
			ResultSet myRs = myStmt.executeQuery("SELECT * FROM marcas.solicitud ORDER BY numerosolicitud ASC LIMIT 20");
			
			//4.- Procesamos Result Set
			while(myRs.next()){
				System.out.println("\n"+"Numero de Solicitud:" + myRs.getString("numerosolicitud"));
				System.out.println("Numero de Registro:" + myRs.getString("numeroregistro"));
				System.out.println("Categoria:" + myRs.getString("categoriaid"));
				System.out.println("Tipo de Marca: "+myRs.getString("tipomarcaid"));
				System.out.println("Estado de la Solicitud: " + myRs.getString("estadoid"));
			}
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
		
		
	}

}