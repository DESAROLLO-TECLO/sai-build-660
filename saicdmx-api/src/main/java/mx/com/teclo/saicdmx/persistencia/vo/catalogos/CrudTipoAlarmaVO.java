package mx.com.teclo.saicdmx.persistencia.vo.catalogos;

public class CrudTipoAlarmaVO extends BaseCrudVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -469819896494184076L;
	
	private Long alarmaId;
	private String alarmaCod;
	private String alarmaNombre;
	
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
}
