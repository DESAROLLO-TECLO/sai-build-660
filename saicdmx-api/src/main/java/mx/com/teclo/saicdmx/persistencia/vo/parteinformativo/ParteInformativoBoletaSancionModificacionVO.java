package mx.com.teclo.saicdmx.persistencia.vo.parteinformativo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//import mx.com.teclo.siidf.persistencia.vo.empleados.EmpleadosVO;

@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParteInformativoBoletaSancionModificacionVO implements Serializable { 
	private static final long serialVersionUID = -4197426921975198371L;

	private Long id;
	private String noConsecutivo;
	//@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy HH:mm", timezone="America/Mexico_City")
	private String fecha;
	
	private String situacionActa;
	private String situacionExtravio;
	private String situacionElaborada;
	private String situacionOtro;
	private String situacionOtroDesc;
	private String situacionSelect;
	
	private String oficialNombre;
	private String oficialPlaca;
	private String oficialAoper;
	private String oficialAgrupamiento;
	private String oficialSector;
	
	private String mensaje;
	private String resultado;
	
	private Long modificadoPor;
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the noConsecutivo
	 */
	public String getNoConsecutivo() {
		return noConsecutivo;
	}
	/**
	 * @param noConsecutivo the noConsecutivo to set
	 */
	public void setNoConsecutivo(String noConsecutivo) {
		this.noConsecutivo = noConsecutivo;
	}
	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	/**
	 * @return the oficialNombre
	 */
	public String getOficialNombre() {
		return oficialNombre;
	}
	/**
	 * @param oficialNombre the oficialNombre to set
	 */
	public void setOficialNombre(String oficialNombre) {
		this.oficialNombre = oficialNombre;
	}
	/**
	 * @return the oficialPlaca
	 */
	public String getOficialPlaca() {
		return oficialPlaca;
	}
	/**
	 * @param oficialPlaca the oficialPlaca to set
	 */
	public void setOficialPlaca(String oficialPlaca) {
		this.oficialPlaca = oficialPlaca;
	}
	/**
	 * @return the situacionActa
	 */
	public String getSituacionActa() {
		return situacionActa;
	}
	/**
	 * @param situacionActa the situacionActa to set
	 */
	public void setSituacionActa(String situacionActa) {
		this.situacionActa = situacionActa;
	}
	/**
	 * @return the situacionExtravio
	 */
	public String getSituacionExtravio() {
		return situacionExtravio;
	}
	/**
	 * @param situacionExtravio the situacionExtravio to set
	 */
	public void setSituacionExtravio(String situacionExtravio) {
		this.situacionExtravio = situacionExtravio;
	}
	/**
	 * @return the situacionElaborada
	 */
	public String getSituacionElaborada() {
		return situacionElaborada;
	}
	/**
	 * @param situacionElaborada the situacionElaborada to set
	 */
	public void setSituacionElaborada(String situacionElaborada) {
		this.situacionElaborada = situacionElaborada;
	}
	/**
	 * @return the situacionOtro
	 */
	public String getSituacionOtro() {
		return situacionOtro;
	}
	/**
	 * @param situacionOtro the situacionOtro to set
	 */
	public void setSituacionOtro(String situacionOtro) {
		this.situacionOtro = situacionOtro;
	}
	/**
	 * @return the situacionOtroDesc
	 */
	public String getSituacionOtroDesc() {
		return situacionOtroDesc;
	}
	/**
	 * @param situacionOtroDesc the situacionOtroDesc to set
	 */
	public void setSituacionOtroDesc(String situacionOtroDesc) {
		this.situacionOtroDesc = situacionOtroDesc;
	}
	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}
	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	/**
	 * @return the resultado
	 */
	public String getResultado() {
		return resultado;
	}
	/**
	 * @param resultado the resultado to set
	 */
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	/**
	 * @return the oficialAoper
	 */
	public String getOficialAoper() {
		return oficialAoper;
	}
	/**
	 * @param oficialAoper the oficialAoper to set
	 */
	public void setOficialAoper(String oficialAoper) {
		this.oficialAoper = oficialAoper;
	}
	/**
	 * @return the situacionSelect
	 */
	public String getSituacionSelect() {
		return situacionSelect;
	}
	/**
	 * @param situacionSelect the situacionSelect to set
	 */
	public void setSituacionSelect(String situacionSelect) {
		this.situacionSelect = situacionSelect;
	}
	/**
	 * @return the modificadoPor
	 */
	public Long getModificadoPor() {
		return modificadoPor;
	}
	/**
	 * @param modificadoPor the modificadoPor to set
	 */
	public void setModificadoPor(Long modificadoPor) {
		this.modificadoPor = modificadoPor;
	}
	/**
	 * @return the oficialAgrupamiento
	 */
	public String getOficialAgrupamiento() {
		return oficialAgrupamiento;
	}
	/**
	 * @param oficialAgrupamiento the oficialAgrupamiento to set
	 */
	public void setOficialAgrupamiento(String oficialAgrupamiento) {
		this.oficialAgrupamiento = oficialAgrupamiento;
	}
	/**
	 * @return the oficialSector
	 */
	public String getOficialSector() {
		return oficialSector;
	}
	/**
	 * @param oficialSector the oficialSector to set
	 */
	public void setOficialSector(String oficialSector) {
		this.oficialSector = oficialSector;
	}
}
