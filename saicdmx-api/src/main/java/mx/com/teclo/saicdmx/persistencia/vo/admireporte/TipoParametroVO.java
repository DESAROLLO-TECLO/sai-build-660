package mx.com.teclo.saicdmx.persistencia.vo.admireporte;

public class TipoParametroVO {
	
	private Long idTipoParametro;
	private String cdTipoParametro;
	private String nbTipoParametro;
	private String txDescripcion;
	private char stActivo;
	
	public Long getIdTipoParametro() {
		return idTipoParametro;
	}
	public void setIdTipoParametro(Long idTipoParametro) {
		this.idTipoParametro = idTipoParametro;
	}
	public String getCdTipoParametro() {
		return cdTipoParametro;
	}
	public void setCdTipoParametro(String cdTipoParametro) {
		this.cdTipoParametro = cdTipoParametro;
	}
	public String getNbTipoParametro() {
		return nbTipoParametro;
	}
	public void setNbTipoParametro(String nbTipoParametro) {
		this.nbTipoParametro = nbTipoParametro;
	}
	
	
	public String getTxDescripcion() {
		return txDescripcion;
	}
	public void setTxDescripcion(String txDescripcion) {
		this.txDescripcion = txDescripcion;
	}
	public char getStActivo() {
		return stActivo;
	}
	public void setStActivo(char stActivo) {
		this.stActivo = stActivo;
	}
	
	
	
	

}
