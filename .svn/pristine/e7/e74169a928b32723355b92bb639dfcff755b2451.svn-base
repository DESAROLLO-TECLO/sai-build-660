package mx.com.teclo.saicdmx.negocio.service.semovi;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.semovi.SemoviVehiculoRobadoDTO;
import mx.com.teclo.saicdmx.persistencia.vo.comuns.FilterValuesVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.InfraccionesConPuntosLicenciaVO;
import mx.com.teclo.saicdmx.persistencia.vo.semovi.SemoviArchivosLicenciaVO;
import mx.com.teclo.saicdmx.persistencia.vo.semovi.VehiculoRobadoHistoricoVO;
import mx.com.teclo.saicdmx.persistencia.vo.semovi.VehiculosRobadosVO;

/**
 * 
 * @author UnitisDes0416
 *
 */
public interface SemoviService {
	
	
	/**
	 * @author UnitisDes0416
	 * @param file  InputStream
	 * @param fechaArchivo String
	 * @param usuarioId Long
	 * @param tipoArchivo Integer
	 * @param nombreArchivo Stirng 
	 * @param fechaCreacion Date
	 * @return List<Object>
	 */
	List<Object> procesaArchivosSemovi(InputStream file, String fechaArchivo, Long usuarioId, Integer tipoArchivo, String nombreArchivo, Date fechaCreacion);
	
	/**
	 * @author UnitisDes0416
	 * @param listaResultados List<Object>
	 * @param usuarioId Long
	 * @param tipoArchivo Integer
	 * @return List<Object>
	 */
	List<Object> cargaArchivosSemovi(List<Object> listaResultados, Long usuarioId, Integer tipoArchivo);
	
	/**
	 * @author UnitisDes0416
	 * @param nombreArchivo String
	 * @param fechaArchivo String
	 * @param idUsuario Long
	 * @param fechaCreacion Date
	 */
	void guardaSemoviArchivo(String nombreArchivo, String fechaArchivo, Long idUsuario, Date fechaCreacion, Integer tipoArchivo);
    /**
     * @author javier07
     * @param tipo
     * @param fechaInicio
     * @param fechaFin
     * @return List<SemoviArchivosLicenciaVO>
     */
	 List<SemoviArchivosLicenciaVO> obtenerArchivosLicencia(Long tipo, String fechaInicio,String fechaFin);
	/**
	 * @author javier07
	 * @param ruta
	 * @return byte[]
	 * @throws IOException 
	 */
    byte[] recuperarArchivo(String ruta) throws IOException;
     
    /**
	 * @author UnitisDes0416
	 * @return Map<String, Object>
	 */
	Map<String, Object> buscaCatTipoArchivos();
	
	/**
	 * @author UnitisDes0416
	 * @return Map<String, Object>
	 */
	Map<String, Object> buscaCatTipoArchivosActivos();
	
	/**
	 * @author UnitisDes0416
	 * @param fechaArchivo String
	 * @return Map<String, Object>
	 */
	Map<String, Object> validaArchivoExiste(String fechaArchivo, Integer codigoArchivo);
	
	
	Map<String, Object> generaArchivoPuntoInfraccion(String nombreArchivo, String fechaArchivo, String empleadoId,List<InfraccionesConPuntosLicenciaVO> listaInfraccionesConPuntosLicenciaVO);
	
	List<InfraccionesConPuntosLicenciaVO> buscarInfraccPuntosLicencia(String fechaArchivo);

	
//	INICIA VEHICULOS ROBADOS
	
	List<FilterValuesVO> buscarOpcionesVehRobados();
	
	List<VehiculosRobadosVO>buscaVehiculosRobados(String opcion, String valor);

	List<VehiculosRobadosVO> buscarVehiculosRobados(String opcion);

	List<VehiculoRobadoHistoricoVO> consultaHistorico(Long idRobo);

	VehiculosRobadosVO consultaDetalleVehiculo(Long idRobo);

	List<FilterValuesVO> buscarModelo();
	
	List<FilterValuesVO> buscarTipo(Long id);

	Long guardarVehiculoRobado(Long long1);

	Boolean guardarDataVehiculoRobado(VehiculosRobadosVO convertVO, Long resultHist)  throws ParseException;

	SemoviVehiculoRobadoDTO updateDataVehiculoRobado(VehiculosRobadosVO convertVO);

	void insertaHistoricoRoboVehiculo(Long idRobo, Long idEstatus);

	SemoviVehiculoRobadoDTO updateDataVehiculoRob(VehiculosRobadosVO convertVO) throws ParseException;

	List<FilterValuesVO> buscarVehiculosEstatus();

	Long getExistExpediente(String valor);

	boolean createExpediente(String expediente, Long long1);

	void cancelaRegistroRobo(Long idRobo);

	ByteArrayOutputStream generarReporteExcel();

	Long verifiReporteExist(String exp, String turno);

	void updateExistExperiente(String expNew, String expOld);

	void updateVehiculosRobExp(String expNew, String expOld);

	List<VehiculosRobadosVO> getExistExpedienteName(Long idExp);
}
