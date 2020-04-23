package mx.com.teclo.saicdmx.persistencia.vo.infracciones;

public class TipoInfraccionVO {
	private String numInfraccion;
	private String nombreTabla;
	private String numControl;
	private String placa;
	
	
	
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getNumControl() {
		return numControl;
	}
	public void setNumControl(String numControl) {
		this.numControl = numControl;
	}
	public String getNumInfraccion() {
		return numInfraccion;
	}
	public void setNumInfraccion(String numInfraccion) {
		this.numInfraccion = numInfraccion;
	}
	public String getNombreTabla() {
		return nombreTabla;
	}
	public void setNombreTabla(String nombreTabla) {
		this.nombreTabla = nombreTabla;
	}
	
	

}
