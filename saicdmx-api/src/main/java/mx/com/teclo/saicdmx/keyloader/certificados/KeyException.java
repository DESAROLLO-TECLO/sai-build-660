package mx.com.teclo.saicdmx.keyloader.certificados;

 
/**
 * Descripci�n: Implementaci�n de Excepciones.
 *
 * Historial de Modificaciones:
 * Descripci�n del Cambio	: Creaci�n
 * @author 					: fjmb 
 * @version 				: 1.0
 * Fecha					: 12/Septiembre/2016.
 * 
 */
 
@SuppressWarnings("serial")
public class KeyException extends RuntimeException {

    public KeyException() {
        super();
    }

    public KeyException(String message) {
        super(message);
    }

    public KeyException(String message, Throwable cause) {
        super(message, cause);
    }

    public KeyException(Throwable cause) {
        super(cause);
    }
}
