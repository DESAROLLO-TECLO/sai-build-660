package mx.com.teclo.saicdmx.api.rest.radares;



import java.awt.PageAttributes.MediaType;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import mx.com.teclo.saicdmx.negocio.service.catalogos.CatalogoService;
import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.negocio.service.empleado.EmpleadoService;
import mx.com.teclo.saicdmx.negocio.service.radarArchivoProcesado.RadarArchivoProcesadosService;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.empleados.EmpleadosDTO;
import mx.com.teclo.saicdmx.persistencia.vo.radares.ArchivoBatchFinanzasVO;
import mx.com.teclo.saicdmx.persistencia.vo.radares.ConsultaArchivosProcesadosVO;
import mx.com.teclo.saicdmx.persistencia.vo.radares.DeteccionesIncorrectasVO;
import mx.com.teclo.saicdmx.persistencia.vo.radares.ListaDeteccionesErroneas;
import mx.com.teclo.saicdmx.persistencia.vo.radares.RadarComboTipoArchivoVO;
import mx.com.teclo.saicdmx.util.enumerados.ArchivoTipoAtributes;
import mx.com.teclo.saicdmx.util.enumerados.EstatusProcesoLote;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.NotFoundException;

import mx.com.teclomexicana.arquitectura.ortogonales.exception.NotFoundException;

import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.UsuarioFirmadoVO;




@Controller
@RestController
public class RadarArchivosProcesadosController {
	@Autowired
	private EmpleadoService empleadoService;
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	@Autowired
	private RadarArchivoProcesadosService radarArchivoService;
	@Autowired
	private CatalogoService catalogoService;

	
	
