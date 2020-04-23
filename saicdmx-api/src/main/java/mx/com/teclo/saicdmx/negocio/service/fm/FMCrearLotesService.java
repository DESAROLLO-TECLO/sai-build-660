package mx.com.teclo.saicdmx.negocio.service.fm;

import java.util.List;
import java.util.Map;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.fm.FMLotesDTO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMDeteccionesDisponiblesFCVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMLotesVO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.FotoMultaLotesVO;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.NotFoundException;

public interface FMCrearLotesService {
	/***
	 * @author José Castillo
	 * @param fechaInicio
	 * @param fechaFin
	 * @param tipoRadar
	 * @param nombreRadar
	 * @param archivoTipo
	 * @return
	 * @throws BusinessException
	 */
	public Map<String, String> validarDeteccionesParaLote(String fechaInicio, String fechaFin, 
												Integer tipoRadar, String nombreRadar, 
												Integer tipoDeteccion, String nombreDeteccion,
												Integer origenPlacaF) throws BusinessException;
	
	/***
	 * @author José Castillo
	 * @param fechaEmision
	 * @param nombreRadar
	 * @param fechaInicio
	 * @param fechaFin
	 * @param anoSalario
	 * @param usuario
	 * @param tipoRadar
	 * @param tipoDeteccion
	 * @param origenPlaca
	 * @return
	 * @throws BusinessException
	 */
//	public FMLotesVO guardarLote(String fechaEmision, String nombreLote, 
//			String fechaInicio, String fechaFin, 
//			Integer anoSalario, String usuario, Integer tipoPersona, 
//			Integer tipoDeteccion, Integer origenPlaca,
//			Integer stLCaptura, Integer stVCP) throws BusinessException;
	
	/***
	 * @author José Castillo
	 * @param loteId
	 * @param usuario
	 * @return
	 * @throws BusinessException
	 */
	public Integer realizarCompletacion(String mensajeErr, Long loteId, Long usuario) throws Exception, BusinessException, NotFoundException;

	/***
	 * @author José Castillo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map getReporteDeteccionesPorLote(String fechaInicio, String fechaFin, 
			Integer tipoDeteccion,Integer tipoPersona, String nombrePersona, 
			String nombreDeteccion, Integer origenPlacaF);
	
	/***
	 * @author José Castillo
	 * @param 
	 * @param usuario
	 * @return
	 * @throws BusinessException
	 */
	public List<FMLotesVO> consultaLotesCreados() throws BusinessException;
	
	public List<FMDeteccionesDisponiblesFCVO> consultaDetDisponibles() throws BusinessException;
	
	/***
	 * @author José Castillo
	 * @param String fechaInicio
	 * @param String fechaFin
	 * @param Integer tipoPersona
	 * @param Integer tipoDeteccion
	 * @return String nombreSugLote
	 * @throws BusinessException
	 */
	public String consultaNombreSugLote(String fechaInicio, String fechaFin, Integer tipoPersona, Integer tipoDeteccion) throws BusinessException;
	
	/***
	 * @author Fernando Campero
	 * @throws BusinessException
	 */
	public Map<String, String> getParametrosLP();
	
	/***
	 * @author Fernando Campero
	 * @param String nbConfig
	 * @throws BusinessException
	 */
	public Map<String, String> getParametrosLPPorNBConfig(String nbConfig);
	
	public Long creacionLote(String fechaEmision, String nombreLote, String fechaInicio, String fechaFin,
			Integer anoSalario, String usuario, Integer tipoPersona, Integer tipoDeteccion, Integer origenPlaca,
			Integer stLCaptura, Integer stVCP) throws BusinessException;
	
	public FMLotesVO crearLoteDetalle(Long idLote, String fechaEmision, String nombreLote, String fechaInicio, String fechaFin,
			Integer anoSalario, String usuario, Integer tipoPersona, Integer tipoDeteccion, Integer origenPlaca,
			Integer stLCaptura, Integer stVCP, String mensajeErr) throws BusinessException, NotFoundException;
	
	public void crearLoteValidaciones(Long idLote) throws BusinessException;

	public FMLotesDTO consultaLoteEnProceso();
}
