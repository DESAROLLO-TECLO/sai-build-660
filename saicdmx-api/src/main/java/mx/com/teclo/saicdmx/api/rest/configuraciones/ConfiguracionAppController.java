package mx.com.teclo.saicdmx.api.rest.configuraciones;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.arquitectura.ortogonales.persistencia.configuracion.vo.ConfiguracionVO;
import mx.com.teclo.arquitectura.ortogonales.service.configuracion.ConfiguracionAplicacionService;
import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.AmbienteVO;

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
