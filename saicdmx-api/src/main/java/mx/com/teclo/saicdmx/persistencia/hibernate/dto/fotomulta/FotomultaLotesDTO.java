package mx.com.teclo.saicdmx.persistencia.hibernate.dto.fotomulta;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="FOTOMULTA_LOTES")
public class FotomultaLotesDTO implements Serializable{
	
	private static final long serialVersionUID = -6424763768858398637L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "LOTE_ID", unique = true, nullable = false)
    private Long loteId;
	
	@Column(name = "NOMBRE")
	private String nombre;
	
	@Column(name = "FECHA_EMISION")
	private Date fechaEmision;
	
	@Column(name = "FECHA_PROC_INICIAL")
	private Date fechaProcInicial;
	
	@Column(name = "FECHA_PROC_FINAL")
	private Date fechaProcFinal;
	
	@Column(name = "LOTE_CREADO_NOTIFICADO")
	private Long loteCreadoNotificado;
	
	@Column(name = "FECHA_CREADO_NOTIFICADO")
	private Date fechaCreadoNotificado;
	
	@Column(name = "LOTE_ENVIADO_CR")
	private Long loteEnviadoCr;
	
	@Column(name = "FECHA_ENVIADO_CR")
	private Date fechaEnviadoCr;
	
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	
	@Column(name = "CANCELADO")
	private Long cancelado;
	
	@Column(name = "CREADO_POR")
	private Long creadoPor;
	
	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultimaModificacion;
	
	@Column(name = "MODIFICADO_POR")
	private Long modificadoPor;
	
	@Column(name = "CANTIDAD_PROCESADOS")
	private Long cantidadProcesados;
	
	@Column(name = "RADAR_TIPO_ID")
	private Integer radarTipoId;
	
	@Column(name = "LIBERADO")
	private Long liberado;
	
	@Column(name = "FECHA_LIBERACION")
	private Date fechaLiberacion;
	
	@Column(name = "EN_CREACION")
	private Integer enCreacion;
	
//	@Column(name = "COMPLEMENTADO")
//	private Long complementado;
	
