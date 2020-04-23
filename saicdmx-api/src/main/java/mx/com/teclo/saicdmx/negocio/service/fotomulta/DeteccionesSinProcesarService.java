package mx.com.teclo.saicdmx.negocio.service.fotomulta;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.DeteccionesPorRadarVO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.DeteccionesResultadoVO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.FotoMultaInfraccionesLiberacionesVO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.FotoMultaUsuarioClasificacionVO;

public interface DeteccionesSinProcesarService {

	/***
	 * @author Jesus Gutierrez
	 * @param tipoFecha
	 * @param origenPlaca
	 * Retorna un objeto con el conteo de detecciones Prevalidadas Y Validadas
	 * @return DeteccionesPorRadarVO
	 */
	public List<DeteccionesPorRadarVO> buscarDetecciones(Integer tipoFecha, Integer origenPlaca);
	
	/***
	 * @author Jesus Gutierrez
	 * @param codigoRadar
	 * @param origenPlaca
	 * @param tipoDeteccion
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map buscarDeteccionesPorTiposFecha(Integer tipoFecha, String codigoRadar, Integer origenPlaca, Integer tipoDeteccion);

	/***
	 * @author Jesus Gutierrez
	 * @param fechaInicio
	 * @param origenPlaca
	 * @param tipoFecha
	 * @param codigoRadar
	 * @return
	 * @throws ParseException
	 */
	public List<DeteccionesResultadoVO> buscarDeteccionesPorMes(String fechaInicio, Integer origenPlaca, Integer tipoFecha, String codigoRadar) throws ParseException;
	
	/**
	 * @author javier07
	 * @return List<DeteccionesPorRadarVO>
	 */
	public List<DeteccionesPorRadarVO> buscarDeteccionesPorRangoTiempo();


	/**
	 * @author javier07
	 * @return List<DeteccionesPorRadarVO>
	 */
	public List<DeteccionesPorRadarVO> buscarAcepRechaPorRangoTiempo();
	
	
	/**
	 * @author javier07
	 * @return List<List<FotoMultaInfraccionesLiberacionesVO>>
	 */
	public List<List<FotoMultaInfraccionesLiberacionesVO>> buscarInfraccLiberacionesPorRangoTiempo();
	

	/**
	 * @author javier07
	 * @return FotoMultaUsuarioClasificacionVO
	 */
    public List<FotoMultaUsuarioClasificacionVO> buscarUsuariosClasificacion ();

}
