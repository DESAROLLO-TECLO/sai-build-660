package mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos;

import java.io.Serializable;

public class TipoAlarmaVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 456913457010206572L;
	
	private Long alarmaId;
	private String alarmaCod;
	private String alarmaNombre;
	private String alarmaStatus;
	
	public Long getAlarmaId() {
		return alarmaId;
	}
	public void setAlarmaId(Long alarmaId) {
		this.alarmaId = alarmaId;
	}
	public String getAlarmaCod() {
		return alarmaCod;
	}
	public void setAlarmaCod(String alarmaCod) {
		this.alarmaCod = alarmaCod;
	}
	public String getAlarmaNombre() {
		return alarmaNombre;
	}
	public void setAlarmaNombre(String alarmaNombre) {
		this.alarmaNombre = alarmaNombre;
	}
	public String getAlarmaStatus() {
		return alarmaStatus;
	}
	public void setAlarmaStatus(String alarmaStatus) {
		this.alarmaStatus = alarmaStatus;
	}
	public String getStatusDesc() {
		return this.getAlarmaStatus().equals("A") ? "Activo" : "Cancelado";
	}
}
