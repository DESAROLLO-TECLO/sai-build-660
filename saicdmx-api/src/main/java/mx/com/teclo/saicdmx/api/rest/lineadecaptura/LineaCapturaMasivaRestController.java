package mx.com.teclo.saicdmx.api.rest.lineadecaptura;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ProtocolException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.xml.sax.SAXException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.security.access.prepost.PreAuthorize;

import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.negocio.service.lineadecaptura.ConsultaLCMasivaService;
import mx.com.teclo.saicdmx.negocio.service.lineadecaptura.ReasignacionLCMasivaService;
import mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura.IncidenciaLCMVO;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.UsuarioFirmadoVO;


/***
 * 
 * @author Sail Jesus Ponce
 *
 */

@RestController
public class LineaCapturaMasivaRestController {

	@Autowired 
	private ReasignacionLCMasivaService reasignacionLCMService;
	
	@Autowired
	private ConsultaLCMasivaService consultaLCMService;
	
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	
	/*Consultar lotes de folios*/
	@RequestMapping(value = "/consultarLotes", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('CONSULTA_LOTE_MASIVO')")
	public ResponseEntity<Map<String, Object>> consultaLotes(
															@RequestParam("fechaInicio")String fechaInicio, 
															@RequestParam("fechaFin")String fechaFin, 
															@RequestParam("cbCampoBusqueda")Integer cbCampoBusqueda, 
															@RequestParam("idLote")Integer idLote, 
															@RequestParam("nameLote")String nameLote, 
															@RequestParam("cbTipoFecha")Integer cbTipoFecha, 
															@RequestParam("cbEstatusLotes")Integer cbEstatusLotes
															) throws NotFoundException{
		Boolean traedatos = false;
		Map request = null;
		try {
		request = consultaLCMService.consultaLotes(fechaInicio, fechaFin, cbCampoBusqueda, idLote, nameLote, cbTipoFecha, cbEstatusLotes);
		traedatos = (Boolean) request.get("respuesta");
		
		}catch (Exception e) {
			throw new NotFoundException("Ha ocurrido un imprevisto! , porfavor contacte al administrador");
		}
		
		if(!traedatos){
			throw new NotFoundException("No se encontraron registros");
		}
		
		return new ResponseEntity<Map<String, Object>>(request, HttpStatus.OK);
		
	}
	
	/*Descargar reporte del lote*/
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/descargaReporteLote", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('DESCARGA_LOTE_MASIVO')")
	public ResponseEntity<byte[]> descargarReporteLote(@RequestParam("lote") String lote){
		Map reporte 					   = consultaLCMService.descargarReporteLote(lote);
    	String filename 				   = (String) reporte.get("nombre") ;
    	ByteArrayOutputStream outputStream = (ByteArrayOutputStream) reporte.get("reporte");
    	final byte[] bytes				   = outputStream.toByteArray();

    	HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/vnd.ms-exce"));
    	headers.add("Content-Disposition", "attachment; filename=" + filename);
    	headers.add("filename",   filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        headers.setContentLength(bytes.length);
        
        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
        
        return response;
	}
	
	/*Valida Reasignaciones Pendientes*/
	@RequestMapping(value = "/validarReasignacionesPendientes", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('VALIDA_REASIGNA_MASIVA')")
	public ResponseEntity<Map<String,Integer>> validarLotePendiente(){
		return new ResponseEntity<Map<String, Integer>>(reasignacionLCMService.validarLotePendiente(),HttpStatus.OK);
	}
	
	/*Carga el archivo de Infracciones*/
	@SuppressWarnings("finally")
	@RequestMapping(value = "/cargaArchivoReasignacion", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('CARGA_REASIGNA_MASIVA')")
	public @ResponseBody Map<String, Object> cargarArchivoReasignar(
			@RequestParam("file") MultipartFile file,
			@RequestParam("fEmision") String fEmision,
			@RequestParam("tipoDescuento") Integer tipoDescuento){
		
		UsuarioFirmadoVO ufVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		Map<String, Object> response = new HashMap<String, Object>();
		List<String> message = new ArrayList<String>();
		ArrayList<String> infracs = new ArrayList<String>();
		String[] infracsToArray = null;
		String name;
		//List<IncidenciaLCMVO> answer = null;
		Map<String, Object> answer = new HashMap<String, Object>();
		
		try{
			name = file.getOriginalFilename();
			InputStream inputStream = file.getInputStream();
			
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			
			String line;
			while ((line = bufferedReader.readLine()) != null){
				//do your processing
				infracs.add(line);
			}
			infracsToArray = new String[infracs.size()];
			infracsToArray = infracs.toArray(infracsToArray);
			answer = reasignacionLCMService.cargarArchivoReasignar(infracsToArray, ufVO.getId(), name, fEmision, tipoDescuento);
			
		}catch(Exception e){
			
		}finally{
			if(!answer.isEmpty()){
				Boolean insidencias = false;
				List<IncidenciaLCMVO> listaIncidencias = null;
				Boolean loteCreado = false; 
				Integer infraccionesAgregadas = 0;
				Boolean loteVacio = false; 
				for (Entry<String, Object> entry : answer.entrySet()) {
				    if(entry.getKey() == "insidencias"){
				    	insidencias = (Boolean) entry.getValue(); 
				    }else if(entry.getKey() == "listaIncidencias"){
				    	listaIncidencias = (List<IncidenciaLCMVO>) entry.getValue(); 
				    }else if(entry.getKey() == "loteCreado"){
				    	loteCreado = (Boolean) entry.getValue(); 
				    }else if(entry.getKey() == "infraccionesAgregadas"){
				    	infraccionesAgregadas = (Integer) entry.getValue(); 
				    }else if(entry.getKey() == "vacio"){
				    	loteVacio = (Boolean) entry.getValue(); 
				    }
				}
				if(insidencias == true){
					for(IncidenciaLCMVO temp : listaIncidencias){
						message.add(temp.toString());
					}
				}
//				response.put("respuesta", Boolean.FALSE);
//				response.put("status", 500);
//				response.put("message", "Se encontraron incidencias, por favor revisar el siguiente documento.");
//				response.put("incidencia", message);
				
				if(loteCreado == true && insidencias == true){
					response.put("respuesta", Boolean.TRUE);
					response.put("status", 100);
				}else if(loteCreado == true && insidencias == false){
					response.put("respuesta", Boolean.TRUE);
					response.put("status", 0);
				}else if(loteVacio == true){
					response.put("message", "El archivo no puede estar vacio.");
					response.put("respuesta", Boolean.FALSE);
					response.put("status", 101);
				}else{
					response.put("respuesta", Boolean.FALSE);
					response.put("status", 500);
				}
				if(insidencias == true){
					response.put("message", "Se encontraron incidencias, por favor revisar el siguiente documento.");
					response.put("incidencia", message);
				}
			}else{
				response.put("respuesta", Boolean.TRUE);
				response.put("status", 0);
			}
			
			
			return response;
		}
	}
	
	/*Reasignar Archivo*/
	@RequestMapping(value = "/reasignarLoteFolio", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('REASIGNA_LOTE_MASIVO')")
	public ResponseEntity<Map<String, Object>> reasignarLoteFolio() throws ProtocolException, IOException, ParserConfigurationException, SAXException, BusinessException, ParseException, NotFoundException{
		Long userId = usuarioFirmadoService.getUsuarioFirmadoVO().getId();
		return new ResponseEntity<Map<String, Object>>(reasignacionLCMService.reasignarLoteFolio(userId), HttpStatus.OK);	
	}
	
	/*Cancelar Archivo*/
	@RequestMapping(value = "/cancelarLoteFolio", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('CANCELAR_LOTE_MASIVO')")
	public ResponseEntity<Map<String, Object>> cancelarLoteFolio(){
		Boolean result = reasignacionLCMService.cancelarLoteFolio();
		Map<String, Object> response = new HashMap<String, Object>();
		if(result){
			response.put("respuesta",Boolean.TRUE);
			response.put("message", "Se canceló el archivo.");
		}else{
			response.put("respuesta",Boolean.FALSE);
			response.put("message", "Hubo un error al cancelar las infracciones, porfavor vuelva a intentar mas tarde.");
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
