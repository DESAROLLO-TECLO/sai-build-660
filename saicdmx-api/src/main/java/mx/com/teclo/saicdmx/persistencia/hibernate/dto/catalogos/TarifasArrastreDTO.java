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
@Table(name="TARIFAS_ARRASTRE")
public class TarifasArrastreDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7198555691315042215L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "T_ARRAS_ID", unique = true, nullable = false)
	private Long tArrasId;
	
	@Column(name = "T_ARRAS_COD")
	private String tArrasCodigo;
	
	@Column(name = "T_ARRAS_NOMBRE")
	private String tArrasNombre;
	
	@Column(name = "T_ARRAS_VALOR")
	private Double tArrasValor;
	
	@Column(name = "T_ARRAS_STATUS")
	private String tArrasEstatus;
	
	@Column(name = "CREADO_POR")
	private Long creadoPor;
	
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	
	@Column(name = "MODIFICADO_POR")
	private Long modPor;
	
	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultimaMod;

	public Long gettArrasId() {
		return tArrasId;
	}

	public void settArrasId(Long tArrasId) {
		this.tArrasId = tArrasId;
	}

	public String gettArrasCodigo() {
		return tArrasCodigo;
	}

	public void settArrasCodigo(String tArrasCodigo) {
		this.tArrasCodigo = tArrasCodigo;
	}

	public String gettArrasNombre() {
		return tArrasNombre;
	}

	public void settArrasNombre(String tArrasNombre) {
		this.tArrasNombre = tArrasNombre;
	}

	public Double gettArrasValor() {
		return tArrasValor;
	}

	public void settArrasValor(Double tArrasValor) {
		this.tArrasValor = tArrasValor;
	}

	public String gettArrasEstatus() {
		return tArrasEstatus;
	}

	public void settArrasEstatus(String tArrasEstatus) {
		this.tArrasEstatus = tArrasEstatus;
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

	public Long getModPor() {
		return modPor;
	}

	public void setModPor(Long modPor) {
		this.modPor = modPor;
	}

	public Date getUltimaMod() {
		return ultimaMod;
	}

	public void setUltimaMod(Date ultimaMod) {
		this.ultimaMod = ultimaMod;
	}
	
}
