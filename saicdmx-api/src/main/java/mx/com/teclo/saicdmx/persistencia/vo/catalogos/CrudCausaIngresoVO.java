package mx.com.teclo.saicdmx.persistencia.vo.catalogos;

public class CrudCausaIngresoVO extends BaseCrudVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4052312469694565718L;
	
	private Long idCausaIngreso;
	private String codigoCausaIngreso;
	private String nombreCausaIngreso;
	
	public Long getIdCausaIngreso() {
		return idCausaIngreso;
	}
	public void setIdCausaIngreso(Long idCausaIngreso) {
		this.idCausaIngreso = idCausaIngreso;
	}
	public String getCodigoCausaIngreso() {
		return codigoCausaIngreso;
	}
	public void setCodigoCausaIngreso(String codigoCausaIngreso) {
		this.codigoCausaIngreso = codigoCausaIngreso;
	}
	public String getNombreCausaIngreso() {
		return nombreCausaIngreso;
	}
	public void setNombreCausaIngreso(String nombreCausaIngreso) {
		this.nombreCausaIngreso = nombreCausaIngreso;
	}
}
