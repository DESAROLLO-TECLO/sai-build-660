package mx.com.teclo.saicdmx.negocio.service.fotomulta;

import java.util.List;
import java.util.Map;

import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.FotomultaCatComboFechasVO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.FotomultaMarcadoVO;

/**
 * 
 * @author Javier Flores
 *
 */
public interface FotomultaMarcadoService {

	/**
	 * @author javier07
	 * @param origenPlaca 
	 * @return Map<String, List<FotomultaMarcadoVO>>
	 */
	Map<String, List<FotomultaMarcadoVO>>[] obtenerDeteccionesParaCancel(String fecha,int tipoRadar, int origenPlaca);
	/**
	 * @author javier07
	 * @return List<FotomultaCatComboFechasVO> 
	 */
	List<FotomultaCatComboFechasVO> obtieneListaFechas();
	
	/**
	 * @author javier07
	 * @param fecha
	 * @return long
	 */
	long cancelarDetecciones(int tipoRadar,int origenPlaca,String motivoCancelacion,Long modificadoPor);
	
	/**
	 * @author javier07
	 * @return byte []
	 */
	 byte [] descargaExcelDeteccMarcado();

	 /**
	  * @author javier07
	  * @return Map<String, List<FotomultaMarcadoVO>>
	  */
	 List<FotomultaMarcadoVO> obtenerValidadas();
	 
	 /**
	  * @author javier07
	  * @return Map<String, List<FotomultaMarcadoVO>>
	  */	 
	 List<FotomultaMarcadoVO> obtenerPrevalidaciones();
	 
	 
}
