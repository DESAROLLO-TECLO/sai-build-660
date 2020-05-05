package mx.com.teclo.saicdmx.persistencia.vo.administracion;

public class EjecutaSoporteOperacionVO {
	
	private String usuarioAutoriza;
	private String infraccionNum;
	private String fechaHora;
	private String infraccionPreImpresa;
	private String infraccionNumNueva;
	private String nciNuevo;
	private String infraccionPlaca;
	private String lstCausaIngreso;
	private String lstTipoIngreso;
	private String numOficio;
	private String oficialPlaca;
	private String resguardo;
	private Integer folioInicial;
	private Integer folioFinal;
	private Integer reciboTotal;
	private Integer reciboUtilizados;
	private Integer reciboCancelados;
	private Integer tipoSoporte;
	private Integer operacionEfectuada;
	private Integer empIdFolios;
	
	private String resultado;
	private String mensaje;
	private Long usuarioId;
	
	/**
	 * @return the usuarioId
	 */
	public Long getUsuarioId() {
		return usuarioId;
	}
	/**
	 * @param usuarioId the usuarioId to set
	 */
	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}
	/**
	 * @return the usuarioAutoriza
	 */
	public String getUsuarioAutoriza() {
		return usuarioAutoriza;
	}
	/**
	 * @param usuarioAutoriza the usuarioAutoriza to set
	 */
	public void setUsuarioAutoriza(String usuarioAutoriza) {
		this.usuarioAutoriza = usuarioAutoriza;
	}
	/**
	 * @return the infraccionNum
	 */
	public String getInfraccionNum() {
		return infraccionNum;
	}
	/**
	 * @param infraccionNum the infraccionNum to set
	 */
	public void setInfraccionNum(String infraccionNum) {
		this.infraccionNum = infraccionNum;
	}
	/**
	 * @return the fechaHora
	 */
	public String getFechaHora() {
		return fechaHora;
	}
	/**
	 * @param fechaHora the fechaHora to set
	 */
	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}
	/**
	 * @return the infraccionPreImpresa
	 */
	public String getInfraccionPreImpresa() {
		return infraccionPreImpresa;
	}
	/**
	 * @param infraccionPreImpresa the infraccionPreImpresa to set
	 */
	public void setInfraccionPreImpresa(String infraccionPreImpresa) {
		this.infraccionPreImpresa = infraccionPreImpresa;
	}
	/**
	 * @return the infraccionNumNueva
	 */
	public String getInfraccionNumNueva() {
		return infraccionNumNueva;
	}
	/**
	 * @param infraccionNumNueva the infraccionNumNueva to set
	 */
	public void setInfraccionNumNueva(String infraccionNumNueva) {
		this.infraccionNumNueva = infraccionNumNueva;
	}
	/**
	 * @return the nciNuevo
	 */
	public String getNciNuevo() {
		return nciNuevo;
	}
	/**
	 * @param nciNuevo the nciNuevo to set
	 */
	public void setNciNuevo(String nciNuevo) {
		this.nciNuevo = nciNuevo;
	}
	/**
	 * @return the infraccionPlaca
	 */
	public String getInfraccionPlaca() {
		return infraccionPlaca;
	}
	/**
	 * @param infraccionPlaca the infraccionPlaca to set
	 */
	public void setInfraccionPlaca(String infraccionPlaca) {
		this.infraccionPlaca = infraccionPlaca;
	}
	/**
	 * @return the lstCausaIngreso
	 */
	public String getLstCausaIngreso() {
		return lstCausaIngreso;
	}
	/**
	 * @param lstCausaIngreso the lstCausaIngreso to set
	 */
	public void setLstCausaIngreso(String lstCausaIngreso) {
		this.lstCausaIngreso = lstCausaIngreso;
	}
	/**
	 * @return the lstTipoIngreso
	 */
	public String getLstTipoIngreso() {
		return lstTipoIngreso;
	}
	/**
	 * @param lstTipoIngreso the lstTipoIngreso to set
	 */
	public void setLstTipoIngreso(String lstTipoIngreso) {
		this.lstTipoIngreso = lstTipoIngreso;
	}
	/**
	 * @return the numOficio
	 */
	public String getNumOficio() {
		return numOficio;
	}
	/**
	 * @param numOficio the numOficio to set
	 */
	public void setNumOficio(String numOficio) {
		this.numOficio = numOficio;
	}
	/**
	 * @return the oficialPlaca
	 */
	public String getOficialPlaca() {
		return oficialPlaca;
	}
	/**
	 * @param oficialPlaca the oficialPlaca to set
	 */
	public void setOficialPlaca(String oficialPlaca) {
		this.oficialPlaca = oficialPlaca;
	}
	/**
	 * @return the resguardo
	 */
	public String getResguardo() {
		return resguardo;
	}
	/**
	 * @param resguardo the resguardo to set
	 */
	public void setResguardo(String resguardo) {
		this.resguardo = resguardo;
	}
	/**
	 * @return the folioInicial
	 */
	public Integer getFolioInicial() {
		return folioInicial;
	}
	/**
	 * @param folioInicial the folioInicial to set
	 */
	public void setFolioInicial(Integer folioInicial) {
		this.folioInicial = folioInicial;
	}
	/**
	 * @return the folioFinal
	 */
	public Integer getFolioFinal() {
		return folioFinal;
	}
	/**
	 * @param folioFinal the folioFinal to set
	 */
	public void setFolioFinal(Integer folioFinal) {
		this.folioFinal = folioFinal;
	}
	/**
	 * @return the reciboTotal
	 */
	public Integer getReciboTotal() {
		return reciboTotal;
	}
	/**
	 * @param reciboTotal the reciboTotal to set
	 */
	public void setReciboTotal(Integer reciboTotal) {
		this.reciboTotal = reciboTotal;
	}
	/**
	 * @return the reciboUtilizados
	 */
	public Integer getReciboUtilizados() {
		return reciboUtilizados;
	}
	/**
	 * @param reciboUtilizados the reciboUtilizados to set
	 */
	public void setReciboUtilizados(Integer reciboUtilizados) {
		this.reciboUtilizados = reciboUtilizados;
	}
	/**
	 * @return the reciboCancelados
	 */
	public Integer getReciboCancelados() {
		return reciboCancelados;
	}
	/**
	 * @param reciboCancelados the reciboCancelados to set
	 */
	public void setReciboCancelados(Integer reciboCancelados) {
		this.reciboCancelados = reciboCancelados;
	}
	/**
	 * @return the tipoSoporte
	 */
	public Integer getTipoSoporte() {
		return tipoSoporte;
	}
	/**
	 * @param tipoSoporte the tipoSoporte to set
	 */
	public void setTipoSoporte(Integer tipoSoporte) {
		this.tipoSoporte = tipoSoporte;
	}
	/**
	 * @return the operacionEfectuada
	 */
	public Integer getOperacionEfectuada() {
		return operacionEfectuada;
	}
	/**
	 * @param operacionEfectuada the operacionEfectuada to set
	 */
	public void setOperacionEfectuada(Integer operacionEfectuada) {
		this.operacionEfectuada = operacionEfectuada;
	}
	/**
	 * @return the empIdFolios
	 */
	public Integer getEmpIdFolios() {
		return empIdFolios;
	}
	/**
	 * @param empIdFolios the empIdFolios to set
	 */
	public void setEmpIdFolios(Integer empIdFolios) {
		this.empIdFolios = empIdFolios;
	}
	/**
	 * @return the resultado
	 */
	public String getResultado() {
		return resultado;
	}
	/**
	 * @param resultado the resultado to set
	 */
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}
	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}
