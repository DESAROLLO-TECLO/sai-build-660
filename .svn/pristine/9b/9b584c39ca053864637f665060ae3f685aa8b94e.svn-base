package mx.com.teclo.saicdmx.persistencia.vo.infracciones;

public class CargaDigitalizacionWebSPVO {
	
	private Long empleado_id;
	private Integer folios_repetidos;
	private Integer folios_liberados;
	private Integer folios_procesados;
	private Integer result_out;
	private String message_out;
	
	public CargaDigitalizacionWebSPVO(Long empleado_id, Integer folios_repetidos, Integer folios_liberados,
			Integer folios_procesados, Integer result_out, String message_out) {
		this.empleado_id = empleado_id;
		this.folios_repetidos = folios_repetidos;
		this.folios_liberados = folios_liberados;
		this.folios_procesados = folios_procesados;
		this.result_out = result_out;
		this.message_out = message_out;
	}
	public Long getEmpleado_id() {
		return empleado_id;
	}
	public void setEmpleado_id(Long empleado_id) {
		this.empleado_id = empleado_id;
	}
	public Integer getFolios_repetidos() {
		return folios_repetidos;
	}
	public void setFolios_repetidos(Integer folios_repetidos) {
		this.folios_repetidos = folios_repetidos;
	}
	public Integer getFolios_liberados() {
		return folios_liberados;
	}
	public void setFolios_liberados(Integer folios_liberados) {
		this.folios_liberados = folios_liberados;
	}
	public Integer getFolios_procesados() {
		return folios_procesados;
	}
	public void setFolios_procesados(Integer folios_procesados) {
		this.folios_procesados = folios_procesados;
	}
	public Integer getResult_out() {
		return result_out;
	}
	public void setResult_out(Integer result_out) {
		this.result_out = result_out;
	}
	public String getMessage_out() {
		return message_out;
	}
	public void setMessage_out(String message_out) {
		this.message_out = message_out;
	}
	public CargaDigitalizacionWebVO generaRespuestaVO(){
		return new CargaDigitalizacionWebVO(
				empleado_id,
				folios_repetidos,
				folios_liberados,
				folios_procesados,
				result_out,
				message_out
				);
	}
}
