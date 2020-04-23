package mx.com.teclo.saicdmx.persistencia.vo.catalogos;

public class CrudBancoVO extends BaseCrudVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5457693935512268606L;
	
	private Long bancoId;
	private String bancoCodigo;
	private String bancoNombre;
	
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
}
