package mx.com.teclo.saicdmx.persistencia.vo.radares;

public class RadarArchivoResumenVO{
	private Long archivoTotal;
    private boolean archivoComplementado;
    private Long complementadasValidasTotal;
    private Long complementadasInvalidasTotal;
    private String complementadoFecha;
    private boolean archivoLiberado;
    private boolean archivoEnLiberacion;
    private boolean mostrarCancelacion;
    private Long liberadasTotal;
    private String liberadasFolios;
    private String complementadasAccion;
    private String rechazadasAccion;
    private String liberadasAccion;
    
    //relation data
    private Long archivoId;
    private String estatusProceso;
    private String fechaEmision;
    private String fechaComplementacion;
    private String fechaLiberacion;
    
    //util data
    private int zipEstatus;
    private Boolean requiredUpdate;
    
	/**
	 * @return the archivoTotal
	 */
	public Long getArchivoTotal() {
		return archivoTotal;
	}
	/**
	 * @param archivoTotal the archivoTotal to set
	 */
	public void setArchivoTotal(Long archivoTotal) {
		this.archivoTotal = archivoTotal;
	}
	/**
	 * @return the archivoComplementado
	 */
	public boolean isArchivoComplementado() {
		return archivoComplementado;
	}
	/**
	 * @param archivoComplementado the archivoComplementado to set
	 */
	public void setArchivoComplementado(boolean archivoComplementado) {
		this.archivoComplementado = archivoComplementado;
	}
	/**
	 * @return the complementadasValidasTotal
	 */
	public Long getComplementadasValidasTotal() {
		return complementadasValidasTotal;
	}
	/**
	 * @param complementadasValidasTotal the complementadasValidasTotal to set
	 */
	public void setComplementadasValidasTotal(Long complementadasValidasTotal) {
		this.complementadasValidasTotal = complementadasValidasTotal;
	}
	/**
	 * @return the complementadasInvalidasTotal
	 */
	public Long getComplementadasInvalidasTotal() {
		return complementadasInvalidasTotal;
	}
	/**
	 * @param complementadasInvalidasTotal the complementadasInvalidasTotal to set
	 */
	public void setComplementadasInvalidasTotal(Long complementadasInvalidasTotal) {
		this.complementadasInvalidasTotal = complementadasInvalidasTotal;
	}
	/**
	 * @return the complementadoFecha
	 */
	public String getComplementadoFecha() {
		return complementadoFecha;
	}
	/**
	 * @param complementadoFecha the complementadoFecha to set
	 */
	public void setComplementadoFecha(String complementadoFecha) {
		this.complementadoFecha = complementadoFecha;
	}
	/**
	 * @return the archivoLiberado
	 */
	public boolean isArchivoLiberado() {
		return archivoLiberado;
	}
	/**
	 * @param archivoLiberado the archivoLiberado to set
	 */
	public void setArchivoLiberado(boolean archivoLiberado) {
		this.archivoLiberado = archivoLiberado;
	}
	/**
	 * @return the archivoEnLiberacion
	 */
	public boolean isArchivoEnLiberacion() {
		return archivoEnLiberacion;
	}
	/**
	 * @param archivoEnLiberacion the archivoEnLiberacion to set
	 */
	public void setArchivoEnLiberacion(boolean archivoEnLiberacion) {
		this.archivoEnLiberacion = archivoEnLiberacion;
	}
	/**
	 * @return the mostrarCancelacion
	 */
	public boolean isMostrarCancelacion() {
		return mostrarCancelacion;
	}
	/**
	 * @param mostrarCancelacion the mostrarCancelacion to set
	 */
	public void setMostrarCancelacion(boolean mostrarCancelacion) {
		this.mostrarCancelacion = mostrarCancelacion;
	}
	/**
	 * @return the liberadasTotal
	 */
	public Long getLiberadasTotal() {
		return liberadasTotal;
	}
	/**
	 * @param liberadasTotal the liberadasTotal to set
	 */
	public void setLiberadasTotal(Long liberadasTotal) {
		this.liberadasTotal = liberadasTotal;
	}
	/**
	 * @return the liberadasFolios
	 */
	public String getLiberadasFolios() {
		return liberadasFolios;
	}
	/**
	 * @param liberadasFolios the liberadasFolios to set
	 */
	public void setLiberadasFolios(String liberadasFolios) {
		this.liberadasFolios = liberadasFolios;
	}
	/**
	 * @return the complementadasAccion
	 */
	public String getComplementadasAccion() {
		return complementadasAccion;
	}
	/**
	 * @param complementadasAccion the complementadasAccion to set
	 */
	public void setComplementadasAccion(String complementadasAccion) {
		this.complementadasAccion = complementadasAccion;
	}
	/**
	 * @return the rechazadasAccion
	 */
	public String getRechazadasAccion() {
		return rechazadasAccion;
	}
	/**
	 * @param rechazadasAccion the rechazadasAccion to set
	 */
	public void setRechazadasAccion(String rechazadasAccion) {
		this.rechazadasAccion = rechazadasAccion;
	}
	/**
	 * @return the liberadasAccion
	 */
	public String getLiberadasAccion() {
		return liberadasAccion;
	}
	/**
	 * @param liberadasAccion the liberadasAccion to set
	 */
	public void setLiberadasAccion(String liberadasAccion) {
		this.liberadasAccion = liberadasAccion;
	}
	
	/****************************************************/
	
	/**
	 * @return the archivoId
	 */
	public Long getArchivoId() {
		return archivoId;
	}
	/**
	 * @param archivoId the archivoId to set
	 */
	public void setArchivoId(Long archivoId) {
		this.archivoId = archivoId;
	}
	/**
	 * @return the estatusProceso
	 */
	public String getEstatusProceso() {
		return estatusProceso;
	}
	/**
	 * @param estatusProceso the estatusProceso to set
	 */
	public void setEstatusProceso(String estatusProceso) {
		this.estatusProceso = estatusProceso;
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
	public String getFechaComplementacion() {
		return fechaComplementacion;
	}
	public void setFechaComplementacion(String fechaComplementacion) {
		this.fechaComplementacion = fechaComplementacion;
	}
	public String getFechaLiberacion() {
		return fechaLiberacion;
	}
	public void setFechaLiberacion(String fechaLiberacion) {
		this.fechaLiberacion = fechaLiberacion;
	}
	public int getZipEstatus() {
		return zipEstatus;
	}
	public void setZipEstatus(int zipEstatus) {
		this.zipEstatus = zipEstatus;
	}
	public Boolean getRequiredUpdate() {
		return requiredUpdate;
	}
	public void setRequiredUpdate(Boolean requiredUpdate) {
		this.requiredUpdate = requiredUpdate;
	}
	
	
}
