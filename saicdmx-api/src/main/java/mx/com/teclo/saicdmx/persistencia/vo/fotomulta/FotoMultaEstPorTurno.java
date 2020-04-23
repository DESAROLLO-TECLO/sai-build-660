package mx.com.teclo.saicdmx.persistencia.vo.fotomulta;

public class FotoMultaEstPorTurno {

	private String fechaPrevaliadacion;	
	private String turno;
	private String total;
	
	/**
	 * @return the fechaPrevaliadacion
	 */
	public String getFechaPrevaliadacion() {
		return fechaPrevaliadacion;
	}
	/**
	 * @param fechaPrevaliadacion the fechaPrevaliadacion to set
	 */
	public void setFechaPrevaliadacion(String fechaPrevaliadacion) {
		this.fechaPrevaliadacion = fechaPrevaliadacion;
	}
	/**
	 * @return the turno
	 */
	public String getTurno() {
		return turno;
	}
	/**
	 * @param turno the turno to set
	 */
	public void setTurno(String turno) {
		this.turno = turno;
	}
	/**
	 * @return the total
	 */
	public String getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(String total) {
		this.total = total;
	}
}
