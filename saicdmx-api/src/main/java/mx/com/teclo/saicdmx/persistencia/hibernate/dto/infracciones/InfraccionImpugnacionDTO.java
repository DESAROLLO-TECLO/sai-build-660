package mx.com.teclo.saicdmx.persistencia.hibernate.dto.infracciones;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="INFRACCION_IMPUGNACION")
public class InfraccionImpugnacionDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4673712887869607364L;

	@Id
	@Column(name="IMPUGNACION_ID" , unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long impugnacionId;
	
	@Column(name="IMPUGNACION_OFICIO_JUR")
	private String impugnacionOficioJur;
	
	@Column(name="IMPUGNACION_JUICIO")
	private String impugnacionJuicio;
	
	@Column(name="ACTOR_APE_PATERNO")
	private String  actorApellidoPaterno; 
	
	@Column(name="ACTOR_APE_MATERNO")
	private String  actorApellidoMaterno;
	
	@Column(name="ACTOR_NOMBRE")
	private String   actorNombre;
	
	@Column(name="FECHA_RECEPCION")
	private Date fechaRecepcion;
	
	@Column(name="CREADO_POR")
	private Long creadoPor;
	
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;
	
	@Column(name="MODIFICADO_POR")
	private Long modificadorPor;
	
	@Column(name="ULTIMA_MODIFICACION")
	private Date ultimaModificacion;
	
	@Column(name="IMPUGNACION_CONSTANCIA_CUMP")
	private String impugnacionConstanciaCump;
	
	@Column(name="REFERENCIA_SEG")
	private String referenciaSeg;

	public Long getImpugnacionId() {
		return impugnacionId;
	}

	public void setImpugnacionId(Long impugnacionId) {
		this.impugnacionId = impugnacionId;
	}

	public String getImpugnacionOficioJur() {
		return impugnacionOficioJur;
	}

	public void setImpugnacionOficioJur(String impugnacionOficioJur) {
		this.impugnacionOficioJur = impugnacionOficioJur;
	}

	public String getImpugnacionJuicio() {
		return impugnacionJuicio;
	}

	public void setImpugnacionJuicio(String impugnacionJuicio) {
		this.impugnacionJuicio = impugnacionJuicio;
	}

	public String getActorApellidoPaterno() {
		return actorApellidoPaterno;
	}

	public void setActorApellidoPaterno(String actorApellidoPaterno) {
		this.actorApellidoPaterno = actorApellidoPaterno;
	}

	public String getActorApellidoMaterno() {
		return actorApellidoMaterno;
	}

	public void setActorApellidoMaterno(String actorApellidoMaterno) {
		this.actorApellidoMaterno = actorApellidoMaterno;
	}

	public String getActorNombre() {
		return actorNombre;
	}

	public void setActorNombre(String actorNombre) {
		this.actorNombre = actorNombre;
	}

	public Date getFechaRecepcion() {
		return fechaRecepcion;
	}

	public void setFechaRecepcion(Date fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
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

	public Long getModificadorPor() {
		return modificadorPor;
	}

	public void setModificadorPor(Long modificadorPor) {
		this.modificadorPor = modificadorPor;
	}

	public Date getUltimaModificacion() {
		return ultimaModificacion;
	}

	public void setUltimaModificacion(Date ultimaModificacion) {
		this.ultimaModificacion = ultimaModificacion;
	}

	public String getImpugnacionConstanciaCump() {
		return impugnacionConstanciaCump;
	}

	public void setImpugnacionConstanciaCump(String impugnacionConstanciaCump) {
		this.impugnacionConstanciaCump = impugnacionConstanciaCump;
	}

	public String getReferenciaSeg() {
		return referenciaSeg;
	}

	public void setReferenciaSeg(String referenciaSeg) {
		this.referenciaSeg = referenciaSeg;
	}
	

	
	
}
