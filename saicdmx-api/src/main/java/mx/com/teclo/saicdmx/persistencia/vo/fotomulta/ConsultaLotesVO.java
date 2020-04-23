package mx.com.teclo.saicdmx.persistencia.vo.fotomulta;

public class ConsultaLotesVO {
	
	private Long loteId;
	private String nombre;
	private Long catTipoRadarId;
	
	private String nombreTipoRadar;
	private String fechaProcInicial;
	private String fechaProcFinal;
	
	private String fechaEmision;
	private String fechaImposicion;
	private String fechaComplementado;
	private String fechaLiberacion;
	
	private String diaLiberacion;
	private String infracNumInicial;
	private String infracNumFinal;
	
	private Integer archivoComplementadas;
	private Integer archivoRechazadas;
	private Integer complementado;
	
	private Integer cantidadProcesados;
	private Integer cantidadCancelados;
	
	private String fechaEnviadoCR;
	private Integer estatusProcesoId;
	private Integer estatusWsAsignacion;
	
	private Integer enProcesoWsAsignacion;
	private Integer estatusWsLiberacion;
	private Integer enProcesoWsLiberacion;
	
	private Integer archivoTipo;
	private String nombreArchivoTipo;
	
	
	/**
	 * @return the loteId
	 */
	public Long getLoteId() {
		return loteId;
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
	 * @return the catTipoRadarId
	 */
	public Long getCatTipoRadarId() {
		return catTipoRadarId;
	}
	/**
	 * @param catTipoRadarId the catTipoRadarId to set
	 */
	public void setCatTipoRadarId(Long catTipoRadarId) {
		this.catTipoRadarId = catTipoRadarId;
	}
	/**
	 * @return the nombreTipoRadar
	 */
	public String getNombreTipoRadar() {
		return nombreTipoRadar;
	}
	/**
	 * @param nombreTipoRadar the nombreTipoRadar to set
	 */
	public void setNombreTipoRadar(String nombreTipoRadar) {
		this.nombreTipoRadar = nombreTipoRadar;
	}
	/**
	 * @return the fechaProcInicial
	 */
	public String getFechaProcInicial() {
		return fechaProcInicial;
	}
	/**
	 * @param fechaProcInicial the fechaProcInicial to set
	 */
	public void setFechaProcInicial(String fechaProcInicial) {
		this.fechaProcInicial = fechaProcInicial;
	}
	/**
	 * @return the fechaProcFinal
	 */
	public String getFechaProcFinal() {
		return fechaProcFinal;
	}
	/**
	 * @param fechaProcFinal the fechaProcFinal to set
	 */
	public void setFechaProcFinal(String fechaProcFinal) {
		this.fechaProcFinal = fechaProcFinal;
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
	 * @return the fechaImposicion
	 */
	public String getFechaImposicion() {
		return fechaImposicion;
	}
	/**
	 * @param fechaImposicion the fechaImposicion to set
	 */
	public void setFechaImposicion(String fechaImposicion) {
		this.fechaImposicion = fechaImposicion;
	}
	/**
	 * @return the fechaComplementado
	 */
	public String getFechaComplementado() {
		return fechaComplementado;
	}
	/**
	 * @param fechaComplementado the fechaComplementado to set
	 */
	public void setFechaComplementado(String fechaComplementado) {
		this.fechaComplementado = fechaComplementado;
	}
	/**
	 * @return the fechaLiberacion
	 */
	public String getFechaLiberacion() {
		return fechaLiberacion;
	}
	/**
	 * @param fechaLiberacion the fechaLiberacion to set
	 */
	public void setFechaLiberacion(String fechaLiberacion) {
		this.fechaLiberacion = fechaLiberacion;
	}
	/**
	 * @return the diaLiberacion
	 */
	public String getDiaLiberacion() {
		return diaLiberacion;
	}
	/**
	 * @param diaLiberacion the diaLiberacion to set
	 */
	public void setDiaLiberacion(String diaLiberacion) {
		this.diaLiberacion = diaLiberacion;
	}
	/**
	 * @return the infracNumInicial
	 */
	public String getInfracNumInicial() {
		return infracNumInicial;
	}
	/**
	 * @param infracNumInicial the infracNumInicial to set
	 */
	public void setInfracNumInicial(String infracNumInicial) {
		this.infracNumInicial = infracNumInicial;
	}
	/**
	 * @return the infracNumFinal
	 */
	public String getInfracNumFinal() {
		return infracNumFinal;
	}
	/**
	 * @param infracNumFinal the infracNumFinal to set
	 */
	public void setInfracNumFinal(String infracNumFinal) {
		this.infracNumFinal = infracNumFinal;
	}
	/**
	 * @return the archivoComplementadas
	 */
	public Integer getArchivoComplementadas() {
		return archivoComplementadas;
	}
	/**
	 * @param archivoComplementadas the archivoComplementadas to set
	 */
	public void setArchivoComplementadas(Integer archivoComplementadas) {
		this.archivoComplementadas = archivoComplementadas;
	}
	/**
	 * @return the archivoRechazadas
	 */
	public Integer getArchivoRechazadas() {
		return archivoRechazadas;
	}
	/**
	 * @param archivoRechazadas the archivoRechazadas to set
	 */
	public void setArchivoRechazadas(Integer archivoRechazadas) {
		this.archivoRechazadas = archivoRechazadas;
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
	 * @return the cantidadCancelados
	 */
	public Integer getCantidadCancelados() {
		return cantidadCancelados;
	}
	/**
	 * @param cantidadCancelados the cantidadCancelados to set
	 */
	public void setCantidadCancelados(Integer cantidadCancelados) {
		this.cantidadCancelados = cantidadCancelados;
	}
	/**
	 * @return the fechaEnviadoCR
	 */
	public String getFechaEnviadoCR() {
		return fechaEnviadoCR;
	}
	/**
	 * @param fechaEnviadoCR the fechaEnviadoCR to set
	 */
	public void setFechaEnviadoCR(String fechaEnviadoCR) {
		this.fechaEnviadoCR = fechaEnviadoCR;
	}
	/**
	 * @return the estatusProcesoId
	 */
	public Integer getEstatusProcesoId() {
		return estatusProcesoId;
	}
	/**
	 * @param estatusProcesoId the estatusProcesoId to set
	 */
	public void setEstatusProcesoId(Integer estatusProcesoId) {
		this.estatusProcesoId = estatusProcesoId;
	}
	/**
	 * @return the estatusWsAsignacion
	 */
	public Integer getEstatusWsAsignacion() {
		return estatusWsAsignacion;
	}
	/**
	 * @param estatusWsAsignacion the estatusWsAsignacion to set
	 */
	public void setEstatusWsAsignacion(Integer estatusWsAsignacion) {
		this.estatusWsAsignacion = estatusWsAsignacion;
	}
	/**
	 * @return the enProcesoWsAsignacion
	 */
	public Integer getEnProcesoWsAsignacion() {
		return enProcesoWsAsignacion;
	}
	/**
	 * @param enProcesoWsAsignacion the enProcesoWsAsignacion to set
	 */
	public void setEnProcesoWsAsignacion(Integer enProcesoWsAsignacion) {
		this.enProcesoWsAsignacion = enProcesoWsAsignacion;
	}
	/**
	 * @return the estatusWsLiberacion
	 */
	public Integer getEstatusWsLiberacion() {
		return estatusWsLiberacion;
	}
	/**
	 * @param estatusWsLiberacion the estatusWsLiberacion to set
	 */
	public void setEstatusWsLiberacion(Integer estatusWsLiberacion) {
		this.estatusWsLiberacion = estatusWsLiberacion;
	}
	/**
	 * @return the enProcesoWsLiberacion
	 */
	public Integer getEnProcesoWsLiberacion() {
		return enProcesoWsLiberacion;
	}
	/**
	 * @param enProcesoWsLiberacion the enProcesoWsLiberacion to set
	 */
	public void setEnProcesoWsLiberacion(Integer enProcesoWsLiberacion) {
		this.enProcesoWsLiberacion = enProcesoWsLiberacion;
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
	 * @return the nombreArchivoTipo
	 */
	public String getNombreArchivoTipo() {
		return nombreArchivoTipo;
	}
	/**
	 * @param nombreArchivoTipo the nombreArchivoTipo to set
	 */
	public void setNombreArchivoTipo(String nombreArchivoTipo) {
		this.nombreArchivoTipo = nombreArchivoTipo;
	}
}
