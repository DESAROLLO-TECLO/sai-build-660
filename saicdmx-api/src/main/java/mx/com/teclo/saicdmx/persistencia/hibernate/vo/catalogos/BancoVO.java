package mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos;

import java.io.Serializable;

public class BancoVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4548496007799512398L;
	
	private Long bancoId;
	private String bancoCodigo;
	private String bancoNombre;
	private String bancoStatus;
	
	public Long getBancoId() {
		return bancoId;
	}
	public void setBancoId(Long bancoId) {
		this.bancoId = bancoId;
	}
	public String getBancoCodigo() {
		return bancoCodigo;
	}
	public void setBancoCodigo(String bancoCodigo) {
		this.bancoCodigo = bancoCodigo;
	}
	public String getBancoNombre() {
		return bancoNombre;
	}
	public void setBancoNombre(String bancoNombre) {
		this.bancoNombre = bancoNombre;
	}
	public String getBancoStatus() {
		return bancoStatus;
	}
	public void setBancoStatus(String bancoStatus) {
		this.bancoStatus = bancoStatus;
	}
	public String getStatusDesc() {
		return this.getBancoStatus().equals("A") ? "Activo" : "Cancelado";
	}
}
