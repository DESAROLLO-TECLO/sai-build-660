package mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura;

public class InfraccionLoteLCMVO {
	private Integer id;
	private String nombre;
	private String fecha_reasignacion;
	private Integer total;
	private Integer cantidad_procesados;
	private Integer cantidad_cancelados;
	private String fecha_emision;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getFecha_reasignacion() {
		return fecha_reasignacion;
	}
	public void setFecha_reasignacion(String fecha_reasignacion) {
		this.fecha_reasignacion = fecha_reasignacion;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getCantidad_procesados() {
		return cantidad_procesados;
	}
	public void setCantidad_procesados(Integer cantidad_procesados) {
		this.cantidad_procesados = cantidad_procesados;
	}
	public Integer getCantidad_cancelados() {
		return cantidad_cancelados;
	}
	public void setCantidad_cancelados(Integer cantidad_cancelados) {
		this.cantidad_cancelados = cantidad_cancelados;
	}
	public String getFecha_emision() {
		return fecha_emision;
	}
	public void setFecha_emision(String fecha_emision) {
		this.fecha_emision = fecha_emision;
	}
}
