package mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

//import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TAI044C_TIPO_FECHAS")
public class TipoFechasDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4324183546853414693L;
	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_TIPO_FECHA")
	private Long idTipoFecha;
	@Column(name="NB_TIPO_FECHA")
	private String nbTipoFecha;
	@Column(name="CD_TIPO_FECHA")
	private String cdTipoFecha;
	@Column(name="NU_ORDEN")
	private Long nuOrden;
	@Column(name="TX_TIPO_FECHA")
	private String txTipoFecha;
	@Column(name="ST_TIPO_FECHA")
	private Long stTipoFecha;
	@Column(name="ID_USR_CREACION")
	private Long idUsrCreacion;
	@Column(name="FH_CREACION")
	private Date fhCreacion;
	@Column(name="ID_USR_MODIFICA")
	private Long idUsrModifica;
	@Column(name="FH_MODIFICACION")
	private Date fhModifica;
	
	
	public Long getIdTipoFecha() {
		return idTipoFecha;
	}
	public void setIdTipoFecha(Long idTipoFecha) {
		this.idTipoFecha = idTipoFecha;
	}
	public String getNbTipoFecha() {
		return nbTipoFecha;
	}
	public void setNbTipoFecha(String nbTipoFecha) {
		this.nbTipoFecha = nbTipoFecha;
	}
	public String getCdTipoFecha() {
		return cdTipoFecha;
	}
	public void setCdTipoFecha(String cdTipoFecha) {
		this.cdTipoFecha = cdTipoFecha;
	}
	public Long getNuOrden() {
		return nuOrden;
	}
	public void setNuOrden(Long nuOrden) {
		this.nuOrden = nuOrden;
	}
	public String getTxTipoFecha() {
		return txTipoFecha;
	}
	public void setTxTipoFecha(String txTipoFecha) {
		this.txTipoFecha = txTipoFecha;
	}
	public Long getStTipoFecha() {
		return stTipoFecha;
	}
	public void setStTipoFecha(Long stTipoFecha) {
		this.stTipoFecha = stTipoFecha;
	}
	public Long getIdUsrCreacion() {
		return idUsrCreacion;
	}
	public void setIdUsrCreacion(Long idUsrCreacion) {
		this.idUsrCreacion = idUsrCreacion;
	}
	public Date getFhCreacion() {
		return fhCreacion;
	}
	public void setFhCreacion(Date fhCreacion) {
		this.fhCreacion = fhCreacion;
	}
	public Long getIdUsrModifica() {
		return idUsrModifica;
	}
	public void setIdUsrModifica(Long idUsrModifica) {
		this.idUsrModifica = idUsrModifica;
	}
	public Date getFhModifica() {
		return fhModifica;
	}
	public void setFhModifica(Date fhModifica) {
		this.fhModifica = fhModifica;
	}
}
