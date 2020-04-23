package mx.com.teclo.saicdmx.bitacora.cambios.administracion;

import mx.com.teclo.saicdmx.persistencia.vo.administracion.OperacionesExtemporaneasVO;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;
/**
 * 
 * @author Javier Flores
 *
 */
public interface BitProcHabilitarExtemporanea {
	
	/**
	 * @author Javier Flores
	 * @param parametrosVO
	 * @return
	 */
	BitacoraCambiosVO habilitarExtemporanea(OperacionesExtemporaneasVO parametrosVO);

}
