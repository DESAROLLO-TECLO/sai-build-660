package mx.com.teclo.saicdmx.api.rest.salidas;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import mx.com.teclo.saicdmx.negocio.service.pagos.DigitalizacionService;
import mx.com.teclo.saicdmx.negocio.service.salidas.SalidaVehiculoService;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.pagos.ExpedienteDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.salidas.ImgSalidasDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.salidas.SalidasDTO;
import mx.com.teclo.saicdmx.persistencia.vo.comuns.FilterValuesVO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.ImagenExpedienteVO;
import mx.com.teclo.saicdmx.persistencia.vo.salidas.ConsultaTrasladoVO;
import mx.com.teclo.saicdmx.persistencia.vo.salidas.ConsultaVehiculoSalidaVO;
import mx.com.teclo.saicdmx.persistencia.vo.salidas.EvidenciasVO;
import mx.com.teclo.saicdmx.persistencia.vo.salidas.FilesSalidaVO;
import mx.com.teclo.saicdmx.persistencia.vo.salidas.GuardarSalidaVO;
import mx.com.teclo.saicdmx.persistencia.vo.salidas.GuardarTrasladoVO;
import mx.com.teclo.saicdmx.persistencia.vo.salidas.InfoPlacaEmpVO;
import mx.com.teclo.saicdmx.persistencia.vo.salidas.SalidaVehiculoBusqVO;
import mx.com.teclo.saicdmx.persistencia.vo.salidas.ValidarInfraccionVO;
import mx.com.teclo.saicdmx.persistencia.vo.salidas.busquedaCatSalidaVO;
import mx.com.teclo.saicdmx.persistencia.vo.salidas.resultCatVO;
import mx.com.teclo.saicdmx.util.enumerados.SalidasEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.ControllerException;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.NotFoundException;

@RestController
public class SalidaVehiculoRestController {
	
	
	@Autowired
	private SalidaVehiculoService salidaVehiculoService;
	
	@Autowired
	DigitalizacionService digitalizacionService;
	
	
	
	
	/* INICIA BUSQUEDA NUEVO VEHICULOS EN SALIDA 
	 * ***********************************
	 * ***********************************
	 * */
	
	@RequestMapping(value = "/validaUserPerfilSalida", method = RequestMethod.GET)
	public @ResponseBody boolean  verificarPerfilCajero() throws NotFoundException {
		boolean results = salidaVehiculoService.buscarUserValido();
		if(!results){
			throw new NotFoundException("¡Cuidado! No Puedes realizar esta operación, verifica tu perfil");
		}
		return results;
	}
	
