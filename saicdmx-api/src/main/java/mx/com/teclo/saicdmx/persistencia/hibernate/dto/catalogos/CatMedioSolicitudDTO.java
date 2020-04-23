package mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="TAI056C_AC_MEDIO_SOLICITUD")
public class CatMedioSolicitudDTO implements Serializable {
	
	private static final long serialVersionUID = 5508479045939981071L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID_MEDIO_SOLICITUD", unique = true, nullable = false)
	private Long idMedioSolicitud;
	
	@Column(name = "CD_MEDIO_SOLICITUD")
	private String cdMedioSolicitud;
	
	@Column(name = "NB_MEDIO_SOLICITUD")
	private String nbMedioSolicitud;
	
	@Column(name = "TX_MEDIO_SOLICITUD")
	private String txMedioSolicitud;
	
	@Column(name = "CD_ORDEN")
	private Integer cdOrden;
	
	@Column(name = "ST_ACTIVO")
	private Integer stActivo;

	@Column(name = "ID_USR_CREACION")
	private Integer idUsrCreacion;
	
	@Column(name = "FH_CREACION")
	private Date fhCreacion;
	
	@Column(name = "ID_USR_MODIFICA")
	private Integer idUsrModifica;
	
	@Column(name = "FH_MODIFICACION")
	private Date fhModificacion;

	public Long getIdMedioSolicitud() {
		return idMedioSolicitud;
	}

	public void setIdMedioSolicitud(Long idMedioSolicitud) {
		this.idMedioSolicitud = idMedioSolicitud;
	}

	public String getCdMedioSolicitud() {
		return cdMedioSolicitud;
	}

	public void setCdMedioSolicitud(String cdMedioSolicitud) {
		this.cdMedioSolicitud = cdMedioSolicitud;
	}

	public String getNbMedioSolicitud() {
		return nbMedioSolicitud;
	}

	public void setNbMedioSolicitud(String nbMedioSolicitud) {
		this.nbMedioSolicitud = nbMedioSolicitud;
	}

	public String getTxMedioSolicitud() {
		return txMedioSolicitud;
	}

	public void setTxMedioSolicitud(String txMedioSolicitud) {
		this.txMedioSolicitud = txMedioSolicitud;
	}

	public Integer getCdOrden() {
		return cdOrden;
	}

	public void setCdOrden(Integer cdOrden) {
		this.cdOrden = cdOrden;
	}

	public Integer getStActivo() {
		return stActivo;
	}

	public void setStActivo(Integer stActivo) {
		this.stActivo = stActivo;
	}

	public Integer getIdUsrCreacion() {
		return idUsrCreacion;
	}

	public void setIdUsrCreacion(Integer idUsrCreacion) {
		this.idUsrCreacion = idUsrCreacion;
	}

	public Date getFhCreacion() {
		return fhCreacion;
	}

	public void setFhCreacion(Date fhCreacion) {
		this.fhCreacion = fhCreacion;
	}

	public Integer getIdUsrModifica() {
		return idUsrModifica;
	}

	public void setIdUsrModifica(Integer idUsrModifica) {
		this.idUsrModifica = idUsrModifica;
	}

	public Date getFhModificacion() {
		return fhModificacion;
	}

	public void setFhModificacion(Date fhModificacion) {
		this.fhModificacion = fhModificacion;
	}
	
	

}
