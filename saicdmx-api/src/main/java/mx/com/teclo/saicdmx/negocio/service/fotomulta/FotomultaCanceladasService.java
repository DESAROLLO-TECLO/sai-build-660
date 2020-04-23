package mx.com.teclo.saicdmx.negocio.service.fotomulta;

import java.util.List;
import java.util.Map;

import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.ComboFechasVO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.ComboMotivoCancelVO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.FotomultaCanceladasVO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.ParametrosCancelacionVO;

/**
 * 
 * @author Javier Flores
 *
 */
public interface FotomultaCanceladasService {
	
	/**
	 * @author Javier Flores
	 * @return List<ComboMotivoCancelVO>
	 */
	List<ComboMotivoCancelVO>obtenerCatMotivoCancel();
	
	/**
	 * @author Javier Flores
	 * @return List<ComboFechasVO>
	 */
	List<ComboFechasVO> obtenerFechasCancelacion();
	
	/**
	 * @author Javier Flores
	 * @param parametrosCancelacionVO
	 * @return Map<String, List<FotomultaCanceladasVO>> 
	 */
	Map<String, List<FotomultaCanceladasVO>> consultarDeteccionesCanceladas(ParametrosCancelacionVO parametrosCancelacionVO);
	

	/**
	 * @author Javier Flores
	 * @return byte[]
	 */
	public byte[] descargaExcelDetecciones();
}
