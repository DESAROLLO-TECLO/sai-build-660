package mx.com.teclo.saicdmx.negocio.service.radararchivo;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import mx.com.teclo.saicdmx.persistencia.vo.comuns.ComboValuesVO;
import mx.com.teclo.saicdmx.persistencia.vo.radarbitacoraprocesoestatus.RadarBitacoraProcesoComplementacionVO;
import mx.com.teclo.saicdmx.persistencia.vo.radares.CargaArchivoComplementracionVO;
import mx.com.teclo.saicdmx.persistencia.vo.radares.RadarArchivoEnComplementacionVO;
import mx.com.teclo.saicdmx.persistencia.vo.radares.RadarDeteccionesCentroReparto;
import mx.com.teclo.saicdmx.persistencia.vo.radares.RadarDeteccionesCentroRepartoV2;
import mx.com.teclo.saicdmx.persistencia.vo.radares.RadaresCatArchivoTipoVO;
import mx.com.teclo.saicdmx.util.enumerados.ArchivoBatchEstatusEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.BusinessException;

/**
 * 
 * @author UnitisDes0416
 *
 */
public interface RadarArchivoService {
	
	/**
	 * @author UnitisDes0416
	 * @return boolean
	 * @throws BusinessException
	 */
	boolean verificarArchivosEnCurso() throws BusinessException;
	
	/**
	 * @author UnitisDes0416
	 * @param file MultipartFile
	 * @param cargaArchivoComplementracionVO CargaArchivoComplementracionVO
	 * @param rutaArchivo String
	 * @return Boolean
	 * @throws IOException
	 */
	Boolean cargaArchivoComplementacionRadares(MultipartFile file, CargaArchivoComplementracionVO cargaArchivoComplementracionVO, String rutaArchivo) throws IOException;
	
	/**
	 * @author UnitisDes0416
	 * @param procesoTipo Integer
	 * @param fileName String
	 * @param ruraRadares String
	 * @param radaresCatArchivoTipoVO 
	 * @return Map
	 */
	@SuppressWarnings("rawtypes")
	Map validaArchivoRadar(String fileName, String ruraRadares, CargaArchivoComplementracionVO cargaArchivoComplementracionVO);
	
	/**
	 * @author UnitisDes0416
	 * @param nombreArchivo String
	 * @param integer 
	 * @param detecciones List<Map>
	 * @param duplicados List<Map>
	 * @param empleadoId Long
	 * @param cargaArchivoComplementracionVO CargaArchivoComplementracionVO
	 */
	@SuppressWarnings("rawtypes")
	void cargaArchivoRadar(String nombreArchivo, Integer integer, List<Map> detecciones, List<Map> duplicados, Long empleadoId, CargaArchivoComplementracionVO cargaArchivoComplementracionVO, Integer origenPlaca, boolean esCarrilConfinado);
	
//	@SuppressWarnings("rawtypes")
//	Map asignarArticulo(Map resultadoArchivo);
	
	/**
	 * @author Fernando Campero
	 * @return RadarArchivoEnComplementacionVO
	 */
	RadarArchivoEnComplementacionVO buscarRadarArchivoEnProceso(String query);
	
	/**
	 * @author UnitisDes0416
	 * @param radarArchivoid Long
	 * @return RadarBitacoraProcesoComplementacionVO
	 */
	List<RadarBitacoraProcesoComplementacionVO> buscaEststusRadarArchivoEnProceso(Long radarArchivoid);

//	List<RadarDeteccionesCentroReparto> buscarListaDeteccionesValidas(Long radarArchivoId);
//
//	List<RadarDeteccionesCentroReparto> buscarListaDeteccionesInvalidas(Long radarArchivoId);
	List<RadarDeteccionesCentroRepartoV2> buscarListaDeteccionesValidas(Long radarArchivoId);

	List<RadarDeteccionesCentroRepartoV2> buscarListaDeteccionesInvalidas(Long radarArchivoId);

	Boolean actualizarArchivoProcesoId(Long radarArchivoId,int recomplementandoCentroReparto, Long idEmpleado, int enproceso);

	Boolean updateDetecciones(Long radarArchivoId, Long idCP, Long id);

	Boolean updateAllCR(Long radarArchivoId);
	
	Boolean updateThisCP(Long radarArchivoId, String Cp, Long id, Long idUsu);
	
	Boolean cambiaCPDeteccion(Long idCP, String cP, Long id, Long radarArchivoId);
	
	Boolean validarUsuarioActivo(String placaOficial);
	
	Map<String, Object> validarPassCert(String placaOficial, String passPrivateKey);
	
	Boolean insertaParametrosFirma(Long radarArchivoId, Long empId, String placaOficial, String empRFC, String pwd);
	
	//List<ComboValuesVO> obtenerAniosSalarioMinimo();
	
	/**
	 * @author UnitisDes0416
	 * @param radarArchivoid Long
	 */
	//void cancelaLoteEnProceso(Long radarArchivoId, String motivoCancelacion);
}
