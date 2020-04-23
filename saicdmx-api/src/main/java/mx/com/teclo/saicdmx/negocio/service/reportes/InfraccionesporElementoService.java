package mx.com.teclo.saicdmx.negocio.service.reportes;

import java.io.ByteArrayOutputStream;
import java.util.List;

import mx.com.teclo.saicdmx.persistencia.vo.reportes.InfraccionesporElementoVO;

public interface InfraccionesporElementoService {
	/**
	 * @author: Fernando Octavio
	 * @param:
     * @return
	 */
	ByteArrayOutputStream descargaExcelInfraccionesporElemento();
	
	/**
	 * @author: Fernando Octavio
	 * @param: parametroBusquedaVO 
	 *         fechaInicio,fechaFin,placasOficiales,idPlacasOficiales
     * @return InfraccionesporElementoVO
	 */
	List<InfraccionesporElementoVO> consultaInfraccionesporElemento (InfraccionesporElementoVO parametrosBusquedaVO);
}
