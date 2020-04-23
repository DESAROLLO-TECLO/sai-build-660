package mx.com.teclo.saicdmx.util.enumerados;


/**
 * 
 * @author UnitisDes0416
 *
 */
public enum MensajesAplicacionEnum {

	ARCHIVO_INVALIDO("EL ARCHIVO TIENE UN FORMATO INCORRECTO");

	private String message;

	private MensajesAplicacionEnum(String message) {
		this.message = message;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	static MensajesAplicacionEnum getArchivoTipo(Integer x) {
		for (MensajesAplicacionEnum currentType : MensajesAplicacionEnum.values()) {
	      if (x.equals(currentType.getMessage())) {
	        return currentType;
	      }
	    }
	    return null;
	}
}
