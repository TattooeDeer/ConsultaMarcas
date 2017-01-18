package beans;
import utility.SqlBuilder;
import java.lang.String;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import Connection.conexion;


@ManagedBean(name = "queryBean")
@SessionScoped
public class queryBean {
	//el formato de los atributos de busqueda es: tabla_atributo
	
	
	
	/*
	private static final ArrayList<String> searchFields = new ArrayList<String>(Arrays.asList("solicitud.numerosolicitud","solicitud.numeroregistro",
			"solicitud.categoriaid","solicitud.coberturaid", "solicitud.tipomarcaid","solicitud.estadoid","solicitud.fechapresentacion",
			"solicitud.fechapublicacion","solicitud.fecharegistro","solicitud.clases","titular.id","representante.id",
			"instanciaAdministrativa.fechavencimiento","instanciaAdministrativa.estadoinstanciaid","instanciaAdministrativa.fechacreacion",
			"anotacion.fechavencimiento","anotacion.estadoanotacionid","anotacion.fechacreacion","marcasCliente.id"));
	 */
	
	private Integer solicitud_numeroSolicitud;
	private Integer solicitud_numeroRegistro;
	private String  solicitud_categoriaId;
	private Integer solicitud_coberturaId;
	private String  solicitud_tipoMarcaId;
	private String  solicitud_estadoId;
	private String    solicitud_fechaPresentacion;
	private String    solicitud_fechaPublicacion;
	private String    solicitud_fechaRegistro;
	private String  solicitud_clases;
	
	private String  anotacion_estadoAnotacionId;
	private String    anotacion_fechaCreacion;
	private String    anotacion_fechaVencimiento;
	
	private String    instanciaAdministrativa_fechaVencimiento;
	private String    instanciaAdministrativa_fechaCreacion;
	private String  instanciaAdministrativa_estadoInstanciaId;
	
	private String  titular_nombre;								//para el campo 'titular' se dan las opciones de busqueda por 'nombre',
	private Integer titular_rut;								//'rut' o 'numero de solicitud' que mantiene dicho titular
	private Integer titular_numeroSolicitud;			
	
	private Integer representante_numeroSolicitud;				//Para el campo 'representante' se dan las opciones de busqueda..
	private Integer representante_rut;							//segun el numero de solicitud que tiene cierto representante,
	private String  representante_nombre;						//rut del representante o el nombre del mismo
	
	//Hay que ver bien a que se refiere con el campo de marcas del cliente
	private Integer marcaCliente_numeroSolicitud;				//Para el campo Marcas del cliente se dan las opciones de busqueda por
	private String    marcaCliente_fechaAlta;						//'numero de solicitud', 'fecha de alta' y 'fecha de baja'
	private String    marcaCliente_fechaBaja;
	
	/*
	private String  solicitud_stmt;
	private String  anotacion_stmt;
	private String  instanciaAdministrativa_stmt;
	private String  titular_stmt;
	private String  representante_stmt;
	private String  marcasCliente_stmt;
	*/
	private ResultSet myRs;
	
	private String solicitud_query;
	private String anotacion_query;
	private String instancia_query;
	private String titular_query;
	private String representante_query;
	
	//Constructor de la clase
	public queryBean(){
		//Valores por defecto de cada variable son null
		setSolicitud_numeroSolicitud(null);
		setSolicitud_numeroRegistro(null);
		setSolicitud_categoriaId(null);
		setSolicitud_coberturaId(null);
		setSolicitud_tipoMarcaId(null);
		setSolicitud_estadoId(null);
		setSolicitud_fechaPresentacion(null);
		setSolicitud_fechaPublicacion(null);
		setSolicitud_fechaRegistro(null);
		setSolicitud_clases(null);
		
		setAnotacion_estadoAnotacionId(null);
		setAnotacion_fechaCreacion(null);
		setAnotacion_fechaVencimiento(null);
		
		setInstanciaAdministrativa_fechaVencimiento(null);
		setInstanciaAdministrativa_fechaCreacion(null);
		setInstanciaAdministrativa_estadoInstanciaId(null);
		
		setTitular_nombre(null);
		setTitular_rut(null);
		setTitular_numeroSolicitud(null);
		
		setRepresentante_numeroSolicitud(null);
		setRepresentante_rut(null);
		setRepresentante_nombre(null);
		
		setMarcaCliente_numeroSolicitud(null);
		setMarcaCliente_fechaAlta(null);
		setMarcaCliente_fechaBaja(null);
		
	}

