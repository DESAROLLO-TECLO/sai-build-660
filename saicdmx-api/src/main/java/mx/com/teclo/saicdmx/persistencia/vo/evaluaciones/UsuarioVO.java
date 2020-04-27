package mx.com.teclo.saicdmx.persistencia.vo.evaluaciones;

import java.io.Serializable;

public class UsuarioVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4886894636330858381L;
	private Long idUsEvaluacion;
	private String empPlaca; 
	private String empApePaterno; 
	private String empApeMaterno; 
	private String empNombre;
	private String fhInicio;
	private String fhFin;
	private Integer nuCalificacion;
	private Integer nuIntentos;
	private String nbStCalificacion;
	public Long getIdUsEvaluacion() {
		return idUsEvaluacion;
	}
	public void setIdUsEvaluacion(Long idUsEvaluacion) {
		this.idUsEvaluacion = idUsEvaluacion;
	}
	public String getEmpPlaca() {
		return empPlaca;
	}
	public void setEmpPlaca(String empPlaca) {
		this.empPlaca = empPlaca;
	}
	public String getEmpApePaterno() {
		return empApePaterno;
	}
	public void setEmpApePaterno(String empApePaterno) {
		this.empApePaterno = empApePaterno;
	}
	public String getEmpApeMaterno() {
		return empApeMaterno;
	}
	public void setEmpApeMaterno(String empApeMaterno) {
		this.empApeMaterno = empApeMaterno;
	}
	public String getEmpNombre() {
		return empNombre;
	}
	public void setEmpNombre(String empNombre) {
		this.empNombre = empNombre;
	}
	public String getFhInicio() {
		return fhInicio;
	}
	public void setFhInicio(String fhInicio) {
		this.fhInicio = fhInicio;
	}
	public String getFhFin() {
		return fhFin;
	}
	public void setFhFin(String fhFin) {
		this.fhFin = fhFin;
	}
	public Integer getNuCalificacion() {
		return nuCalificacion;
	}
	public void setNuCalificacion(Integer nuCalificacion) {
		this.nuCalificacion = nuCalificacion;
	}
	public Integer getNuIntentos() {
		return nuIntentos;
	}
	public void setNuIntentos(Integer nuIntentos) {
		this.nuIntentos = nuIntentos;
	}
	public String getNbStCalificacion() {
		return nbStCalificacion;
	}
	public void setNbStCalificacion(String nbStCalificacion) {
		this.nbStCalificacion = nbStCalificacion;
	}
}