	@RequestMapping(value = "/filtroBusqGral", method = RequestMethod.GET)
	public ResponseEntity<List<FilterValuesVO>> filtroBusqGral() throws NotFoundException {
		List<FilterValuesVO> filterValues = salidaVehiculoService.filterTipoBusq();
		if(filterValues.isEmpty()){
			throw new NotFoundException("Hubo un inconveniente al obtener resultados");
		}
		return new ResponseEntity<List<FilterValuesVO>>(filterValues, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/salidaVehiculoBusqueda", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('SALIDA_VEHICULO_BUSQUEDA')")
	public ResponseEntity<List<SalidaVehiculoBusqVO>> salidaVehiculoBusqueda(
		@RequestParam(value = "parametroBusq" ) String tipoBusqueda, @RequestParam(value = "valorBusq") String valorBusq) throws NotFoundException {
		List<SalidaVehiculoBusqVO> infracciones = salidaVehiculoService.getConsultaSalidasVehiculosBean(tipoBusqueda, valorBusq);
		if(infracciones.isEmpty()){
			throw new NotFoundException("No se encontraron registros");
		}
		return new ResponseEntity<List<SalidaVehiculoBusqVO>>(infracciones, HttpStatus.OK);
	}
	
	/* FIN BUSQUEDA NUEVO VEHICULOS EN SALIDA 
	 * ***********************************
	 * ***********************************
	 * */
	
	/* INICIA ALTA NUEVO VEHICULOS EN SALIDA 
	 * ***********************************
	 * ***********************************
	 * */
	
	@RequestMapping(value = "/busquedaInfoInfrac", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSQUEDA_INFO_INFRAC')")
	public ResponseEntity<SalidaVehiculoBusqVO> busquedaInfoInfrac(@RequestParam(value = "numInfrac" ) String numInfrac) throws NotFoundException {
		SalidaVehiculoBusqVO infoInfrac = salidaVehiculoService.infoInfraccion(numInfrac);
		if(infoInfrac == null){
			throw new NotFoundException("Hubo un inconveniente al obtener resultados");
		}
		return new ResponseEntity<SalidaVehiculoBusqVO>(infoInfrac, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/filtroTipoSalida", method = RequestMethod.GET)
	public ResponseEntity<List<FilterValuesVO>> filterTipoSalida() throws NotFoundException {
		List<FilterValuesVO> filterValues = salidaVehiculoService.filterTipoSalida();
		if(filterValues.isEmpty()){
			throw new NotFoundException("Hubo un inconveniente al obtener resultados");
		}
		return new ResponseEntity<List<FilterValuesVO>>(filterValues, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/comboAdjunDestino", method = RequestMethod.GET)
	public ResponseEntity<List<FilterValuesVO>> comboAdjunDestino() throws NotFoundException {
		List<FilterValuesVO> filterValues = salidaVehiculoService.comboAdjunDestino();
		if(filterValues.isEmpty()){
			throw new NotFoundException("Hubo un inconveniente al obtener resultados");
		}
		return new ResponseEntity<List<FilterValuesVO>>(filterValues, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/comboFaseCompacta", method = RequestMethod.GET)
	public ResponseEntity<List<FilterValuesVO>> comboFaseCompacta() throws NotFoundException {
		List<FilterValuesVO> filterValues = salidaVehiculoService.comboFaseCompacta();
		if(filterValues.isEmpty()){
			throw new NotFoundException("Hubo un inconveniente al obtener resultados");
		}
		return new ResponseEntity<List<FilterValuesVO>>(filterValues, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/validaPlaca", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('VALIDA_INFRACCION_PAGADA')")
	public ResponseEntity<ValidarInfraccionVO> validaPlaca(@RequestParam(value = "infracNum" ) String infracNum) throws NotFoundException {
		ValidarInfraccionVO filterValues = salidaVehiculoService.validaPlaca(infracNum);
		if(filterValues.equals(null)){
			throw new NotFoundException("No existe la infracción");
		}
		return new ResponseEntity<ValidarInfraccionVO>(filterValues, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/comboDepDestino", method = RequestMethod.GET)
	public ResponseEntity<List<FilterValuesVO>> comboDepDestino(@RequestParam(value = "depOrigen" ) String depOrigen) throws NotFoundException {
		List<FilterValuesVO> filterValues = salidaVehiculoService.comboDepDestino(depOrigen);
		if(filterValues.isEmpty()){
			throw new NotFoundException("Hubo un inconveniente al obtener resultados");
		}
		return new ResponseEntity<List<FilterValuesVO>>(filterValues, HttpStatus.OK);
	}

	
	@RequestMapping(value = "/validaPlacaOficial", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSCAR_EMPLEADO_POR_PLACA')")
	public ResponseEntity<InfoPlacaEmpVO> validaPlacaOficial(@RequestParam(value = "placaOficial" ) String placaOficial) throws NotFoundException {
		InfoPlacaEmpVO filterValues = salidaVehiculoService.validarPlacaOficial(placaOficial);
		if(filterValues == null){
			throw new NotFoundException("No se encontró la placa");
		}
		return new ResponseEntity<InfoPlacaEmpVO>(filterValues, HttpStatus.OK);
	}
	
	

	@RequestMapping(value = "/guardarSalidaVehiculo", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('GUARDAR_SALIDA_VEHICULO')")
	public  ResponseEntity<GuardarSalidaVO>  guardarRegistro (@RequestParam(value = "parametroVO")String parametroVO ,
			@RequestParam(value = "files") String files, @RequestParam(value = "tmno")int tmno ) throws NotFoundException,ParseException{
		GuardarSalidaVO convertVO = this.conversionAngularJavaCP(parametroVO);
		
		
		GuardarSalidaVO original = salidaVehiculoService.getOriginalIngreso(convertVO.getNuminfrac());
		salidaVehiculoService.guardarSalida(convertVO);
		Long idMovimiento = salidaVehiculoService.getIdMovVeh(convertVO.getNuminfrac());
		
		if(convertVO.getMensaje() == null){
			throw new NotFoundException("Movimiento no registrada correctamente");
		}else {
			convertVO.setMovEstatus("E");
			salidaVehiculoService.insertIntoBitac( original,convertVO);
		}
		
		if(tmno > 0){
			List<FilesSalidaVO> filesVO = this.conversionAngularJavaFiles(files);
			salidaVehiculoService.insertToImgSalida(filesVO,convertVO, idMovimiento);
		}

		return new ResponseEntity<GuardarSalidaVO>(convertVO,HttpStatus.OK);
	}
	

	private GuardarSalidaVO conversionAngularJavaCP (String jsonDocumentoVO){
		GuardarSalidaVO newObject = null;
        try {
        	ObjectMapper mapper = new ObjectMapper();
           	mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
           	newObject = mapper.readValue(jsonDocumentoVO.toString(), GuardarSalidaVO.class);
        } catch (IOException e) {
        	e.printStackTrace();
        }
        return newObject;
	}
	private List<FilesSalidaVO> conversionAngularJavaFiles (String files){
		List<FilesSalidaVO> newObject = null;
        try {
        	ObjectMapper mapper = new ObjectMapper();
           	mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
           	newObject = mapper.readValue(files.toString(),  new TypeReference<List<FilesSalidaVO>>(){});
        } catch (IOException e) {
        	e.printStackTrace();
        }
        return newObject;
	}

	
	
	/* FINALIZA ALTA NUEVO VEHICULOS EN SALIDA 
	 * ***********************************
	 * ***********************************
	 * */
	
	
	/* INICIA CONSULTA VEHICULOS EN SALIDA 
	 * ***********************************
	 * ***********************************
	 * */
	
	@RequestMapping(value = "/filtroComboTipoBusqueda", method = RequestMethod.GET)
	public  ResponseEntity<List<FilterValuesVO>>  filtroComboTipoBusq () throws NotFoundException{
		List<FilterValuesVO> filterComboTipo = salidaVehiculoService.filterComboTipoBusq();
		if(filterComboTipo.isEmpty()){
			throw new NotFoundException("Hubo un inconveniente al obtener resultados");
		}
		 return new ResponseEntity<List<FilterValuesVO>>(filterComboTipo,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/filtroComboTipoOrden", method = RequestMethod.GET)
	public  ResponseEntity<List<FilterValuesVO>>  filtroComboTipoOrden () throws NotFoundException{
		List<FilterValuesVO> filtroComboTipoOrden = salidaVehiculoService.filtroComboTipoOrden();
		if(filtroComboTipoOrden.isEmpty()){
			throw new NotFoundException("No se encontraron registros");
		}
		 return new ResponseEntity<List<FilterValuesVO>>(filtroComboTipoOrden,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/filtroComboTipoSalida", method = RequestMethod.GET)
	public  ResponseEntity<List<FilterValuesVO>>  filtroComboTipoSalida () throws NotFoundException{
		List<FilterValuesVO> filtroComboTipoSalida = salidaVehiculoService.filtroComboTipoSalida();
		if(filtroComboTipoSalida.isEmpty()){
			throw new NotFoundException("No se encontraron registros");
		}
		 return new ResponseEntity<List<FilterValuesVO>>(filtroComboTipoSalida,HttpStatus.OK);
	}

	@RequestMapping(value = "/buscarSalidaVehiculo", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('CONSULTA_SALIDA_VEHICULO')")
	public  ResponseEntity<List<ConsultaVehiculoSalidaVO>>  buscarVehiculosSalida (@RequestParam(value = "tipoBusq" ) String tipoBusq,
			@RequestParam(value = "valorCombo" ) String valorCombo,
			@RequestParam(value = "tipoBusqSalida" ) String tipoBusqSalida,@RequestParam(value = "fechaInicio" ) String fechaInicio,
			@RequestParam(value = "fechaFin" ) String fechaFin,
			@RequestParam(value = "valorBusqueda") String tipoBusqEspecific) throws NotFoundException{
		List<ConsultaVehiculoSalidaVO> resultsBusquedaVehiculos = salidaVehiculoService.busquedaVehiculoConsulta(tipoBusq,valorCombo, tipoBusqSalida, fechaInicio,fechaFin,  tipoBusqEspecific );
		if(resultsBusquedaVehiculos.isEmpty()){
			throw new NotFoundException("No se encontraron registros");
		}
		 return new ResponseEntity<List<ConsultaVehiculoSalidaVO>>(resultsBusquedaVehiculos,HttpStatus.OK);
	}

	@Transactional(readOnly = true)
	@RequestMapping(value="/mostrarEvidencia", method=RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('MOSTRAR_EVIDENCIA_VEH_SALIDAS')")
	public ResponseEntity<List<EvidenciasVO>> mostrarTodoExpediente(@RequestParam(value = "idSalidas") String idSalidas, @RequestParam(value = "tipo") String tipo,
			@RequestParam(value= "numInfrac")String numInfrac) throws ControllerException, NotFoundException
	{	
		
		List<EvidenciasVO> listaImagenes = new ArrayList<EvidenciasVO>();
		String nombreImgComp = numInfrac+"_"+tipo+"_"+idSalidas+".jpg";
		List<ImgSalidasDTO> expedienteDTO = salidaVehiculoService.getExpediente(numInfrac, tipo,nombreImgComp);
		
		if (!expedienteDTO.isEmpty() && expedienteDTO.size() != 0) {
			for(ImgSalidasDTO expVO : expedienteDTO){
				EvidenciasVO imagenEVO = new EvidenciasVO();
				imagenEVO.setImg(blobToArrayBytes(expVO.getArchivo()));
				if(imagenEVO.getImg() != null && imagenEVO.getImg().length > 0)
				{
					imagenEVO.setExiste(true);
				}else{
					String urlPath = "http://imagenes.infracciones.df.gob.mx/";
					String ruta = salidaVehiculoService.obtenerRutaLocalMovimiento(numInfrac, expVO.getTipo(),idSalidas );
					imagenEVO.setLocalPath(urlPath+ruta);
					
				}
			
				listaImagenes.add(imagenEVO);
			}
		}else{
			throw new NotFoundException("No se encontró expediente por mostrar");
		}
		
		return new ResponseEntity<List<EvidenciasVO>>(listaImagenes, HttpStatus.OK);
	}
	
	@SuppressWarnings("unused")
	@Transactional(readOnly = true)
	@RequestMapping(value="/descargaEvidencia", method=RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('DESCARGAR_EXPEDIENTE_VEH_SALIDAS')")
	public ResponseEntity<List<EvidenciasVO>> bajarExpediente(@RequestParam(value = "idSalidas") String idSalidas, @RequestParam(value = "tipo") String tipo,
			@RequestParam(value = "numInfrac") String numInfrac) throws ControllerException, IOException, NotFoundException
	{	
		List<EvidenciasVO> listaImagenes = new ArrayList<EvidenciasVO>();
		String nombreImgComp = numInfrac+"_"+tipo+"_"+idSalidas+".jpg";
		List<ImgSalidasDTO> expedienteDTO = salidaVehiculoService.getExpediente(numInfrac, tipo, nombreImgComp);
		
		HttpHeaders headers = new  HttpHeaders();
		String filename = null;
		
		if (!expedienteDTO.isEmpty() && expedienteDTO.size() != 0) {
			for(ImgSalidasDTO expVO : expedienteDTO){
				EvidenciasVO imagenEVO = new EvidenciasVO();
				
				imagenEVO.setImg(blobToArrayBytes(expVO.getArchivo()));
				if(imagenEVO.getImg() != null && imagenEVO.getImg().length > 0)
				{
					imagenEVO.setExiste(true);
				}else{
					String urlPath = "http://imagenes.infracciones.df.gob.mx/";
					String ruta = salidaVehiculoService.obtenerRutaLocalMovimiento(numInfrac, expVO.getTipo(),idSalidas );
					imagenEVO.setLocalPath(urlPath+ruta);
				}			
				listaImagenes.add(imagenEVO);
			}
		}else{
			throw new NotFoundException("El expediente no tiene imagenes por mostrar");
		}
		
		filename = idSalidas;
		headers.setContentType(MediaType.parseMediaType("application/jpg"));
		headers.add("Content-Disposition", "attachmnt; filename ="+filename);
		headers.add("filename",   filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		
		if(listaImagenes == null){
			throw new NotFoundException("El expediente no tiene imagenes por mostrar");
		}		
		return new ResponseEntity<List<EvidenciasVO>>(listaImagenes,headers,HttpStatus.OK );
	
	}

	private static byte[] blobToArrayBytes(Blob blob){
		byte[] data=null;
		if(blob!=null){
			try {
				data = blob.getBytes(1, (int)blob.length());
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}		     
		return data;
	}
	/* TERMINA CONSULTA VEHICULOS EN SALIDA 
	 * ***********************************
	 * ***********************************
	 * */
	
	
	/*INICIA CATALOGO SALIDAS*/

	@RequestMapping(value = "/filtroComboCatSalidas", method = RequestMethod.GET)
	public  ResponseEntity<List<FilterValuesVO>>  filtroComboCatSalidas () throws NotFoundException{
		List<FilterValuesVO> filterComboTipo = salidaVehiculoService.filterComboCatSalidas();
		if(filterComboTipo.isEmpty()){
			throw new NotFoundException("Hubo un inconveniente al obtener resultados");
		}
		 return new ResponseEntity<List<FilterValuesVO>>(filterComboTipo,HttpStatus.OK);
	
	
	}
	
	@RequestMapping(value = "/filtroComboTypeSearchCompacta", method = RequestMethod.GET)
	public  ResponseEntity<List<FilterValuesVO>>  filtroComboCatCompactacion () throws NotFoundException{
		List<FilterValuesVO> filterComboTipo = salidaVehiculoService.filtroComboCatCompactacion();
		if(filterComboTipo.isEmpty()){
			throw new NotFoundException("Hubo un inconveniente al obtener resultados");
		}
		 return new ResponseEntity<List<FilterValuesVO>>(filterComboTipo,HttpStatus.OK);
	
	
	}
	@RequestMapping(value = "/filtroComboTypeSearchAdjudica", method = RequestMethod.GET)
	public  ResponseEntity<List<FilterValuesVO>>  filtroComboCatAdjudicacion () throws NotFoundException{
		List<FilterValuesVO> filterComboTipo = salidaVehiculoService.filtroComboCatAdjudicacion();
		if(filterComboTipo.isEmpty()){
			throw new NotFoundException("Hubo un inconveniente al obtener resultados");
		}
		 return new ResponseEntity<List<FilterValuesVO>>(filterComboTipo,HttpStatus.OK);
	
	}
	
	@RequestMapping(value = "/busquedaCatSalidas", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSQUEDA_CAT_SALIDAS')")
	public  ResponseEntity<List<resultCatVO>>  busquedaCatSalidas (@RequestParam(value = "catVO")String catVO ) throws NotFoundException{
		busquedaCatSalidaVO convertVO = this.conversionToBusquedaCat(catVO);
		List<resultCatVO> listCat = salidaVehiculoService.getListCatSalidas(convertVO);
		if(listCat.isEmpty()){
			throw new NotFoundException("No se encontraron registros");
		}
		return new ResponseEntity<List<resultCatVO>>(listCat,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getCatByIdCat", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('GET_CAT_BY_IDCAT')")
	public  ResponseEntity<resultCatVO>  getCatByIdCat (@RequestParam(value = "idCat")Long idCat, @RequestParam(value = "tipoCat")Long tipoCat ) throws NotFoundException{
		resultCatVO listCat = salidaVehiculoService.getListCatSalidasByIdCat(idCat, tipoCat);
		if(listCat == null){
			throw new NotFoundException("No se encontraron registros");
		}
		return new ResponseEntity<resultCatVO>(listCat,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/filtroComboActiveInactive", method = RequestMethod.GET)
	public  ResponseEntity<List<FilterValuesVO>>  filtroComboActiveInactive () throws NotFoundException{
		List<FilterValuesVO> filterComboTipo = salidaVehiculoService.filtroComboActiveInactive();
		if(filterComboTipo.isEmpty()){
			throw new NotFoundException("No se encontraron registros");
		}
		 return new ResponseEntity<List<FilterValuesVO>>(filterComboTipo,HttpStatus.OK);
	
	}
	
	@RequestMapping(value = "/updateCatSalidas", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('UPDATE_CAT_SALIDAS')")
	public  ResponseEntity<Boolean>  updateCatSalidas (@RequestParam(value = "catVO")String catVO, @RequestParam(value = "tipoCat") Long tipoCat ) throws NotFoundException{
		resultCatVO convertVO = this.conversionToUpdateCat(catVO);
		boolean a = salidaVehiculoService.getResultsUpdate(convertVO, tipoCat);
		if(!a){
			throw new NotFoundException("Error al actualizar el registro");
		}
		return new ResponseEntity<Boolean>(a,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/insertCatSalidas", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('INSERT_CAT_SALIDAS')")
	public  ResponseEntity<Boolean>  insertCatSalidas (@RequestParam(value = "catVO")String catVO, @RequestParam(value = "tipoCat") Long tipoCat ) throws NotFoundException{
		resultCatVO convertVO = this.conversionToUpdateCat(catVO);
		boolean a = salidaVehiculoService.getResultsInsertSalida(convertVO, tipoCat);
		if(!a){
			throw new NotFoundException("Error al actualizar el registro");
		}
		return new ResponseEntity<Boolean>(a,HttpStatus.OK);
	}
	
	private busquedaCatSalidaVO conversionToBusquedaCat (String jsonDocumentoVO){
		busquedaCatSalidaVO newObject = null;
        try {
        	ObjectMapper mapper = new ObjectMapper();
           	mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
           	newObject = mapper.readValue(jsonDocumentoVO.toString(), busquedaCatSalidaVO.class);
        } catch (IOException e) {
        	e.printStackTrace();
        }
        return newObject;
	}
	
	private resultCatVO conversionToUpdateCat (String jsonDocumentoVO){
		resultCatVO newObject = null;
        try {
        	ObjectMapper mapper = new ObjectMapper();
           	mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
           	newObject = mapper.readValue(jsonDocumentoVO.toString(), resultCatVO.class);
        } catch (IOException e) {
        	e.printStackTrace();
        }
        return newObject;
	}
	
}
	
	

