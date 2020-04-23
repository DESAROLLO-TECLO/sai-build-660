package mx.com.teclo.saicdmx.api.rest.seguridad;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.saicdmx.negocio.service.caja.CajaService;
import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.util.comun.ResponseConverter;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.dto.MenuDinamicoDTO;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.facade.MenuDinamicoFacade;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.util.TokenUtils;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.AuthenticationRequestVO;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.AuthenticationResponseVO;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.MenuDinamicoVO;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.UsuarioFirmadoVO;
import mx.com.teclomexicana.arquitectura.ortogonales.service.bitacora.BitacoraCambiosService;

@RestController
@RequestMapping("/login")
public class LoginRestController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	@Qualifier("userDetailsSIIService")
	private UserDetailsService userDetailsService;
	
	@Autowired
	private MenuDinamicoFacade menuDinamicoFacade;
	
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	
	@Autowired
	private CajaService cajaService;

	@Autowired
	private BitacoraCambiosService  bitacoraCambiosService;
    
	
	public final String tokenHeader = "X-Auth-Token";

    @Value("${app.config.codigo}")
    private String codeApplication;
    
    
    
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<AuthenticationResponseVO> authenticationRequest(@RequestBody AuthenticationRequestVO authenticationRequest) throws AuthenticationException, BusinessException {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		UsuarioFirmadoVO usuarioFirmado = (UsuarioFirmadoVO) authentication.getPrincipal();
		if (usuarioFirmado.getPerfilId().equals(new Long(12))) {
			cajaService.configurarCaja(usuarioFirmado);
		}
		//login
		SecurityContextHolder.getContext().setAuthentication(authentication);
		//UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
 	    bitacoraCambiosService.guardarBitacoraCambiosParametros(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 1L, 1L, usuarioFirmado.getUsername(), usuarioFirmado.getUsername(), usuarioFirmado.getId(), "0","", ParametrosBitacoraEnum.ORIGEN_W.getParametro());

		String token = tokenUtils.generateToken(usuarioFirmado);
		return new ResponseEntity<AuthenticationResponseVO>(new AuthenticationResponseVO(token), HttpStatus.OK);
	}

	@RequestMapping(value = "refresh", method = RequestMethod.GET)
	public ResponseEntity<AuthenticationResponseVO> authenticationRequest(HttpServletRequest request) {
		String token = request.getHeader(tokenHeader);
		String username = this.tokenUtils.getUserNameFromToken(token);
		UsuarioFirmadoVO user = (UsuarioFirmadoVO) this.userDetailsService.loadUserByUsername(username);
		if (this.tokenUtils.canTokenBeRefreshed(token, user.getLastPasswordReset())) {
			String refreshedToken = this.tokenUtils.refreshToken(token,user);
			return new ResponseEntity<AuthenticationResponseVO>(new AuthenticationResponseVO(refreshedToken), HttpStatus.OK);
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@RequestMapping(value = "/menus", method = RequestMethod.GET)
	public ResponseEntity<List<MenuDinamicoVO>> buscarMenuUsuario() throws NotFoundException {
		UsuarioFirmadoVO usuarioFirmado = usuarioFirmadoService.getUsuarioFirmadoVO();
		List<MenuDinamicoDTO> menus = menuDinamicoFacade.buscarMenuUsuario(usuarioFirmado.getPerfilId(),usuarioFirmado.getId(),codeApplication);
		List<MenuDinamicoVO> menusVO = ResponseConverter.converterLista(new ArrayList<>(), menus, MenuDinamicoVO.class);
		if (menus.isEmpty()) {
			throw new NotFoundException("No se encontraron menus con los datos informados.");
		}	
		return new ResponseEntity<List<MenuDinamicoVO>>(menusVO, HttpStatus.OK);
	}
}
