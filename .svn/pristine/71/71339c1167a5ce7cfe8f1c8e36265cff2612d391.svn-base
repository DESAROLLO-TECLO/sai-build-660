package mx.com.teclo.saicdmx.persistencia.hibernate.dao.fotomulta;

import java.util.Date;
import java.util.List;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.fotomulta.FotomultaDeteccionDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.fotomulta.FotomultaMotivoCancelacionDTO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.FechasCancelacionVO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.FotomultaCatComboFechasVO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.ParametrosCancelacionVO;

/**
 * 
 * @author javier07
 *
 */
public interface FotomultaCancelacionDeteccionDAO extends BaseDao<FotomultaDeteccionDTO>{

	/**
	 * @author javier07
	 * @param fecha
	 * @return List<FotomultaDeteccionDTO>
	 */
	 List<FotomultaDeteccionDTO> getDeteccionesPorCancelar(ParametrosCancelacionVO parametros);

	 /**
	 *  
	 * @author javier07
	 * @param fechaFin
	 * @return List<FotomultaCatComboFechasVO>
	 */
	 List<FotomultaCatComboFechasVO> getFechaDetecciones(Date fechaFin);
	
	/**
	 * @author javier07
	 * @param fecha
	 * @return List<FotomultaDeteccionDTO>
	 */
	 List<FotomultaDeteccionDTO> getPrevalidacionesPorCancelar(ParametrosCancelacionVO parametros);

	 
	 /**
	  * @author javier07
	  * @param fecha
	  * @return long
	  */
	 long cancelarDetecciones(ParametrosCancelacionVO parametros);
	 
	 /**
	  * @author javier07
	  * @param fecha
	  * @return long
	  */
	 long cancelarPrevalidaciones(ParametrosCancelacionVO parametros);
	 
	 /**
	  * @author Javier Flores
	  * @param parametros
	  * @return  List<FotomultaDeteccionDTO>
	  */
	 List<FotomultaDeteccionDTO> consultarDeteccionesCanceladas(ParametrosCancelacionVO parametros);

	 /**
	  * @author Javier Flores
	  * @return List<FotomultaMotivoCancelacionDTO>
	  */
	 List<FotomultaMotivoCancelacionDTO> getCatMotivoCancelacion();
	 
	 /**
	  * @author Javier Flores
	  * @return List<FechasCancelacionVO>
	  */
	 List<FechasCancelacionVO> obtenerFechasCancelacion();
}
