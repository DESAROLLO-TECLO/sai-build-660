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
@Table(name="PROGRAMAS")
public class ProgramaDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -28172994810053479L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "PROG_ID", unique = true, nullable = false)
	private Long programaId;
	
	@Column(name = "PROG_COD")
	private String programaCodigo;
	
	@Column(name = "PROG_NOMBRE")
	private String programaNombre;
	
	@Column(name = "PROG_STATUS")
	private String programaStatus;
	
	@Column(name = "CREADO_POR")
	private Long creadoPor;
	
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	
	@Column(name = "MODIFICADO_POR")
	private Long modificadoPor;
	
	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultimaModificacion;
	
	public Long getProgramaId() {
		return programaId;
	}
	public void setProgramaId(Long programaId) {
		this.programaId = programaId;
	}
	public String getProgramaCodigo() {
		return programaCodigo;
	}
	public void setProgramaCodigo(String programaCodigo) {
		this.programaCodigo = programaCodigo;
	}
	public String getProgramaNombre() {
		return programaNombre;
	}
	public void setProgramaNombre(String programaNombre) {
		this.programaNombre = programaNombre;
	}
	public String getProgramaStatus() {
		return programaStatus;
	}
	public void setProgramaStatus(String programaStatus) {
		this.programaStatus = programaStatus;
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
