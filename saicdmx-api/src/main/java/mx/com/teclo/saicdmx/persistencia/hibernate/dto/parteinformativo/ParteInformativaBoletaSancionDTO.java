package mx.com.teclo.saicdmx.persistencia.hibernate.dto.parteinformativo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.empleados.EmpleadosDTO;

@Entity
@Table(name="PARTE_INFO_BOLETA_SANCION")
public class ParteInformativaBoletaSancionDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID_REG", unique = true, nullable = false)
	private Long idReg;
	
	@Column(name = "NO_CONSECUTIVO", length=15)
	private String noConsecutivo;

	@Column(name = "SIT_CON_ACTA", length=1)
	private String situacionActa;
	
	@Column(name = "SIT_EXTRAVIO", length=1)
	private String situacionExtravio;
	
	@Column(name = "SIT_MAL_ELABORADA", length=1)
	private String situacionElaborada;
	
	@Column(name = "SIT_OTRO", length=1)
	private String situacionOtro;
	
	@Column(name = "SIT_OTRO_DESC", length=50)
	private String situacionOtroDesc;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name="OFICIAL_ID", referencedColumnName="EMP_ID", insertable=false, updatable=false)
	private EmpleadosDTO oficialId;
	
	@Column(name = "SECTOR", length=50)
	private String sector;
	
	@Column(name = "FECHA_SELLO")
	private Date fecha;
	
	@Column(name = "CREADO_POR")
	private Long creadoPor;
	
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	
	@Column(name = "MODIFICADO_POR")
	private Long modificadoPor;
	
	@Column(name = "FECHA_MODIFICACION")
	private Date fechaModificacion;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "piId")
	private List<ParteInformativoBoletaInfracsDTO> infracciones;

	/**
	 * @return the idReg
	 */
	public Long getIdReg() {
		return idReg;
	}

	/**
	 * @param idReg the idReg to set
	 */
	public void setIdReg(Long idReg) {
		this.idReg = idReg;
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
	 * @return the oficialId
	 */
	public EmpleadosDTO getOficialId() {
		return oficialId;
	}

	/**
	 * @param oficialId the oficialId to set
	 */
	public void setOficialId(EmpleadosDTO oficialId) {
		this.oficialId = oficialId;
	}

	/**
	 * @return the sector
	 */
	public String getSector() {
		return sector;
	}

	/**
	 * @param sector the sector to set
	 */
	public void setSector(String sector) {
		this.sector = sector;
	}

	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
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
	 * @return the fechaModificacion
	 */
	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	/**
	 * @param fechaModificacion the fechaModificacion to set
	 */
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	/**
	 * @return the infracciones
	 */
	public List<ParteInformativoBoletaInfracsDTO> getInfracciones() {
		return infracciones;
	}

	/**
	 * @param infracciones the infracciones to set
	 */
	public void setInfracciones(List<ParteInformativoBoletaInfracsDTO> infracciones) {
		this.infracciones = infracciones;
	}
}
