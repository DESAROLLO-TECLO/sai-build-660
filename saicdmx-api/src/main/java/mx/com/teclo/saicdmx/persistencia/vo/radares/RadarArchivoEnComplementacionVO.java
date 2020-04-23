package mx.com.teclo.saicdmx.persistencia.vo.radares;

import java.util.Date;

/**
 * 
 * @author UnitisDes0416
 *
 */
public class RadarArchivoEnComplementacionVO {
	
	private Long radarArchivoId;
	private String archivoNombre;
	private Date fechaEmision;;
	private String estatusProceso;
	private String enProceso;
	private Integer totalValidas;
	private Integer totalInvalidas;
	private Integer nuArchAEnviar;
	private Integer nuArchEnviados;
	private Integer fcFirmadas;
	private Integer estatusProcesoid;
	private Integer archivoTipoId;
	
	/**
	 * @return the radarArchivoId
	 */
	public Long getRadarArchivoId() {
		return radarArchivoId;
	}
	/**
	 * @param radarArchivoId the radarArchivoId to set
	 */
	public void setRadarArchivoId(Long radarArchivoId) {
		this.radarArchivoId = radarArchivoId;
	}
	/**
	 * @return the archivoNombre
	 */
	public String getArchivoNombre() {
		return archivoNombre;
	}
	/**
	 * @param archivoNombre the archivoNombre to set
	 */
	public void setArchivoNombre(String archivoNombre) {
		this.archivoNombre = archivoNombre;
	}
	/**
	 * @return the fechaEmision
	 */
	public Date getFechaEmision() {
		return fechaEmision;
	}
	/**
	 * @param fechaEmision the fechaEmision to set
	 */
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	/**
	 * @return the estatusProceso
	 */
	public String getEstatusProceso() {
		return estatusProceso;
	}
	/**
	 * @param estatusProceso the estatusProceso to set
	 */
	public void setEstatusProceso(String estatusProceso) {
		this.estatusProceso = estatusProceso;
	}
	/**
	 * @return the enProceso
	 */
	public String getEnProceso() {
		return enProceso;
	}
	/**
	 * @param enProceso the enProceso to set
	 */
	public void setEnProceso(String enProceso) {
		this.enProceso = enProceso;
	}
	/**
	 * @return the totalValidas
	 */
	public Integer getTotalValidas() {
		return totalValidas;
	}
	/**
	 * @param totalValidas the totalValidas to set
	 */
	public void setTotalValidas(Integer totalValidas) {
		this.totalValidas = totalValidas;
	}
	/**
	 * @return the totalInvalidas
	 */
	public Integer getTotalInvalidas() {
		return totalInvalidas;
	}
	/**
	 * @param totalInvalidas the totalInvalidas to set
	 */
	public void setTotalInvalidas(Integer totalInvalidas) {
		this.totalInvalidas = totalInvalidas;
	}
	public Integer getNuArchAEnviar() {
		return nuArchAEnviar;
	}
	public void setNuArchAEnviar(Integer nuArchAEnviar) {
		this.nuArchAEnviar = nuArchAEnviar;
	}
	public Integer getNuArchEnviados() {
		return nuArchEnviados;
	}
	public void setNuArchEnviados(Integer nuArchEnviados) {
		this.nuArchEnviados = nuArchEnviados;
	}
	public Integer getFcFirmadas() {
		return fcFirmadas;
	}
	public void setFcFirmadas(Integer fcFirmadas) {
		this.fcFirmadas = fcFirmadas;
	}
	public Integer getEstatusProcesoid() {
		return estatusProcesoid;
	}
	public void setEstatusProcesoid(Integer estatusProcesoid) {
		this.estatusProcesoid = estatusProcesoid;
	}
	public Integer getArchivoTipoId() {
		return archivoTipoId;
	}
	public void setArchivoTipoId(Integer archivoTipoId) {
		this.archivoTipoId = archivoTipoId;
	}
}