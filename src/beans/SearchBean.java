package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import Connection.conexion;
import utility.SqlBuilder;

import java.lang.String;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;



/*****
 * Esta clase es basicamente un 'queryBean' 2.0, con una implementacion mas
 * sencilla y reducida, acotada a la vista entregada
 * *******/

@ManagedBean(name="SearchBean")
@SessionScoped
public class SearchBean {
	//El formato de los atributos es (excepto en los atributos de opcion_busqueda e input_busqueda): tabla_atributo
	private String opcion_busqueda;
	private String input_busqueda;
	
	private String opcion_signo;
	private String input_signo;
	
	private String solicitud_fechaPresentacion_desde;
	private String solicitud_fechaPresentacion_hasta;
	private String solicitud_fechaPublicacion_desde;
	private String solicitud_fechaPublicacion_hasta;
	private String solicitud_fechaRegistro_desde;
	private String solicitud_fechaRegistro_hasta;
	
	private String tipoMarca_id;
	
	private String cobertura_id;
	
	private String categoria_id;
	
	private String estado_id;
	
	private String representante_rut;
	
	private String titular_rut;
	
	private static String columnas;
	
	private ResultSet myRs;
	
	private static String SqlStmt;
	
	/*****************CONSTRUCTOR**********************/
	public SearchBean(){
		/*OBS: muchas de las cosas que hay que mostrar, como por ejemplo el pais del titular, hay que ir a buscarlas en realidad
		 * a otra tabla; en 'titular' solo esta el campo: 'idpais', lo que implica que al querer mostrar el pais de un titular en la
		 * sentencia sql hay que indicar que se tiene que mostrar la descripcion del pais (donde esta el nombre del mismo), tal que
		 * titular.idpais = pais.id
		 * 
		 */
		
		/*OBS: Para recuperar los datos de los titulares y representantes hay que usar una UNION entre las sentencias sql de
		 * las entidades por separado, por ejemplo:
		 * 
		 * SELECT t.nombre, p.descripcion FROM titular t, pais p
		 * WHERE t.numerosolicitud = '1070518' AND t.idpais = p.id
		 * UNION
		 * SELECT r.nombre, p.descripcion FROM representante r, pais p
		 * WHERE r.numerosolicitud = '1070518'AND r.idpais = p.id;
		 * 
		 * */
		//Estas son las columnas a mostar como resultado de la busqueda
		/*setColumnas("solicitud.numerosolicitud, solicitud.numeroregistro, e.descripcion, s.fechapresentacion, s.fechapublicacion,"
				+ "t.nombre, c.descripcion, p.descripcion, r.nombre, i.fecha, i.fechavencimiento, ei.descripcion, i.observacion");
				*/
		setColumnas("titular.nombre, pais.descripcion, representante.nombre, solicitud.numerosolicitud, "
				+ "solicitud.numeroregistro, estado.descripcion, "
				+ "solicitud.fechapresentacion, solicitud.fechapublicacion, comuna.descripcion, instancia.fecha, "
				+ "instancia.fechavencimiento, estadoinstancia.descripcion, "
				+ "instancia.observacion, categoria.descripcion, tipomarca.descripcion");
		setOpcion_busqueda("");
		setInput_busqueda("");
		
		
	}
	
	
	public void CreateSQL(){
		
		SqlBuilder sqlBuilder = new SqlBuilder();
		
		sqlBuilder.SELECT_insert(getColumnas());
		//A continuacion se agregan las reglas de relacion entre tablas de la BD
		
					
		//Opcion Busqueda No.
		if(getOpcion_busqueda().equals("No. Solicitud")){

			sqlBuilder.WHERE_insert("solicitud.numerosolicitud",getInput_busqueda());
			sqlBuilder.FROM_insert("solicitud");
			}
			
		else if(getOpcion_busqueda().equals("No. Registro")){
			
			sqlBuilder.FROM_insert("solicitud");
			sqlBuilder.WHERE_insert("solicitud.numeroregistro", getInput_busqueda());
			}
		else if(getOpcion_busqueda().equals("No. Anotacion")){
			sqlBuilder.FROM_insert("anotacion");
			sqlBuilder.WHERE_insert("anotacion.numeroinapi", getInput_busqueda());
			sqlBuilder.FROM_JOIN("solicitud", "solicitud.numerosolicitud", "anotacion.numerosolicitud", "JOIN");
		}
		else{
			sqlBuilder.FROM_insert("solicitud");
		}
		
		
		//Opcion Signo
		System.out.println(getOpcion_signo());
		if(getOpcion_signo().equals("que_contenga")){
				sqlBuilder.WHERE_insert("solicitud.denominacion", "%"+getInput_signo()+"%");
				System.out.println("\nque contenga: \n"+sqlBuilder.getWHERE_stmt());

			}
			
		else if(getOpcion_signo().equals("exactamente")){
				sqlBuilder.WHERE_insert("solicitud.denominacion", getInput_signo());
				System.out.println("\nexactamente: \n"+sqlBuilder.getWHERE_stmt());
			}
		else if(getOpcion_signo().equals("que_empiece")){
				sqlBuilder.WHERE_insert("solicitud.denominacion", getInput_signo()+"%");
				System.out.println("\nque empiece: \n"+sqlBuilder.getWHERE_stmt());
			
		}
		else if(getOpcion_signo().equals("que_termine")){
				sqlBuilder.WHERE_insert("solicitud.denominacion", "%"+getInput_signo());
				System.out.println("\nque termine: \n"+sqlBuilder.getWHERE_stmt());
		}

		//Puesto que realizaremos varios JOINS, insertamos solo una tabla: solicitud
		sqlBuilder.FROM_JOIN("titular", "solicitud.numerosolicitud", "titular.numerosolicitud", "JOIN");
		//sqlBuilder.FROM_JOIN("anotacion", "solicitud.numerosolicitud", "anotacion.numerosolicitud", "JOIN");
		sqlBuilder.FROM_JOIN("representante", "solicitud.numerosolicitud", "representante.numerosolicitud", "JOIN");
		sqlBuilder.FROM_JOIN("pais", "titular.idpais", "pais.id", "JOIN");
		sqlBuilder.FROM_JOIN("pais pa", "pa.id", "representante.idpais", "JOIN");
		sqlBuilder.FROM_JOIN("categoria", "categoria.id", "solicitud.categoriaid", "JOIN");
		sqlBuilder.FROM_JOIN("tipomarca", "tipomarca.id", "solicitud.tipomarcaid", "JOIN");
		sqlBuilder.FROM_JOIN("estado", "estado.id", "solicitud.estadoid", "JOIN");
		sqlBuilder.FROM_JOIN("instancia", "instancia.numerosolicitud", "solicitud.numerosolicitud", "JOIN");
		sqlBuilder.FROM_JOIN("estadoinstancia", "estadoinstancia.id", "instancia.estadoinstanciaid", "JOIN");
		sqlBuilder.FROM_JOIN("comuna", "comuna.id", "titular.idcomuna", "JOIN");
		sqlBuilder.FROM_JOIN("comuna co", "co.id", "representante.idcomuna", "JOIN");

			
			//TODO: hay que ver como meterlo en una busqueda separada para que no deje la caga
			//case("No. Anotacion")
		
		
		//TODO: hay que modificar la implementacion de WHERE_insert de forma, hacerlo implementando otro metodo WHERE
		//sqlBuilder.WHERE_insert("solicitud.fechapresentacion", solicitud_fechaPresentacion_desde);
		
		sqlBuilder.WHERE_insert("tipomarca.id", getTipoMarca_id());
		sqlBuilder.WHERE_insert("cobertura.id", getCobertura_id());
		sqlBuilder.WHERE_insert("estado.id", getEstado_id());
		sqlBuilder.WHERE_insert("representante.rut", getRepresentante_rut());
		sqlBuilder.WHERE_insert("titular.rut", getTitular_rut());
		sqlBuilder.WHERE_insert("categoria.id", getCategoria_id());
		sqlBuilder.WHERE_OVERLAP(getSolicitud_fechaPresentacion_desde(), getSolicitud_fechaPresentacion_hasta(), "solicitud.fechapresentacion");
		sqlBuilder.WHERE_OVERLAP(getSolicitud_fechaPublicacion_desde(), getSolicitud_fechaPublicacion_hasta(), "solicitud.fechapublicacion");
		sqlBuilder.WHERE_OVERLAP(getSolicitud_fechaRegistro_desde(), getSolicitud_fechaRegistro_hasta(), "solicitud.fecharegistro");
		
		//Armamos la query final
		
		setSqlStmt(sqlBuilder.buildQuery());
		System.out.println(sqlBuilder.getFinal_stmt());
		

	}
	
	
	
