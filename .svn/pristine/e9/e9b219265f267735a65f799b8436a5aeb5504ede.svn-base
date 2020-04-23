package mx.com.teclo.saicdmx.persistencia.hibernate.dto.atencionCiudadana;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="TAI049D_AC_EXPEDIENTES")
public class ExpedienteTramiteDTO implements Serializable{

	private static final long serialVersionUID = 4167978824333484105L;
	
	@Id
	@SequenceGenerator(name = "SQAI049D_AC_EXP", sequenceName = "SQAI049D_AC_EXP", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQAI049D_AC_EXP")
	@Column(name = "ID_EXPEDIENTE", nullable = false, unique = true)
	private Long idExpediente;
	
	@Column(name = "ID_AC_TRAMITE")
	private String folioTramite;
	
	@Column(name="NB_ARCHIVO")
	private String nbArchivo;
	
	@Column(name = "LB_ARCHIVO")
	private Blob archivo;
	
	@Column(name="CD_TIPO")
	private String tipoExp;
	
	@Column(name="ST_ACTIVO")
	private Integer stActivo;
	
	@Column(name ="ID_USR_CREACION", updatable = false)
	private Long creadoPor;
	
	@Column(name="FH_CREACION", updatable = false)
	private Date fhCreacion;
	
	@Column(name="ID_USR_MODIFICA")
	private Long modificadoPor;
	
	@Column(name = "FH_MODIFICACION")
	private Date fhModificacion;

	public Long getIdExpediente() {
		return idExpediente;
	}

	public void setIdExpediente(Long idExpediente) {
		this.idExpediente = idExpediente;
	}

	public String getFolioTramite() {
		return folioTramite;
	}

	public void setFolioTramite(String folioTramite) {
		this.folioTramite = folioTramite;
	}

	public String getNbArchivo() {
		return nbArchivo;
	}

	public void setNbArchivo(String nbArchivo) {
		this.nbArchivo = nbArchivo;
	}

	public Blob getArchivo() {
		return archivo;
	}

	public void setArchivo(Blob archivo) {
		this.archivo = archivo;
	}

	public String getTipoExp() {
		return tipoExp;
	}

	public void setTipoExp(String tipoExp) {
		this.tipoExp = tipoExp;
	}

	public Integer getStActivo() {
		return stActivo;
	}

	public void setStActivo(Integer stActivo) {
		this.stActivo = stActivo;
	}

	public Long getCreadoPor() {
		return creadoPor;
	}

	public void setCreadoPor(Long creadoPor) {
		this.creadoPor = creadoPor;
	}

	public Date getFhCreacion() {
		return fhCreacion;
	}

	public void setFhCreacion(Date fhCreacion) {
		this.fhCreacion = fhCreacion;
	}

	public Long getModificadoPor() {
		return modificadoPor;
	}

	public void setModificadoPor(Long modificadoPor) {
		this.modificadoPor = modificadoPor;
	}

	public Date getFhModificacion() {
		return fhModificacion;
	}

	public void setFhModificacion(Date fhModificacion) {
		this.fhModificacion = fhModificacion;
	}
}
