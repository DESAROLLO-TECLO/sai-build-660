package mx.com.teclo.saicdmx.api.rest.radares;


import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import mx.com.teclo.saicdmx.negocio.service.radarCatalogos.RadarCatalogoService;
import mx.com.teclo.saicdmx.persistencia.vo.comuns.FilterValuesVO;
import mx.com.teclo.saicdmx.persistencia.vo.radares.RadarCatConsultaCPVO;
import mx.com.teclo.saicdmx.persistencia.vo.radares.RadarCatConsultaUTVO;
import mx.com.teclo.saicdmx.util.enumerados.ArchivoTipoAtributes;





@RestController
public class RadarCatalogosController {

	@Autowired
	private RadarCatalogoService radarCatalogoService;

	@RequestMapping(value = "/comboCatalogos", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('GET_COMBO_CATALOGOS')")
	public ResponseEntity<List<FilterValuesVO>> getComboCatalogos() {
		List<FilterValuesVO> listaComboArchivoVO = radarCatalogoService.getComboCatalgos();
        return new ResponseEntity<List<FilterValuesVO>>(listaComboArchivoVO,HttpStatus.OK);
		
	} 
	 @RequestMapping(value = "/comboTipoBusqueda", method = RequestMethod.GET)
		@PreAuthorize("hasAnyAuthority('GET_COMBO_TIPO_BUSQUEDA')")
		public ResponseEntity<List<FilterValuesVO>> comboTipoBusqueda(@RequestParam(value = "tipoCatalogo") String tipoCatalogo) {
		 List<FilterValuesVO> listaComboArchivoVO = new ArrayList<FilterValuesVO>(); 
		  
	      if(tipoCatalogo.equals(ArchivoTipoAtributes.CAT_UT_VALUE)){
	    	  
	    	  listaComboArchivoVO = radarCatalogoService.getComboTipoBusquedaCatUT();
	      }else{
	      listaComboArchivoVO = radarCatalogoService.getComboTipoBusquedaCatCP();
	      }
		  return new ResponseEntity<List<FilterValuesVO>>(listaComboArchivoVO,HttpStatus.OK);
			
		}
	 
	 
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/comboValor", method = RequestMethod.GET)
		@PreAuthorize("hasAnyAuthority('GET_COMBO_TIPO_BUSQUEDA_VALOR')")
	public ResponseEntity<Map> getComboValor(@RequestParam(value = "tipoBusqueda") String tipoBusqueda) {
		 Map resultadoArchivo = new HashMap<>(); 
		 List<FilterValuesVO> listaComboArchivoVO = new ArrayList<FilterValuesVO>(); 
	     switch(tipoBusqueda){
	     case ArchivoTipoAtributes.CAT_FILTRO_TODOS:
	    	 resultadoArchivo.put("resultado", "all");
	    	 break;
	     case ArchivoTipoAtributes.CAT_FILTRO_UT:
	    	 resultadoArchivo.put("resultado", "ok");
	    	 break;
	     case ArchivoTipoAtributes.CAT_FILTRO_SECTOR:
	    	 resultadoArchivo.put("resultado", "");
	    	 listaComboArchivoVO = radarCatalogoService.getComboCatSectores();
	    	 resultadoArchivo.put("listaCombo",listaComboArchivoVO);
	    	 break;
	     case ArchivoTipoAtributes.CAT_FILTRO_DELEGACION:
	    	 resultadoArchivo.put("resultado", "");
	    	 listaComboArchivoVO = radarCatalogoService.getComboCatDelegaciones();
	    	 resultadoArchivo.put("listaCombo",listaComboArchivoVO);
	    	 break;
	     case ArchivoTipoAtributes.CAT_FILTRO_CP:
	    	 
	    	 resultadoArchivo.put("resultado", "ok");
	    	 break;
	     case ArchivoTipoAtributes.CAT_FILTRO_EDO:
	    	 resultadoArchivo.put("resultado", "");
	    	 listaComboArchivoVO = radarCatalogoService.getComboCatEstados();
	    	 resultadoArchivo.put("listaCombo",listaComboArchivoVO);
	    	 break;
	    	 
	     case ArchivoTipoAtributes.CAT_FILTRO_DELEGACION_ESTADO:
	    	 resultadoArchivo.put("resultado", "");
	    	 listaComboArchivoVO = radarCatalogoService.getComboCatDelegacionEstado();
	    	 resultadoArchivo.put("listaCombo",listaComboArchivoVO);
	    	 break;
	    	 
	     default:
               throw new IllegalArgumentException("Hubo un inconveniente al obtener resultados");
	     
	     
	     }
		 
	     return new ResponseEntity<Map>(resultadoArchivo, HttpStatus.OK);
			
		}
	
	
	 @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/consulta", method = RequestMethod.GET)
		@PreAuthorize("hasAnyAuthority('GET_CONSULTA')")
		public ResponseEntity<Map> consulta(@RequestParam(value = "catalogo") String catalogo, @RequestParam(value = "tipoBusqueda") String tipoBusqueda, @RequestParam(value = "valor") String valor,@RequestParam(value = "archivoTipoUT") String archivoTipoUT, @RequestParam(value = "tipoRadarUT") String tipoRadarUT, @RequestParam(value = "estatusUT") String estatusUT) {
		 Map resultadoConsulta = new HashMap<Object, Object>(); 
		if(catalogo.equals(ArchivoTipoAtributes.CAT_UT_VALUE)){
		 List<RadarCatConsultaUTVO> listaConsultaUTVO = radarCatalogoService.getConsultaUnidadTerritorial(tipoBusqueda,valor,new Long(archivoTipoUT),new Long(tipoRadarUT),new Long(estatusUT));
		 resultadoConsulta.put("result", listaConsultaUTVO);
		 resultadoConsulta.put("opcion", "ut");
		}else {
			List<RadarCatConsultaCPVO> listaConsultaCPVO = new ArrayList();
			if(tipoBusqueda.equals("Todos")){
				boolean result =  radarCatalogoService.comprobacionCPAll();
					if(result == true){
						listaConsultaCPVO = radarCatalogoService.consultaOpcionalAll();
						resultadoConsulta.put("result", listaConsultaCPVO);
						resultadoConsulta.put("opcion", "excede");
					}else{
						listaConsultaCPVO = radarCatalogoService.getConsultaCodigosPostales(tipoBusqueda,valor);
						 resultadoConsulta.put("result", listaConsultaCPVO);
						 resultadoConsulta.put("opcion", "cp");
					}
			}else{
					listaConsultaCPVO = radarCatalogoService.getConsultaCodigosPostales(tipoBusqueda,valor);
					 resultadoConsulta.put("result", listaConsultaCPVO);
					 resultadoConsulta.put("opcion", "cp");
				}
		}
		
		return new ResponseEntity<Map>(resultadoConsulta,HttpStatus.OK);
			
		}
	 
	 @RequestMapping(value = "/comboEstatusUT", method = RequestMethod.GET)
		@PreAuthorize("hasAnyAuthority('COMBO_ESTATUS_UT')")
		public ResponseEntity<List<FilterValuesVO>> comboEstatusUT() {
			  
		  return new ResponseEntity<List<FilterValuesVO>>(radarCatalogoService.getComboEstatus(),HttpStatus.OK);
			
		}
	 
	 // UT
	 @RequestMapping(value = "/comboEstatus", method = RequestMethod.GET)
		// @PreAuthorize("hasAnyAuthority('COMBO_ESTATUS_UT')")
		public ResponseEntity<List<FilterValuesVO>> comboEstatus() {
			  
		  return new ResponseEntity<List<FilterValuesVO>>(radarCatalogoService.getComboEstatusUT(),HttpStatus.OK);
			
		}
	 
	 @RequestMapping(value = "/comboDelegacionUT", method = RequestMethod.GET)
		@PreAuthorize("hasAnyAuthority('COMBO_DELEGACION_UT')")
		public ResponseEntity<List<FilterValuesVO>> comboDelegacionUT() {
			  
		  return new ResponseEntity<List<FilterValuesVO>>(radarCatalogoService.getComboCatDelegaciones(),HttpStatus.OK);
			
		}
	 
	 @RequestMapping(value = "/comboSectorUT", method = RequestMethod.GET)
		@PreAuthorize("hasAnyAuthority('COMBO_SECTOR_UT')")
		public ResponseEntity<List<FilterValuesVO>> comboSectorUT(@RequestParam(value = "idEstado") String idDelegacion) {
			  
		  return new ResponseEntity<List<FilterValuesVO>>(radarCatalogoService.getComboCatSectoresPorDelgac(idDelegacion),HttpStatus.OK);
			
		}
	 
	 @RequestMapping(value = "/comboZonasCP", method = RequestMethod.GET)
		@PreAuthorize("hasAnyAuthority('COMBO_ZONAS_CP')")
		public ResponseEntity<List<FilterValuesVO>> comboZonasCP() {
			  
		  return new ResponseEntity<List<FilterValuesVO>>(radarCatalogoService.getComboZonasCP(),HttpStatus.OK);
			
		}
	 @RequestMapping(value = "/comboEstadoUT", method = RequestMethod.GET)
	//	@PreAuthorize("hasAnyAuthority('COMBO_ESTADO_CP')")
		public ResponseEntity<List<FilterValuesVO>> comboEstadoUT() {
			  
		  return new ResponseEntity<List<FilterValuesVO>>(radarCatalogoService.getComboCatEstadosUT(),HttpStatus.OK);
			
		}
	 
	 @RequestMapping(value = "/comboEstadoCP", method = RequestMethod.GET)
		@PreAuthorize("hasAnyAuthority('COMBO_ESTADO_CP')")
		public ResponseEntity<List<FilterValuesVO>> comboEstadoCP() {
			  
		  return new ResponseEntity<List<FilterValuesVO>>(radarCatalogoService.getComboCatEstados(),HttpStatus.OK);
			
		}
	 
	 @RequestMapping(value = "/getMunicipio", method = RequestMethod.GET)
		@PreAuthorize("hasAnyAuthority('COMBO_MUNICIPIO_CP')")
		public ResponseEntity<List<FilterValuesVO>> getMunicipio(@RequestParam(value = "idEstado") String idEstado) {
			  
		  return new ResponseEntity<List<FilterValuesVO>>(radarCatalogoService.getComboCatDelegaciones(idEstado),HttpStatus.OK);
			
		}
	 
	 @RequestMapping(value = "/guardarNewUT", method = RequestMethod.GET)
		@PreAuthorize("hasAnyAuthority('GUARDAR_NEW_UT')")
		public ResponseEntity<RadarCatConsultaUTVO> guardarNewUT(@RequestParam(value="unidadTerritorialVO")String unidadTerritorialVO ) {
		 RadarCatConsultaUTVO convertVO = this.conversionAngularJava(unidadTerritorialVO);
		 radarCatalogoService.guardarNewUT(convertVO);
		 return new ResponseEntity<RadarCatConsultaUTVO>(convertVO,HttpStatus.OK);
			
		}
	 
	 @RequestMapping(value = "/guardarNewCP", method = RequestMethod.GET)
		@PreAuthorize("hasAnyAuthority('GUARDAR_NEW_CP')")
		public ResponseEntity<RadarCatConsultaCPVO> guardarNewCP(@RequestParam(value="centroRepartoVO")String centroRepartoVO ) {
		 RadarCatConsultaCPVO convertVO = this.conversionAngularJavaCP(centroRepartoVO);
		 radarCatalogoService.guardarNewCP(convertVO);
		 return new ResponseEntity<RadarCatConsultaCPVO>(convertVO,HttpStatus.OK);
			
		}
	 
	 @RequestMapping(value = "/buscarUnidadTerritorialPorUT", method = RequestMethod.GET)
		@PreAuthorize("hasAnyAuthority('BUSCAR_UNIDAD_TERRITORIAL_POR_UT')")
		public ResponseEntity<RadarCatConsultaUTVO> buscarUnidadTerritorialPorUT(@RequestParam(value="valorId")String valorId ) {
		
		 RadarCatConsultaUTVO consultaUTIDVO = radarCatalogoService.buscarUnidadTerritorialPorUT(valorId);
		  return new ResponseEntity<RadarCatConsultaUTVO>(consultaUTIDVO,HttpStatus.OK);
			
		}
	 
	 @RequestMapping(value = "/buscarUnidadTerritorialPorCP", method = RequestMethod.GET)
		@PreAuthorize("hasAnyAuthority('BUSCAR_UNIDAD_TERRITORIAL_POR_CP')")
		public ResponseEntity<RadarCatConsultaCPVO> buscarUnidadTerritorialPorCP(@RequestParam(value="valorId")String valorId ) {
		
		 RadarCatConsultaCPVO consultaCPIDVO = radarCatalogoService.buscarUnidadTerritorialPorCP(valorId);
		  return new ResponseEntity<RadarCatConsultaCPVO>(consultaCPIDVO,HttpStatus.OK);
			
		}
	 /*
	 @RequestMapping(value = "/guardarUTup", method = RequestMethod.GET)
		//@PreAuthorize("hasAnyAuthority('ComprobacionUt')")
		public Boolean comprobacionUT(@RequestParam(value="unidadTerritorialVO")String unidadTerritorialVO ) {
		 RadarCatConsultaUTVO convertVO = this.conversionAngularJava(unidadTerritorialVO);
		 boolean resp = radarCatalogoService.comprobacionUT(convertVO);
		 return resp;
		}
	 */
	 @RequestMapping(value = "/guardarUTup", method = RequestMethod.GET)
		//@PreAuthorize("hasAnyAuthority('ComprobacionUt')")
		public Integer comprobacionUT(@RequestParam(value="unidadTerritorialVO")String unidadTerritorialVO ) {
		 RadarCatConsultaUTVO convertVO = this.conversionAngularJava(unidadTerritorialVO);
		
		 
		int resp = radarCatalogoService.comprobacionUT(convertVO);
		
		 return resp;
		 
		}
	 
	 @RequestMapping(value = "/guardarUpdateUT", method = RequestMethod.GET)
		@PreAuthorize("hasAnyAuthority('GUARDAR_UPDATE_UT')")
		public ResponseEntity<RadarCatConsultaUTVO> guardarUpdateUT(@RequestParam(value="unidadTerritorialVO")String unidadTerritorialVO ) throws ParseException {
		 RadarCatConsultaUTVO convertVO = this.conversionAngularJava(unidadTerritorialVO);
		 radarCatalogoService.guardarUpdateUT(convertVO);
		 return new ResponseEntity<RadarCatConsultaUTVO>(convertVO,HttpStatus.OK);
			
		}
	 
	 
	 @RequestMapping(value = "/guardarUpdateCP", method = RequestMethod.GET)
		@PreAuthorize("hasAnyAuthority('GUARDAR_UPDATE_CP')")
		public ResponseEntity<RadarCatConsultaCPVO> guardarUpdateCP(@RequestParam(value="centroRepartoVO")String centroRepartoVO ) throws ParseException {
		 RadarCatConsultaCPVO convertVO = this.conversionAngularJavaCP(centroRepartoVO);
		 radarCatalogoService.guardarUpdateCP(convertVO);
		 return new ResponseEntity<RadarCatConsultaCPVO>(convertVO,HttpStatus.OK);
			
		}
	 
	 @RequestMapping(value = "/comboArchivoTipo", method = RequestMethod.GET)
		//@PreAuthorize("hasAnyAuthority('COMBO_ARCHIVO_TIPO')")
		public ResponseEntity<List<FilterValuesVO>> comboArchivoTipo() {
			  
		  return new ResponseEntity<List<FilterValuesVO>>(radarCatalogoService.getComboArchivoTipo(),HttpStatus.OK);
			
		}
	 
	 // UT Busqueda
	 @RequestMapping(value = "/comboArchivoTipoUTBusq", method = RequestMethod.GET)
		public ResponseEntity<List<FilterValuesVO>> comboArchivoTipoUTBusq() {
			  
		  return new ResponseEntity<List<FilterValuesVO>>(radarCatalogoService.getComboArchivoTipoUTBusq(),HttpStatus.OK);
			
	 }
	 
	 // UT
	 @RequestMapping(value = "/comboArchivoTipoUT", method = RequestMethod.GET)
		public ResponseEntity<List<FilterValuesVO>> comboArchivoTipoUT() {
			  
		  return new ResponseEntity<List<FilterValuesVO>>(radarCatalogoService.getComboArchivoTipoUT(),HttpStatus.OK);
			
	 }
	 
	 @RequestMapping(value = "/comboTipoRadar", method = RequestMethod.GET)
		//@PreAuthorize("hasAnyAuthority('COMBO_TIPO_RADAR')")
		public ResponseEntity<List<FilterValuesVO>> comboTipoRadar() {
			  
		  return new ResponseEntity<List<FilterValuesVO>>(radarCatalogoService.getComboTipoRadar(),HttpStatus.OK);
			
		}
	 
	 // UT Busqueda
	 @RequestMapping(value = "/comboTipoRadarUTBusq", method = RequestMethod.GET)
		//@PreAuthorize("hasAnyAuthority('COMBO_TIPO_RADAR')")
		public ResponseEntity<List<FilterValuesVO>> comboTipoRadarUTBusq() {
			  
		  return new ResponseEntity<List<FilterValuesVO>>(radarCatalogoService.getComboTipoRadarUTBusq(),HttpStatus.OK);
			
		}
	 
	 // UT
	 @RequestMapping(value = "/comboTipoRadarUT", method = RequestMethod.GET)
		//@PreAuthorize("hasAnyAuthority('COMBO_TIPO_RADAR')")
		public ResponseEntity<List<FilterValuesVO>> comboTipoRadarUT() {
			  
		  return new ResponseEntity<List<FilterValuesVO>>(radarCatalogoService.getComboTipoRadarUT(),HttpStatus.OK);
			
		}
	 
	 
		// Convertir angularVO a java VO
		private RadarCatConsultaUTVO conversionAngularJava (String jsonDocumentoVO){
			RadarCatConsultaUTVO newObject = null;
	        try {
	        	ObjectMapper mapper = new ObjectMapper();
	           	mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	           	newObject = mapper.readValue(jsonDocumentoVO.toString(), RadarCatConsultaUTVO.class);
	        } catch (IOException e) {
	        	e.printStackTrace();
	        }
	        return newObject;
		}
		
		private RadarCatConsultaCPVO conversionAngularJavaCP (String jsonDocumentoVO){
			RadarCatConsultaCPVO newObject = null;
	        try {
	        	ObjectMapper mapper = new ObjectMapper();
	           	mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	           	newObject = mapper.readValue(jsonDocumentoVO.toString(), RadarCatConsultaCPVO.class);
	        } catch (IOException e) {
	        	e.printStackTrace();
	        }
	        return newObject;
		}
	 
		@RequestMapping(value = "/cambiarEstatusUT", method = RequestMethod.GET)
		// @PreAuthorize("hasAnyAuthority('COMBO_SECTOR_UT')")
		public Boolean cambiarEstatus(@RequestParam(value = "accion") String accion, @RequestParam(value = "idUT") int idUT) {
			Boolean confirmacion = false;
			
			confirmacion = radarCatalogoService.cambiarEstatus(accion,idUT);
			
			
			return confirmacion;
		}
	 
}
