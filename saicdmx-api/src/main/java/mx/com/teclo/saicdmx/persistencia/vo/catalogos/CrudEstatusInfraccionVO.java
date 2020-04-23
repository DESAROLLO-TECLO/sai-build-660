package mx.com.teclo.saicdmx.persistencia.vo.catalogos;

public class CrudEstatusInfraccionVO extends BaseCrudVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6389653173237793323L;
	
	private Long estatusId;
	private String estatusCodigo;
	private String estatusNombre;
	
	public Long getEstatusId() {
		return estatusId;
	}
	public void setEstatusId(Long estatusId) {
		this.estatusId = estatusId;
	}
	public String getEstatusCodigo() {
		return estatusCodigo;
	}
	public void setEstatusCodigo(String estatusCodigo) {
		this.estatusCodigo = estatusCodigo;
	}
	public String getEstatusNombre() {
		return estatusNombre;
	}
	public void setEstatusNombre(String estatusNombre) {
		this.estatusNombre = estatusNombre;
	}
}
