package mx.com.teclo.saicdmx.persistencia.vo.atencionciudadana;

import java.io.Serializable;
import java.util.Date;

public class EstatusSeguimientoTramiteACVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8893612543323461892L;
	
	private Long idStSeguimiento;
	private String cdStSeguimiento;
	private String nbStSeguimiento;
	private String txStSeguimiento;
	private String cdColor;
	private Integer cdOrden;
	private Integer stActivo;
	private Long idUsrCreacion;
	private Date fhCreacion;
	private Long idUsrModifica;
	private Date fhModificacion;
	
	public Long getIdStSeguimiento() {
		return idStSeguimiento;
	}
	public void setIdStSeguimiento(Long idStSeguimiento) {
		this.idStSeguimiento = idStSeguimiento;
	}
	public String getCdStSeguimiento() {
		return cdStSeguimiento;
	}
	public void setCdStSeguimiento(String cdStSeguimiento) {
		this.cdStSeguimiento = cdStSeguimiento;
	}
	public String getNbStSeguimiento() {
		return nbStSeguimiento;
	}
	public void setNbStSeguimiento(String nbStSeguimiento) {
		this.nbStSeguimiento = nbStSeguimiento;
	}
	public String getTxStSeguimiento() {
		return txStSeguimiento;
	}
	public void setTxStSeguimiento(String txStSeguimiento) {
		this.txStSeguimiento = txStSeguimiento;
	}
	public String getCdColor() {
		return cdColor;
	}
	public void setCdColor(String cdColor) {
		this.cdColor = cdColor;
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
	public Date getFhModificacion() {
		return fhModificacion;
	}
	public void setFhModificacion(Date fhModificacion) {
		this.fhModificacion = fhModificacion;
	}
}
