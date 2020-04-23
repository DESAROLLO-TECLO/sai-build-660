package mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos;

import java.util.Date;

public class CatMedioSolicitudVO {
	
	private Long idMedioSolicitud;
	private String cdMedioSolicitud;
	private String nbMedioSolicitud;
	private String txMedioSolicitud;
	private Integer cdOrden;
	private Integer stActivo;
	private Integer idUsrCreacion;
	private Date fhCreacion;
	private Integer idUsrModifica;
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
