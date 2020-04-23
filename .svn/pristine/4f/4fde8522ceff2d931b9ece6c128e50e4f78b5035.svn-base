package mx.com.teclo.ms.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;
import mx.com.teclo.ms.persistencia.vo.TipoFechaVO;
import mx.com.teclo.ms.service.CatalogoMsService;

@RestController
@RequestMapping(value = "/ms/catalogos")
public class CatalogoMsRestController {

	@Autowired
	private CatalogoMsService catalogoMsService;

	@RequestMapping(value = "/tipoFechasAll", method = RequestMethod.GET)
	public ResponseEntity<List<TipoFechaVO>> getCatalogoTipoFechasAll() {
		List<TipoFechaVO> listaTipoFechaVO = catalogoMsService.getCatalogoTipoFechasAll();
		return new ResponseEntity<List<TipoFechaVO>>(listaTipoFechaVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/tipoFechasOpcion", method = RequestMethod.GET)
	public ResponseEntity<List<TipoFechaVO>> getCatalogoTipoFechasOpcion(
			@RequestParam (value = "opcion") String opcion) throws NotFoundException{
		String[] op = opcion.split(","); //Espera una lista de numeros separada por numeros
		Long listId[] = new Long[op.length];
		try {
			for(int x = 0; x< op.length ;x++) {
				listId[x] = Long.valueOf(op[x]);
			}
		}catch (Exception e) {
			throw new NotFoundException("Algun valor de las opciones es incorrectas");
		}
		List<TipoFechaVO> listaTipoFechaVO = catalogoMsService.getCatalogoTipoFechasOp(listId);
//		List<TipoFechaVO> listaTipoFechaVO = null;
		return new ResponseEntity<List<TipoFechaVO>>(listaTipoFechaVO, HttpStatus.OK);
	}
}