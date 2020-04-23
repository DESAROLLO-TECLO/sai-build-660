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
@Table(name="DENOMINACIONES")
public class DenominacionDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 986869058341693150L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "DENOM_ID", unique = true, nullable = false)
	private Long denominacionId;
	
	@Column(name = "DENOM_COD")
	private String denominacionCodigo;
	
	@Column(name = "DENOM_NOMBRE")
	private String denominacionNombre;
	
	@Column(name = "DENOM_VALOR")
	private Long denominacionValor;
	
	@Column(name = "DENOM_TIPO")
	private String denominacionTipo;
	
	@Column(name = "DENOM_STATUS")
	private String denominacionStatus;
	
	@Column(name = "CREADO_POR")
	private Long creadoPor;
	
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	
	@Column(name = "MODIFICADO_POR")
	private Long modificadoPor;
	
	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultimaModificacion;
	
	public Long getDenominacionId() {
		return denominacionId;
	}
	public void setDenominacionId(Long denominacionId) {
		this.denominacionId = denominacionId;
	}
	public String getDenominacionCodigo() {
		return denominacionCodigo;
	}
	public void setDenominacionCodigo(String denominacionCodigo) {
		this.denominacionCodigo = denominacionCodigo;
	}
	public String getDenominacionNombre() {
		return denominacionNombre;
	}
	public void setDenominacionNombre(String denominacionNombre) {
		this.denominacionNombre = denominacionNombre;
	}
	public Long getDenominacionValor() {
		return denominacionValor;
	}
	public void setDenominacionValor(Long denominacionValor) {
		this.denominacionValor = denominacionValor;
	}
	public String getDenominacionTipo() {
		return denominacionTipo;
	}
	public void setDenominacionTipo(String denominacionTipo) {
		this.denominacionTipo = denominacionTipo;
	}
	public String getDenominacionStatus() {
		return denominacionStatus;
	}
	public void setDenominacionStatus(String denominacionStatus) {
		this.denominacionStatus = denominacionStatus;
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
