package mx.com.teclo.saicdmx.api.rest.impugnacion;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.negocio.service.empleado.EmpleadoService;
import mx.com.teclo.saicdmx.negocio.service.impugnacion.ImpugnacionService;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.empleados.EmpleadosDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.infracciones.InfraccionImpugnacionDTO;
import mx.com.teclo.saicdmx.persistencia.vo.impugnacion.ImpugnacionAltaVO;
import mx.com.teclo.saicdmx.persistencia.vo.impugnacion.ImpugnacionParametrosVO;
import mx.com.teclo.saicdmx.persistencia.vo.impugnacion.ImpugnacionVO;
import mx.com.teclo.saicdmx.persistencia.vo.impugnacion.VConsulaCanceladasDetalleVO;
import mx.com.teclo.saicdmx.persistencia.vo.impugnacion.VConsultaCanceladas;
import mx.com.teclo.saicdmx.persistencia.vo.impugnacion.VImpugnacionConsultaVO;
import mx.com.teclo.saicdmx.util.comun.ResponseConverter;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.UsuarioFirmadoVO;


/**
 * 
 * @author javier07
 *
 */
@RestController
public class ImpugnacionRestController {
	
	@Autowired
	private ImpugnacionService impugnacionService;
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	@Autowired
	private EmpleadoService empleadoService;
	