//	@Column(name = "FECHA_COMPLEMENTADO")
//	private Date fechaComplementado;
	
	@Column(name = "CANTIDAD_CANCELADOS")
	private Long cantidadCancelados;
	
	@Column(name = "ARCHIVO_TIPO")
	private Integer archivoTipo;

	/**
	 * @return the loteId
	 */
	public Long getLoteId() {
		return loteId;
	}

	/**
	 * @return the archivoTipo
	 */
	public Integer getArchivoTipo() {
		return archivoTipo;
	}

	/**
	 * @param archivoTipo the archivoTipo to set
	 */
	public void setArchivoTipo(Integer archivoTipo) {
		this.archivoTipo = archivoTipo;
	}

	/**
	 * @param loteId the loteId to set
	 */
	public void setLoteId(Long loteId) {
		this.loteId = loteId;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the fechaEmision
	 */
	public Date getFechaEmision() {
		return fechaEmision;
	}

	/**
	 * @param fechaEmision the fechaEmision to set
	 */
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	/**
	 * @return the fechaProcInicial
	 */
	public Date getFechaProcInicial() {
		return fechaProcInicial;
	}

	/**
	 * @param fechaProcInicial the fechaProcInicial to set
	 */
	public void setFechaProcInicial(Date fechaProcInicial) {
		this.fechaProcInicial = fechaProcInicial;
	}

	/**
	 * @return the fechaProcFinal
	 */
	public Date getFechaProcFinal() {
		return fechaProcFinal;
	}

	/**
	 * @param fechaProcFinal the fechaProcFinal to set
	 */
	public void setFechaProcFinal(Date fechaProcFinal) {
		this.fechaProcFinal = fechaProcFinal;
	}

	/**
	 * @return the loteCreadoNotificado
	 */
	public Long getLoteCreadoNotificado() {
		return loteCreadoNotificado;
	}

	/**
	 * @param loteCreadoNotificado the loteCreadoNotificado to set
	 */
	public void setLoteCreadoNotificado(Long loteCreadoNotificado) {
		this.loteCreadoNotificado = loteCreadoNotificado;
	}

	/**
	 * @return the fechaCreadoNotificado
	 */
	public Date getFechaCreadoNotificado() {
		return fechaCreadoNotificado;
	}

	/**
	 * @param fechaCreadoNotificado the fechaCreadoNotificado to set
	 */
	public void setFechaCreadoNotificado(Date fechaCreadoNotificado) {
		this.fechaCreadoNotificado = fechaCreadoNotificado;
	}

	/**
	 * @return the loteEnviadoCr
	 */
	public Long getLoteEnviadoCr() {
		return loteEnviadoCr;
	}

	/**
	 * @param loteEnviadoCr the loteEnviadoCr to set
	 */
	public void setLoteEnviadoCr(Long loteEnviadoCr) {
		this.loteEnviadoCr = loteEnviadoCr;
	}

	/**
	 * @return the fechaEnviadoCr
	 */
	public Date getFechaEnviadoCr() {
		return fechaEnviadoCr;
	}

	/**
	 * @param fechaEnviadoCr the fechaEnviadoCr to set
	 */
	public void setFechaEnviadoCr(Date fechaEnviadoCr) {
		this.fechaEnviadoCr = fechaEnviadoCr;
	}

	/**
	 * @return the fechaCreacion
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * @param fechaCreacion the fechaCreacion to set
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 * @return the cancelado
	 */
	public Long getCancelado() {
		return cancelado;
	}

	/**
	 * @param cancelado the cancelado to set
	 */
	public void setCancelado(Long cancelado) {
		this.cancelado = cancelado;
	}

	/**
	 * @return the creadoPor
	 */
	public Long getCreadoPor() {
		return creadoPor;
	}

	/**
	 * @param creadoPor the creadoPor to set
	 */
	public void setCreadoPor(Long creadoPor) {
		this.creadoPor = creadoPor;
	}

	/**
	 * @return the ultimaModificacion
	 */
	public Date getUltimaModificacion() {
		return ultimaModificacion;
	}

	/**
	 * @param ultimaModificacion the ultimaModificacion to set
	 */
	public void setUltimaModificacion(Date ultimaModificacion) {
		this.ultimaModificacion = ultimaModificacion;
	}

	/**
	 * @return the modificadoPor
	 */
	public Long getModificadoPor() {
		return modificadoPor;
	}

	/**
	 * @param modificadoPor the modificadoPor to set
	 */
	public void setModificadoPor(Long modificadoPor) {
		this.modificadoPor = modificadoPor;
	}

	/**
	 * @return the cantidadProcesados
	 */
	public Long getCantidadProcesados() {
		return cantidadProcesados;
	}

	/**
	 * @param cantidadProcesados the cantidadProcesados to set
	 */
	public void setCantidadProcesados(Long cantidadProcesados) {
		this.cantidadProcesados = cantidadProcesados;
	}

	/**
	 * @return the radarTipoId
	 */
	public Integer getRadarTipoId() {
		return radarTipoId;
	}

	/**
	 * @param radarTipoId the radarTipoId to set
	 */
	public void setRadarTipoId(Integer radarTipoId) {
		this.radarTipoId = radarTipoId;
	}

	/**
	 * @return the liberado
	 */
	public Long getLiberado() {
		return liberado;
	}

	/**
	 * @param liberado the liberado to set
	 */
	public void setLiberado(Long liberado) {
		this.liberado = liberado;
	}

	/**
	 * @return the fechaLiberacion
	 */
	public Date getFechaLiberacion() {
		return fechaLiberacion;
	}

	/**
	 * @param fechaLiberacion the fechaLiberacion to set
	 */
	public void setFechaLiberacion(Date fechaLiberacion) {
		this.fechaLiberacion = fechaLiberacion;
	}

	/**
	 * @return the enCreacion
	 */
	public Integer getEnCreacion() {
		return enCreacion;
	}

	/**
	 * @param enCreacion the enCreacion to set
	 */
	public void setEnCreacion(Integer enCreacion) {
		this.enCreacion = enCreacion;
	}

//	/**
//	 * @return the complementado
//	 */
//	public Long getComplementado() {
//		return complementado;
//	}

//	/**
//	 * @param complementado the complementado to set
//	 */
//	public void setComplementado(Long complementado) {
//		this.complementado = complementado;
//	}

//	/**
//	 * @return the fechaComplementado
//	 */
//	public Date getFechaComplementado() {
//		return fechaComplementado;
//	}

//	/**
//	 * @param fechaComplementado the fechaComplementado to set
//	 */
//	public void setFechaComplementado(Date fechaComplementado) {
//		this.fechaComplementado = fechaComplementado;
//	}

	/**
	 * @return the cantidadCancelados
	 */
	public Long getCantidadCancelados() {
		return cantidadCancelados;
	}

	/**
	 * @param cantidadCancelados the cantidadCancelados to set
	 */
	public void setCantidadCancelados(Long cantidadCancelados) {
		this.cantidadCancelados = cantidadCancelados;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
