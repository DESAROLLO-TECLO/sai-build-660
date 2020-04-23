package mx.com.teclo.saicdmx.persistencia.vo.fotomulta;

public class FotoMultaLotesVO {
	
	private Long loteID;
	private String nombre;
	private String fechaEmision;
	private String fechaProcInicio;
	private String fechaProcFin;
	private Long empleadoId;
	private Integer tipoRadar;
	private Integer cantidadProcesados;
	private Integer complementado;
	private Integer enCreacion;
	private Integer archivoTipo;
	/**
	 * @return the loteID
	 */
	public Long getLoteID() {
		return loteID;
	}
	/**
	 * @param loteID the loteID to set
	 */
	public void setLoteID(Long loteID) {
		this.loteID = loteID;
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
	public String getFechaEmision() {
		return fechaEmision;
	}
	/**
	 * @param fechaEmision the fechaEmision to set
	 */
	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	/**
	 * @return the fechaProcInicio
	 */
	public String getFechaProcInicio() {
		return fechaProcInicio;
	}
	/**
	 * @param fechaProcInicio the fechaProcInicio to set
	 */
	public void setFechaProcInicio(String fechaProcInicio) {
		this.fechaProcInicio = fechaProcInicio;
	}
	/**
	 * @return the fechaProcFin
	 */
	public String getFechaProcFin() {
		return fechaProcFin;
	}
	/**
	 * @param fechaProcFin the fechaProcFin to set
	 */
	public void setFechaProcFin(String fechaProcFin) {
		this.fechaProcFin = fechaProcFin;
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
	 * @return the tipoRadar
	 */
	public Integer getTipoRadar() {
		return tipoRadar;
	}
	/**
	 * @param tipoRadar the tipoRadar to set
	 */
	public void setTipoRadar(Integer tipoRadar) {
		this.tipoRadar = tipoRadar;
	}
	/**
	 * @return the cantidadProcesados
	 */
	public Integer getCantidadProcesados() {
		return cantidadProcesados;
	}
	/**
	 * @param cantidadProcesados the cantidadProcesados to set
	 */
	public void setCantidadProcesados(Integer cantidadProcesados) {
		this.cantidadProcesados = cantidadProcesados;
	}
	/**
	 * @return the complementado
	 */
	public Integer getComplementado() {
		return complementado;
	}
	/**
	 * @param complementado the complementado to set
	 */
	public void setComplementado(Integer complementado) {
		this.complementado = complementado;
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
}
