
package mx.com.teclo.saicdmx.api.rest.reportes;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import mx.com.teclo.saicdmx.negocio.service.estadistica.MapaService;
import mx.com.teclo.saicdmx.negocio.service.estadistica.TipoInfraccionService;
import mx.com.teclo.saicdmx.negocio.service.infracciones.InfraccionesEstadisticasService;
import mx.com.teclo.saicdmx.persistencia.vo.estadistica.TipoInfraccionesVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.DepositosEstadisticasVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.EntradaSalidaDepositosGraficaVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.InfraccionesEstadisticasVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.InfraccionesEstadisticasporDispositivoVO;
import mx.com.teclo.saicdmx.util.comun.ResponseConverter;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.NotFoundException;

@RestController
public class InfraccionReporteRestController {
	
	@Autowired
	private InfraccionesEstadisticasService infraccionesEstadisticasService;
	
	@Autowired
	private MapaService mapaService;
	
	@Autowired
	private TipoInfraccionService tipoInfraccionService;
	
//	@RequestMapping(value = "/infracciones/creadasPagadas", method = RequestMethod.GET)
//	@PreAuthorize("hasAnyAuthority('REPORTES_INFRACCIONES')")
//	public ResponseEntity<List<InfraccionesEstadisticasVO>> obieneInfraccCreadasPagadas()throws NotFoundException{
//		List<InfraccionesEstadisticasVO> listaReportes = infraccionesEstadisticasService.infraccionesEstadisticas();
//		if (listaReportes.isEmpty()) {
//			throw new NotFoundException("No se encontraron registros con los datos informados");
//		}
//		return new  ResponseEntity<List<InfraccionesEstadisticasVO>>(listaReportes, HttpStatus.OK);
//	}
//	GRAFICA DE INFRACCIONES CREADAS VS PAGADAS
	@RequestMapping(value = "/infracciones/creadasPagadas", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('ESTADISTICAS_INFRACCIONES_CREADAS_VS_PAGADAS')")
	public ResponseEntity<List<InfraccionesEstadisticasVO>>  infraccionesCreadasPagadas(@RequestParam(value = "filtroBusqueda" ) String jsonFiltroBusqueda,
			@RequestParam(value = "tipoClasificacion" ) String tipoClasificacion ) throws Exception, NotFoundException  {
	
		InfraccionesEstadisticasVO filtro = crearFiltroBusquedaReporte(jsonFiltroBusqueda);
		List<InfraccionesEstadisticasVO> listaReportes=null;
	    
		if(tipoClasificacion.equals("meses") && filtro.getFechaInicio().equals("")){
	    	listaReportes = infraccionesEstadisticasService.infraccionesCreadasPagadasEstatico();
	    }
	    if(tipoClasificacion.equals("meses") && !filtro.getFechaInicio().equals("")){
	    	listaReportes=infraccionesEstadisticasService.infraccionesEstadisticas(filtro.getFechaInicio(), filtro.getFechaFin());
	    }
		if(tipoClasificacion.equals("años")){
			listaReportes=infraccionesEstadisticasService.infraccionesEstadisticasAños(filtro.getFechaInicio(), filtro.getFechaFin());
		}
   
		if (listaReportes.isEmpty() || listaReportes==null) {
			throw new NotFoundException("No se encontraron registros");
		}
		return new ResponseEntity<List<InfraccionesEstadisticasVO>>(listaReportes, HttpStatus.OK);
	}

	private InfraccionesEstadisticasVO crearFiltroBusquedaReporte(String jsonEmbarqueVO) throws Exception {
	InfraccionesEstadisticasVO filtro = null;
        
              ObjectMapper mapper = new ObjectMapper();
                mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                filtro = mapper.readValue(jsonEmbarqueVO.toString(), InfraccionesEstadisticasVO.class);
                
         return filtro;
   }

	@RequestMapping(value = "/infracciones/infraccionesporDispositivo", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('ESTADISTICAS_INFRACCIONES_POR_DISPOSITIVO')")
	public ResponseEntity<List<InfraccionesEstadisticasporDispositivoVO>> obieneInfraccDispositivo(
			@RequestParam(value = "fechaInicio") String fechaInicio,
			@RequestParam(value = "fechaFin") String fechaFin)throws NotFoundException, ParseException{
		
		List<InfraccionesEstadisticasporDispositivoVO> listaReportes = new ArrayList<InfraccionesEstadisticasporDispositivoVO>();
		
		if(fechaInicio.equals("") || fechaFin.equals("")){
			listaReportes = infraccionesEstadisticasService.infraccionesporDispositivoEstatico();
		}else{
			listaReportes = infraccionesEstadisticasService.infraccionesporDispositivo(fechaInicio, fechaFin);
		}
		
		if (listaReportes.isEmpty()) {
			throw new NotFoundException("No se encontraron registros");
		}
        return new  ResponseEntity<List<InfraccionesEstadisticasporDispositivoVO>>(listaReportes, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/infracciones/depositosEstadisticas", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('ESTADISTICAS_INFRACCIONES_POR_DEPOSITO')")
	public ResponseEntity<List<DepositosEstadisticasVO>> obtieneDepositosEstadisticas()throws NotFoundException{
		
		List<DepositosEstadisticasVO> listaReportesDepositos = infraccionesEstadisticasService.depositosEstadisticas();
		if (listaReportesDepositos.isEmpty()) {
			throw new NotFoundException("No se encontraron registros");
		}
		return new  ResponseEntity<List<DepositosEstadisticasVO>>(listaReportesDepositos, HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value = "/infracciones/entradaSalidaDepositosGrafica", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('ESTADISTICAS_ENTRADAS_SALIDAS_DEPOSITOS')")
	public ResponseEntity<List<EntradaSalidaDepositosGraficaVO>> obtieneEntradaSalidaDepoitosGrafica(
			@RequestParam(value = "fechaInicio") String fechaInicio,
			@RequestParam(value = "fechaFin") String fechaFin)throws NotFoundException{
		
		List<EntradaSalidaDepositosGraficaVO> listaReportesEntradaSalidaDepositos = infraccionesEstadisticasService.entradaSalidaDepositosGrafica(fechaInicio, fechaFin);
		if (listaReportesEntradaSalidaDepositos.isEmpty()) {
			throw new NotFoundException("No se encontraron registros");
		}
		return new  ResponseEntity<List<EntradaSalidaDepositosGraficaVO>>(listaReportesEntradaSalidaDepositos, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/infracciones/entradaSalidaDepositosGraficaFechas", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('ESTADISTICAS_ENTRADAS_SALIDAS_DEPOS_FECHAS')")
	public ResponseEntity<List<EntradaSalidaDepositosGraficaVO>> obtieneEntradaSalidaDepoitosGraficaFechas(
			@RequestParam(value = "fechaInicio") String fechaInicio,
			@RequestParam(value = "fechaFin") String fechaFin)throws Exception, NotFoundException {
		
		List<EntradaSalidaDepositosGraficaVO> listaReportesEntradaSalidaDepositos = infraccionesEstadisticasService.entradaSalidaDepositosGraficaFechas(fechaInicio, fechaFin);
		if (listaReportesEntradaSalidaDepositos.isEmpty()) {
			throw new NotFoundException("No se encontraron registros");
		}
		return new  ResponseEntity<List<EntradaSalidaDepositosGraficaVO>>(listaReportesEntradaSalidaDepositos, HttpStatus.OK);
		
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/infraccionesmapa", method = RequestMethod.GET)
	public ResponseEntity<Map> actualizarCartoTipoFecha(
			@RequestParam(value = "radares[]") String[] paramValues,
			@RequestParam(value = "fechaInicio") String fechaInicio,
			@RequestParam(value = "fechaFin") String fechaFin){
		Map lVO = mapaService.tipoInfraccionPorFecha(paramValues, fechaInicio, fechaFin);
		if(lVO == null)
			 return new ResponseEntity<Map>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Map>(lVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/tipoInfracciones", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.GET)
	public ResponseEntity<List<TipoInfraccionesVO>> tiposInfracciones(){
		
		List<TipoInfraccionesVO> lVO = ResponseConverter.converterLista(new ArrayList<>(), 
				tipoInfraccionService.tipoInfracciones(), TipoInfraccionesVO.class);
		if(lVO == null)
			 return new ResponseEntity<List<TipoInfraccionesVO>>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<TipoInfraccionesVO>>(lVO, HttpStatus.OK);
	}
}
