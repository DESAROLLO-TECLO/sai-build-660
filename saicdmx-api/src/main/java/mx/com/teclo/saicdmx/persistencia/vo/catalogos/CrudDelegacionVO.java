package mx.com.teclo.saicdmx.persistencia.vo.catalogos;

public class CrudDelegacionVO extends BaseCrudVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2108455354879060173L;
	
	private Long edoId;
	private Long delId;
	private Long regId;
	private String delCod;
	private String delNombre;
	
	public Long getEdoId() {
		return edoId;
	}
	public void setEdoId(Long edoId) {
		this.edoId = edoId;
	}
	public Long getDelId() {
		return delId;
	}
	public void setDelId(Long delId) {
		this.delId = delId;
	}
	public Long getRegId() {
		return regId;
	}
	public void setRegId(Long regId) {
		this.regId = regId;
	}
	public String getDelCod() {
		return delCod;
	}
	public void setDelCod(String delCod) {
		this.delCod = delCod;
	}
	public String getDelNombre() {
		return delNombre;
	}
	public void setDelNombre(String delNombre) {
		this.delNombre = delNombre;
	}
}
