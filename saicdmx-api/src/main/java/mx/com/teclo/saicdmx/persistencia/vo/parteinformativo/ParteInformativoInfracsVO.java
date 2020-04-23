package mx.com.teclo.saicdmx.persistencia.vo.parteinformativo;

import java.io.Serializable;

public class ParteInformativoInfracsVO implements Serializable{
	
	private static final long serialVersionUID = 4434711932124853308L;

	private Long piId;
	private String tipo;
	private String infracNum;
	private String infracPlaca;
	private Long modificadoPor;
	private String p_resultado;
	private String p_mensaje;
	
	/**
	 * @return the piId
	 */
	public Long getPiId() {
		return piId;
	}
	/**
	 * @param piId the piId to set
	 */
	public void setPiId(Long piId) {
		this.piId = piId;
	}
	/**
	 * @return the infracNum
	 */
	public String getInfracNum() {
		return infracNum;
	}
	/**
	 * @param infracNum the infracNum to set
	 */
	public void setInfracNum(String infracNum) {
		this.infracNum = infracNum;
	}
	/**
	 * @return the infracPlaca
	 */
	public String getInfracPlaca() {
		return infracPlaca;
	}
	/**
	 * @param infracPlaca the infracPlaca to set
	 */
	public void setInfracPlaca(String infracPlaca) {
		this.infracPlaca = infracPlaca;
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
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	/**
	 * @return the p_resultado
	 */
	public String getResultado() {
		return p_resultado;
	}
	/**
	 * @param p_resultado the p_resultado to set
	 */
	public void setResultado(String p_resultado) {
		this.p_resultado = p_resultado;
	}
	/**
	 * @return the p_mensaje
	 */
	public String getMensaje() {
		return p_mensaje;
	}
	/**
	 * @param p_mensaje the p_mensaje to set
	 */
	public void setMensaje(String p_mensaje) {
		this.p_mensaje = p_mensaje;
	}
}
