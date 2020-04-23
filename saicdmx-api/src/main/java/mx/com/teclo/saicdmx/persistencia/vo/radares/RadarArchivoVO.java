package mx.com.teclo.saicdmx.persistencia.vo.radares;

import java.util.Date;

/**
 * 
 * @author UnitisDes0416
 *
 */
public class RadarArchivoVO {
	
	private Long radarArchivoId;
	private String archivoNombre;
	private Date fechaEmision;
	private Long estatusProcesoId;
	private Integer enProceso;
	private Integer origenLote;
	private Long idTipoDeteccion;
	private Long empleadoId;
	private Date fehcaUltimaModificacion;
	private Integer anioSalario;
	private Long archivoTipoId;
	private Long archivoTipoProcesoId;
	private Long IdSalarioMin;
	private Integer responsableProceso;
	private Date fechaImposicion;
	
	public Long getIdSalarioMin() {
		return IdSalarioMin;
	}
	public void setIdSalarioMin(Long idSalarioMin) {
		IdSalarioMin = idSalarioMin;
	}
	/**
	 * @return the radarArchivoId
	 */
	public Long getRadarArchivoId() {
		return radarArchivoId;
	}
	/**
	 * @return the archivoTipoId
	 */
	public Long getArchivoTipoId() {
		return archivoTipoId;
	}
	/**
	 * @param archivoTipoId the archivoTipoId to set
	 */
	public void setArchivoTipoId(Long archivoTipoId) {
		this.archivoTipoId = archivoTipoId;
	}
	/**
	 * @param radarArchivoId the radarArchivoId to set
	 */
	public void setRadarArchivoId(Long radarArchivoId) {
		this.radarArchivoId = radarArchivoId;
	}
	/**
	 * @return the archivoNombre
	 */
	public String getArchivoNombre() {
		return archivoNombre;
	}
	/**
	 * @param archivoNombre the archivoNombre to set
	 */
	public void setArchivoNombre(String archivoNombre) {
		this.archivoNombre = archivoNombre;
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
	 * @return the estatusProcesoId
	 */
	public Long getEstatusProcesoId() {
		return estatusProcesoId;
	}
	/**
	 * @param estatusProcesoId the estatusProcesoId to set
	 */
	public void setEstatusProcesoId(Long estatusProcesoId) {
		this.estatusProcesoId = estatusProcesoId;
	}
	/**
	 * @return the enProceso
	 */
	public Integer getEnProceso() {
		return enProceso;
	}
	/**
	 * @param enProceso the enProceso to set
	 */
	public void setEnProceso(Integer enProceso) {
		this.enProceso = enProceso;
	}
	/**
	 * @return the enProceso
	 */
	public Long getIdTipoDeteccion() {
		return idTipoDeteccion;
	}
	/**
	 * @param enProceso the enProceso to set
	 */
	public void setIdTipoDeteccion(Long idTipoDeteccion) {
		this.idTipoDeteccion = idTipoDeteccion;
	}
	/**
	 * @return the enProceso
	 */
	public Integer getOrigenLote() {
		return origenLote;
	}
	/**
	 * @param enProceso the enProceso to set
	 */
	public void setOrigenLote(Integer origenLote) {
		this.origenLote = origenLote;
	}
	/**
	 * @return the empleadoId
	 */
	public Long getEmpleadoId() {
		return empleadoId;
	}
	/**
	 * @param empleadoId the empleadoId to set
	 */
	public void setEmpleadoId(Long empleadoId) {
		this.empleadoId = empleadoId;
	}
	/**
	 * @return the anioSalario
	 */
	public Integer getAnioSalario() {
		return anioSalario;
	}
	/**
	 * @return the fehcaUltimaModificacion
	 */
	public Date getFehcaUltimaModificacion() {
		return fehcaUltimaModificacion;
	}
	/**
	 * @param fehcaUltimaModificacion the fehcaUltimaModificacion to set
	 */
	public void setFehcaUltimaModificacion(Date fehcaUltimaModificacion) {
		this.fehcaUltimaModificacion = fehcaUltimaModificacion;
	}
	/**
	 * @param anioSalario the anioSalario to set
	 */
	public void setAnioSalario(Integer anioSalario) {
		this.anioSalario = anioSalario;
	}
	/**
	 * @return the archivoTipoProcesoId
	 */
	public Long getArchivoTipoProcesoId() {
		return archivoTipoProcesoId;
	}
	/**
	 * @param archivoTipoProcesoId the archivoTipoProcesoId to set
	 */
	public void setArchivoTipoProcesoId(Long archivoTipoProcesoId) {
		this.archivoTipoProcesoId = archivoTipoProcesoId;
	}
	
	public Integer getResponsableProceso() {
		return responsableProceso;
	}
	public void setResponsableProceso(Integer responsableProceso) {
		this.responsableProceso = responsableProceso;
	}
	public Date getFechaImposicion() {
		return fechaImposicion;
	}
	public void setFechaImposicion(Date fechaImposicion) {
		this.fechaImposicion = fechaImposicion;
	}
}