package mx.com.teclo.saicdmx.persistencia.hibernate.dto.empleados;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLEADOS_FIRMA")
public class EmpleadoFirmaDTO implements Serializable {
 
 
	private static final long serialVersionUID = -5576489050291267102L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CERT_ID ", unique = true, nullable = false)
	private Long certId;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "EMP_ID")
	private EmpleadosDTO empleadoDTO;
	@Column(name = "CERT_NOMBRE")
	private String certNombre;
	@Column(name = "KEY_NOMBRE")
	private String keyNombre;
	@Column(name = "CERT_ARCHIVO")
	private Blob certArchivo;
	@Column(name = "KEY_ARCHIVO")
	private Blob keyArchivo;
	@Column(name = "CERT_EMITIDO_PARA")
	private String certEmitidoPara;
	@Column(name = "CERT_EMITIDO_POR")
	private String certEmitidoPor;
	@Column(name = "CERT_VALIDO_DESDE")
	private Date certValidoDesde;
	@Column(name = "CERT_VALIDO_HASTA")
	private Date certValidoHasta;
	@Column(name = "VALIDADO")
	private Integer validado;
	@Column(name = "ESTATUS")
	private Integer estatus;
	@Column(name = "CREADO_POR")
	private Long creadoPor;
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	@Column(name = "MODIFICADO_POR")
	private Long modificadoPor;
	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultimaModificacion;
	
	public Long getCertId() {
		return certId;
	}
	public void setCertId(Long certId) {
		this.certId = certId;
	}
	public EmpleadosDTO getEmpleadoDTO() {
		return empleadoDTO;
	}
	public void setEmpleadoDTO(EmpleadosDTO empleadoDTO) {
		this.empleadoDTO = empleadoDTO;
	}
	public String getCertNombre() {
		return certNombre;
	}
	public void setCertNombre(String certNombre) {
		this.certNombre = certNombre;
	}
	public String getKeyNombre() {
		return keyNombre;
	}
	public void setKeyNombre(String keyNombre) {
		this.keyNombre = keyNombre;
	}
	public Blob getCertArchivo() {
		return certArchivo;
	}
	public void setCertArchivo(Blob certArchivo) {
		this.certArchivo = certArchivo;
	}
	public Blob getKeyArchivo() {
		return keyArchivo;
	}
	public void setKeyArchivo(Blob keyArchivo) {
		this.keyArchivo = keyArchivo;
	}
	public String getCertEmitidoPara() {
		return certEmitidoPara;
	}
	public void setCertEmitidoPara(String certEmitidoPara) {
		this.certEmitidoPara = certEmitidoPara;
	}
	public String getCertEmitidoPor() {
		return certEmitidoPor;
	}
	public void setCertEmitidoPor(String certEmitidoPor) {
		this.certEmitidoPor = certEmitidoPor;
	}
	public Date getCertValidoDesde() {
		return certValidoDesde;
	}
	public void setCertValidoDesde(Date certValidoDesde) {
		this.certValidoDesde = certValidoDesde;
	}
	public Date getCertValidoHasta() {
		return certValidoHasta;
	}
	public void setCertValidoHasta(Date certValidoHasta) {
		this.certValidoHasta = certValidoHasta;
	}
	 
	
	public Integer getValidado() {
		return validado;
	}
	public void setValidado(Integer validado) {
		this.validado = validado;
	}
	public Integer getEstatus() {
		return estatus;
	}
	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
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