	/**
	 * 
	 * @param valor
	 * @param tipoBusqueda
	 * @return <List<ImpugnacionVO>
	 * @throws BusinessException
	 */
	@RequestMapping(value = "buscarImpugnaciones", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSCAR_IMPUGNACIONES')")
	public ResponseEntity<List<ImpugnacionVO>> buscarImpugnaciones(@RequestParam(value = "valor" ) String valor, @RequestParam(value = "tipoBusqueda" ) String tipoBusqueda) throws NotFoundException  {
		
		List<InfraccionImpugnacionDTO> listaImpugnacionDTO = null;
		listaImpugnacionDTO =  impugnacionService.buscarImpugnaciones(tipoBusqueda, valor);
		if (listaImpugnacionDTO.isEmpty()) {
			throw new NotFoundException("No se encontraron registros");

		}	
		List<ImpugnacionVO> listaImpugnacionVO = ResponseConverter.converterLista(new ArrayList<>(), listaImpugnacionDTO, ImpugnacionVO.class);
		
		return new ResponseEntity<List<ImpugnacionVO>>(listaImpugnacionVO, HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 * @return List<VImpugnacionConsultaVO>
	 * @throws NotFoundException
	 */
	@RequestMapping(value = "infraccionImpugna", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSCAR_INFRAC_IMPUGNACION')")
	public ResponseEntity<List<VImpugnacionConsultaVO>> buscarInfraccionesImpuga(@RequestParam(value = "id" ) String id) throws NotFoundException  {
		
		List<VImpugnacionConsultaVO> listaConsulta = null; 		
		listaConsulta =  impugnacionService.buscarInfraccionImpugna(id);
		
		return new ResponseEntity<List<VImpugnacionConsultaVO>>(listaConsulta, HttpStatus.OK);
	}	
	
    /**
     * 
     * @param impugnacionVO
     * @throws BusinessException
     */
	@RequestMapping(value = "actualizaInformacion",  method = RequestMethod.PUT)
	@PreAuthorize("hasAnyAuthority('ACTUALIZAR_INFORMACION_IMPUGNACION')")
	public ResponseEntity<Void> actualizarInformacion(@RequestBody ImpugnacionParametrosVO impugnacionVO) throws BusinessException{

		UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
		EmpleadosDTO empleado = empleadoService.getEmpleadoById(usuario.getId());
		impugnacionVO.setEmpleadoId(empleado.getEmpId());
		
		impugnacionService.actualizarInformacion(impugnacionVO);	
		return new ResponseEntity<Void>(HttpStatus.CREATED);		
		
	}
	/**
	 * 
	 * @param id
	 * @return List<ImpugnacionVO>
	 * @throws NotFoundException
	 */
	@RequestMapping(value = "buscarPorId", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSCAR_IMPUGNACIONES_ID')")
	public ResponseEntity<List<ImpugnacionVO>> buscarImpugnaciones(@RequestParam(value = "id" ) String id) throws NotFoundException  {
		
		List<InfraccionImpugnacionDTO> listaImpugnaDTO = impugnacionService.buscarPorId(id);			
		List<ImpugnacionVO> listaImpugnacionVO = ResponseConverter.converterLista(new ArrayList<>(), listaImpugnaDTO, ImpugnacionVO.class);		
		
		return new ResponseEntity<List<ImpugnacionVO>> (listaImpugnacionVO, HttpStatus.OK);
	}
	
    /**
     * 
     * @param impugnacionVO
     * @return ImpugnacionAltaVO
     * @throws BusinessException
     * Llama a un sp el cual evalua con base en los parametros recibidos si realiza
     * un alta,una baja,o una modificación
     * @throws ParseException 
     * 
     */
	@RequestMapping(value = "callImpugnaInfracc",  method = RequestMethod.PUT)
	@PreAuthorize("hasAnyAuthority('MODIFICA_IMPUGNA_INFRACCION')")
	public ResponseEntity<ImpugnacionParametrosVO> callImpugnaInfracc(@RequestBody ImpugnacionParametrosVO impugnacionVO) throws BusinessException, ParseException{
		
		UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
		EmpleadosDTO empleado = empleadoService.getEmpleadoById(usuario.getId());
		impugnacionVO.setEmpleadoId(empleado.getEmpId());
		ImpugnacionParametrosVO resultadoVO = null;
		resultadoVO =  impugnacionService.modificaInfracciones(impugnacionVO);	
		return new ResponseEntity<ImpugnacionParametrosVO>(resultadoVO,HttpStatus.CREATED);		
		
	}
	
	/**
	 * 
	 * @param impugnacionVO
	 * @return ImpugnacionAltaVO
	 * @throws BusinessException
	 */
	@RequestMapping(value = "saveImpugnacion",  method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('ALTA_IMPUGNACION')")
	public ResponseEntity<ImpugnacionAltaVO> altaImpugnacion(@RequestBody ImpugnacionAltaVO impugnacionVO) throws BusinessException{
		
		UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
		EmpleadosDTO empleado = empleadoService.getEmpleadoById(usuario.getId());
		impugnacionVO.setEmpleadoId(empleado.getEmpId());
		
		ImpugnacionAltaVO impugnacionAltaVO = null;
		impugnacionAltaVO = impugnacionService.altaImpugnacion(impugnacionVO);
		return new ResponseEntity<ImpugnacionAltaVO>(impugnacionAltaVO,HttpStatus.CREATED);		
		
	}
	
	/**
	 * 
	 * @param tipoBusqueda
	 * @param valor
	 * @return List<VConsultaCanceladas>
	 * @throws NotFoundException
	 */
	@RequestMapping(value = "buscarCanceladas", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSCAR_IMPUGNACIONES_CANCELADAS')")
	public ResponseEntity<List<VConsultaCanceladas>> buscarImpugnacionesCanceldas( @RequestParam(value = "tipoBusqueda" ) String tipoBusqueda,@RequestParam(value = "valor" ) String valor) throws NotFoundException  {
		List<VConsultaCanceladas> listaCanceladas = null;
		try{
			listaCanceladas =  impugnacionService.obtenerCanceladas(tipoBusqueda, valor);
		}catch(Exception e){
			throw new NotFoundException("No se pudo realizar la consulta, debido a: /n" + e);
		}
		if (listaCanceladas.isEmpty()) {
			throw new NotFoundException("No se encontraron registros");
		}			
		return new ResponseEntity<List<VConsultaCanceladas>>(listaCanceladas, HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param valor
	 * @return VConsulaCanceladasDetalleVO
	 * @throws NotFoundException
	 */
	@ResponseBody
	@RequestMapping(value = "detalleCanceladas", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('CONSULTA_DETALLE_IMPUGNACIONES_CANCELADAS')")
	public ResponseEntity<VConsulaCanceladasDetalleVO> buscarImpugnacionesCanceldas(@RequestParam(value = "valor" ) String valor) throws NotFoundException  {
		
		VConsulaCanceladasDetalleVO detalleCanceladas = null;
		detalleCanceladas =  impugnacionService.obtenerVDetalleCanceladas(valor);
		if (detalleCanceladas.equals(null)) {
			throw new NotFoundException("No se encontraron registros");

		}			
		return new ResponseEntity<VConsulaCanceladasDetalleVO>(detalleCanceladas, HttpStatus.OK);
	}

	
	
}