	//Metodo de busqueda
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
			
			
			/*
			String sql = "SELECT * FROM marcas.solicitud WHERE (numerosolicitud,numeroregistro,categoriaid,coberturaid,"
					+ "tipomarcaid,estadoid,fechapresentacion,fechapublicacion,fecharegistro,clases) = (" + getSolicitud_numeroSolicitud().toString() + ","
					+ getSolicitud_numeroRegistro().toString() + "," + getSolicitud_coberturaId().toString() + "," + getSolicitud_tipoMarcaId().toString() + ","
					+ getSolicitud_estadoId() + "," + getSolicitud_fechaPresentacion().toString()
					+ ","+ getSolicitud_fechaPublicacion().toString() + "," + getSolicitud_fechaRegistro().toString() + "," + getSolicitud_clases().toString() + ");";
			
			setSolicitud_stmt(sql);
			 
			setAnotacion_stmt("SELECT * FROM marcas.anotacion WHERE (estadoanotacionid,fechacreacion) = (" + getAnotacion_estadoAnotacionId() + ","
					+ getAnotacion_fechaCreacion().toString() + getAnotacion_fechaVencimiento().toString() + ");");
			
			setInstanciaAdministrativa_stmt("SELECT * FROM marcas.instancia WHERE (fechavencimiento,estadoinstancia,fechacreacion) = ("
					+ getInstanciaAdministrativa_fechaVencimiento().toString() + "," + getInstanciaAdministrativa_estadoInstanciaId().toString()
					+ "," + getInstanciaAdministrativa_fechaCreacion().toString() + ");");
			
			setTitular_stmt("SELECT * FROM marcas.titular WHERE (nombre,rut,numerosolicitud) = ("
					+ getTitular_nombre() + "," + getTitular_rut().toString() + "," + getTitular_numeroSolicitud().toString() + ");");
			
			setRepresentante_stmt("SELECT * FROM marcas.representante WHERE (numerosolicitud,rut,nombre) = ("
					+ getRepresentante_numeroSolicitud().toString() + "," + getRepresentante_rut().toString()
					+ "," + getRepresentante_nombre() + ");");
			
			//Falta la busqueda por marcas del cliente
			
			*/
			
			SqlBuilder solicitud_builder = new SqlBuilder();
			SqlBuilder anotacion_builder = new SqlBuilder();
			SqlBuilder instanciaAdministrativa_builder = new SqlBuilder();
			SqlBuilder titular_builder = new SqlBuilder();
			SqlBuilder representante_builder = new SqlBuilder();
			
			/*********Solicitud*********/
			ArrayList<String> solicitud_columns = new ArrayList<String>(Arrays.asList("*"));
			ArrayList<String> solicitud_fields = new ArrayList<String>(Arrays.asList("numerosolicitud","numeroregistro","categoriaid",
					"coberturaid","tipomarcaid","estadoid","fechapresentacion","fechapublicacion","fecharegistro"/*,"clases"*/));
			ArrayList<String> solicitud_response = new ArrayList<String>(Arrays.asList(
					getSolicitud_numeroSolicitud().toString(), 
					getSolicitud_numeroRegistro().toString(),
					getSolicitud_categoriaId().toString(),
					getSolicitud_coberturaId().toString(),
					getSolicitud_tipoMarcaId().toString(),
					getSolicitud_estadoId().toString(),
					getSolicitud_fechaPresentacion().toString(),
					getSolicitud_fechaPublicacion().toString(),
					getSolicitud_fechaRegistro().toString()/*,
					getSolicitud_clases().toString()*/));
			
			
			
