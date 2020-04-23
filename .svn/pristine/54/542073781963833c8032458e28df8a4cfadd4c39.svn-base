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
@Table(name="TAI047C_AC_TIPO_DOCUMENTO")
public class CatTipoDocumentoDTO implements Serializable{
	

	private static final long serialVersionUID = 5508479045939981071L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID_DOCUMENTO", unique = true, nullable = false)
	private Long idDocumento;
	
	@Column(name = "CD_DOCUMENTO")
	private String cdDocumento;
	
	@Column(name = "NB_DOCUMENTO")
	private String nbDocumento;
	
	@Column(name = "TX_DESCRIPCION")
	private String txDescripcion;
	
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

	public Long getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(Long idDocumento) {
		this.idDocumento = idDocumento;
	}

	public String getCdDocumento() {
		return cdDocumento;
	}

	public void setCdDocumento(String cdDocumento) {
		this.cdDocumento = cdDocumento;
	}

	public String getNbDocumento() {
		return nbDocumento;
	}

	public void setNbDocumento(String nbDocumento) {
		this.nbDocumento = nbDocumento;
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

