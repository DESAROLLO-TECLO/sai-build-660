package mx.com.teclo.saicdmx.persistencia.vo.catalogos;

import java.io.Serializable;

public class TipoFechaVO implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6197041247320699002L;
	
	
	private Long idTipoFecha; 
	private String nbTipoFecha;
	private Long nuOrden;
	private String txTipoFecha;
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
	
	

}
