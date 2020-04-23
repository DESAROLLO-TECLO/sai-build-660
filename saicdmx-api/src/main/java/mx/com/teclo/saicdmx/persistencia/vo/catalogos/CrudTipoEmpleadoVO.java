package mx.com.teclo.saicdmx.persistencia.vo.catalogos;


public class CrudTipoEmpleadoVO extends BaseCrudVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5240423399029502697L;
	
	private Long empTipId;
	private String empTipCodigo;
	private String empTipNombre;
	public Long getEmpTipId() {
		return empTipId;
	}
	public void setEmpTipId(Long empTipId) {
		this.empTipId = empTipId;
	}
	public String getEmpTipCodigo() {
		return empTipCodigo;
	}
	public void setEmpTipCodigo(String empTipCodigo) {
		this.empTipCodigo = empTipCodigo;
	}
	public String getEmpTipNombre() {
		return empTipNombre;
	}
	public void setEmpTipNombre(String empTipNombre) {
		this.empTipNombre = empTipNombre;
	}
}
