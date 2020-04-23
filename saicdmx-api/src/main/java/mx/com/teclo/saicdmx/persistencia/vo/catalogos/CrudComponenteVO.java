package mx.com.teclo.saicdmx.persistencia.vo.catalogos;

public class CrudComponenteVO extends BaseCrudVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3272345043200986875L;
	
	private Long idComponente;
	private String codigoComponente;
	private String nombreComponente;
	private Long ordenComponente;
	
	public Long getIdComponente() {
		return idComponente;
	}
	public void setIdComponente(Long idComponente) {
		this.idComponente = idComponente;
	}
	public String getCodigoComponente() {
		return codigoComponente;
	}
	public void setCodigoComponente(String codigoComponente) {
		this.codigoComponente = codigoComponente;
	}
	public String getNombreComponente() {
		return nombreComponente;
	}
	public void setNombreComponente(String nombreComponente) {
		this.nombreComponente = nombreComponente;
	}
	public Long getOrdenComponente() {
		return ordenComponente;
	}
	public void setOrdenComponente(Long ordenComponente) {
		this.ordenComponente = ordenComponente;
	}
}