			/*********Anotacion*********/
			
			
			ArrayList<String> anotacion_columns = new ArrayList<String>(Arrays.asList("numeroinapi","numerosolicitud","numeroregistro",
					"fecha","fechavencimiento","estadoanotacionid","observacion"));
			ArrayList<String> anotacion_fields = new ArrayList<String>(Arrays.asList("estadoanotacionid", "fechacreacion",
					"fechavencimiento"));
			ArrayList<String> anotacion_response = new ArrayList<String>(Arrays.asList(
					getAnotacion_estadoAnotacionId().toString(),
					getAnotacion_fechaCreacion().toString(),
					getAnotacion_fechaVencimiento().toString()));
					
			
			/*********Instancia Administrativa*********/
			
			
			ArrayList<String> instanciaAdministrativa_columns = new ArrayList<String>(Arrays.asList("numeroinapi","numerosolicitud",
					"fecha","fechavencimiento","estadoinstanciaid","observacion"));
			ArrayList<String> instanciaAdministrativa_fields = new ArrayList<String>(Arrays.asList("fechavencimiento",
					"fechacreacion", "estadoinstanciaid"));
			ArrayList<String> instanciaAdministrativa_response = new ArrayList<String>(Arrays.asList(
					getInstanciaAdministrativa_fechaVencimiento().toString(),
					getInstanciaAdministrativa_fechaCreacion().toString(),
					getInstanciaAdministrativa_estadoInstanciaId().toString()));
			
			
			/**************Titular***************/
			
			
			ArrayList<String> titular_columns = new ArrayList<String>(Arrays.asList("nombre","rut","numerosolicitud","email","fono","domicilio","idpais","idciudad"));
			ArrayList<String> titular_fields = new ArrayList<String>(Arrays.asList("nombre", "rut", "numerosolicitud"));
			ArrayList<String> titular_response = new ArrayList<String>(Arrays.asList(
					getTitular_nombre().toString(),
					getTitular_rut().toString(),
					getTitular_numeroSolicitud().toString()));
			
			
			/**************Representante************/
			
			ArrayList<String> representante_columns = new ArrayList<String>(Arrays.asList("rut","nombre","numerosolicitud","email","fono","domicilio","idpais","idciudad"));
			ArrayList<String> representante_fields = new ArrayList<String>(Arrays.asList("numerosolicitud","rut","nombre"));
			ArrayList<String> representante_response = new ArrayList<String>(Arrays.asList(
					getRepresentante_numeroSolicitud().toString(),
					getRepresentante_rut().toString(),
					getRepresentante_nombre().toString()));
			
			
			SqlBuilder builder = new SqlBuilder();
			
