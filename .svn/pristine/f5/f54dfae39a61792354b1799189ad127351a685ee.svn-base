package mx.com.teclo.saicdmx.api.rest.logs;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.negocio.service.empleado.EmpleadoService;
import mx.com.teclo.saicdmx.negocio.service.logs.LogsConsultaService;
import mx.com.teclo.saicdmx.negocio.service.logs.LogsProcedureService;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.empleados.EmpleadosDTO;
import mx.com.teclo.saicdmx.persistencia.vo.logs.ComboVO;
import mx.com.teclo.saicdmx.persistencia.vo.logs.LogsBusquedaPorIdVO;
import mx.com.teclo.saicdmx.persistencia.vo.logs.LogsBusquedaVO;
import mx.com.teclo.saicdmx.persistencia.vo.logs.LogsConsultaComboVO;
import mx.com.teclo.saicdmx.persistencia.vo.logs.LogsVO;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.UsuarioFirmadoVO;
/**
 * 
 * @author javier07
 *
 */
@RestController
@RequestMapping("/logsController")
public class LogsRestController {

	@Autowired
    private LogsConsultaService logsConsultaService;
	@Autowired
    private LogsProcedureService logsProcedureService;
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	@Autowired
	private EmpleadoService empleadoService;
	
	private static final String MODIFICAR_LOG = "M";
    private static final String NUEVO_LOG = "A";
    private static final String AGREGAR_PERFIL = "AP";
    private static final String ELIMINAR_PERFIL = "EP";
    