	@RequestMapping(value = "/getComboTipoArchivo", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('COMBO_TIPO_ARCHIVO')")
	public ResponseEntity<List<RadarComboTipoArchivoVO>> getComboArchivoTipo() {
    	List<RadarComboTipoArchivoVO> listaComboArchivoVO = new LinkedList<RadarComboTipoArchivoVO>();
    	//listaComboArchivoVO.add(generaCombo("TODOS", String.valueOf(ArchivoTipoAtributes.DEFAULT)));
    	listaComboArchivoVO.add(generaCombo("Todos", "-1"));
        listaComboArchivoVO.addAll(radarArchivoService.getComboArchivoTipo());
    	return new ResponseEntity<List<RadarComboTipoArchivoVO>>(listaComboArchivoVO,HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/getComboTipoRadar", method = RequestMethod.GET)
	//@PreAuthorize("hasAnyAuthority('COMBO_TIPO_ARCHIVO')")
	public ResponseEntity<List<RadarComboTipoArchivoVO>> getComboRadarTipo(@RequestParam("tipoArchivo")Integer tipoArchivo) {
    	List<RadarComboTipoArchivoVO> listaComboArchivoVO = new LinkedList<RadarComboTipoArchivoVO>();
    	listaComboArchivoVO.add(generaCombo("Todos", String.valueOf(ArchivoTipoAtributes.DEFAULT)));
        listaComboArchivoVO.addAll(radarArchivoService.getComboRadarTipo(tipoArchivo));
    	return new ResponseEntity<List<RadarComboTipoArchivoVO>>(listaComboArchivoVO,HttpStatus.OK);
		
	}
	
    private RadarComboTipoArchivoVO generaCombo(String label, String value) {
    RadarComboTipoArchivoVO listaComboArchivoVO = new RadarComboTipoArchivoVO();
    	
    	listaComboArchivoVO.setLabel(label);
    	listaComboArchivoVO.setValue(value);
        return listaComboArchivoVO;
    }
    
    @RequestMapping(value = "/getComboFechasBusqueda", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('COMBO_FECHAS_BUSQUEDA')")
    public ResponseEntity<List<RadarComboTipoArchivoVO>> getComboFechasBusqueda() {	
    	List<RadarComboTipoArchivoVO> lista = new LinkedList<RadarComboTipoArchivoVO>();
    	lista.add(generaCombo("--- Seleccione ---",
                String.valueOf("-1")));
        lista.add(generaCombo("Fecha Emision",
                String.valueOf(ArchivoTipoAtributes.FECHA_EMISION)));
        lista.add(generaCombo("Fecha Complementación",
                String.valueOf(ArchivoTipoAtributes.FECHA_COMPLEMENTACION)));
        lista.add(generaCombo("Fecha Liberación",
                String.valueOf(ArchivoTipoAtributes.FECHA_LIBERACION)));
        
        return new ResponseEntity<List<RadarComboTipoArchivoVO>>(lista,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/getComboArchivoEstatus", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('COMBO_ARCHIVO_ESTATUS')")
    public ResponseEntity<List<RadarComboTipoArchivoVO>> getComboArchivoEstatus() {
    	List<RadarComboTipoArchivoVO> lista = new LinkedList<RadarComboTipoArchivoVO>();
        lista.add(generaCombo("Todos", String.valueOf(ArchivoTipoAtributes.DEFAULT)));
        lista.add(generaCombo("Creado",
                String.valueOf(ArchivoTipoAtributes.CREADO)));
        lista.add(generaCombo("Complementado",
                String.valueOf(ArchivoTipoAtributes.ESTATUS_COMPLEMENTADO)));
        lista.add(generaCombo("Liberado",
                String.valueOf(ArchivoTipoAtributes.ESTATUS_LIBERADO)));
        lista.add(generaCombo("Cancelado",
                String.valueOf(ArchivoTipoAtributes.ESTATUS_CANCELADO)));
        
        
        return new ResponseEntity<List<RadarComboTipoArchivoVO>>(lista,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/getComboOrigenProceso", method = RequestMethod.GET)
    //@PreAuthorize("hasAnyAuthority('COMBO_ORIGEN_PROCESO')")
    public ResponseEntity<List<RadarComboTipoArchivoVO>> getComboOrigenProceso() {
    	List<RadarComboTipoArchivoVO> lista = new LinkedList<RadarComboTipoArchivoVO>();
        lista.add(generaCombo("Todos", String.valueOf(-1))); //Todos
        lista.add(generaCombo("Radares",
                String.valueOf(1))); //Archivos
        lista.add(generaCombo("Dispositivos Fijos",
                String.valueOf(0))); //Web Service
        return new ResponseEntity<List<RadarComboTipoArchivoVO>>(lista,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/buscarArchivosProcesados", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('BUSCAR_ARCHIVOS_PROCESADOS')")
    public ResponseEntity<List<ConsultaArchivosProcesadosVO>> buscarArchivosProcesados(			
		@RequestParam(value = "origenProceso" ) Integer origenProceso,
		@RequestParam(value = "tipoProceso" ) Integer tipoProceso,
		@RequestParam(value = "tipoDeteccion") Integer tipoDeteccion,
    	@RequestParam(value = "tipoArchivo" ) Integer tipoArchivo,
    	@RequestParam(value = "tipoPersona" ) Integer tipoPersona,
    	@RequestParam(value = "tipoFecha" ) Integer tipoFecha,
    	@RequestParam(value = "fechaInicio" ) String fechaInicio,
    	@RequestParam(value = "fechaFin" ) String fechaFin
    	) {
    	List<ConsultaArchivosProcesadosVO> lista = new ArrayList<ConsultaArchivosProcesadosVO>();
    	lista = radarArchivoService.getArchivosProcesados(origenProceso, tipoProceso, tipoDeteccion, tipoArchivo,
    			tipoPersona, tipoFecha, fechaInicio, fechaFin);
    	return new ResponseEntity<List<ConsultaArchivosProcesadosVO>>(lista,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/buscarArchivosProcesadosAll", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('BUSCAR_ARCHIVOS_PROCESADOS_ALL')")
    public ResponseEntity<List<ConsultaArchivosProcesadosVO>> buscarArchivosProcesadosAll(
    	@RequestParam(value = "tipoArchivo" ) String tipoArchivo,
    	@RequestParam(value = "origenProceso" ) Integer origenProceso) {
    	List<ConsultaArchivosProcesadosVO> lista = new ArrayList<ConsultaArchivosProcesadosVO>();
    	lista = radarArchivoService.getArchivosProcesadosAll(tipoArchivo, origenProceso);
    	return new ResponseEntity<List<ConsultaArchivosProcesadosVO>>(lista,HttpStatus.OK);
    }
    
	@RequestMapping(value = "/complementarArchivo", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('COMPLEMENTAR_ARCHIVO')")
	public ResponseEntity<Integer> complementarArchivo(@RequestParam(value = "archivoId") Long archivoId) throws BusinessException{
		Integer result = radarArchivoService.complementarArchivo(archivoId);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
    
    @RequestMapping(value = "/liberarArchivo", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('LIBERAR_ARCHIVO')")
    public ResponseEntity<Boolean> liberarInfracciones(@RequestParam(value = "archivoId" )String archivoId) throws NotFoundException {
    	Boolean success = false;
    	Long usuarioFirmado = usuarioFirmadoService.getUsuarioFirmadoVO().getId();		
    	ArchivoBatchFinanzasVO archivoVO = radarArchivoService.cargarArchivo(archivoId);
    	                                                                                                                                                               
    	if(archivoVO == null){
    		throw new NotFoundException("No se encontro el registro del archivo.");
    	}
    	
        if ((archivoVO != null) && (archivoVO.getEstatusProcesoId() == EstatusProcesoLote.COMPLEMENTADO.getEstatusProceso())) {
        	radarArchivoService.setArchivoListoParaLiberar(EstatusProcesoLote.LISTO_PARA_LIBERAR.getEstatusProceso(), usuarioFirmado, 0, archivoId); 
        	success = true;
        }
        return new ResponseEntity<Boolean>(success, HttpStatus.OK);
    }   
    
    
    @RequestMapping(value = "/generarArchivoZIP", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('GENERAR_ARCHIVO_ZIP')")
    public void generarArchivoZIP(
    		@RequestParam(value = "archivoId" )String archivoId, 
    		@RequestParam(value = "tipoZIP" )Integer tipoZIP) {
    	System.out.println("Estas en generar archivon Zip de tipoZip: "+tipoZIP);
    	 ArchivoBatchFinanzasVO abf = radarArchivoService.cargarArchivo(archivoId);

        switch (tipoZIP) {
            case ArchivoTipoAtributes.ZIP_COMPLEMENTADAS:
                radarArchivoService.crearExcelComplementadas(archivoId, abf.getFileName());
                break;
            case ArchivoTipoAtributes.ZIP_RECHAZADAS:
            	radarArchivoService.crearExcelRechazadas(archivoId, abf.getFileName());
                break;

        }
        
    }
    
    @RequestMapping(value = "/descargarArchivosZIP", method = RequestMethod.GET)   
    @PreAuthorize("hasAnyAuthority('DESCARGAR_ARCHIVO_ZIP')")
    public ResponseEntity<byte[]> descargarArchivosZIP(
    		@RequestParam(value = "archivoId" ) String archivoId, 
    		@RequestParam(value = "tipoZIP" ) Integer tipoZIP) throws IOException {

    	System.out.println("Estas en descargar archivon Zip de tipoZip: "+tipoZIP);
    	ResponseEntity<byte[]> response = null; 
    	ArchivoBatchFinanzasVO a = radarArchivoService.cargarArchivo(archivoId);
        response = radarArchivoService.generarZip(tipoZIP,a,archivoId);
        
        return response;
        }
 
    @RequestMapping(value = "/enviarArchivo", method = RequestMethod.GET)
//	@PreAuthorize("hasAnyAuthority('ENVIAR_ARCHIVO')")
	public ResponseEntity<Integer> enviarArchivo(
			@RequestParam(value = "archivoId") Long archivoId) throws BusinessException{
    	
    	Long usuarioFirmado = usuarioFirmadoService.getUsuarioFirmadoVO().getId();
		Integer result = radarArchivoService.enviarArchivo(archivoId, usuarioFirmado);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
}
