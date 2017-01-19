package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import Connection.conexion;

import java.lang.String;
import java.sql.*;
import java.util.ArrayList;

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
	
	private Date solicitud_fechaPresentacion_desde;
	private Date solicitud_fechaPresentacion_hasta;
	private Date solicitud_fechaPublicacion_desde;
	private Date solicitud_fechaPublicacion_hasta;
	private Date solicitud_fechaRegistro_desde;
	private Date solicitud_fechaRegistro_hasta;
	
	private String tipoMarca_descripcion;
	
	private String cobertura_descripcion;
	
	private String categoria_descripcion;
	
	private String estado_descripcion;
	
	private String representante_rut;
	
	private String titular_rut;
	
	private static String columnas;
	
	
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
		setColumnas("s.numerosolicitud, s.numeroregistro, e.descripcion, s.fechapresentacion, s.fechapublicacion,"
				+ "t.nombre, c.descripcion, p.descripcion, r.nombre, i.fecha, i.fechavencimiento, ei.descripcion, i.observacion");
		setOpcion_busqueda("");
		
		
		
		
		
	}
	
	/*Hay que hacer multiples JOINS
	 * SELECT t.nombre, p.descripcion, r.nombre, s.numerosolicitud 
	 * FROM solicitud s
	 *	JOIN titular t
     *		ON s.numerosolicitud = t.numerosolicitud
     *  JOIN representante r
     *		ON s.numerosolicitud = r.numerosolicitud
	 *	JOIN pais p
     *		ON t.idpais = p.id
     *	JOIN pais pa
     *		ON r.idpais = pa.id
	 *	WHERE p.descripcion = 'COLOMBIA';
	 */
	
	
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
			
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error al crear la conexion con la BD!");
			return "fail";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "fail";
		}
		
		
		
		return "Justice for Harambe!!!";
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
	
	
	public Date getSolicitud_fechaPresentacion_desde() {
		return solicitud_fechaPresentacion_desde;
	}
	public void setSolicitud_fechaPresentacion_desde(Date solicitud_fechaPresentacion_desde) {
		this.solicitud_fechaPresentacion_desde = solicitud_fechaPresentacion_desde;
	}
	
	
	public Date getSolicitud_fechaPresentacion_hasta() {
		return solicitud_fechaPresentacion_hasta;
	}
	public void setSolicitud_fechaPresentacion_hasta(Date solicitud_fechaPresentacion_hasta) {
		this.solicitud_fechaPresentacion_hasta = solicitud_fechaPresentacion_hasta;
	}
	
	
	public Date getSolicitud_fechaPublicacion_desde() {
		return solicitud_fechaPublicacion_desde;
	}
	public void setSolicitud_fechaPublicacion_desde(Date solicitud_fechaPublicacion_desde) {
		this.solicitud_fechaPublicacion_desde = solicitud_fechaPublicacion_desde;
	}
	
	
	public Date getSolicitud_fechaPublicacion_hasta() {
		return solicitud_fechaPublicacion_hasta;
	}
	public void setSolicitud_fechaPublicacion_hasta(Date solicitud_fechaPublicacion_hasta) {
		this.solicitud_fechaPublicacion_hasta = solicitud_fechaPublicacion_hasta;
	}
	
	
	public Date getSolicitud_fechaRegistro_desde() {
		return solicitud_fechaRegistro_desde;
	}
	public void setSolicitud_fechaRegistro_desde(Date solicitud_fechaRegistro_desde) {
		this.solicitud_fechaRegistro_desde = solicitud_fechaRegistro_desde;
	}
	
	
	public Date getSolicitud_fechaRegistro_hasta() {
		return solicitud_fechaRegistro_hasta;
	}
	public void setSolicitud_fechaRegistro_hasta(Date solicitud_fechaRegistro_hasta) {
		this.solicitud_fechaRegistro_hasta = solicitud_fechaRegistro_hasta;
	}
	
	
	public String getTipoMarca_descripcion() {
		return tipoMarca_descripcion;
	}
	public void setTipoMarca_descripcion(String tipoMarca_descripcion) {
		this.tipoMarca_descripcion = tipoMarca_descripcion;
	}
	
	
	public String getCobertura_descripcion() {
		return cobertura_descripcion;
	}
	public void setCobertura_descripcion(String cobertura_descripcion) {
		this.cobertura_descripcion = cobertura_descripcion;
	}
	
	
	public String getCategoria_descripcion() {
		return categoria_descripcion;
	}
	public void setCategoria_descripcion(String categoria_descripcion) {
		this.categoria_descripcion = categoria_descripcion;
	}
	
	
	public String getEstado_descripcion() {
		return estado_descripcion;
	}
	public void setEstado_descripcion(String estado_descripcion) {
		this.estado_descripcion = estado_descripcion;
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
		this.columnas = col;
	}
		
	
	
	
}
