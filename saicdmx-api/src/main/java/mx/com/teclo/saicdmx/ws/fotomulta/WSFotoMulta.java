package mx.com.teclo.saicdmx.ws.fotomulta;

import mx.com.teclomexicana.fotomultas.ws.AsignacionLote;
import mx.com.teclomexicana.fotomultas.ws.LiberacionLote;
import mx.com.teclomexicana.fotomultas.ws.ServiceResult;

/***
 * 
 * @author Jesus Gutierrez
 *
 */

public interface WSFotoMulta {
	
	public ServiceResult liberacionLote(LiberacionLote request);
	
	public ServiceResult asignacionLote(AsignacionLote request);
}
