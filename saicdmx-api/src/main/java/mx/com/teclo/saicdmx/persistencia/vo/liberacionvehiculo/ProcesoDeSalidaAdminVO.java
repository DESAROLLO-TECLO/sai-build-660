package mx.com.teclo.saicdmx.persistencia.vo.liberacionvehiculo;

public class ProcesoDeSalidaAdminVO {

	private String usuarioDeposito;
	private String infracnumctrl;
	private String numeroPedido;
	private String vehiculoTipo;
	private String usuario;
	
	private Integer resultado;
	private String mensaje;
	
	
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
	 * @return the numeroPedido
	 */
	public String getNumeroPedido() {
		return numeroPedido;
	}
	/**
	 * @param numeroPedido the numeroPedido to set
	 */
	public void setNumeroPedido(String numeroPedido) {
		this.numeroPedido = numeroPedido;
	}
	/**
	 * @return the vehiculoTipo
	 */
	public String getVehiculoTipo() {
		return vehiculoTipo;
	}
	/**
	 * @param vehiculoTipo the vehiculoTipo to set
	 */
	public void setVehiculoTipo(String vehiculoTipo) {
		this.vehiculoTipo = vehiculoTipo;
	}
	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}
	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	/**
	 * @return the resultado
	 */
	public Integer getResultado() {
		return resultado;
	}
	/**
	 * @param resultado the resultado to set
	 */
	public void setResultado(Integer resultado) {
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
