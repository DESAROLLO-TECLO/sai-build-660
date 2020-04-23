package mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos;

import java.io.Serializable;

public class CatTipoTramiteVO implements Serializable {
	
	private static final long serialVersionUID = 5661983939587738208L;
	
	private Long idTramite;
	private String cdTramite;
	private String nbTramite;
	private String txDescripcion;
	private Integer cdOrden;
	
	
	public Long getIdTramite() {
		return idTramite;
	}
	public void setIdTramite(Long idTramite) {
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
}
