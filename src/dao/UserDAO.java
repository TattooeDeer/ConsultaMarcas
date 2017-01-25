package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Connection.conexion;
import beans.ResultBean;
import beans.SearchBean;

public class UserDAO {
	
	private static ResultSet myRs;
 
	public static ArrayList<ResultBean> getData(){
		try {
			//Creamos la conexion a la BD
			Connection my_conn;
			my_conn = conexion.crearConexion();
			//Creamos el objeto Statement, en este se ejecutara la query final
			Statement myStmt = my_conn.createStatement();
	
			//Se envia la sentencia y el objeto myStmt para ser ejecutado en setMyRs()
			setMyRs(SearchBean.getSQL_stmt(), myStmt);
			
			ArrayList<ResultBean> resultados_busqueda = new ArrayList<ResultBean>();
			
			boolean found = false;
			while(myRs.next()){
				ResultBean r = new ResultBean();
				
				r.setTitular_nombre(myRs.getString(1));
				r.setTitular_pais(myRs.getString(2));
				r.setTitular_comuna(myRs.getString(9));
				r.setRepresentante_nombre(myRs.getString(3));
				r.setNumeroSolicitud(myRs.getString(4));
				r.setNumeroRegistro(myRs.getString(5));
				r.setEstadoSolicitud(myRs.getString(6));
				r.setFechaPresentacion(myRs.getString(7));
				r.setFechaPublicacion(myRs.getString(8));
				r.setFechaInstancia(myRs.getString(10));
				r.setFechaVencimiento(myRs.getString(11));
				r.setEstadoInstancia(myRs.getString(12));
				r.setObservacionInstancia(myRs.getString(13));
				r.setCategoria(myRs.getString(14));
				r.setTipoMarca(myRs.getString(15));
				
				found = true;
			}
			//Cerramos la conexion...
			myRs.close();
			if (found) {
                return resultados_busqueda;
            } else {
                return null; // no entires found
            }
			
			
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("\n Error en getData -->" + e.getMessage());
			e.printStackTrace();
			return null;
			
		}

			
	}

	public ResultSet getMyRs() {
		return myRs;
	}

	public static void setMyRs(String stmt, Statement myStmt) throws SQLException {
		myRs = myStmt.executeQuery(stmt);
	}
}
