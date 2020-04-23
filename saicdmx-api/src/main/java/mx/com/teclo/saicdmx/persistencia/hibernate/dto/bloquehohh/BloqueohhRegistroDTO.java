package mx.com.teclo.saicdmx.persistencia.hibernate.dto.bloquehohh;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "BLOQUEOHH_REGISTRO")
public class BloqueohhRegistroDTO implements Serializable {

	 
	private static final long serialVersionUID = 8642572925546093591L;
	 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "BLOQUEOHH_REGISTRO_ID")
	private Long registroId;
	@Column(name = "NUMERO_SERIE_HH_ID")
	private String  numeroSeriehh;
	@Column(name = "OFICIAL_ID")
	private Long oficialId;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CAT_TIPO_BLOQUEO_ID")
	private BloqueohhCatTipoBloqueoDTO bloqueohhCatTipoBloqueo;
	@Column(name = "ESTATUS_BLOQUEO")
	private Integer estatusBloqueo;
	@Column(name = "ACTIVO")
	private Integer activo;
	@Column(name = "FECHA_BLOQUEO")
	private Date fechaBloqueo;
	@Column(name = "FECHA_DESBLOQUEO")
	private Date fechaDesbloqueo;
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	@Column(name = "USUARIO_MODIFICA")
	private Long usuarioModifica;
	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultimaModificacion;
	@Column(name = "CREADO_POR")
	private Long creadoPor;
	public Long getRegistroId() {
		return registroId;
	}
	public void setRegistroId(Long registroId) {
		this.registroId = registroId;
	}
	public String getNumeroSeriehh() {
		return numeroSeriehh;
	}
	public void setNumeroSeriehh(String numeroSeriehh) {
		this.numeroSeriehh = numeroSeriehh;
	}
	public Long getOficialId() {
		return oficialId;
	}
	public void setOficialId(Long oficialId) {
		this.oficialId = oficialId;
	}
	 
	public BloqueohhCatTipoBloqueoDTO getBloqueohhCatTipoBloqueo() {
		return bloqueohhCatTipoBloqueo;
	}
	public void setBloqueohhCatTipoBloqueo(BloqueohhCatTipoBloqueoDTO bloqueohhCatTipoBloqueo) {
		this.bloqueohhCatTipoBloqueo = bloqueohhCatTipoBloqueo;
	}
	public Integer getEstatusBloqueo() {
		return estatusBloqueo;
	}
	public void setEstatusBloqueo(Integer estatusBloqueo) {
		this.estatusBloqueo = estatusBloqueo;
	}
	public Integer getActivo() {
		return activo;
	}
	public void setActivo(Integer activo) {
		this.activo = activo;
	}
	public Date getFechaBloqueo() {
		return fechaBloqueo;
	}
	public void setFechaBloqueo(Date fechaBloqueo) {
		this.fechaBloqueo = fechaBloqueo;
	}
	public Date getFechaDesbloqueo() {
		return fechaDesbloqueo;
	}
	public void setFechaDesbloqueo(Date fechaDesbloqueo) {
		this.fechaDesbloqueo = fechaDesbloqueo;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Long getUsuarioModifica() {
		return usuarioModifica;
	}
	public void setUsuarioModifica(Long usuarioModifica) {
		this.usuarioModifica = usuarioModifica;
	}
	public Date getUltimaModificacion() {
		return ultimaModificacion;
	}
	public void setUltimaModificacion(Date ultimaModificacion) {
		this.ultimaModificacion = ultimaModificacion;
	}
	public Long getCreadoPor() {
		return creadoPor;
	}
	public void setCreadoPor(Long creadoPor) {
		this.creadoPor = creadoPor;
	}
	


}