			setSolicitud_query(builder.buildQuery(solicitud_columns,"solicitud",solicitud_fields,solicitud_response));
			setAnotacion_query(builder.buildQuery(anotacion_columns,"anotacion",anotacion_fields,anotacion_response));
			setInstancia_query(builder.buildQuery(instanciaAdministrativa_columns,"instancia",instanciaAdministrativa_fields,instanciaAdministrativa_response));
			setTitular_query(builder.buildQuery(titular_columns,"titular",titular_fields,titular_response));
			setRepresentante_query(builder.buildQuery(representante_columns,"representante",representante_fields,representante_response));
			
			
			//Se ejecuta la Query sobre la BD
			/*
			System.out.println(getSolicitud_stmt());
			setMyRs(getSolicitud_stmt(),myStmt);
			*/
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
			System.out.println("Error al crear la conexion con la BD!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(myRs == null){
			return "fail";
		}
		else{
			System.out.println("\nExito en la busqueda-2");
			return "success";
		}
			
		
	}

	
	
	/***********VALIDADORES**************/
	
	//				TODO
	//Los validadores confirman que el valor ingresado corresponda al tipo de dato que debe ser el campo
	//Ademas, fijan el valor de la variable correspondiente a la wildcard "%" si no es llenado
	public void stringValidator(){};
	public void intValidator(){};
	public void dateValidator(){};
	
	/**********GETTER/SETTER************/
	public Integer getSolicitud_numeroSolicitud() {
		return solicitud_numeroSolicitud;
	}
	public void setSolicitud_numeroSolicitud(Integer solicitud_numeroSolicitud) {
		this.solicitud_numeroSolicitud = solicitud_numeroSolicitud;
	}

	
	public Integer getSolicitud_numeroRegistro() {
		return solicitud_numeroRegistro;
	}
	public void setSolicitud_numeroRegistro(Integer solicitud_numeroRegistro) {
			this.solicitud_numeroRegistro = solicitud_numeroRegistro;
	}

	
	public String getSolicitud_categoriaId() {
		return solicitud_categoriaId;
	}
	public void setSolicitud_categoriaId(String solicitud_categoriaId) {
		this.solicitud_categoriaId = solicitud_categoriaId;
	}

	
	public Integer getSolicitud_coberturaId() {
		return solicitud_coberturaId;
	}
	public void setSolicitud_coberturaId(Integer solicitud_coberturaId) {
		this.solicitud_coberturaId = solicitud_coberturaId;
	}

	
	public String getSolicitud_tipoMarcaId() {
		return solicitud_tipoMarcaId;
	}
	public void setSolicitud_tipoMarcaId(String solicitud_tipoMarcaId) {
		this.solicitud_tipoMarcaId = solicitud_tipoMarcaId;
	}

	
	public String getSolicitud_estadoId() {
		return solicitud_estadoId;
	}
	public void setSolicitud_estadoId(String solicitud_estadoId) {
		this.solicitud_estadoId = solicitud_estadoId;
	}

	
	public String getSolicitud_fechaPresentacion() {
		return solicitud_fechaPresentacion;
	}
	public void setSolicitud_fechaPresentacion(String solicitud_fechaPresentacion) {
		this.solicitud_fechaPresentacion = solicitud_fechaPresentacion;
	}

	
	public String getSolicitud_fechaPublicacion() {
		return solicitud_fechaPublicacion;
	}
	public void setSolicitud_fechaPublicacion(String solicitud_fechaPublicacion) {
		this.solicitud_fechaPublicacion = solicitud_fechaPublicacion;
	}

	
	public String getSolicitud_fechaRegistro() {
		return solicitud_fechaRegistro;
	}
	public void setSolicitud_fechaRegistro(String solicitud_fechaRegistro) {
		this.solicitud_fechaRegistro = solicitud_fechaRegistro;
	}

	
	public String getSolicitud_clases() {
		return solicitud_clases;
	}
	public void setSolicitud_clases(String solicitud_clases) {
		this.solicitud_clases = solicitud_clases;
	}

	
	public String getAnotacion_estadoAnotacionId() {
		return anotacion_estadoAnotacionId;
	}
	public void setAnotacion_estadoAnotacionId(String anotacion_estadoAnotacionId) {
		this.anotacion_estadoAnotacionId = anotacion_estadoAnotacionId;
	}

	
	public String getAnotacion_fechaCreacion() {
		return anotacion_fechaCreacion;
	}
	public void setAnotacion_fechaCreacion(String anotacion_fechaCreacion) {
		this.anotacion_fechaCreacion = anotacion_fechaCreacion;
	}

	
	public String getAnotacion_fechaVencimiento() {
		return anotacion_fechaVencimiento;
	}
	public void setAnotacion_fechaVencimiento(String anotacion_fechaVencimiento) {
		this.anotacion_fechaVencimiento = anotacion_fechaVencimiento;
	}

	
	public String getInstanciaAdministrativa_fechaVencimiento() {
		return instanciaAdministrativa_fechaVencimiento;
	}
	public void setInstanciaAdministrativa_fechaVencimiento(String instanciaAdministrativa_fechaVencimiento) {
		this.instanciaAdministrativa_fechaVencimiento = instanciaAdministrativa_fechaVencimiento;
	}

	
	public String getInstanciaAdministrativa_fechaCreacion() {
		return instanciaAdministrativa_fechaCreacion;
	}
	public void setInstanciaAdministrativa_fechaCreacion(String instanciaAdministrativa_fechaCreacion) {
		this.instanciaAdministrativa_fechaCreacion = instanciaAdministrativa_fechaCreacion;
	}

	
	public String getInstanciaAdministrativa_estadoInstanciaId() {
		return instanciaAdministrativa_estadoInstanciaId;
	}
	public void setInstanciaAdministrativa_estadoInstanciaId(String instanciaAdministrativa_estadoInstanciaId) {
		this.instanciaAdministrativa_estadoInstanciaId = instanciaAdministrativa_estadoInstanciaId;
	}

	
	public String getTitular_nombre() {
		return titular_nombre;
	}
	public void setTitular_nombre(String titular_nombre) {
		this.titular_nombre = titular_nombre;
	}

	
	public Integer getTitular_rut() {
		return titular_rut;
	}
	public void setTitular_rut(Integer titular_rut) {
		this.titular_rut = titular_rut;
	}

	
	public Integer getTitular_numeroSolicitud() {
		return titular_numeroSolicitud;
	}
	public void setTitular_numeroSolicitud(Integer titular_numeroSolicitud) {
		this.titular_numeroSolicitud = titular_numeroSolicitud;
	}

	public Integer getRepresentante_numeroSolicitud() {
		return representante_numeroSolicitud;
	}

	public void setRepresentante_numeroSolicitud(Integer representante_numeroSolicitud) {
		this.representante_numeroSolicitud = representante_numeroSolicitud;
	}

	public Integer getRepresentante_rut() {
		return representante_rut;
	}

	public void setRepresentante_rut(Integer representante_rut) {
		this.representante_rut = representante_rut;
	}

	public String getRepresentante_nombre() {
		return representante_nombre;
	}

	public void setRepresentante_nombre(String representante_nombre) {
		this.representante_nombre = representante_nombre;
	}

	public Integer getMarcaCliente_numeroSolicitud() {
		return marcaCliente_numeroSolicitud;
	}

	public void setMarcaCliente_numeroSolicitud(Integer marcaCliente_numeroSolicitud) {
		this.marcaCliente_numeroSolicitud = marcaCliente_numeroSolicitud;
	}

	public String getMarcaCliente_fechaAlta() {
		return marcaCliente_fechaAlta;
	}

	public void setMarcaCliente_fechaAlta(String marcaCliente_fechaAlta) {
		this.marcaCliente_fechaAlta = marcaCliente_fechaAlta;
	}

	public String getMarcaCliente_fechaBaja() {
		return marcaCliente_fechaBaja;
	}

	public void setMarcaCliente_fechaBaja(String marcaCliente_fechaBaja) {
		this.marcaCliente_fechaBaja = marcaCliente_fechaBaja;
	}
