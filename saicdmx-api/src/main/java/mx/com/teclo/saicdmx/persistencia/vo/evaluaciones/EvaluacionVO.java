package mx.com.teclo.saicdmx.persistencia.vo.evaluaciones;

import java.io.Serializable;

public class EvaluacionVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6393219597642411521L;

	private Integer idEvaluacion;
	private String cdEvaluacion;
	private String nbTipoEvaluacion;
	private String nbEvaluacion;
	private String txEvaluacion;
	private Integer nuSecciones;
	private Integer nuPreguntas;
	private String fhVigIni;
	private String fhVigFin;
	private Integer nuCalificacionApro;
	private String nuEvaluados;
	private Integer nuPromedioCal;
	private Integer nuPorAplicacion;
	private String txEfectividad;
	
	public Integer getIdEvaluacion() {
		return idEvaluacion;
	}
	public void setIdEvaluacion(Integer idEvaluacion) {
		this.idEvaluacion = idEvaluacion;
	}
	public String getCdEvaluacion() {
		return cdEvaluacion;
	}
	public void setCdEvaluacion(String cdEvaluacion) {
		this.cdEvaluacion = cdEvaluacion;
	}
	public String getNbTipoEvaluacion() {
		return nbTipoEvaluacion;
	}
	public void setNbTipoEvaluacion(String nbTipoEvaluacion) {
		this.nbTipoEvaluacion = nbTipoEvaluacion;
	}
	public String getNbEvaluacion() {
		return nbEvaluacion;
	}
	public void setNbEvaluacion(String nbEvaluacion) {
		this.nbEvaluacion = nbEvaluacion;
	}
	public String getTxEvaluacion() {
		return txEvaluacion;
	}
	public void setTxEvaluacion(String txEvaluacion) {
		this.txEvaluacion = txEvaluacion;
	}
	public Integer getNuSecciones() {
		return nuSecciones;
	}
	public void setNuSecciones(Integer nuSecciones) {
		this.nuSecciones = nuSecciones;
	}
	public Integer getNuPreguntas() {
		return nuPreguntas;
	}
	public void setNuPreguntas(Integer nuPreguntas) {
		this.nuPreguntas = nuPreguntas;
	}
	public String getFhVigIni() {
		return fhVigIni;
	}
	public void setFhVigIni(String fhVigIni) {
		this.fhVigIni = fhVigIni;
	}
	public String getFhVigFin() {
		return fhVigFin;
	}
	public void setFhVigFin(String fhVigFin) {
		this.fhVigFin = fhVigFin;
	}
	public Integer getNuCalificacionApro() {
		return nuCalificacionApro;
	}
	public void setNuCalificacionApro(Integer nuCalificacionApro) {
		this.nuCalificacionApro = nuCalificacionApro;
	}
	public String getNuEvaluados() {
		return nuEvaluados;
	}
	public void setNuEvaluados(String nuEvaluados) {
		this.nuEvaluados = nuEvaluados;
	}
	public Integer getNuPromedioCal() {
		return nuPromedioCal;
	}
	public void setNuPromedioCal(Integer nuPromedioCal) {
		this.nuPromedioCal = nuPromedioCal;
	}
	public Integer getNuPorAplicacion() {
		return nuPorAplicacion;
	}
	public void setNuPorAplicacion(Integer nuPorAplicacion) {
		this.nuPorAplicacion = nuPorAplicacion;
	}
	public String getTxEfectividad() {
		return txEfectividad;
	}
	public void setTxEfectividad(String txEfectividad) {
		this.txEfectividad = txEfectividad;
	}
}
