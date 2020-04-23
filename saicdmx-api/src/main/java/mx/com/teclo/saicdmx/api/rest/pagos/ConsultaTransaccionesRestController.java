package mx.com.teclo.saicdmx.api.rest.pagos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.negocio.service.pagos.ConsultaTransaccionesService;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.pagos.TransaccionDTO;
import mx.com.teclo.saicdmx.persistencia.vo.bloqueohh.BloqueohhCatTipoBloqueoVO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.CentroPagosVO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.ConsultaTransaccionesVO;
import mx.com.teclo.saicdmx.util.comun.ResponseConverter;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.UsuarioFirmadoVO;

@RestController
public class ConsultaTransaccionesRestController {

	@Autowired 
	private ConsultaTransaccionesService consultaTransaccionesService;
	
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	
	@RequestMapping(value = "/pagos/consultaTodasTransacciones", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('CONSULTA_TRANSACCIONES')")
	public ResponseEntity<List<ConsultaTransaccionesVO>> consultaTodasTransacciones()throws NotFoundException{
		UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
		List<ConsultaTransaccionesVO> transaccionesListVO=consultaTransaccionesService.consultaTransacciones(usuario.getId());
		if(transaccionesListVO.isEmpty()){
			throw new NotFoundException("No se encontraron registros");
		}
		return  new ResponseEntity<List<ConsultaTransaccionesVO>>(transaccionesListVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/pagos/consultaTransaccionesInfraccion", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('CONSULTA_TRANSACCIONES_INFRACCION')")
	public ResponseEntity<List<ConsultaTransaccionesVO>> consultaTransaccionesInfraccion(@RequestParam(value = "valorBuscado") String valorBuscado)throws NotFoundException{
		UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
		List<ConsultaTransaccionesVO> transaccionesListVO=consultaTransaccionesService.consultaTransaccionesByNumInfraccion(valorBuscado,usuario.getId());
		if(transaccionesListVO.isEmpty()){
			throw new NotFoundException("No se encontraron registros");
		}
		
		return  new ResponseEntity<List<ConsultaTransaccionesVO>>(transaccionesListVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/pagos/consultaTransaccionesReferencia", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('CONSULTA_TRANSACCIONES_REFERENCIA')")
	public ResponseEntity<List<ConsultaTransaccionesVO>> consultaTransaccionesReferencia(@RequestParam(value = "valorBuscado") String valorBuscado)throws NotFoundException{
		UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
		List<ConsultaTransaccionesVO> transaccionesListVO=consultaTransaccionesService.consultaTransaccionesByNumControl(valorBuscado,usuario.getId());
		if(transaccionesListVO.isEmpty()){
			throw new NotFoundException("No se encontraron registros");
		}
		
		return  new ResponseEntity<List<ConsultaTransaccionesVO>>(transaccionesListVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/pagos/consultaTransaccionesFechas", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('CONSULTA_TRANSACCIONES_FECHAS')")
	public ResponseEntity<List<ConsultaTransaccionesVO>> consultaTransaccionesFechas(@RequestParam(value = "fechaInicio") String fechaInicio, 
			@RequestParam(value = "fechaFin") String fechaFin,@RequestParam(value = "tipoBusqueda") String tipoBusqueda)throws NotFoundException{
		UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
		List<ConsultaTransaccionesVO> transaccionesListVO=consultaTransaccionesService.consultaTransaccionesFechas(fechaInicio,fechaFin,tipoBusqueda,usuario.getId());
		if(transaccionesListVO.isEmpty()){
			throw new NotFoundException("No se encontraron registros");
		}
		
		return  new ResponseEntity<List<ConsultaTransaccionesVO>>(transaccionesListVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/pagos/consultaTransaccionesCentroPagos", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('CONSULTA_TRANSACCIONES_CENTRO_PAGOS')")
	public ResponseEntity<List<ConsultaTransaccionesVO>> consultaTransaccionesCentroPagos(@RequestParam(value = "tipoBusqueda") String tipoBusqueda,
			@RequestParam(value = "valorBusqueda") String valorBusqueda, 
			@RequestParam(value = "fechaTransaccion") String fechaTransacion)throws NotFoundException{
		UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
		List<ConsultaTransaccionesVO> transaccionesListVO=consultaTransaccionesService.consultaTransaccionesCentroPagos(tipoBusqueda,valorBusqueda,fechaTransacion,usuario.getId());
		if(transaccionesListVO.isEmpty()){
			throw new NotFoundException("No se encontraron registros");
		}
		
		return  new ResponseEntity<List<ConsultaTransaccionesVO>>(transaccionesListVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/pagos/validacionManual", method = RequestMethod.PUT)
	@PreAuthorize("hasAnyAuthority('VALIDACION_TRANSACCION_MANUAL')")
	public ResponseEntity<ConsultaTransaccionesVO> validacionManual(@RequestBody ConsultaTransaccionesVO transacionVO)throws NotFoundException{
		UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
		ConsultaTransaccionesVO transaccionesListVO=consultaTransaccionesService.validacionManual(transacionVO,usuario.getId());
		if(transaccionesListVO==null || transaccionesListVO.getTranId()==null){
			throw new NotFoundException("No se encontraron registros");
		}
		
		return  new ResponseEntity<ConsultaTransaccionesVO>(transaccionesListVO, HttpStatus.OK);
	}
	
}
