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
@Table(name="BANCOS")
public class BancoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1359106618026519116L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "BAN_ID", unique = true, nullable = false)
	private Long bancoId;
	
	@Column(name = "BAN_COD")
	private String bancoCodigo;
	
	@Column(name = "BAN_NOMBRE")
	private String bancoNombre;
	
	@Column(name = "BAN_STATUS")
	private String bancoStatus;
	
	@Column(name = "CREADO_POR")
	private Long creadoPor;
	
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	
	@Column(name = "MODIFICADO_POR")
	private Long modificadoPor;
	
	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultimaModificacion;
	
	public Long getBancoId() {
		return bancoId;
	}
	public void setBancoId(Long bancoId) {
		this.bancoId = bancoId;
	}
	public String getBancoCodigo() {
		return bancoCodigo;
	}
	public void setBancoCodigo(String bancoCodigo) {
		this.bancoCodigo = bancoCodigo;
	}
	public String getBancoNombre() {
		return bancoNombre;
	}
	public void setBancoNombre(String bancoNombre) {
		this.bancoNombre = bancoNombre;
	}
	public String getBancoStatus() {
		return bancoStatus;
	}
	public void setBancoStatus(String bancoStatus) {
		this.bancoStatus = bancoStatus;
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
