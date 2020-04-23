package mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos;

import java.util.Date;

public class CatTipoDocumentoVO {
private Long idDocumento;
private String cdDocumento;
private String nbDocumento;
private String txDocumento;
private Integer cdOrden;
private Integer stActivo;
private Integer idUsrCreacion;
private Date fhCreacion;
private Integer idUsrModifica;
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
public String getTxDocumento() {
	return txDocumento;
}
public void setTxDocumento(String txDocumento) {
	this.txDocumento = txDocumento;
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
