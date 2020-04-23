package mx.com.teclo.saicdmx.api.rest.articulos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.saicdmx.negocio.service.articulos.ArticulosService;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.articulos.ArticuloDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.articulos.ArticulosInfraccionesFinanzasDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.articulos.SancionesArticulosDTO;
import mx.com.teclo.saicdmx.persistencia.vo.articulos.ArticuloVO;
import mx.com.teclo.saicdmx.persistencia.vo.articulos.ArticulosInfraccionesFinanzasVO;
import mx.com.teclo.saicdmx.persistencia.vo.articulos.SancionArticuloVO;
import mx.com.teclo.saicdmx.util.comun.ResponseConverter;

@RestController
public class ArticulosRestController {
	
	@Autowired
	private ArticulosService articulosService;
	
	@RequestMapping(value = "/articulos", method = RequestMethod.GET)
	public ResponseEntity<List<ArticuloVO>> buscarArticulosInfracciones() throws NotFoundException {
		List<ArticuloDTO> articuloDTO = new ArrayList<ArticuloDTO>();
		articuloDTO = articulosService.buscarArticulosInfracciones();
		if (articuloDTO == null){
			return new ResponseEntity<List<ArticuloVO>>(HttpStatus.NOT_FOUND);
		}
		List<ArticuloVO> articuloVO = new ArrayList<ArticuloVO>();
		articuloVO =  ResponseConverter.converterLista(new ArrayList<>(), 
				articuloDTO, ArticuloVO.class);
		return new ResponseEntity<List<ArticuloVO>>(articuloVO , HttpStatus.OK);
	}
	
	@RequestMapping(value = "/articulosPorFecha", method = RequestMethod.GET)
	public ResponseEntity<List<ArticulosInfraccionesFinanzasVO>> buscarArticulosPorFecha(@RequestParam(value = "fecha") String fechaString) throws NotFoundException, ParseException{
		SimpleDateFormat  formatter = new SimpleDateFormat();
		if(fechaString.length()<=10){
			fechaString += " 12:00:00";
			formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		}
		else
			formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date fecha = formatter.parse(fechaString);
		
		List<ArticulosInfraccionesFinanzasDTO> articulosInfraccionesFinanzasDTO = new ArrayList<ArticulosInfraccionesFinanzasDTO>();
		articulosInfraccionesFinanzasDTO = articulosService.buscarArticulosActivosPorFecha(fecha);
		if (articulosInfraccionesFinanzasDTO == null){
			return new ResponseEntity<List<ArticulosInfraccionesFinanzasVO>>(HttpStatus.NOT_FOUND);
		}
		List<ArticulosInfraccionesFinanzasVO> articulosInfraccionesFinanzasVO = new ArrayList<ArticulosInfraccionesFinanzasVO>();
		articulosInfraccionesFinanzasVO =  ResponseConverter.converterLista(new ArrayList<>(), 
				articulosInfraccionesFinanzasDTO, ArticulosInfraccionesFinanzasVO.class);
		return new ResponseEntity<List<ArticulosInfraccionesFinanzasVO>>(articulosInfraccionesFinanzasVO , HttpStatus.OK);
	}
	