    /**
     * @author javier07
     * @param logId
     * @param nombreArchivo
     * @param response
     * @return InputStreamResource
     * @throws IOException
     */
    @RequestMapping(value = "/descargaArchivo", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('LOGS_DESCARGA_ARCHIVO')")
    public ResponseEntity<InputStreamResource>  descargarArchivo(@RequestParam(value = "id")Long logId,
    		@RequestParam(value = "nombre")String nombreArchivo,
    		HttpServletResponse response) throws IOException  {
 
        LogsBusquedaPorIdVO log = null;
        log =logsConsultaService.busquedaLogPorId(logId);
        log.setLogNombre(nombreArchivo);

    	File file = new File(log.getRutaArchivo() + "/"+ log.getLogNombre());
    	
		HttpHeaders respHeaders = new HttpHeaders();
		respHeaders.setContentType(new MediaType("application","force-download"));
		respHeaders.setContentLength(file.length());
		response.setHeader("Content-disposition", "attachment; filename=\"" + log.getLogNombre() + ".txt\"");
		response.setHeader("filename", log.getLogNombre());
		
		InputStreamResource isr = new InputStreamResource(new FileInputStream(file));
		return new ResponseEntity<InputStreamResource>(isr, respHeaders, HttpStatus.OK);
	}
    

   
    /**
     * Lista los <b>Archivos que contiene un log</> dependiendo del log
     * especificado.
     *
     * @param ruta Ruta donde se encuntran los archivos a listar.
     * @return Regresa un objeto de tipo <b>List<LogsVO><b/>
     */
    @RequestMapping(value = "/consultaArchivosPorLog", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('LOGS_CONSULTA_ARCHIVOS')")
    public ResponseEntity<List<LogsVO>> getArchivosPorLog(@RequestParam(value = "id")Long logId) throws NotFoundException  {

    	LogsBusquedaPorIdVO logInfo = getLogPorId(logId);  
        File file = new File(logInfo.getRutaArchivo());      
        List<LogsVO> listaArchivos = logsConsultaService.obtenerListaArchivosLogs(file, logInfo, logId);
  
        if (listaArchivos.isEmpty()) {
			throw new NotFoundException("No se encontraron registros");
		}	
        return new ResponseEntity<List<LogsVO>>(listaArchivos, HttpStatus.OK);
    }

  
    /**
     * Obtiene los <b>Todos los Logs</> existentes en la base de datos.
     *
     * @return Regresa un objeto de tipo <b>LogsVO<b/>.
     */
    @RequestMapping(value = "/consultaPorId", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('LOGS_CONSULTA_ID')")
    public LogsBusquedaPorIdVO getLogPorId(@RequestParam(value = "id") Long id) {
        return logsConsultaService.busquedaLogPorId(id);
    }

    /**
     * Obtiene los <b>Logs Activos</> existentes en la base de datos.
     *
     * @return Regresa un objeto de tipo <b>List<LogsVO><b/>.
     */
    @RequestMapping(value = "/consultaActivos", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('LOGS_CONSULTA_ACTIVOS')")
    public List<LogsBusquedaVO> getLogsActivos() {
        return logsConsultaService.busquedaLogsTodos();
    }

    /**
     * Obtiene los <b>Logs</> pertenecientes a un perfil web.
     *
     * @return Regresa un objeto de tipo <b>List<LogsVO><b/>.
     */
    @RequestMapping(value = "/consultaPorPerfil", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('LOGS_CONSULTA_LOGS_PORPERFIL')")
    public List<LogsConsultaComboVO> getLogsPorPerfil() {
 	
    	UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
        return logsConsultaService.busquedaLogsPorPerfil(usuario.getPerfilId());
    }

    /**
     * Obtiene los <b>Perfiles Asociados</> a un log.
     *
     * @return Regresa un objeto de tipo <b>List<ComboVO><b/>.
     */
    @RequestMapping(value = "/consultaPerfilesActivos", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('LOGS_CONSULTA_PERFILES_ACTIVOS')")
    public List<ComboVO> getPerfilesActivosPorLog(@RequestParam(value = "id")Long logId) {
        return logsConsultaService.perfilesAsignadosPorLog(logId);
    }

    /**
     * Obtiene los <b>Perfiles no Asociados</> a un log.
     *
     * @return Regresa un objeto de tipo <b>List<ComboVO><b/>.
     */
    @RequestMapping(value = "/consultaPerfilesNoAsignados", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('LOGS_CONSULTA_PERFILES_NOACTIVOS')")
    public List<ComboVO> getPerfilesNoActivosPorLog(@RequestParam(value = "id")Long logId) {
        return logsConsultaService.perfilesNoAsignadosPorLog(logId);
    }

    /**
     * Crea <b>Logs</>.
     * @throws ParseException 
     */
    @RequestMapping(value = "/crear", method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('LOGS_ALTA')")
    public LogsVO crearLog(@RequestBody LogsVO logVO) throws ParseException {
    	
    	UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
		EmpleadosDTO empleado = empleadoService.getEmpleadoById(usuario.getId());
		logVO.setCreadoPor(empleado.getEmpId());
        return logsProcedureService.crudLog(logVO, NUEVO_LOG);
    }

    /**
     * Modifica <b>Logs</>.
     * @throws ParseException 
     */
    @RequestMapping(value = "/actualizar", method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('LOGS_ACTUALIZA')")
    public LogsVO modificarLog(@RequestBody LogsVO logVO) throws ParseException {
    	
    	UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
		EmpleadosDTO empleado = empleadoService.getEmpleadoById(usuario.getId());
		logVO.setCreadoPor(empleado.getEmpId());
		
        return logsProcedureService.crudLog(logVO, MODIFICAR_LOG);
    }

    /**
     * Agrega un <b>Perfil a un Log</>.
     * @throws ParseException 
     *
     */
    @RequestMapping(value = "/agregarPerfil", method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('LOGS_ALTA_PERFIL')")
    public void agregarPerfil(@RequestBody LogsVO logVO) throws ParseException {
    	
    	UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
		EmpleadosDTO empleado = empleadoService.getEmpleadoById(usuario.getId());
		logVO.setCreadoPor(empleado.getEmpId());
		
        logsProcedureService.crudLog(logVO, AGREGAR_PERFIL);
    }

    /**
     * Elimina un <b>Perfil de un Log</>.
     * @throws ParseException 
     *
     */
    @RequestMapping(value = "/eliminarPerfil", method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('LOGS_BAJA_PERFIL')")
    public void eliminarPerfil(@RequestBody LogsVO logVO) throws ParseException {
    	
    	UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
		EmpleadosDTO empleado = empleadoService.getEmpleadoById(usuario.getId());
		logVO.setCreadoPor(empleado.getEmpId());
		
        logsProcedureService.crudLog(logVO, ELIMINAR_PERFIL);
    }

    /**
     * Habilita o deshabilita la <b>Consulta de Logs</> dependiendo del log
     * especificado.
     *
     */
    @RequestMapping(value = "/cambiarEstatus", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('LOGS_CAMBIA_ESTATUS')")
    public void cambioDeEstatus(@RequestParam(value = "id")Long logId,@RequestParam(value = "accion") String accion) {
        logsProcedureService.cambioDeEstatus(logId, accion);
    }
}