	//METODO DE BUSQUEDA PRINCIPAL, a este se debe llamar desde la vista cuando el usuario envia el formulario
	public String search(){
		try {
			//Creamos la conexion a la BD
			Connection my_conn;
			my_conn = conexion.crearConexion();
			//Creamos el objeto Statement, en este se ejecutara la query final
			Statement myStmt = my_conn.createStatement();
			
			//las querys se crean en base a el conjunto de campos asociados a la tabla correspondiente ar
			//De esta forma, si el usuario pregunta por la fecha de una instancia, no se le mostraran TODOS los
			//titulares que hay en la BD
			
			SqlBuilder sqlBuilder = new SqlBuilder();
			
			sqlBuilder.SELECT_insert(getColumnas());
			//A continuacion se agregan las reglas de relacion entre tablas de la BD
			
						
			//Opcion Busqueda No.
			if(getOpcion_busqueda().equals("No. Solicitud")){

				sqlBuilder.WHERE_insert("solicitud.numerosolicitud",getInput_busqueda());
				sqlBuilder.FROM_insert("solicitud");
				}
				
			else if(getOpcion_busqueda().equals("No. Registro")){
				
				sqlBuilder.FROM_insert("solicitud");
				sqlBuilder.WHERE_insert("solicitud.numeroregistro", getInput_busqueda());
				}
			else if(getOpcion_busqueda().equals("No. Anotacion")){
				sqlBuilder.FROM_insert("anotacion");
				sqlBuilder.WHERE_insert("anotacion.numeroinapi", getInput_busqueda());
				sqlBuilder.FROM_JOIN("solicitud", "solicitud.numerosolicitud", "anotacion.numerosolicitud", "JOIN");
			}
			else{
				sqlBuilder.FROM_insert("solicitud");
			}
			
			
			//Opcion Signo
			if(getOpcion_busqueda().equals("que_contenga")){
					sqlBuilder.WHERE_insert("solicitud.denominacion", "%"+getInput_signo()+"%");

				}
				
			else if(getOpcion_busqueda().equals("exactamente")){
					sqlBuilder.WHERE_insert("solicitud.denominacion", getInput_signo());
				}
			else if(getOpcion_busqueda().equals("que_empiece")){
					sqlBuilder.WHERE_insert("solicitud.denominacion", getInput_signo()+"%");
				
			}
			else if(getOpcion_busqueda().equals("que_termine")){
					sqlBuilder.WHERE_insert("solicitud.denominacion", "%"+getInput_signo());
			}

			//Puesto que realizaremos varios JOINS, insertamos solo una tabla: solicitud
			sqlBuilder.FROM_JOIN("titular", "solicitud.numerosolicitud", "titular.numerosolicitud", "JOIN");
			//sqlBuilder.FROM_JOIN("anotacion", "solicitud.numerosolicitud", "anotacion.numerosolicitud", "JOIN");
			sqlBuilder.FROM_JOIN("representante", "solicitud.numerosolicitud", "representante.numerosolicitud", "JOIN");
			sqlBuilder.FROM_JOIN("pais", "titular.idpais", "pais.id", "JOIN");
			sqlBuilder.FROM_JOIN("pais pa", "pa.id", "representante.idpais", "JOIN");
			sqlBuilder.FROM_JOIN("categoria", "categoria.id", "solicitud.categoriaid", "JOIN");
			sqlBuilder.FROM_JOIN("tipomarca", "tipomarca.id", "solicitud.tipomarcaid", "JOIN");
			sqlBuilder.FROM_JOIN("estado", "estado.id", "solicitud.estadoid", "JOIN");
			sqlBuilder.FROM_JOIN("instancia", "instancia.numerosolicitud", "solicitud.numerosolicitud", "JOIN");
			sqlBuilder.FROM_JOIN("estadoinstancia", "estadoinstancia.id", "instancia.estadoinstanciaid", "JOIN");
			sqlBuilder.FROM_JOIN("comuna", "comuna.id", "titular.idcomuna", "JOIN");
			sqlBuilder.FROM_JOIN("comuna co", "co.id", "representante.idcomuna", "JOIN");

				
				//TODO: hay que ver como meterlo en una busqueda separada para que no deje la caga
				//case("No. Anotacion")
			
			
			//TODO: hay que modificar la implementacion de WHERE_insert de forma, hacerlo implementando otro metodo WHERE
			//sqlBuilder.WHERE_insert("solicitud.fechapresentacion", solicitud_fechaPresentacion_desde);
			
			sqlBuilder.WHERE_insert("tipomarca.id", getTipoMarca_id());
			sqlBuilder.WHERE_insert("cobertura.id", getCobertura_id());
			sqlBuilder.WHERE_insert("estado.id", getEstado_id());
			sqlBuilder.WHERE_insert("representante.rut", getRepresentante_rut());
			sqlBuilder.WHERE_insert("titular.rut", getTitular_rut());
			sqlBuilder.WHERE_insert("categoria.id", getCategoria_id());
			sqlBuilder.WHERE_OVERLAP(getSolicitud_fechaPresentacion_desde(), getSolicitud_fechaPresentacion_hasta(), "solicitud.fechapresentacion");
			sqlBuilder.WHERE_OVERLAP(getSolicitud_fechaPublicacion_desde(), getSolicitud_fechaPublicacion_hasta(), "solicitud.fechapublicacion");
			sqlBuilder.WHERE_OVERLAP(getSolicitud_fechaRegistro_desde(), getSolicitud_fechaRegistro_hasta(), "solicitud.fecharegistro");
			
			//Armamos la query final
			sqlBuilder.buildQuery();
			
			System.out.print(sqlBuilder.getFinal_stmt());
			//Se envia la sentencia y el objeto myStmt para ser ejecutado en setMyRs()
			setMyRs(sqlBuilder.getFinal_stmt(), myStmt);
			
			//lets see...
			while(myRs.next()){
				System.out.println("\n"+"titular:" + myRs.getString(1));
				System.out.println("Pais:" + myRs.getString(2));
				System.out.println("Representante: " + myRs.getString(3));
				System.out.println("Numero de Solicitud: "+myRs.getString(4));
				System.out.println("Numero de registro: " + myRs.getString(5));
				System.out.println("Estado de la Solicitud: " + myRs.getString(6));
				System.out.println("Fecha de Presentacion: " + myRs.getString(7));
				System.out.println("Fecha de Publicacion: " + myRs.getString(8));
				System.out.println("Comuna Titular: " + myRs.getString(9));
				System.out.println("Fecha Instancia: " + myRs.getString(10));
				System.out.println("Fecha de Vencimiento: " + myRs.getString(11));
				System.out.println("Estado de la instancia: " + myRs.getString(12));
				System.out.println("Observacion de la Instancia: " + myRs.getString(13));
				System.out.println("Categoria: " + myRs.getString(14));
				System.out.println("Tipo de Marca: " + myRs.getString(15));
			}
			//Cerramos la conexion...
			myRs.close();
			
			/*
			 * setColumnas("titular.nombre, pais.descripcion, representante.nombre, solicitud.numerosolicitud, "
				+ "solicitud.numeroregistro, estado.descripcion, "
				+ "solicitud.fechapresentacion, solicitud.fechapublicacion, comuna.descripcion, instancia.fecha, "
				+ "instancia.fechavencimiento, estadoinstancia.descripcion, "
				+ "instancia.observacion, categoria.descripcion, tipomarca.descripcion");
			 * 
			 * */
			if(myRs == null){
				return "fail";
			}
			else{
				System.out.println("\nExito en la busqueda-1");
				return "success";
			}
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "fail";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "fail";
		}
	}
	
	
	/************GETTER/SETTER***************/
	public String getOpcion_busqueda() {
		return opcion_busqueda;
	}
	public void setOpcion_busqueda(String opcion_busqueda) {
		this.opcion_busqueda = opcion_busqueda;
	}
	
	
	public String getInput_busqueda() {
		return input_busqueda;
	}
	public void setInput_busqueda(String input_busqueda) {
		this.input_busqueda = input_busqueda;
	}
	
	
	public String getOpcion_signo() {
		return opcion_signo;
	}
	public void setOpcion_signo(String opcion_signo) {
		this.opcion_signo = opcion_signo;
	}


