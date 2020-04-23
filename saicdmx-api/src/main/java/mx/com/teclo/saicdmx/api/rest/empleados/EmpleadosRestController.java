package mx.com.teclo.saicdmx.api.rest.empleados;

import java.util.Map;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.saicdmx.negocio.service.catalogos.CatalogoServiceImpl;
import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.negocio.service.empleado.EmpleadoService;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.DepositosEmpleadosDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.empleados.EmpleadosDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.AgrupamientosVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.SectorVO;
import mx.com.teclo.saicdmx.persistencia.vo.empleados.EmpleadosSinPsswdVO;
import mx.com.teclo.saicdmx.persistencia.vo.empleados.VEmpleadoDepAutorizaVO;
import mx.com.teclo.saicdmx.util.comun.ResponseConverter;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.UsuarioFirmadoVO;

@RestController
public class EmpleadosRestController {

	@Autowired
	private EmpleadoService empleadoService;

	@Autowired
	private CatalogoServiceImpl catalogoService;

	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;

	@RequestMapping(value = "/empleado", method = RequestMethod.GET)
	public ResponseEntity<EmpleadosSinPsswdVO> buscarEmpleadoPorPlaca(@RequestParam(value = "placa") String placa)
			throws NotFoundException {
		EmpleadosDTO empleadoDTO = null;
		empleadoDTO = empleadoService.buscarEmpleadoParaAltaInfraccion(placa);
		if (empleadoDTO == null) {
			return new ResponseEntity<EmpleadosSinPsswdVO>(HttpStatus.NOT_FOUND);
		}
		EmpleadosSinPsswdVO empleadoVO = new EmpleadosSinPsswdVO();
		ResponseConverter.copiarPropriedades(empleadoVO, empleadoDTO);
		SectorVO sectorVO = new SectorVO();
		ResponseConverter.copiarPropriedades(sectorVO, empleadoDTO.getSector());
		AgrupamientosVO agrupamientoVO = new AgrupamientosVO();
		ResponseConverter.copiarPropriedades(agrupamientoVO, empleadoDTO.getAgrupamiento());
		empleadoVO.setSector(sectorVO);
		empleadoVO.setAgrupamiento(agrupamientoVO);
		return new ResponseEntity<EmpleadosSinPsswdVO>(empleadoVO, HttpStatus.OK);
	}

	@RequestMapping(value = "/empleadoParaModificarDeposito", method = RequestMethod.GET)
	public ResponseEntity<VEmpleadoDepAutorizaVO> empleadoFirmadoParaModDeposito(
			@RequestParam(value = "placa") String placa, @RequestParam(value = "pwd") String pwd)
			throws NotFoundException {

		UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
		EmpleadosDTO empleado = empleadoService.getEmpleadoById(usuario.getId());

		DepositosEmpleadosDTO depEmpDTO = catalogoService.depositoEmpleadoPorEmpId(empleado.getEmpId());

		if (depEmpDTO == null)
			return new ResponseEntity<VEmpleadoDepAutorizaVO>(HttpStatus.NOT_FOUND);

		VEmpleadoDepAutorizaVO vEmpleadoDepAutorizaVO = empleadoService.buscarVEmpleadoDepAutorizaVOporEmpIdNombrePlaca(
				placa, pwd, depEmpDTO.getDepId().getDepId().toString());
		if (vEmpleadoDepAutorizaVO == null)
			return new ResponseEntity<VEmpleadoDepAutorizaVO>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<VEmpleadoDepAutorizaVO>(vEmpleadoDepAutorizaVO, HttpStatus.OK);
	}

	@RequestMapping(value = "/validarPerfilEmpleado", method = RequestMethod.GET)
	public ResponseEntity<Boolean> validarPerfilEmpleado(@RequestParam(value = "screen") Integer screen)
			throws NotFoundException {
		Long empleadoId = usuarioFirmadoService.getUsuarioFirmadoVO().getPerfilId();
		Boolean perfilValido = empleadoService.validarPerfilEmpleado(empleadoId, screen);
		return new ResponseEntity<Boolean>(perfilValido, HttpStatus.OK);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/validarPerfilParaUsuarios", method = RequestMethod.GET)
	public ResponseEntity<Map> validarPerfilEmpleadoParaUsuarios() throws NotFoundException {
		Long empleadoPerfil = usuarioFirmadoService.getUsuarioFirmadoVO().getPerfilId();
		Map perfiles = empleadoService.validarPerfilParaUsuarios(empleadoPerfil);
		return new ResponseEntity<Map>(perfiles, HttpStatus.OK);
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/validarPerfilParaCertificadosSAT", method = RequestMethod.GET)
	public ResponseEntity<Map> validarPerfilEmpleadoParaPerfilesWeb() throws NotFoundException {
		Long empleadoPerfil = usuarioFirmadoService.getUsuarioFirmadoVO().getPerfilId();
		Map perfiles = empleadoService.validarPerfilParaCertificadosSAT(empleadoPerfil);
		return new ResponseEntity<Map>(perfiles, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/empleadoLogeado", method = RequestMethod.GET)
	public ResponseEntity<EmpleadosSinPsswdVO> empleadoLogeado() throws NotFoundException {
		String placaOficial = usuarioFirmadoService.getUsuarioFirmadoVO().getLogin();
		EmpleadosDTO empleadoDTO = null;
		empleadoDTO = empleadoService.getEmpleadoByPlaca(placaOficial);
		if (empleadoDTO == null) {
			return new ResponseEntity<EmpleadosSinPsswdVO>(HttpStatus.NOT_FOUND);
		}
		EmpleadosSinPsswdVO empleadoVO = new EmpleadosSinPsswdVO();
		ResponseConverter.copiarPropriedades(empleadoVO, empleadoDTO);
		SectorVO sectorVO = new SectorVO();
		ResponseConverter.copiarPropriedades(sectorVO, empleadoDTO.getSector());
		AgrupamientosVO agrupamientoVO = new AgrupamientosVO();
		ResponseConverter.copiarPropriedades(agrupamientoVO, empleadoDTO.getAgrupamiento());
		empleadoVO.setSector(sectorVO);
		empleadoVO.setAgrupamiento(agrupamientoVO);
		return new ResponseEntity<EmpleadosSinPsswdVO>(empleadoVO, HttpStatus.OK);
	}
	
}
