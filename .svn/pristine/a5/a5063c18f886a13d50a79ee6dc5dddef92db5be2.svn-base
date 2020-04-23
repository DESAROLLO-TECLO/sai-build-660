package mx.com.teclo.saicdmx.negocio.service.radarArchivoProcesado;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import mx.com.teclo.saicdmx.persistencia.vo.radares.ArchivoBatchFinanzasVO;
import mx.com.teclo.saicdmx.persistencia.vo.radares.ConsultaArchivosProcesadosVO;
import mx.com.teclo.saicdmx.persistencia.vo.radares.DeteccionesIncorrectasVO;
import mx.com.teclo.saicdmx.persistencia.vo.radares.RadarArchivoResumenVO;
import mx.com.teclo.saicdmx.persistencia.vo.radares.RadarComboTipoArchivoVO;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.BusinessException;

public interface RadarArchivoProcesadosService {
	
	List<RadarComboTipoArchivoVO> getComboArchivoTipo();
	List<RadarComboTipoArchivoVO> getComboRadarTipo(Integer tipoArchivo);

	List<ConsultaArchivosProcesadosVO> getArchivosProcesados(
			Integer origenProceso,
			Integer tipoProceso,
			Integer tipoDeteccion,
			Integer tipoArchivo,
			Integer tipoPersona,
			Integer tipoFecha,
			String fechaInicio, 
			String fechaFin);
	
	ArchivoBatchFinanzasVO cargarArchivo(String archivoId);

	void setArchivoListoParaLiberar(Integer archivoListoParaLiberar, Long empId, Integer i, String archivoId);
	
	/***
	 * @author Jesus Gutierrez
	 * @throws BusinessException
	 */
	public void cancelarArchivo(ArchivoBatchFinanzasVO objetoVO, String motivo) throws BusinessException;
	

	

	
	public RadarArchivoResumenVO buscaRadarArchivoACancelar(Long archivoId);
	
	/***
	 * @author Jesus Gutierrez
	 * @param archivoId
	 * @param estatusProcesoId
	 * @param accion 1: Complementar, 2: Rechazadas, 3: Liberadas
	 * @return String
	 */
	public String accionTotalesArchivo(Long archivoId, Integer estatusProcesoId, Integer accion);
	
	void crearExcelComplementadas(String archivoId, String fileName);
	Map crearExcelErrores(List<DeteccionesIncorrectasVO> detErrores);

	

	void crearExcelRechazadas(String archivoId, String fileName);
	/***
	 * @author Jesus Gutierrez
	 * @param archivoId
	 * @return Boolean
	 */
	Boolean notificaLiberacionLote(Long archivoId, Integer tipoProcesoWS);
	
	/***
	 * @author Jesus Gutierrez
	 * @param archivoId
	 * @param tipoProcesoWS
	 * @param nombreLote
	 * @return Boolean
	 */
	Boolean notificaAsignacionLote(Long archivoId, Integer tipoProcesoWS, String nombreLote);

	List<ConsultaArchivosProcesadosVO> getArchivosProcesadosAll(String tipoArchivo, Integer origenProceso);

	Integer complementarArchivo(Long archivoId) throws BusinessException;
	
	/***
	 * @author Dagoberto Marroquin
	 * @throws IOException
	 */
	ResponseEntity<byte[]> generarZip(Integer tipoZIP, ArchivoBatchFinanzasVO a, String archivoId) throws  IOException;
	
	/***
    * @author José Carmen Castillo Navarrete
    * @throws BusinessException
    */
	public void cancelarArchivoTotal(ArchivoBatchFinanzasVO objetoVO, String motivo) throws BusinessException;

	/***
	* @author Fernando Campero
	* @param archivoId, nombreArchivo, liberado
	* @return String
	 * @throws BusinessException 
	*/
	Integer enviarArchivo(Long archivoId, Long empId) throws BusinessException;
}
