package mx.com.teclo.saicdmx.persistencia.hibernate.dto.parteinformativo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.Transient;

@Entity
@Table(name="PARTE_INFO_BOLETA_INFRACS")
public class ParteInformativoBoletaInfracsDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ParteInformativoBoletaInfracsPK parteInformativoBoletaInfracsPK;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name="PI_ID", referencedColumnName="ID_REG", insertable=false, updatable=false)
	private ParteInformativaBoletaSancionDTO piId;
	
	@Column(name = "CREADO_POR", length=50)
	private Long creadoPor;
	
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	
	@Column(name = "MODIFICADO_POR")
	private Long modificadoPor;
	
	@Column(name = "FECHA_MODIFICACION")
	private Date fechaModificacion;

	/**
	 * @return the piId
	 */
	public ParteInformativaBoletaSancionDTO getPiId() {
		return piId;
	}

	/**
	 * @param piId the piId to set
	 */
	public void setPiId(ParteInformativaBoletaSancionDTO piId) {
		this.piId = piId;
	}
	
	/**
	 * @return the infracNum
	 */
	@Transient
	public String getInfracNum() {
		return this.parteInformativoBoletaInfracsPK.getInfracNum();
		//return infracNum;
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
	 * @return the parteInformativoBoletaInfracsPK
	 */
	public ParteInformativoBoletaInfracsPK getParteInformativoBoletaInfracsPK() {
		return parteInformativoBoletaInfracsPK;
	}

	/**
	 * @param parteInformativoBoletaInfracsPK the parteInformativoBoletaInfracsPK to set
	 */
	public void setParteInformativoBoletaInfracsPK(ParteInformativoBoletaInfracsPK parteInformativoBoletaInfracsPK) {
		this.parteInformativoBoletaInfracsPK = parteInformativoBoletaInfracsPK;
	}
}
