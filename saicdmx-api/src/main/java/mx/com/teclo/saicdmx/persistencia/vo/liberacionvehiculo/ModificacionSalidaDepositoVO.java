package mx.com.teclo.saicdmx.persistencia.vo.liberacionvehiculo;

public class ModificacionSalidaDepositoVO {
	private String infracnumctrl;
	private String nombredeposito;
	private String nombreresguardo;
	private String ingresoserie;
	private Integer depositoId;
	private String usuarioDeposito;
	private String codInternoVehiculo;
	
	
	/**
	 * @return the infracnumctrl
	 */
	public String getInfracnumctrl() {
		return infracnumctrl;
	}
	/**
	 * @param infracnumctrl the infracnumctrl to set
	 */
	public void setInfracnumctrl(String infracnumctrl) {
		this.infracnumctrl = infracnumctrl;
	}
	/**
	 * @return the nombredeposito
	 */
	public String getNombredeposito() {
		return nombredeposito;
	}
	/**
	 * @param nombredeposito the nombredeposito to set
	 */
	public void setNombredeposito(String nombredeposito) {
		this.nombredeposito = nombredeposito;
	}
	/**
	 * @return the nombreresguardo
	 */
	public String getNombreresguardo() {
		return nombreresguardo;
	}
	/**
	 * @param nombreresguardo the nombreresguardo to set
	 */
	public void setNombreresguardo(String nombreresguardo) {
		this.nombreresguardo = nombreresguardo;
	}
	
	/**
	 * @return the ingresoserie
	 */
	
	public String getIngresoserie() {
		return ingresoserie;
	}
	
	/**
	 * @param ingresoserie the ingresoserie to set
	 */
	public void setIngresoserie(String ingresoserie) {
		this.ingresoserie = ingresoserie;
	}
	
	/**
	 * @return the depositoId
	 */
	
	public Integer getDepositoId() {
		return depositoId;
	}
	
	/**
	 * @param depositoId the depositoId to set
	 */
	
	public void setDepositoId(Integer depositoId) {
		this.depositoId = depositoId;
	}
	
	/**
	 * @return the usuarioDeposito
	 */
	
	public String getUsuarioDeposito() {
		return usuarioDeposito;
	}
	
	/**
	 * @param usuarioDeposito the usuarioDeposito to set
	 */
	
	public void setUsuarioDeposito(String usuarioDeposito) {
		this.usuarioDeposito = usuarioDeposito;
	}
	
	/**
	 * @return the codInternoVehiculo
	 */
	
	public String getCodInternoVehiculo() {
		return codInternoVehiculo;
	}
	
	/**
	 * @param codInternoVehiculo the codInternoVehiculo to set
	 */
	public void setCodInternoVehiculo(String codInternoVehiculo) {
		this.codInternoVehiculo = codInternoVehiculo;
	}
}
