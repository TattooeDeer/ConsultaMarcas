package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Connection.conexion;
import beans.FilaReporte;
import beans.SearchBean;

public class UserDAO {
	
	private static ResultSet myRs;
 
	public static List<FilaReporte> buildReport(){
		try {
			//Creamos la conexion a la BD
			Connection my_conn;
			my_conn = conexion.crearConexion();
			//Creamos el objeto Statement, en este se ejecutara la query final
			Statement myStmt = my_conn.createStatement();
	
			//Se envia la sentencia y el objeto myStmt para ser ejecutado en setMyRs()
			System.out.print("\nOrigen:UserDAO, SqlStmt(SearchBean): "+SearchBean.getSqlStmt());
			setMyRs(SearchBean.getSqlStmt(), myStmt);
			
			List<FilaReporte> resultados_busqueda = new ArrayList<FilaReporte>();
			
			boolean found = false;
			int i=1;
			while(myRs.next()){
				FilaReporte r = new FilaReporte();
				
				r.setIdRow(i);
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

				System.out.println("\n"+r.getIdRow()+"\n"+r.getTitular_nombre()+"\n"+r.getTitular_pais()+"\n"+r.getTitular_comuna()+"\n"+r.getRepresentante_nombre()+"\n"+r.getNumeroSolicitud()+"\n"+r.getNumeroRegistro()+"\n"+r.getEstadoSolicitud()+"\n"+r.getFechaPresentacion()+"\n"+r.getFechaPublicacion()+"\n"+r.getFechaInstancia()+"\n"+r.getFechaVencimiento()+"\n"+r.getEstadoInstancia()+"\n"+r.getObservacionInstancia()+"\n"+r.getCategoria()+"\n"+r.getTipoMarca());
				
				
				i++;
				resultados_busqueda.add(r);
				found = true;
			}
			//Cerramos la conexion...
			myRs.close();
			if (found) {
                return resultados_busqueda;
            } else {
                return resultados_busqueda; // no entires found
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
