package beans;

import java.sql.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
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
	
	private int    solicitud_numeroSolicitud;
	private int    solicitud_numeroRegistro;
	private char   solicitud_categoriaId;
	private int    solicitud_coberturaId;
	private char   solicitud_tipoMarcaId;
	private char   solicitud_estadoId;
	private Date   solicitud_fechaPresentacion;
	private Date   solicitud_fechaPublicacion;
	private Date   solicitud_fechaRegistro;
	private char   solicitud_clases;
	private char   anotacion_estadoAnotacionId;
	private Date   anotacion_fechaActualizacion;
	private Date   anotacion_fechaVencimiento;
	private Date   instanciaAdministrativa_fechaVencimiento;
	private Date   instanciaAdministrativa_fechaCreacion;
	private char   instanciaAdministrativa_estadoInstanciaId;
	private String titular_nombre;								//para el campo 'titular' se dan las opciones de busqueda por 'nombre',
	private int    titular_rut;									//'rut' o 'numero de solicitud' que mantiene dicho titular
	private int    titular_numeroSolicitud;			
	private int    representante_numeroSolicitud;				//Para el campo 'representante' se dan las opciones de busqueda..
	private int    representante_rut;							//segun el numero de solicitud que tiene cierto representante,
	private String representante_nombre;						//rut del representante o el nombre del mismo
	private int    marcaCliente_numeroSolicitud;				//Para el campo Marcas del cliente se dan las opciones de busqueda por
	private Date   marcaCliente_fechaAlta;						//'numero de solicitud', 'fecha de alta' y 'fecha de baja'
	private Date   marcaCliente_fechaBaja;
	
	
	
	
	//Constructor de la clase
	public queryBean(){
		
	}




	public int getSolicitud_numeroSolicitud() {
		return solicitud_numeroSolicitud;
	}
	public void setSolicitud_numeroSolicitud(int solicitud_numeroSolicitud) {
		this.solicitud_numeroSolicitud = solicitud_numeroSolicitud;
	}


	public int getSolicitud_numeroRegistro() {
		return solicitud_numeroRegistro;
	}
	public void setSolicitud_numeroRegistro(int solicitud_numeroRegistro) {
		this.solicitud_numeroRegistro = solicitud_numeroRegistro;
	}


	public char getSolicitud_categoriaId() {
		return solicitud_categoriaId;
	}
	public void setSolicitud_categoriaId(char solicitud_categoriaId) {
		this.solicitud_categoriaId = solicitud_categoriaId;
	}


	public int getSolicitud_coberturaId() {
		return solicitud_coberturaId;
	}
	public void setSolicitud_coberturaId(int solicitud_coberturaId) {
		this.solicitud_coberturaId = solicitud_coberturaId;
	}

	
	public char getSolicitud_tipoMarcaId() {
		return solicitud_tipoMarcaId;
	}
	public void setSolicitud_tipoMarcaId(char solicitud_tipoMarcaId) {
		this.solicitud_tipoMarcaId = solicitud_tipoMarcaId;
	}


	public char getSolicitud_estadoId() {
		return solicitud_estadoId;
	}
	public void setSolicitud_estadoId(char solicitud_estadoId) {
		this.solicitud_estadoId = solicitud_estadoId;
	}


	public Date getSolicitud_fechaPresentacion() {
		return solicitud_fechaPresentacion;
	}
	public void setSolicitud_fechaPresentacion(Date solicitud_fechaPresentacion) {
		this.solicitud_fechaPresentacion = solicitud_fechaPresentacion;
	}


	public Date getSolicitud_fechaPublicacion() {
		return solicitud_fechaPublicacion;
	}
	public void setSolicitud_fechaPublicacion(Date solicitud_fechaPublicacion) {
		this.solicitud_fechaPublicacion = solicitud_fechaPublicacion;
	}


	public Date getSolicitud_fechaRegistro() {
		return solicitud_fechaRegistro;
	}
	public void setSolicitud_fechaRegistro(Date solicitud_fechaRegistro) {
		this.solicitud_fechaRegistro = solicitud_fechaRegistro;
	}


	public char getSolicitud_clases() {
		return solicitud_clases;
	}
	public void setSolicitud_clases(char solicitud_clases) {
		this.solicitud_clases = solicitud_clases;
	}


	public char getAnotacion_estadoAnotacionId() {
		return anotacion_estadoAnotacionId;
	}
	public void setAnotacion_estadoAnotacionId(char anotacion_estadoAnotacionId) {
		this.anotacion_estadoAnotacionId = anotacion_estadoAnotacionId;
	}


	public Date getAnotacion_fechaActualizacion() {
		return anotacion_fechaActualizacion;
	}
	public void setAnotacion_fechaActualizacion(Date anotacion_fechaActualizacion) {
		this.anotacion_fechaActualizacion = anotacion_fechaActualizacion;
	}


	public Date getAnotacion_fechaVencimiento() {
		return anotacion_fechaVencimiento;
	}
	public void setAnotacion_fechaVencimiento(Date anotacion_fechaVencimiento) {
		this.anotacion_fechaVencimiento = anotacion_fechaVencimiento;
	}

	
	public Date getInstanciaAdministrativa_fechaVencimiento() {
		return instanciaAdministrativa_fechaVencimiento;
	}
	public void setInstanciaAdministrativa_fechaVencimiento(Date instanciaAdministrativa_fechaVencimiento) {
		this.instanciaAdministrativa_fechaVencimiento = instanciaAdministrativa_fechaVencimiento;
	}


	public Date getInstanciaAdministrativa_fechaCreacion() {
		return instanciaAdministrativa_fechaCreacion;
	}
	public void setInstanciaAdministrativa_fechaCreacion(Date instanciaAdministrativa_fechaCreacion) {
		this.instanciaAdministrativa_fechaCreacion = instanciaAdministrativa_fechaCreacion;
	}


	public char getInstanciaAdministrativa_estadoInstanciaId() {
		return instanciaAdministrativa_estadoInstanciaId;
	}
	public void setInstanciaAdministrativa_estadoInstanciaId(char instanciaAdministrativa_estadoInstanciaId) {
		this.instanciaAdministrativa_estadoInstanciaId = instanciaAdministrativa_estadoInstanciaId;
	}


	public String getTitular_nombre() {
		return titular_nombre;
	}
	public void setTitular_nombre(String titular_nombre) {
		this.titular_nombre = titular_nombre;
	}


	public int getTitular_rut() {
		return titular_rut;
	}
	public void setTitular_rut(int titular_rut) {
		this.titular_rut = titular_rut;
	}


	public int getTitular_numeroSolicitud() {
		return titular_numeroSolicitud;
	}
	public void setTitular_numeroSolicitud(int titular_numeroSolicitud) {
		this.titular_numeroSolicitud = titular_numeroSolicitud;
	}


	public int getRepresentante_numeroSolicitud() {
		return representante_numeroSolicitud;
	}
	public void setRepresentante_numeroSolicitud(int representante_numeroSolicitud) {
		this.representante_numeroSolicitud = representante_numeroSolicitud;
	}


	public int getRepresentante_rut() {
		return representante_rut;
	}
	public void setRepresentante_rut(int representante_rut) {
		this.representante_rut = representante_rut;
	}

	
	public String getRepresentante_nombre() {
		return representante_nombre;
	}
	public void setRepresentante_nombre(String representante_nombre) {
		this.representante_nombre = representante_nombre;
	}
	
	
	public int getMarcaCliente_numeroSolicitud() {
		return marcaCliente_numeroSolicitud;
	}
	public void setMarcaCliente_numeroSolicitud(int marcaCliente_numeroSolicitud) {
		this.marcaCliente_numeroSolicitud = marcaCliente_numeroSolicitud;
	}

	
	public Date getMarcaCliente_fechaAlta() {
		return marcaCliente_fechaAlta;
	}
	public void setMarcaCliente_fechaAlta(Date marcaCliente_fechaAlta) {
		this.marcaCliente_fechaAlta = marcaCliente_fechaAlta;
	}


	public Date getMarcaCliente_fechaBaja() {
		return marcaCliente_fechaBaja;
	}
	public void setMarcaCliente_fechaBaja(Date marcaCliente_fechaBaja) {
		this.marcaCliente_fechaBaja = marcaCliente_fechaBaja;
	}
	
	
}
