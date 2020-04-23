package mx.com.teclo.saicdmx.persistencia.hibernate.dto.fotomulta;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="FOTOMULTA_PREVALIDACIONES")
public class FotomultaPrevalidacionDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4405819541171602677L;

	@Id
	@Column(name = "PREVALIDACION_ID", unique = true, nullable = false)
	private Long prevalidacionId;
	
	@Column(name="PLACA")
	private String placa;
	
	@Column(name="FECHA")
	private String fecha;
	
	@Column(name="HORA")
	private String hora;
		
	@Column(name="TDSKUID")
	private String tdskuid;
		
	@Column(name="RADAR_TIPO_ID")
	private Long radarTipoId;

	@Column(name="USUARIO_PREVALIDADOR_ID")
	private Long usuarioPrevalidadorId;	
	
	@Column(name="FECHA_PREVALIDACION")
	private Date fechaPrevalidacion;	

	@Column(name="ACEPTADA")
	private int aceptada;

	@Column(name="MOTIVO_DESCARTE_ID")
	private Long motivoDescarteId;	
		
	@Column(name="DUPLICADA")
	private int duplicada;

	@Column(name="VALIDADA_SSP")
	private int validadaSSP;

	@Column(name="ACEPTADA_SSP")
	private int aceptadaSSP;
	
	@Column(name="OFICIAL_PLACA")
	private String oficialPlaca;
	
	@Column(name="OFICIAL_NOMBRE")
	private String oficialNombre;
	
	@Column(name="FECHA_CREACION")	
	private Date fechaCreacion;

	@Column(name="CREADO_POR")
	private Integer creadoPor;
	
	@Column(name="ULTIMA_MODIFICACION")
	private Date ultimaModificacion;
	
	@Column(name="MODIFICADO_POR")
	private Long modificadoPor;
		
	@Column(name="CANCELADA")
	private Integer cancelada;
		
	@Column(name="FECHA_CANCELACION")
	private Date fechaCancelacion;
	
	@Column(name="MOTIVO_CANCELACION")
	private String motivoCancelacion;
	
	@Column(name="ORIGEN_PLACA")
	private Integer origenPlaca;
	
	@Column(name="MOTIVO_CANCELACION_ID")	
	private Integer motivoCancelacionId;
		
	@Column(name="DC_CANCELADO_POR")		
	private Long canceladoPorDc;

	public Long getPrevalidacionId() {
		return prevalidacionId;
	}

	public void setPrevalidacionId(Long prevalidacionId) {
		this.prevalidacionId = prevalidacionId;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getTdskuid() {
		return tdskuid;
	}

	public void setTdskuid(String tdskuid) {
		this.tdskuid = tdskuid;
	}

	public Long getRadarTipoId() {
		return radarTipoId;
	}

	public void setRadarTipoId(Long radarTipoId) {
		this.radarTipoId = radarTipoId;
	}

	public Long getUsuarioPrevalidadorId() {
		return usuarioPrevalidadorId;
	}

	public void setUsuarioPrevalidadorId(Long usuarioPrevalidadorId) {
		this.usuarioPrevalidadorId = usuarioPrevalidadorId;
	}

	public Date getFechaPrevalidacion() {
		return fechaPrevalidacion;
	}

	public void setFechaPrevalidacion(Date fechaPrevalidacion) {
		this.fechaPrevalidacion = fechaPrevalidacion;
	}

	public int getAceptada() {
		return aceptada;
	}

	public void setAceptada(int aceptada) {
		this.aceptada = aceptada;
	}

	public Long getMotivoDescarteId() {
		return motivoDescarteId;
	}

	public void setMotivoDescarteId(Long motivoDescarteId) {
		this.motivoDescarteId = motivoDescarteId;
	}

	public int getDuplicada() {
		return duplicada;
	}

	public void setDuplicada(int duplicada) {
		this.duplicada = duplicada;
	}

	public int getValidadaSSP() {
		return validadaSSP;
	}

	public void setValidadaSSP(int validadaSSP) {
		this.validadaSSP = validadaSSP;
	}

	public int getAceptadaSSP() {
		return aceptadaSSP;
	}

	public void setAceptadaSSP(int aceptadaSSP) {
		this.aceptadaSSP = aceptadaSSP;
	}

	public String getOficialPlaca() {
		return oficialPlaca;
	}

	public void setOficialPlaca(String oficialPlaca) {
		this.oficialPlaca = oficialPlaca;
	}

	public String getOficialNombre() {
		return oficialNombre;
	}

	public void setOficialNombre(String oficialNombre) {
		this.oficialNombre = oficialNombre;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Integer getCreadoPor() {
		return creadoPor;
	}

	public void setCreadoPor(Integer creadoPor) {
		this.creadoPor = creadoPor;
	}

	public Date getUltimaModificacion() {
		return ultimaModificacion;
	}

	public void setUltimaModificacion(Date ultimaModificacion) {
		this.ultimaModificacion = ultimaModificacion;
	}

	public Long getModificadoPor() {
		return modificadoPor;
	}

	public void setModificadoPor(Long modificadoPor) {
		this.modificadoPor = modificadoPor;
	}

	public Long getCanceladoPorDc() {
		return canceladoPorDc;
	}

	public void setCanceladoPorDc(Long canceladoPorDc) {
		this.canceladoPorDc = canceladoPorDc;
	}

	public Integer getCancelada() {
		return cancelada;
	}

	public void setCancelada(Integer cancelada) {
		this.cancelada = cancelada;
	}

	public Date getFechaCancelacion() {
		return fechaCancelacion;
	}

	public void setFechaCancelacion(Date fechaCancelacion) {
		this.fechaCancelacion = fechaCancelacion;
	}

	public String getMotivoCancelacion() {
		return motivoCancelacion;
	}

	public void setMotivoCancelacion(String motivoCancelacion) {
		this.motivoCancelacion = motivoCancelacion;
	}

	public Integer getOrigenPlaca() {
		return origenPlaca;
	}

	public void setOrigenPlaca(Integer origenPlaca) {
		this.origenPlaca = origenPlaca;
	}

	public Integer getMotivoCancelacionId() {
		return motivoCancelacionId;
	}

	public void setMotivoCancelacionId(Integer motivoCancelacionId) {
		this.motivoCancelacionId = motivoCancelacionId;
	}

	public Long getCanceladoPor() {
		return canceladoPorDc;
	}

	public void setCanceladoPor(Long canceladoPorDc) {
		this.canceladoPorDc = canceladoPorDc;
	}

}
