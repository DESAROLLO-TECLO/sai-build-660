package mx.com.teclo.saicdmx.api.rest.bloqueohh;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.util.UriComponentsBuilder;

import mx.com.teclo.saicdmx.negocio.service.bloqueohh.BloqueohhService;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.bloquehohh.BloqueohhCatTipoBloqueoDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.bloquehohh.BloqueohhRegistroDTO;
import mx.com.teclo.saicdmx.persistencia.vo.bloqueohh.BloqueohhCatTipoBloqueoVO;
import mx.com.teclo.saicdmx.persistencia.vo.bloqueohh.BloqueohhRegistroVO;
import mx.com.teclo.saicdmx.persistencia.vo.comuns.FilterValuesVO;
import mx.com.teclo.saicdmx.util.comun.ResponseConverter;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.NotFoundException;

/**
 * Copyright (c) 2016, Teclo Mexicana.
 * Descripcion					: BloquehhRestController
 * Historial de Modificaciones	:
 * Descripcion del Cambio		: Creacion
 * @author 						: fjmb 
 * @version 					: 1.0
 * Fecha						: 29/Septiembre/2016
 * 
 */

@RestController
public class BloquehhRestController {

	@Autowired
	private BloqueohhService bloqueohhService;

	@RequestMapping(value = "/handhelds/tipoBloqueo", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSCAR_TIPO_BLOQUEO')")
	public ResponseEntity<List<BloqueohhCatTipoBloqueoVO>> buscaCatTipoBloqueo() {

		List<BloqueohhCatTipoBloqueoDTO> bloqueohhCatTipoBloqueoDTO = bloqueohhService.obtenerTipoBloqueo();
		BloqueohhCatTipoBloqueoDTO todos = new BloqueohhCatTipoBloqueoDTO();
 		todos.setTipoBloqueoId(new Long(3));
		todos.setCodigo(3);
		todos.setDescripcion("Todos");
		bloqueohhCatTipoBloqueoDTO.add(0,todos);
		
		if (bloqueohhCatTipoBloqueoDTO.isEmpty())
			return new ResponseEntity<List<BloqueohhCatTipoBloqueoVO>>(HttpStatus.NOT_FOUND);

		List<BloqueohhCatTipoBloqueoVO> bloqueohhCatTipoBloqueoVO = ResponseConverter.converterLista(new ArrayList<>(),	bloqueohhCatTipoBloqueoDTO, BloqueohhCatTipoBloqueoVO.class);
		return new ResponseEntity<List<BloqueohhCatTipoBloqueoVO>>(bloqueohhCatTipoBloqueoVO, HttpStatus.OK);
	}

	@RequestMapping(value = "/handhelds/bloqueados", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('FIND_HH_BLOQUEADOS')")
	public ResponseEntity<List<BloqueohhRegistroVO>> consultaRegistrosBloqueados(@RequestParam(value = "numeroSeriehh") String numeroSeriehh,
																				 @RequestParam(value = "placaOficial") String placaOficial)throws NotFoundException {

		//Long numeroSerie = numeroSeriehh.equals("null") ? null : Long.valueOf(numeroSeriehh).longValue();

		List<BloqueohhRegistroVO> bloquehhRegistroVO = bloqueohhService.consultaRegistrosBloqueados(placaOficial, numeroSeriehh);
		if (bloquehhRegistroVO.isEmpty())
			throw new NotFoundException("No se encontraron registros");

		return new ResponseEntity<List<BloqueohhRegistroVO>>(bloquehhRegistroVO, HttpStatus.OK);

	}

	@RequestMapping(value = "/handhelds/desbloquear", method = RequestMethod.PUT)
	@PreAuthorize("hasAnyAuthority('UNLOCK_HANDHELD')")
	public ResponseEntity<BloqueohhRegistroVO> desbloquearHandHeld(@RequestBody BloqueohhRegistroVO bloqueohhRegistroVO, UriComponentsBuilder ucBuilder) {

 
		BloqueohhRegistroVO bloquehhRegistroVO = new BloqueohhRegistroVO();

		BloqueohhRegistroDTO bloqueohhRegistroDTO = bloqueohhService.desbloquearHandHeld( bloqueohhRegistroVO.getRegistroId(), bloqueohhRegistroVO.getOficialId());
		
		ResponseConverter.copiarPropriedades(bloquehhRegistroVO, bloqueohhRegistroDTO);
		HttpHeaders headers = new HttpHeaders();
 		headers.setLocation(ucBuilder.path("/handhelds/desbloquear/{id}").buildAndExpand(bloquehhRegistroVO.getRegistroId()).toUri());
		return new ResponseEntity<BloqueohhRegistroVO>(bloquehhRegistroVO, headers, HttpStatus.CREATED);

	}
	
	@RequestMapping(value = "/handhelds", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('DETAIL_INF_HANDHELD')")
	public ResponseEntity<List<BloqueohhRegistroVO>> consultaInformacionhh( @RequestParam(value = "estatusBloqueo") String estatusBloqueo, 
							                                                @RequestParam(value = "placaOficial")String placaOficial, 
							                                                @RequestParam(value = "tipoBloqueo") String tipoBloqueo,
							                                                @RequestParam(value = "numeroSeriehh") String numeroSeriehh,
							                                                @RequestParam(value = "fechaInicio") String fechaInicio,
							                                  			    @RequestParam(value = "fechaFin") String fechaFin) throws NotFoundException{ 
	                                                 
 
   Integer estatusBloqueL = Integer.valueOf(estatusBloqueo).intValue();
    Integer  tipoBloqueoL = Integer.valueOf(tipoBloqueo).intValue();
  
    //Long numeroSeriehhL = numeroSeriehh.equals("null") ? null : Long.valueOf(numeroSeriehh).longValue();
    placaOficial = placaOficial.equals("null") ? null : placaOficial;
    
	List<BloqueohhRegistroVO> listBloqueohhRegistroVO = bloqueohhService.informacionRegistros( estatusBloqueL, placaOficial,  tipoBloqueoL,
			numeroSeriehh, fechaInicio, fechaFin);	
	if (listBloqueohhRegistroVO.isEmpty())
		throw new NotFoundException("No se encontraron registros");
 
	return new ResponseEntity<List<BloqueohhRegistroVO>>(listBloqueohhRegistroVO, HttpStatus.OK);
 	}
	
	
	@RequestMapping(value = "/handhelds/estatushh", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('FIND_STATUS_HANDHELD')")
	public ResponseEntity<List<FilterValuesVO>> buscarEstatusHandHeld() throws NotFoundException {
		
		return new ResponseEntity<List<FilterValuesVO>>(bloqueohhService.obtenerTipoEstatus(), HttpStatus.OK);
	}
	
    
    @RequestMapping(value = "/handhelds/reporte", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('REPORT_EXCEL_HANDHELD')")
    public ResponseEntity<byte[]> generarReporteExcel()  {
    	
    	String filename 				   = "ReporteBloqueoHandheld.xls" ;
    	ByteArrayOutputStream outputStream =  bloqueohhService.generarReporteExcel();
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
    


}
