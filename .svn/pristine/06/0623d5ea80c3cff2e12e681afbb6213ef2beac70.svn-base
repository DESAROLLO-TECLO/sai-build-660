package mx.com.teclo.saicdmx.api.rest.admireporte;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;
import mx.com.teclo.saicdmx.negocio.service.reportes.AdmiReporteService;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.reportes.ComponentesDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.reportes.FormatoDescargaDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.reportes.PropiedadesDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.reportes.TipoParametroDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.reportes.TipoReportesDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.reportes.TipoTitulosDTO;
import mx.com.teclo.saicdmx.persistencia.vo.admireporte.ComponenteVO;
import mx.com.teclo.saicdmx.persistencia.vo.admireporte.ConfigParamVO;
import mx.com.teclo.saicdmx.persistencia.vo.admireporte.FormatoDescargaVO;
import mx.com.teclo.saicdmx.persistencia.vo.admireporte.PropiedadesVO;
import mx.com.teclo.saicdmx.persistencia.vo.admireporte.ReporteVO;
import mx.com.teclo.saicdmx.persistencia.vo.admireporte.TipoParametroVO;
import mx.com.teclo.saicdmx.persistencia.vo.admireporte.TipoReporteVO;
import mx.com.teclo.saicdmx.persistencia.vo.admireporte.TipoTituloVO;
import mx.com.teclo.saicdmx.util.comun.ResponseConverter;

@RestController
@RequestMapping("/adminReporteController")
public class AdminReporteRestController {

	@Autowired
	private AdmiReporteService admiReporteService;

	@RequestMapping(value = "/listaTipoReportes", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSQUEDAD_TIPO_REPORTE')")
	public ResponseEntity<List<TipoReporteVO>> getListaTipoReporte() throws NotFoundException {

		List<TipoReportesDTO> listaDTO = admiReporteService.obtenerListaTipoReporte();
		List<TipoReporteVO> listaTipoReportes = ResponseConverter.converterLista(new ArrayList<>(), listaDTO,
				TipoReporteVO.class);
		return new ResponseEntity<List<TipoReporteVO>>(listaTipoReportes, HttpStatus.OK);
	}

	@RequestMapping(value = "/listaFormatoDescarga", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSQUEDAD_TIPO_REPORTE')")
	public ResponseEntity<List<FormatoDescargaVO>> getListaFormatoDescarga() throws NotFoundException {
		List<FormatoDescargaDTO> listaFormato = admiReporteService.obtenerListaFormatoDescarga();
		List<FormatoDescargaVO> listaFormatoVO = ResponseConverter.converterLista(new ArrayList<>(), listaFormato,
				FormatoDescargaVO.class);

		return new ResponseEntity<List<FormatoDescargaVO>>(listaFormatoVO, HttpStatus.OK);
	}

	@RequestMapping(value = "/listaComponentes", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSQUEDAD_TIPO_REPORTE')")
	public ResponseEntity<List<ComponenteVO>> getListaCompoenentes() throws NotFoundException {

		List<ComponentesDTO> listComponentesDTO = admiReporteService.obtenerListaComponentes();
		List<ComponenteVO> listaComponentesVO = ResponseConverter.converterLista(new ArrayList<>(), listComponentesDTO,
				ComponenteVO.class);
		return new ResponseEntity<List<ComponenteVO>>(listaComponentesVO, HttpStatus.OK);
	}

	@RequestMapping(value = "/listaParametros", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSQUEDAD_TIPO_REPORTE')")
	public ResponseEntity<List<TipoParametroVO>> getListaParametros() throws NotFoundException {
		List<TipoParametroDTO> listParametroDTO = admiReporteService.obtenerListaParametros();
		List<TipoParametroVO> listParametroVO = ResponseConverter.converterLista(new ArrayList<>(), listParametroDTO,
				TipoParametroVO.class);
		return new ResponseEntity<List<TipoParametroVO>>(listParametroVO, HttpStatus.OK);
	}

	@RequestMapping(value = "/listaPropiedades", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSQUEDAD_TIPO_REPORTE')")
	public ResponseEntity<List<PropiedadesVO>> getListaPropiedades() throws NotFoundException {
		List<PropiedadesDTO> listPropiedadesDTO = admiReporteService.obtenerPropiedades();
		List<PropiedadesVO> listPropiedadesVO = ResponseConverter.converterLista(new ArrayList<>(), listPropiedadesDTO,
				PropiedadesVO.class);
		return new ResponseEntity<List<PropiedadesVO>>(listPropiedadesVO, HttpStatus.OK);
	}
	
	@RequestMapping(value="/listaTipoTitulo", method= RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSQUEDAD_TIPO_REPORTE')")
	public ResponseEntity<List<TipoTituloVO>> getListaTipoTitulo() throws NotFoundException{
		List<TipoTitulosDTO> listTipoTituloDTO = admiReporteService.obtenerTipoTitulo();
		List<TipoTituloVO> listTipoTituloVO = ResponseConverter.converterLista(new ArrayList<>(), listTipoTituloDTO, TipoTituloVO.class);
		return new ResponseEntity<List<TipoTituloVO>>(listTipoTituloVO, HttpStatus.OK);
	}
	
	@RequestMapping(value="/validarReporte", method = RequestMethod.GET)
	public ResponseEntity<List<ConfigParamVO>> guardarReporte(@RequestParam(value = "cadena") String cadena)throws NotFoundException{
			
		return new ResponseEntity<List<ConfigParamVO>>(admiReporteService.identificacionParametro(cadena), HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/creaQuery", method = RequestMethod.GET, produces = "text/plain")
	public ResponseEntity<String> queryValue(@RequestParam(value = "arrayParamValue") String jsonn,
											 @RequestParam(value="cadena")String cadena) throws NotFoundException{
		String message = admiReporteService.formarQuery("[" + jsonn + "]", cadena);
		
		if(message.equals("error")){
			throw new NotFoundException(
					"query incorrecto");
		}
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	
	@RequestMapping(value="/guardarReporteBd", method= RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('NUEVO_REPORTE')")
	public ResponseEntity<ReporteVO> guardarReporteBb(@Valid @RequestBody ReporteVO reporteVO) throws NotFoundException{
		admiReporteService.saveReporte(reporteVO);
		return new ResponseEntity<ReporteVO>(reporteVO, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/guardarConfigParam", method=RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('CONFIG_PARAMETROS')")
	public ResponseEntity<ConfigParamVO> guardarConfigParam(@Valid @RequestBody ConfigParamVO configParamVO) throws NotFoundException{
		admiReporteService.saveConfigparam(configParamVO);
		return new ResponseEntity<ConfigParamVO>(configParamVO, HttpStatus.CREATED);
	}
	

}
