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
@Table(name = "TAI046C_AC_TIPO_TRAMITE")
public class TipoTramiteDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1035565413039110359L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_TRAMITE", unique = true, nullable = false)
	private String idTramite;

	@Column(name = "CD_TRAMITE")
	private String cdTramite;

	@Column(name = "NB_TRAMITE")
	private String nbTramite;

	@Column(name = "TX_DESCRIPCION")
	private String txDescripcion;

	@Column(name = "CD_ORDEN")
	private Integer cdOrden;

	@Column(name = "ST_ACTIVO")
	private boolean stActivo;

	@Column(name = "ID_USR_CREACION")
	private Integer idUserCreacion;

	@Column(name = "FH_CREACION")
	private Date fhCreacion;

	@Column(name = "ID_USR_MODIFICA")
	private Integer idUsrModifica;

	@Column(name = "FH_MODIFICACION")
	private Date fhModificacion;

	public String getIdTramite() {
		return idTramite;
	}

	public void setIdTramite(String idTramite) {
		this.idTramite = idTramite;
	}

	public String getCdTramite() {
		return cdTramite;
	}

	public void setCdTramite(String cdTramite) {
		this.cdTramite = cdTramite;
	}

	public String getNbTramite() {
		return nbTramite;
	}

	public void setNbTramite(String nbTramite) {
		this.nbTramite = nbTramite;
	}

	public String getTxDescripcion() {
		return txDescripcion;
	}

	public void setTxDescripcion(String txDescripcion) {
		this.txDescripcion = txDescripcion;
	}

	public Integer getCdOrden() {
		return cdOrden;
	}

	public void setCdOrden(Integer cdOrden) {
		this.cdOrden = cdOrden;
	}

	public boolean isStActivo() {
		return stActivo;
	}

	public void setStActivo(boolean stActivo) {
		this.stActivo = stActivo;
	}

	public Integer getIdUserCreacion() {
		return idUserCreacion;
	}

	public void setIdUserCreacion(Integer idUserCreacion) {
		this.idUserCreacion = idUserCreacion;
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