	public String getInput_signo() {
		return input_signo;
	}
	public void setInput_signo(String input_signo) {
		this.input_signo = input_signo;
	}


	public String getSolicitud_fechaPresentacion_desde() {
		return solicitud_fechaPresentacion_desde;
	}
	public void setSolicitud_fechaPresentacion_desde(String solicitud_fechaPresentacion_desde) {
		
		this.solicitud_fechaPresentacion_desde = solicitud_fechaPresentacion_desde;
	}
	
	
	public String getSolicitud_fechaPresentacion_hasta() {
		return solicitud_fechaPresentacion_hasta;
	}
	public void setSolicitud_fechaPresentacion_hasta(String solicitud_fechaPresentacion_hasta) {
		
		this.solicitud_fechaPresentacion_hasta = solicitud_fechaPresentacion_hasta;
	}
	
	
	public String getSolicitud_fechaPublicacion_desde() {
		
		return solicitud_fechaPublicacion_desde;
	}
	public void setSolicitud_fechaPublicacion_desde(String solicitud_fechaPublicacion_desde) {
		
		this.solicitud_fechaPublicacion_desde = solicitud_fechaPublicacion_desde;
	}
	
	
	public String getSolicitud_fechaPublicacion_hasta() {
		return solicitud_fechaPublicacion_hasta;
	}
	public void setSolicitud_fechaPublicacion_hasta(String solicitud_fechaPublicacion_hasta) {
		
		this.solicitud_fechaPublicacion_hasta = solicitud_fechaPublicacion_hasta;
	}
	
	
	public String getSolicitud_fechaRegistro_desde() {
		return solicitud_fechaRegistro_desde;
	}
	public void setSolicitud_fechaRegistro_desde(String solicitud_fechaRegistro_desde) {
		
		this.solicitud_fechaRegistro_desde = solicitud_fechaRegistro_desde;
	}
	
	
	public String getSolicitud_fechaRegistro_hasta() {
		return solicitud_fechaRegistro_hasta;
	}
	public void setSolicitud_fechaRegistro_hasta(String solicitud_fechaRegistro_hasta) {
		this.solicitud_fechaRegistro_hasta = solicitud_fechaRegistro_hasta;
	}
	
	
	public String getTipoMarca_id() {
		return tipoMarca_id;
	}
	public void setTipoMarca_id(String tipoMarca_id) {
		this.tipoMarca_id = tipoMarca_id;
	}
	
	
	public String getCobertura_id() {
		return cobertura_id;
	}
	public void setCobertura_id(String cobertura_id) {
		this.cobertura_id = cobertura_id;
	}
	
	
	public String getCategoria_id() {
		return categoria_id;
	}
	public void setCategoria_id(String categoria_id) {
		this.categoria_id = categoria_id;
	}
	
	
	public String getEstado_id() {
		return estado_id;
	}
	public void setEstado_id(String estado_id) {
		this.estado_id = estado_id;
	}
	
	
	public String getRepresentante_rut() {
		return representante_rut;
	}
	public void setRepresentante_rut(String representante_rut) {
		this.representante_rut = representante_rut;
	}
	
	
	public String getTitular_rut() {
		return titular_rut;
	}
	public void setTitular_rut(String titular_rut) {
		this.titular_rut = titular_rut;
	}


	
	
	public static String getColumnas() {
		return columnas;
	}


	public void setColumnas(String col) {
		SearchBean.columnas = col;
	}
		
	public void setMyRs(String stmt, Statement myStmt) throws SQLException {
		this.myRs = myStmt.executeQuery(stmt);
		
	}
	public ResultSet getMyRs() {
		return this.myRs;
	}


	public static String getSqlStmt() {
		return SqlStmt;
	}


	public void setSqlStmt(String sQL_stmt) {
		SqlStmt = sQL_stmt;
	}

	
	
}
