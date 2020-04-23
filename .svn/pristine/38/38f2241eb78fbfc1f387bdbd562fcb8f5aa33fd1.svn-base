package mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos;

public class TipoIngresoVO {
	
	private Long tIngrId;
	private String tIngrCod;
	private String tIngrNombre;
	private String tIngrStatus;
	
	public Long gettIngrId() {
		return tIngrId;
	}
	public void settIngrId(Long tIngrId) {
		this.tIngrId = tIngrId;
	}
	public String gettIngrCod() {
		return tIngrCod;
	}
	public void settIngrCod(String tIngrCod) {
		this.tIngrCod = tIngrCod;
	}
	public String gettIngrNombre() {
		return tIngrNombre;
	}
	public void settIngrNombre(String tIngrNombre) {
		this.tIngrNombre = tIngrNombre;
	}
	public String gettIngrStatus() {
		return tIngrStatus;
	}
	public void settIngrStatus(String tIngrStatus) {
		this.tIngrStatus = tIngrStatus;
	}
	public String getStatusDesc() {
		return this.gettIngrStatus().equals("A") ? "Activo" : "Cancelado";
	}
}
