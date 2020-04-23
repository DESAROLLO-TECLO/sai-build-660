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
@Table(name="CAUSALES")
public class CausalesDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3111806381546449507L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "CAUSAL_ID", unique = true, nullable = false)
	private Long causalId;
	
	@Column(name = "CAUSAL_COD")
	private String causalCodigo;
	
	@Column(name = "CAUSAL_NOMBRE")
	private String causalNombre;
	
	@Column(name = "CAUSAL_STATUS")
	private String causalStatus;
	
	@Column(name = "CREADO_POR")
	private Long creadoPor;
	
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	
	@Column(name = "MODIFICADO_POR")
	private Long modificadoPor;
	
	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultimaModificacion;
	
	public Long getCausalId() {
		return causalId;
	}
	public void setCausalId(Long causalId) {
		this.causalId = causalId;
	}
	public String getCausalCodigo() {
		return causalCodigo;
	}
	public void setCausalCodigo(String causalCodigo) {
		this.causalCodigo = causalCodigo;
	}
	public String getCausalNombre() {
		return causalNombre;
	}
	public void setCausalNombre(String causalNombre) {
		this.causalNombre = causalNombre;
	}
	public String getCausalStatus() {
		return causalStatus;
	}
	public void setCausalStatus(String causalStatus) {
		this.causalStatus = causalStatus;
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
