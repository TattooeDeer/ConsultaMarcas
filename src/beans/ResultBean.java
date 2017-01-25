package beans;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dao.UserDAO;




@ManagedBean(name="ResultBean")
@SessionScoped
public class ResultBean {
	private String numeroSolicitud;
	private String numeroRegistro;
	private String clase;
	private String denominacion;
	private String titular_nombre;
	private String titular_pais;
	private String representante_nombre;
	
	
	public ArrayList<ResultBean> getRecords(){
		return UserDAO.getData();
		
	}
	
	
	public String getNumeroSolicitud() {
		return numeroSolicitud;
	}
	public void setNumeroSolicitud(String numeroSolicitud) {
		this.numeroSolicitud = numeroSolicitud;
	}
	public String getNumeroRegistro() {
		return numeroRegistro;
	}
	public void setNumeroRegistro(String numeroRegistro) {
		this.numeroRegistro = numeroRegistro;
	}
	public String getClase() {
		return clase;
	}
	public void setClase(String clase) {
		this.clase = clase;
	}
	public String getDenominacion() {
		return denominacion;
	}
	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}
	public String getTitular_nombre() {
		return titular_nombre;
	}
	public void setTitular_nombre(String titular_nombre) {
		this.titular_nombre = titular_nombre;
	}
	public String getTitular_pais() {
		return titular_pais;
	}
	public void setTitular_pais(String titular_pais) {
		this.titular_pais = titular_pais;
	}
	public String getRepresentante_nombre() {
		return representante_nombre;
	}
	public void setRepresentante_nombre(String representante_nombre) {
		this.representante_nombre = representante_nombre;
	}
	public String getEstadoSolicitud() {
		return estadoSolicitud;
	}
	public void setEstadoSolicitud(String estadoSolicitud) {
		this.estadoSolicitud = estadoSolicitud;
	}
	public String getFechaPresentacion() {
		return fechaPresentacion;
	}
	public void setFechaPresentacion(String fechaPresentacion) {
		this.fechaPresentacion = fechaPresentacion;
	}
	public String getFechaPublicacion() {
		return fechaPublicacion;
	}
	public void setFechaPublicacion(String fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}
	public String getTitular_comuna() {
		return titular_comuna;
	}
	public void setTitular_comuna(String titular_comuna) {
		this.titular_comuna = titular_comuna;
	}
	public String getFechaInstancia() {
		return fechaInstancia;
	}
	public void setFechaInstancia(String fechaInstancia) {
		this.fechaInstancia = fechaInstancia;
	}
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public String getEstadoInstancia() {
		return estadoInstancia;
	}
	public void setEstadoInstancia(String estadoInstancia) {
		this.estadoInstancia = estadoInstancia;
	}
	public String getObservacionInstancia() {
		return observacionInstancia;
	}
	public void setObservacionInstancia(String observacionInstancia) {
		this.observacionInstancia = observacionInstancia;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getTipoMarca() {
		return tipoMarca;
	}
	public void setTipoMarca(String tipoMarca) {
		this.tipoMarca = tipoMarca;
	}
	private String estadoSolicitud;
	private String fechaPresentacion;
	private String fechaPublicacion;
	private String titular_comuna;
	private String fechaInstancia;
	private String fechaVencimiento;
	private String estadoInstancia;
	private String observacionInstancia;
	private String categoria;
	private String tipoMarca;
	
	
}
