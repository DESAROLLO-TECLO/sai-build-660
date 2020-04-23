package mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos;

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
@Table(name="GRUAS")
public class GruaDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7994335639027141668L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "GRUA_ID", unique = true, nullable = false)
	private Long gruaId;
	@Column(name = "GRUA_COD")
	private String gruaCod;
	@Column(name = "GRUA_SMS")
	private Integer gruaSms;
	@Column(name = "CONSE_ID")
	private Long conseId;
	@Column(name = "GSTAT_ID")
	private Long gStatId;
	@Column(name = "GRUA_STATUS")
	private String gruaStatus;
	@Column(name = "CREADO_POR")
	private Long creadoPor;
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	@Column(name = "MODIFICADO_POR")
	private Long modPor;
	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultimaMod;	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "TIP_G_ID", nullable = false)
	private TipoGruaDTO tipoGrua;	
	@Column(name = "GRUA_COBRAR_ARRASTRE")
	private String gruaCobrarArrastre;
	/**
	 * @return the gruaId
	 */
	public Long getGruaId() {
		return gruaId;
	}
	/**
	 * @param gruaId the gruaId to set
	 */
	public void setGruaId(Long gruaId) {
		this.gruaId = gruaId;
	}
	/**
	 * @return the gruaCod
	 */
	public String getGruaCod() {
		return gruaCod;
	}
	/**
	 * @param gruaCod the gruaCod to set
	 */
	public void setGruaCod(String gruaCod) {
		this.gruaCod = gruaCod;
	}
	/**
	 * @return the gruaSms
	 */
	public Integer getGruaSms() {
		return gruaSms;
	}
	/**
	 * @param gruaSms the gruaSms to set
	 */
	public void setGruaSms(Integer gruaSms) {
		this.gruaSms = gruaSms;
	}
	/**
	 * @return the conseId
	 */
	public Long getConseId() {
		return conseId;
	}
	/**
	 * @param conseId the conseId to set
	 */
	public void setConseId(Long conseId) {
		this.conseId = conseId;
	}
	/**
	 * @return the gStatId
	 */
	public Long getgStatId() {
		return gStatId;
	}
	/**
	 * @param gStatId the gStatId to set
	 */
	public void setgStatId(Long gStatId) {
		this.gStatId = gStatId;
	}
	/**
	 * @return the gruaStatus
	 */
	public String getGruaStatus() {
		return gruaStatus;
	}
	/**
	 * @param gruaStatus the gruaStatus to set
	 */
	public void setGruaStatus(String gruaStatus) {
		this.gruaStatus = gruaStatus;
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
	 * @return the modPor
	 */
	public Long getModPor() {
		return modPor;
	}
	/**
	 * @param modPor the modPor to set
	 */
	public void setModPor(Long modPor) {
		this.modPor = modPor;
	}
	/**
	 * @return the ultimaMod
	 */
	public Date getUltimaMod() {
		return ultimaMod;
	}
	/**
	 * @param ultimaMod the ultimaMod to set
	 */
	public void setUltimaMod(Date ultimaMod) {
		this.ultimaMod = ultimaMod;
	}	
	/**
	 * @return the tipoGrua
	 */
	public TipoGruaDTO getTipoGrua() {
		return tipoGrua;
	}
	/**
	 * @param tipoGrua the tipoGrua to set
	 */
	public void setTipoGrua(TipoGruaDTO tipoGrua) {
		this.tipoGrua = tipoGrua;
	}
	/**
	 * @return the gruaCobrarArrastre
	 */
	public String getGruaCobrarArrastre() {
		return gruaCobrarArrastre;
	}
	/**
	 * @param gruaCobrarArrastre the gruaCobrarArrastre to set
	 */
	public void setGruaCobrarArrastre(String gruaCobrarArrastre) {
		this.gruaCobrarArrastre = gruaCobrarArrastre;
	}
}
