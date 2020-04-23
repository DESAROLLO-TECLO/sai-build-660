package mx.com.teclo.saicdmx.negocio.service.logs;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import mx.com.teclo.saicdmx.persistencia.hibernate.dao.logs.WebLogsDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.logs.WebLogsDTO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.logs.PerfilMybatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.logs.WebLogsMybatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.logs.ComboVO;
import mx.com.teclo.saicdmx.persistencia.vo.logs.LogsBusquedaPorIdVO;
import mx.com.teclo.saicdmx.persistencia.vo.logs.LogsBusquedaVO;
import mx.com.teclo.saicdmx.persistencia.vo.logs.LogsConsultaComboVO;
import mx.com.teclo.saicdmx.persistencia.vo.logs.LogsVO;
/**
 * 
 * @author javier07
 *
 */
@Service
public class LogsConsultaServiceImpl implements LogsConsultaService{

	@Autowired
	private WebLogsDAO webLogsDAO;
	@Autowired
	private WebLogsMybatisDAO webLogsMybatisDAO;
	@Autowired
	private PerfilMybatisDAO perfilMybatisDAO;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public List<LogsBusquedaVO> busquedaLogsTodos() {
		
		List<LogsBusquedaVO> listaVO = new ArrayList<LogsBusquedaVO>();
		
		for (WebLogsDTO weblDTO : webLogsDAO.busquedaLogsTodos()) {
		
			LogsBusquedaVO logVO = new LogsBusquedaVO();
			logVO.setLogId(weblDTO.getLogId());
			logVO.setLogNombre(weblDTO.getLogNombre());
			logVO.setLogDescripcion(weblDTO.getLogDescripcion());;
			logVO.setLogEstatus(weblDTO.getLogEstatus());
			logVO.setRutaArchivos(weblDTO.getRutaArchivos());;
			listaVO.add(logVO);	
		}

		return listaVO;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<LogsConsultaComboVO> busquedaLogsPorPerfil(Long perfilId) {
		
		return webLogsMybatisDAO.busquedaPorPerfil(perfilId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public LogsBusquedaPorIdVO busquedaLogPorId(Long id) {
		
		
		WebLogsDTO logDTO = webLogsDAO.busquedaLogPorId(id);
		
		LogsBusquedaPorIdVO logVO = new LogsBusquedaPorIdVO();
		logVO.setLogId(logDTO.getLogId());
		logVO.setLogNombre(logDTO.getLogNombre());
		logVO.setLogDescripcion(logDTO.getLogDescripcion());;
		logVO.setLogEstatus(logDTO.getLogEstatus());
		logVO.setRutaArchivo(logDTO.getRutaArchivos());;
		logVO.setTipoExtensiones(logDTO.getTipoExtensiones());
		
		
		return logVO;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ComboVO> perfilesAsignadosPorLog(Long id) {
		
		return perfilMybatisDAO.perfilesAsignadosPorLog(id);
	}
	/**
	 * {@inheritDoc}
	 */

	@Override
	public List<ComboVO> perfilesNoAsignadosPorLog(Long id) {
		
		return perfilMybatisDAO.consultaPerfilesNoAsignados(id);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<LogsVO> obtenerListaArchivosLogs(File file,LogsBusquedaPorIdVO logInfo,Long logId) {
		   
		    File[] listFiles = file.listFiles();
	        
		    List<LogsVO> listaArchivos = new ArrayList<LogsVO>();
	        
		    try {
                 if (listFiles.length > 0) {

	                LogsVO log;
	                for (File archivo : listFiles) {
	                    if (archivo.isFile() && compruebaExtension(archivo, logInfo.getTipoExtensiones())) {
	                        log = new LogsVO();
	                        log.setLogId(logId);
	                        Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("America/Mexico_City"));
	                        calendar.setTimeInMillis(archivo.lastModified());
	                        log.setLogNombre(archivo.getName());
	                        log.setRutaArchivo(logInfo.getRutaArchivo());
	                        log.setFechaModficacion(calendar);
	                        log.setUltimaFechaModificacion(obtenerFechaString(calendar));
	                        listaArchivos.add(log);
	                    }
	                }
	                Collections.sort(listaArchivos, Collections.reverseOrder());
	            }
	        } catch (Exception exception) {
	            System.out.println("ERROR EN LA CLASE [" + LogsConsultaServiceImpl.class + "]:" + exception.getMessage());
	            exception.printStackTrace();
	        }
	   return listaArchivos;
	        
	}
	
	/**
     * Da formato a la <b>Fecha de Modificaci�n</> de un archivo.
     *
     * @return Regresa un objeto de tipo <b>String<b/>.
     */
	 public String obtenerFechaString(@RequestParam(value = "fecha")Calendar calendar) {
	        String dia = Integer.toString(calendar.get(Calendar.DATE));
	        String mes = Integer.toString(calendar.get(Calendar.MONTH) + 1);
	        String annio = Integer.toString(calendar.get(Calendar.YEAR));
	        String fecha = dia + "/" + mes + "/" + annio;
	        return fecha;
	    }

	    
	   /**
	    * Valida la extensión del archivo recibido
	    * @param archivo
	    * @param campoExtensiones
	    * @return boolean
	    */
	    public boolean compruebaExtension(File archivo, String campoExtensiones) {
	        String[] extensiones = campoExtensiones.split("\\|");
	        int indiceExtension = archivo.getName().lastIndexOf(".");
	        String extensionArchivo = archivo.getName().substring(indiceExtension + 1);

	        for (int contador = 0; contador < extensiones.length; contador++) {
	            if (extensionArchivo.equalsIgnoreCase(extensiones[contador])) {
	                return true;
	            }
	        }
	        return false;
	    }

	
}
