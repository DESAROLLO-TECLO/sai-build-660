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
@Table(name="TURNOS")
public class TurnoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2728050885818214181L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "TURNO_ID", unique = true, nullable = false)	
	private Long turnoId;
	
	@Column(name = "TURNO_COD")
	private String turnoCod;
	
	@Column(name = "TURNO_NOMBRE")
	private String turnoNombre;
	
	@Column(name = "TURNO_INICIO")
	private Date turnoInicio;
	
	@Column(name = "TURNO_FIN")
	private Date turnoFin;
	
	@Column(name = "TURNO_STATUS")
	private String turnoStatus;
	
	@Column(name = "CREADO_POR")
	private Long creadoPor;
	
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	
	@Column(name = "MODIFICADO_POR")
	private Long modificadoPor;
	
	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultimaModificacion;
	
	public Long getTurnoId() {
		return turnoId;
	}
	public void setTurnoId(Long turnoId) {
		this.turnoId = turnoId;
	}
	public String getTurnoCod() {
		return turnoCod;
	}
	public void setTurnoCod(String turnoCod) {
		this.turnoCod = turnoCod;
	}
	public String getTurnoNombre() {
		return turnoNombre;
	}
	public void setTurnoNombre(String turnoNombre) {
		this.turnoNombre = turnoNombre;
	}
	public Date getTurnoInicio() {
		return turnoInicio;
	}
	public void setTurnoInicio(Date turnoInicio) {
		this.turnoInicio = turnoInicio;
	}
	public Date getTurnoFin() {
		return turnoFin;
	}
	public void setTurnoFin(Date turnoFin) {
		this.turnoFin = turnoFin;
	}
	public String getTurnoStatus() {
		return turnoStatus;
	}
	public void setTurnoStatus(String turnoStatus) {
		this.turnoStatus = turnoStatus;
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
