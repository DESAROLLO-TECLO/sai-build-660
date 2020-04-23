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
@Table(name="PARTE_INFO_C_DOCS_INFRACS")
public class ParteInformativoCDocsInfracsDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ParteInformativoCDocsInfracsPK parteInformativoCDocsInfracsPK;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name="PI_ID", referencedColumnName="ID_PI", insertable=false, updatable=false)
	private ParteInformativoCDocsDTO piId;
	
	@Column(name = "CREADO_POR", length=50)
	private Long creadoPor;
	
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	
	@Column(name = "MODIFICADO_POR")
	private Long modificadoPor;
	
	@Column(name = "FECHA_MODIFICACION")
	private Date fechaModificacion;
	
	/**
	 * @return the parteInformativoCDocsInfracsPK
	 */
	public ParteInformativoCDocsInfracsPK getParteInformativoCDocsInfracsPK() {
		return parteInformativoCDocsInfracsPK;
	}

	/**
	 * @param parteInformativoCDocsInfracsPK the parteInformativoCDocsInfracsPK to set
	 */
	public void setParteInformativoCDocsInfracsPK(ParteInformativoCDocsInfracsPK parteInformativoCDocsInfracsPK) {
		this.parteInformativoCDocsInfracsPK = parteInformativoCDocsInfracsPK;
	}

	/**
	 * @return the piId
	 */
	public ParteInformativoCDocsDTO getPiId() {
		return piId;
	}

	/**
	 * @param piId the piId to set
	 */
	public void setPiId(ParteInformativoCDocsDTO piId) {
		this.piId = piId;
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
	 * @return the infracNum
	 */
	@Transient
	public String getInfracNum() {
		return this.parteInformativoCDocsInfracsPK.getInfracNum();
		//return infracNum;
	}

	/**
	 * @return the infracPlaca
	 */
	@Transient
	public String getInfracPlaca() {
		return parteInformativoCDocsInfracsPK.getInfracPlaca();
	}
}
