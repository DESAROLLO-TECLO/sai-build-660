package mx.com.teclo.saicdmx.api.rest.configuraciones;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.arquitectura.ortogonales.persistencia.configuracion.vo.ConfiguracionVO;
import mx.com.teclo.arquitectura.ortogonales.persistencia.configuracion.vo.ResolucionVO;
import mx.com.teclo.arquitectura.ortogonales.persistencia.configuracion.vo.TemaVO;
import mx.com.teclo.arquitectura.ortogonales.responsehttp.ControllerException;
import mx.com.teclo.arquitectura.ortogonales.service.configuracion.ConfiguracionAplicacionService;
import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.AmbienteVO;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.UsuarioFirmadoVO;

@RestController
@RequestMapping("/aplicacion")
public class ConfiguracionAppController {

 

    @Value("${app.config.codigo}")
    private String codeApplication;
    
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
    
	@Autowired
	private ConfiguracionAplicacionService configuracionAplicacionService;
    
//	OBTENER CONFIGURACIÓN DE LA APLICACIÓN
	@RequestMapping(value = "/configuraciones", method = RequestMethod.GET)
	public ResponseEntity<ConfiguracionVO> buscarMenuUsuario() throws NotFoundException {
			
		ConfiguracionVO configuracionVO = null;
		
		configuracionVO = configuracionAplicacionService.buscarConfiguracionXAplicacion(codeApplication);
		if (configuracionVO.equals(null)) {
			throw new NotFoundException("No se encontraron las configuraciones de la aplicación.");
		}	
		return new ResponseEntity<ConfiguracionVO>(configuracionVO, HttpStatus.OK);
	}
	
//	OBTENER RESOLUCIONES
	@RequestMapping(value = "/resoluciones", method = RequestMethod.GET)
	public ResponseEntity<List<ResolucionVO>> obtenerResoluciones() throws NotFoundException {
		
		List<ResolucionVO> resolucionVO;
		
		resolucionVO = configuracionAplicacionService.obtenerListaResoluciones();
		if (resolucionVO.equals(null)) {
			throw new NotFoundException("No se encontraron las resoluciones de la aplicación.");
		}
		
		return new ResponseEntity<List<ResolucionVO>>(resolucionVO, HttpStatus.OK);
	}
	
//	OBTENER TEMAS
	@RequestMapping(value = "/temas", method = RequestMethod.GET)
	public ResponseEntity<List<TemaVO>> obtenerTemas() throws NotFoundException {
		
		List<TemaVO> temaVO;
		
		temaVO = configuracionAplicacionService.obtenerTemasXAplicacion(codeApplication);
		if (temaVO.equals(null)) {
			throw new NotFoundException("No se encontraron los temas de la aplicación.");
		}
		
		return new ResponseEntity<List<TemaVO>>(temaVO, HttpStatus.OK);
	}
	
//	GUARDAR/ACTUALIZAR CONFIGURACION
	@RequestMapping(value="/guardarConfiguracion", method = RequestMethod.POST)
	public ResponseEntity<ConfiguracionVO> guardarConfiguracion(
		@RequestBody ConfiguracionVO configuracionVO)
	throws ControllerException, IOException {
		
		UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
		
		configuracionAplicacionService.saveConfiguracion(configuracionVO, codeApplication, usuario);
		
		return new ResponseEntity<ConfiguracionVO>(configuracionVO, HttpStatus.OK);
	}	
	
	/**
	 * @author Cesar Gomez
	 * @return <AmbienteVO>
	*/
//	OBTENER CONFIGURACIÓN DE LA APLICACIÓN
	@RequestMapping(value = "/ambiente", method = RequestMethod.GET)
	public ResponseEntity<AmbienteVO> findCdAmbiente() throws NotFoundException {
			
		AmbienteVO ambienteVO = null;
		
		ambienteVO = configuracionAplicacionService.getCdAmbienteXAplication(codeApplication);
		if (ambienteVO.equals(null)) {
			throw new NotFoundException("No se encontró el código del ambiente");
		}	
		return new ResponseEntity<AmbienteVO>(ambienteVO, HttpStatus.OK);
	}
}
