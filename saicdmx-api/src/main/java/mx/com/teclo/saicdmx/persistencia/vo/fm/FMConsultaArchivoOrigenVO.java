package mx.com.teclo.saicdmx.persistencia.vo.fm;

import java.util.Date;

public class FMConsultaArchivoOrigenVO {

	private Integer id_Archivo;
	private String id_Tipo_Archivo;
	private String nb_Archivo;
	private Double porcentaje;
	private String nb_Original; 
	private Integer id_Tipo_Deteccion; 
	private String st_Proceso; 
	private Integer cantidad_Cargado; 
	private Date fh_Ini_Carga; 
	private Date fh_Fin_Carga;
	private Integer st_Activo;
	private Integer cantidad_Reportado; 
	private Integer cantidad_Inactivo; 
	private Integer cantidad_Procesado; 
	private Integer st_Complementado;
	private Integer cantidadProcesable;

	

	public Integer getId_Archivo() {
		return id_Archivo;
	}
	public void setId_Archivo(Integer id_Archivo) {
		this.id_Archivo = id_Archivo;
	}
	
	
	
	
	public String getId_Tipo_Archivo() {
		return id_Tipo_Archivo;
	}
	public void setId_Tipo_Archivo(String id_Tipo_Archivo) {
		this.id_Tipo_Archivo = id_Tipo_Archivo;
	}
	public String getNb_Archivo() {
		return nb_Archivo;
	}
	
	
	public void setNb_Archivo(String nb_Archivo) {
		this.nb_Archivo = nb_Archivo;
	}
	
	
	
	
	public Double getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(Double porcentaje) {
		this.porcentaje = porcentaje;
	}
	public String getNb_Original() {
		return nb_Original;
	}
	public void setNb_Original(String nb_Original) {
		this.nb_Original = nb_Original;
	}
	public Integer getId_Tipo_Deteccion() {
		return id_Tipo_Deteccion;
	}
	public void setId_Tipo_Deteccion(Integer id_Tipo_Deteccion) {
		this.id_Tipo_Deteccion = id_Tipo_Deteccion;
	}
	public String getSt_Proceso() {
		return st_Proceso;
	}
	public void setSt_Proceso(String st_Proceso) {
		this.st_Proceso = st_Proceso;
	}
	public Integer getCantidad_Cargado() {
		return cantidad_Cargado;
	}
	public void setCantidad_Cargado(Integer cantidad_Cargado) {
		this.cantidad_Cargado = cantidad_Cargado;
	}
	public Date getFh_Ini_Carga() {
		return fh_Ini_Carga;
	}
	public void setFh_Ini_Carga(Date fh_Ini_Carga) {
		this.fh_Ini_Carga = fh_Ini_Carga;
	}
	public Date getFh_Fin_Carga() {
		return fh_Fin_Carga;
	}
	public void setFh_Fin_Carga(Date fh_Fin_Carga) {
		this.fh_Fin_Carga = fh_Fin_Carga;
	}
	public Integer getSt_Activo() {
		return st_Activo;
	}
	public void setSt_Activo(Integer st_Activo) {
		this.st_Activo = st_Activo;
	}
	public Integer getCantidad_Reportado() {
		return cantidad_Reportado;
	}
	public void setCantidad_Reportado(Integer cantidad_Reportado) {
		this.cantidad_Reportado = cantidad_Reportado;
	}
	public Integer getCantidad_Inactivo() {
		return cantidad_Inactivo;
	}
	public void setCantidad_Inactivo(Integer cantidad_Inactivo) {
		this.cantidad_Inactivo = cantidad_Inactivo;
	}
	public Integer getCantidad_Procesado() {
		return cantidad_Procesado;
	}
	public void setCantidad_Procesado(Integer cantidad_Procesado) {
		this.cantidad_Procesado = cantidad_Procesado;
	}
	public Integer getSt_Complementado() {
		return st_Complementado;
	}
	public void setSt_Complementado(Integer st_Complementado) {
		this.st_Complementado = st_Complementado;
	}
	public Integer getCantidadProcesable() {
		return cantidadProcesable;
	}
	public void setCantidadProcesable(Integer cantidadProcesable) {
		this.cantidadProcesable = cantidadProcesable;
	}
	
	
	
	
}
