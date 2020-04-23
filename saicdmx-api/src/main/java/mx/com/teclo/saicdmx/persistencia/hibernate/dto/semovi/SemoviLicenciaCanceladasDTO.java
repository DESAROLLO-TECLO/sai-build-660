package mx.com.teclo.saicdmx.persistencia.hibernate.dto.semovi;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SMV_LICENCIA_CANCELADAS")
public class SemoviLicenciaCanceladasDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8091913931825620892L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SMV_LICENCIA_CANCELADAS_ID")
	private Long smvLicenciaCanceladasId;
	@Column(name = "FOLIO_LICENCIA")
	private String folioLicencia;
	@Column(name = "TIPO_LICENCIA")
	private String tipoLicencia;
	@Column(name = "CURP")
	private String curp;
	@Column(name = "FECHA_ULTIMA_INFRACCION")
	private Date fechaUltimaInfraccion;
	@Column(name = "PUNTOS_ACUMULADOS")
	private Integer puntosdAcumulados;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ARCHIVO_LICENCIA_ID")
	private SemoviArchivosLicenciaDTO semoviArchivosLicenciaDTO;
	@Column(name = "CREADO_POR")
	private Long creadoPor;
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	
	/**
	 * @return the smvLicenciaCanceladasId
	 */
	public Long getSmvLicenciaCanceladasId() {
		return smvLicenciaCanceladasId;
	}
	/**
	 * @param smvLicenciaCanceladasId the smvLicenciaCanceladasId to set
	 */
	public void setSmvLicenciaCanceladasId(Long smvLicenciaCanceladasId) {
		this.smvLicenciaCanceladasId = smvLicenciaCanceladasId;
	}
	/**
	 * @return the folioLicencia
	 */
	public String getFolioLicencia() {
		return folioLicencia;
	}
	/**
	 * @param folioLicencia the folioLicencia to set
	 */
	public void setFolioLicencia(String folioLicencia) {
		this.folioLicencia = folioLicencia;
	}
	/**
	 * @return the tipoLicencia
	 */
	public String getTipoLicencia() {
		return tipoLicencia;
	}
	/**
	 * @param tipoLicencia the tipoLicencia to set
	 */
	public void setTipoLicencia(String tipoLicencia) {
		this.tipoLicencia = tipoLicencia;
	}
	/**
	 * @return the curp
	 */
	public String getCurp() {
		return curp;
	}
	/**
	 * @param curp the curp to set
	 */
	public void setCurp(String curp) {
		this.curp = curp;
	}
	/**
	 * @return the fechaUltimaInfraccion
	 */
	public Date getFechaUltimaInfraccion() {
		return fechaUltimaInfraccion;
	}
	/**
	 * @param fechaUltimaInfraccion the fechaUltimaInfraccion to set
	 */
	public void setFechaUltimaInfraccion(Date fechaUltimaInfraccion) {
		this.fechaUltimaInfraccion = fechaUltimaInfraccion;
	}
	/**
	 * @return the puntosdAcumulados
	 */
	public Integer getPuntosdAcumulados() {
		return puntosdAcumulados;
	}
	/**
	 * @param puntosdAcumulados the puntosdAcumulados to set
	 */
	public void setPuntosdAcumulados(Integer puntosdAcumulados) {
		this.puntosdAcumulados = puntosdAcumulados;
	}
	/**
	 * @return the semoviArchivosLicenciaDTO
	 */
	public SemoviArchivosLicenciaDTO getSemoviArchivosLicenciaDTO() {
		return semoviArchivosLicenciaDTO;
	}
	/**
	 * @param semoviArchivosLicenciaDTO the semoviArchivosLicenciaDTO to set
	 */
	public void setSemoviArchivosLicenciaDTO(SemoviArchivosLicenciaDTO semoviArchivosLicenciaDTO) {
		this.semoviArchivosLicenciaDTO = semoviArchivosLicenciaDTO;
	}
	/**
	 * @return the creadoPor
	 */
	public Long getCreadoPor() {
		return creadoPor;
	}
	/**
	 * @param creadoPor the creadoPor to set
	 */
	public void setCreadoPor(Long creadoPor) {
		this.creadoPor = creadoPor;
	}
	/**
	 * @return the fechaCreacion
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	/**
	 * @param fechaCreacion the fechaCreacion to set
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
