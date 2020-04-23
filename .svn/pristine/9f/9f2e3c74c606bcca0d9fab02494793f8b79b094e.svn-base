package mx.com.teclo.saicdmx.api.rest.pagos;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.hibernate.engine.jdbc.NonContextualLobCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.codec.binary.Base64;

import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.negocio.service.pagos.DigitalizacionService;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.pagos.ExpedientesDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.pagos.ExpedienteDTO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.CatalogoExpedienteVO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.ExpedientePagoVO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.ImagenExpedienteVO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.TipoExpedienteVO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.TodoExpedienteVO;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.ControllerException;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.UsuarioFirmadoVO;

@RestController
public class DigitalizacionRestController {

	@Autowired
	DigitalizacionService digitalizacionService;
	
	@Autowired
	ExpedientesDAO expedientesDAO;
	
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	
	
	@RequestMapping(value = "/consultaInfraccionparaExpediente", method = RequestMethod.GET)
//	@PreAuthorize("hasAnyAuthority('FIND_INFRAC_PAGO')")
	public ResponseEntity<List<String>> buscarInfraccioExpediente(@RequestParam(value = "tipoParametro") String tipoParametro, 
																		     @RequestParam(value = "valorParametro")String valorParametro) throws NotFoundException {
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		String [] InfraccionExpedienteVO = digitalizacionService.obtenerInfraccionExpediente(valorParametro, tipoParametro, usuarioFirmadoVO.getId());
		List <String> tmpExpediente = InfraccionExpedienteVO != null ? Arrays.asList(InfraccionExpedienteVO) : null;
		if (InfraccionExpedienteVO == null || InfraccionExpedienteVO.length < 1)
			throw new NotFoundException("No se encontraron registros");
		return new ResponseEntity<List<String>>(tmpExpediente, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/obtenerExpediente", method=RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('DESPLEGAR_EXPEDIENTE')")
	public ResponseEntity<List<TodoExpedienteVO>> obtenerExpedientes(@RequestParam(value = "infracNum") String infracNum) throws NotFoundException
	{
		//Aqui va 2 funciones, una que trae todo el catalogo 
		//y otra que trae las captutas de cierto expediente
		Integer infracNumExiste = digitalizacionService.corroborarNumeroInfraccion(infracNum);
		List<CatalogoExpedienteVO> catalogo = digitalizacionService.obtenerCatalogoExpediente();
		List<TipoExpedienteVO> expedientes = digitalizacionService.obtenerCapturasExpediente(infracNum);
		List<TodoExpedienteVO> listaCompleta = new ArrayList<TodoExpedienteVO>();
			
		if (infracNumExiste != 0){
			for (CatalogoExpedienteVO cevo : catalogo) {
				TipoExpedienteVO evoExiste = obtenerExpediente(expedientes, cevo.getTIPO());
				if(evoExiste != null)
				{
					TodoExpedienteVO tevo = new TodoExpedienteVO();
					tevo.setDESCRIPCION(cevo.getDESCRIPCION());
					tevo.setTIPO(cevo.getTIPO());
					tevo.setREGISTRADO(true);
					/*if(evoExiste.getARCHIVO() != null){
						tevo.setARCHIVO(blobToArrayBytes(evoExiste.getARCHIVO()));
					}else{
						//ARCHIVO EN LINEA 
						File file = new File("http://www.blogdefarmacia.com/wp-content/uploads/2013/09/nabo-propiedades-beneficiosas.jpg");
						//ARCHIVO LOCAL 
						//File file = new File("C:/"+digitalizacionService.obtenerRutaLocal(infracNum, cevo.getTIPO()));
						if(file != null){
							tevo.setARCHIVO(Files.readAllBytes(file.toPath()));
						}
						else
						{
							throw new NotFoundException("No se pudo encontrar el expediente "+tevo.getTIPO());
						}
					}*/
					listaCompleta.add(tevo);
				}else{
					TodoExpedienteVO tevo = new TodoExpedienteVO();
					tevo.setDESCRIPCION(cevo.getDESCRIPCION());
					tevo.setTIPO(cevo.getTIPO());
					tevo.setREGISTRADO(false);
					/*tevo.setARCHIVO(null);*/
					listaCompleta.add(tevo);
				}
			}
			return new ResponseEntity<List<TodoExpedienteVO>>(listaCompleta, HttpStatus.OK);
		}
		else
		{
			throw new NotFoundException("No existe este numero de infracción");
		}
	}
	
	/*
	 * César Gómez
	 * 
	 */
	@Transactional
	@RequestMapping(value="/capturaExpediente", method=RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('CAPTURA_EXPEDIENTE')")
	public ResponseEntity<ExpedientePagoVO> guardarExpediente(@RequestBody ExpedientePagoVO expedientePagoVO) throws ControllerException, IOException
	{		
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		
		ExpedienteDTO expedienteDTO = new ExpedienteDTO();
		
//		Actualización: Se obtiene el id máximo del último registro en la tabla PAGO_IMÁGENES
		Long idArchivo = expedientesDAO.getIdArchivo();
		
		String infracNum = expedientePagoVO.getInfracNum();
		byte[] archivo = expedientePagoVO.getArchivo();
//		Actualización: Se obtiene el tipo de catálogo del documento
		String tipoCatalogo = expedientePagoVO.getTipoCatalogo();
//		Actualización: Se obtiene el tipo de archivo del documento
		String tipoArchivo = expedientePagoVO.getTipoArchivo();
		
//		Actualización: Se valida si el id es null se setea 1
		if(idArchivo != null) {
			idArchivo++;
		} else {
			idArchivo = 1L;
		}
		
		if(infracNum != null && infracNum != "" && archivo != null && tipoCatalogo != null && tipoCatalogo != "")
		{
			expedienteDTO.setInfrac_Num(infracNum);
			
			expedienteDTO.setImg_expediente_id(idArchivo);
			
//			Actualización: Se obtiene y se setea el número de control de la infracción
			expedienteDTO.setInfrac_num_ctrl(expedientesDAO.obtenerNumControl(infracNum));
//			Actualización: Se setea el tipo de catálogo
			expedienteDTO.setTipo(tipoCatalogo);
			
//			Actualización: Se valida la extensión del archivo cargado para guardarlo con la extensión correcta
			if(tipoArchivo.equals("application/pdf")) {
				expedienteDTO.setNombre_archivo(infracNum+"_"+idArchivo+"_"+tipoCatalogo+".pdf");
			} else {
				expedienteDTO.setNombre_archivo(infracNum+"_"+idArchivo+"_"+tipoCatalogo+".jpg");
			}
			
			expedienteDTO.setActivo(1);
			
			expedienteDTO.setArchivo(arrayBytesToBlob(archivo));
			
			expedienteDTO.setCreado_por(usuarioFirmadoVO.getId());
			
			expedienteDTO.setFecha_creacion(new Date());
			
			expedienteDTO.setModificado_por(usuarioFirmadoVO.getId());
			
			expedienteDTO.setUltima_modificacion(new Date());
			
			expedientesDAO.saveOrUpdate(expedienteDTO);
			
		} else {
			throw new ControllerException("No se pudo cargar el expediente, el archivo no es una imagen");
		}
		
		return new ResponseEntity<ExpedientePagoVO>(expedientePagoVO, HttpStatus.OK);
	}
	
	@Transactional(readOnly = true)
	@RequestMapping(value="/mostrarExpediente", method=RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('MOSTRAR_EXPEDIENTE')")
	public ResponseEntity<ImagenExpedienteVO> mostrarExpediente(@RequestParam(value = "infracNum") String infracNum, @RequestParam(value = "tipo") String tipo) throws ControllerException
	{	
		ImagenExpedienteVO imagenEVO = new ImagenExpedienteVO();
		
		ExpedienteDTO expedienteDTO = expedientesDAO.getExpedientePorTipo(infracNum, tipo);
		
//		Se setea el id del archivo cargado
		imagenEVO.setIdArchivo(expedienteDTO.getImg_expediente_id());
//		Se setea el nombre del archivo cargado
		imagenEVO.setNombreArchivo(expedienteDTO.getNombre_archivo());
		
		if (expedienteDTO != null) {
			imagenEVO.setBdPath(blobToArrayBytes(expedienteDTO.getArchivo()));
			
			/* Función que decide si no esta en la base de datos, ubicarlo en el Storage Server */
			if(imagenEVO.getBdPath() != null && imagenEVO.getBdPath().length > 0)
			{
				imagenEVO.setExisteEnBD(true);
			}
			else
			{
				//String urlPath = "http://localhost:10240/datos/imagenes/";
				String urlPath = "http://imagenes.infracciones.df.gob.mx/";
//				Actualización: Se manda como parámetro el id del archivo para obtener la ruta
				String ruta = digitalizacionService.obtenerRutaLocal(infracNum, expedienteDTO.getImg_expediente_id(), tipo);
				imagenEVO.setLocalPath(urlPath+ruta);
			}
		}
		
		return new ResponseEntity<ImagenExpedienteVO>(imagenEVO, HttpStatus.OK);
	}
	
	@Transactional(readOnly = true)
	@RequestMapping(value="/mostrarTodoExpediente", method=RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('MOSTRAR_TODO_EXPEDIENTE')")
	public ResponseEntity<List<ImagenExpedienteVO>> mostrarTodoExpediente(@RequestParam(value = "infracNum") String infracNum) throws ControllerException
	{	
		
		List<ImagenExpedienteVO> listaImagenes = new ArrayList<ImagenExpedienteVO>();
		
		List<ExpedienteDTO> expedienteDTO = expedientesDAO.getTodoExpediente(infracNum);
		
		if (!expedienteDTO.isEmpty()) {
			for(ExpedienteDTO expVO : expedienteDTO){
				ImagenExpedienteVO imagenEVO = new ImagenExpedienteVO();
				
//				Actualización: Se obtiene el nombre en el catálogo del archivo cargado
				String nombreCatDocumento = digitalizacionService.obtenerCatDocumento(infracNum, expVO.getTipo());
				
				imagenEVO.setNombreArchivo(expVO.getNombre_archivo());
				
				imagenEVO.setBdPath(blobToArrayBytes(expVO.getArchivo()));
				
//				Actualización: Se setea el nombre del documento obtenido
				imagenEVO.setNombreCatDocumento(nombreCatDocumento);
				
				imagenEVO.setTipoCatDocumento(expVO.getTipo());
				
//				Actualización: Se obtiene la extención del archivo mediante el nombre
				String extension = expVO.getNombre_archivo();
				String[] partesNombreArchivo = extension.split("\\.");
				String extensionArchivo = partesNombreArchivo[1];
				
				imagenEVO.setExtensionArchivo(extensionArchivo);
				
				/* Función que decide si no esta en la base de datos, ubicarlo en el Storage Server */
				if(imagenEVO.getBdPath() != null && imagenEVO.getBdPath().length > 0)
				{
					imagenEVO.setExisteEnBD(true);
				}
				else
				{
					String urlPath = "http://imagenes.infracciones.df.gob.mx/";
//					Actualización: Se manda como parámetro el id del archivo para obtener la ruta
					String ruta = digitalizacionService.obtenerRutaLocal(infracNum, expVO.getImg_expediente_id(), expVO.getTipo());
					imagenEVO.setLocalPath(urlPath+ruta);
				}
				listaImagenes.add(imagenEVO);
			}
		}
		
		return new ResponseEntity<List<ImagenExpedienteVO>>(listaImagenes, HttpStatus.OK);
	}
	
	@Transactional(readOnly = true)
	@RequestMapping(value="/bajarExpediente", method=RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('DESCARGAR_EXPEDIENTE')")
	public ResponseEntity<ImagenExpedienteVO> bajarExpediente(@RequestParam(value = "infracNum") String infracNum, @RequestParam(value = "tipo") String tipo) throws ControllerException, IOException
	{	
//		Actualización: Se modifica el método en la manera de obtener el archivo, y se eliminaron los header
		ResponseEntity<ImagenExpedienteVO> re = mostrarExpediente(infracNum, tipo);

		if(re.getBody() != null)
			return new ResponseEntity<ImagenExpedienteVO>(re.getBody(), HttpStatus.OK);
				
		throw new ControllerException("No se pudo mostrar el expediente");
	}
	
	@Transactional
	@RequestMapping(value="/eliminarExpediente", method=RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BORRAR_EXPEDIENTE')")
	public ResponseEntity<String> eliminarExpediente(@RequestParam(value = "infracNum") String infracNum, @RequestParam(value = "tipo") String tipo)throws ControllerException{
		
//		Actualización: Se modifica el método para obtener el dcumento del expediente y ya no eliminarlo,
//		sino cambiar el estatus a 0
		
		ExpedienteDTO expedienteDTO = expedientesDAO.getExpedientePorTipo(infracNum, tipo);
		
		expedienteDTO.setActivo(0);
		
		expedientesDAO.saveOrUpdate(expedienteDTO);
		
		return new ResponseEntity<String>("true", HttpStatus.OK);
	}
	
//	Este método ya no se usa
	private boolean validarArchivo(String base64)
	{
		String[] formatos = {"image/bmp", "image/gif", "image/jpg", "image/tif", "image/png", "image/jpeg", "application/pdf"};
		for(String f :formatos)
		{
			if(base64.contains(f))
			{
				return true;
			}
		}
		return false;
	}
	
	private TipoExpedienteVO obtenerExpediente(List<TipoExpedienteVO> lista, String tipo)
	{
		for(TipoExpedienteVO evo : lista)
		{
			if(evo.getTIPO().equals(tipo))
				return evo;
		}
		return null;
	}
	
	private static byte[] blobToArrayBytes(Blob blob){
		byte[] data=null;
		if(blob!=null){
			try {
				data = blob.getBytes(1, (int)blob.length());
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}		     
		return data;
	}
	
	public static Blob arrayBytesToBlob(byte[] archivo){
		Blob blob = NonContextualLobCreator.INSTANCE.wrap(NonContextualLobCreator.INSTANCE.createBlob(archivo));		
		return blob;
	}
}
