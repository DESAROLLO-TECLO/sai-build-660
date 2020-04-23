package mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos;

public class UnidadTerritorialVO 
{
	private UTIdVO utId;

	private String utCod;
	private String utNombre;
	private String utStatus;
	
	
	public UTIdVO getUtId() {
		return utId;
	}
	public void setUtId(UTIdVO utId) {
		this.utId = utId;
	}
	public String getUtCod() {
		return utCod;
	}
	public void setUtCod(String utCod) {
		this.utCod = utCod;
	}
	public String getUtNombre() {
		return utNombre;
	}
	public void setUtNombre(String utNombre) {
		this.utNombre = utNombre;
	}
	public String getUtStatus() {
		return utStatus;
	}
	public void setUtStatus(String utStatus) {
		this.utStatus = utStatus;
	}
	public String getStatusDesc() {
		return this.getUtStatus().equals("A") ? "Activo" : "Cancelado";
	}
}