/*
	public String getSolicitud_stmt() {
		return solicitud_stmt;
	}

	public void setSolicitud_stmt(String solicitud_stmt) {
		this.solicitud_stmt = solicitud_stmt;
	}

	public String getAnotacion_stmt() {
		return anotacion_stmt;
	}

	public void setAnotacion_stmt(String anotacion_stmt) {
		this.anotacion_stmt = anotacion_stmt;
	}

	public String getInstanciaAdministrativa_stmt() {
		return instanciaAdministrativa_stmt;
	}

	public void setInstanciaAdministrativa_stmt(String instanciaAdministrativa_stmt) {
		this.instanciaAdministrativa_stmt = instanciaAdministrativa_stmt;
	}

	public String getTitular_stmt() {
		return titular_stmt;
	}

	public void setTitular_stmt(String titular_stmt) {
		this.titular_stmt = titular_stmt;
	}

	public String getRepresentante_stmt() {
		return representante_stmt;
	}

	public void setRepresentante_stmt(String representante_stmt) {
		this.representante_stmt = representante_stmt;
	}

	public String getMarcasCliente_stmt() {
		return marcasCliente_stmt;
	}

	public void setMarcasCliente_stmt(String marcasCliente_stmt) {
		this.marcasCliente_stmt = marcasCliente_stmt;
	}
*/
	public void setMyRs(String stmt, Statement myStmt) throws SQLException {
		this.myRs = myStmt.executeQuery(stmt);
		
	}

	public ResultSet getMyRs() {
		return this.myRs;
	}

	public String getSolicitud_query() {
		return solicitud_query;
	}

	public void setSolicitud_query(String solicitud_query) {
		this.solicitud_query = solicitud_query;
	}

	public String getAnotacion_query() {
		return anotacion_query;
	}

	public void setAnotacion_query(String anotacion_query) {
		this.anotacion_query = anotacion_query;
	}

	public String getInstancia_query() {
		return instancia_query;
	}

	public void setInstancia_query(String instancia_query) {
		this.instancia_query = instancia_query;
	}

	public String getTitular_query() {
		return titular_query;
	}

	public void setTitular_query(String titular_query) {
		this.titular_query = titular_query;
	}

	public String getRepresentante_query() {
		return representante_query;
	}

	public void setRepresentante_query(String representante_query) {
		this.representante_query = representante_query;
	}

	public void setMyRs(ResultSet myRs) {
		this.myRs = myRs;
	}

	
	
}