	@RequestMapping(value = "/fraccionPorFechaYArtNum", method = RequestMethod.GET)
	public ResponseEntity<List<ArticulosInfraccionesFinanzasVO>> buscarArticulosPorFechaYArticuloNumero
		(@RequestParam(value = "fecha") String fechaString,
		@RequestParam(value = "articuloNumero") Long articuloNumero) throws NotFoundException, ParseException{
		SimpleDateFormat  formatter = new SimpleDateFormat();
		if(fechaString.length()<=10){
			fechaString += " 12:00:00";
			formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		}
		else
			formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		Date fecha = formatter.parse(fechaString);
		
		List<ArticulosInfraccionesFinanzasDTO> articulosInfraccionesFinanzasDTO = new ArrayList<ArticulosInfraccionesFinanzasDTO>();
		articulosInfraccionesFinanzasDTO = articulosService.buscarFraccionesActivasPorFechaYArticuloNumero(fecha, articuloNumero);
		if (articulosInfraccionesFinanzasDTO == null){
			return new ResponseEntity<List<ArticulosInfraccionesFinanzasVO>>(HttpStatus.NOT_FOUND);
		}
		List<ArticulosInfraccionesFinanzasVO> articulosInfraccionesFinanzasVO = new ArrayList<ArticulosInfraccionesFinanzasVO>();
		articulosInfraccionesFinanzasVO =  ResponseConverter.converterLista(new ArrayList<>(), 
				articulosInfraccionesFinanzasDTO, ArticulosInfraccionesFinanzasVO.class);
		return new ResponseEntity<List<ArticulosInfraccionesFinanzasVO>>(articulosInfraccionesFinanzasVO , HttpStatus.OK);
	}
	
	@RequestMapping(value = "/incisoParrafoPorFechaArtNumYFraccion", method = RequestMethod.GET)
	public ResponseEntity<List<ArticulosInfraccionesFinanzasVO>> buscarIncisoParrafoActivosPorFechaArticuloNumeroYFraccion
		(@RequestParam(value = "fecha") String fechaString,
		@RequestParam(value = "articuloNumero") Long articuloNumero,
		@RequestParam(value = "fraccion") Long fraccion) throws NotFoundException, ParseException{
		
		SimpleDateFormat  formatter = new SimpleDateFormat();
		if(fechaString.length()<=10){
			fechaString += " 12:00:00";
			formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		}
		else
			formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		Date fecha = formatter.parse(fechaString);
		
		List<ArticulosInfraccionesFinanzasDTO> articulosInfraccionesFinanzasDTO = new ArrayList<ArticulosInfraccionesFinanzasDTO>();
		articulosInfraccionesFinanzasDTO = 
				articulosService.buscarIncisoParrafoActivosPorFechaArticuloNumeroYFraccion(fecha, articuloNumero, fraccion);
		if (articulosInfraccionesFinanzasDTO == null){
			return new ResponseEntity<List<ArticulosInfraccionesFinanzasVO>>(HttpStatus.NOT_FOUND);
		}
		List<ArticulosInfraccionesFinanzasVO> articulosInfraccionesFinanzasVO = new ArrayList<ArticulosInfraccionesFinanzasVO>();
		articulosInfraccionesFinanzasVO =  ResponseConverter.converterLista(new ArrayList<>(), 
				articulosInfraccionesFinanzasDTO, ArticulosInfraccionesFinanzasVO.class);
		return new ResponseEntity<List<ArticulosInfraccionesFinanzasVO>>(articulosInfraccionesFinanzasVO , HttpStatus.OK);
	}
	
	@RequestMapping(value = "/sancionArticulo", method = RequestMethod.GET)
	public ResponseEntity<List<SancionArticuloVO>> buscarSancionesPorArticulo
	(@RequestParam(value="articuloId")Long articuloId) throws NotFoundException {
		List<SancionesArticulosDTO> sancionesArticulosDTO = new ArrayList<SancionesArticulosDTO>();
		sancionesArticulosDTO = articulosService.buscarSancionesPorArticulo(articuloId);
		if (sancionesArticulosDTO == null){
			return new ResponseEntity<List<SancionArticuloVO>>(HttpStatus.NOT_FOUND);
		}
		List<SancionArticuloVO> sancionesArticulosVO = new ArrayList<SancionArticuloVO>();
		sancionesArticulosVO =  ResponseConverter.converterLista(new ArrayList<>(), 
				sancionesArticulosDTO, SancionArticuloVO.class);
		return new ResponseEntity<List<SancionArticuloVO>>(sancionesArticulosVO , HttpStatus.OK);
	}
	
}
