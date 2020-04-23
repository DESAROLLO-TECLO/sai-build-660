package mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TIPO_ALARMAS")
public class TipoAlarmaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1004615255973845243L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "TIP_ALARM_ID", unique = true, nullable = false)
	private Long alarmaId;
	
	@Column(name = "TIP_ALARM_COD")
	private String alarmaCod;
	
	@Column(name = "TIP_ALARM_NOMBRE")
	private String alarmaNombre;
	
	@Column(name = "TIP_ALARM_STATUS")
	private String alarmaStatus;
	
	@Column(name = "CREADO_POR")
	private Long creadoPor;
	
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	
	@Column(name = "MODIFICADO_POR")
	private Long modificadoPor;
	
	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultimaModificacion;
	
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
	public Long getCreadoPor() {
		return creadoPor;
	}
	public void setCreadoPor(Long creadoPor) {
		this.creadoPor = creadoPor;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Long getModificadoPor() {
		return modificadoPor;
	}
	public void setModificadoPor(Long modificadoPor) {
		this.modificadoPor = modificadoPor;
	}
	public Date getUltimaModificacion() {
		return ultimaModificacion;
	}
	public void setUltimaModificacion(Date ultimaModificacion) {
		this.ultimaModificacion = ultimaModificacion;
	}
}
