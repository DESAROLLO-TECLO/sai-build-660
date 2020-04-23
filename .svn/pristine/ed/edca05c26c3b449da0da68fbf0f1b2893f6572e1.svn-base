package mx.com.teclo.saicdmx.api.rest.infraccionexpediente;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.negocio.service.infracciones.InfraccionService;
import mx.com.teclo.saicdmx.negocio.service.infraccionexpediente.ExpedienteService;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.infracciones.InfraccionesImagenesDTO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.ImagenesExpedientesReporteVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.InfraccionValidaImagenVO;
import mx.com.teclo.saicdmx.persistencia.vo.vconsultaexpediente.VConsultaExpediente;
import mx.com.teclo.saicdmx.persistencia.vo.vconsultaexpediente.VDirectorioDigitalizacionVO;
import mx.com.teclo.saicdmx.persistencia.vo.vconsultaexpediente.VImagenesHandHeldVO;
import mx.com.teclo.saicdmx.persistencia.vo.vconsultaexpediente.VImagenesIngresoVO;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.UsuarioFirmadoVO;

@RestController
public class InfraccionExpedienteRestController {
	
	@Autowired
	private ExpedienteService expedienteService;
	
	@Autowired
	private InfraccionService infraccionService;
	
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;

	@RequestMapping(value="infraccionExpediente", method = RequestMethod.GET)
	public ResponseEntity<List<VConsultaExpediente>> buscaVConsultaExpediente(@RequestParam("valor") String valor){
		List<VConsultaExpediente> VConsultaExpedientes = expedienteService.getVConsultaExpediente(valor);
		return new ResponseEntity<List<VConsultaExpediente>>(VConsultaExpedientes, HttpStatus.OK);
	}
	
	@RequestMapping(value="imagenesIngreso", method = RequestMethod.GET)
	public ResponseEntity<List<VImagenesIngresoVO>> buscaVImagenesIngresoVO(@RequestParam("valor") String valor){
		List<VImagenesIngresoVO> vImagenesIngresoVO = expedienteService.getVImagenesIngresoVO(valor);
		return new ResponseEntity<List<VImagenesIngresoVO>>(vImagenesIngresoVO, HttpStatus.OK);
	}
	
	@RequestMapping(value="directorioDigitalizacion", method = RequestMethod.GET)
	public ResponseEntity<List<VDirectorioDigitalizacionVO>> buscaVImagenesIngresoVO(@RequestParam("valor") String valor, @RequestParam("anvRev") String anvRev){
		List<VDirectorioDigitalizacionVO> vDirectorioDigitalizacionVO = expedienteService.getVDirectorioDigitalizacionVO(valor,anvRev);
		return new ResponseEntity<List<VDirectorioDigitalizacionVO>>(vDirectorioDigitalizacionVO, HttpStatus.OK);
	}
	
	@RequestMapping(value="imagenesHandHeld", method = RequestMethod.GET)
	public ResponseEntity<List<VImagenesHandHeldVO>> buscaVImagenesHandHeldVO(@RequestParam("valor") String valor){
		List<VImagenesHandHeldVO> vImagenesHandHeldVO = expedienteService.getVImagenesHandHeldVO(valor);
		return new ResponseEntity<List<VImagenesHandHeldVO>>(vImagenesHandHeldVO, HttpStatus.OK);
	}
	
	@RequestMapping(value="/spValidaImagenesExpediente", method = RequestMethod.POST)
	//@PreAuthorize("hasAnyAuthority('SP_INFRACCION_VALIDA_IMAGEN')")
	public ResponseEntity<InfraccionValidaImagenVO> sSPInfraccionValidaImagenVO
	(@RequestBody InfraccionValidaImagenVO infraccionValidaImagenVO)throws BusinessException{
		UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
		//EmpleadosDTO empleado = empleadoService.getEmpleadoById(usuario.getId());
		
		infraccionValidaImagenVO.setpEmpId(usuario.getId());
		infraccionValidaImagenVO = expedienteService.SPInfraccionValidaImagenVO(infraccionValidaImagenVO);
		return new ResponseEntity<InfraccionValidaImagenVO>(infraccionValidaImagenVO, HttpStatus.OK);
	}

	@RequestMapping(value="/descargaImagenExpediente", method = RequestMethod.GET)
	public ResponseEntity<byte[]> descargaImgenExpediente(@RequestParam("ruta") String ruta) throws IOException{

		byte[] bytes = expedienteService.descargarImagenInfraccion(ruta);
		
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.parseMediaType("application/jpg"));
		headers.add("Content-Disposition", "attachmnt; filename ="+"filename.jpg");
		headers.add("filename", "filename.jpg");
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

		return new ResponseEntity<byte[]>(bytes, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/reporteExpedienteClasico", method = RequestMethod.GET)
	//@PreAuthorize("hasAnyAuthority('REPORTE_CONSULTA_INFRACCION')")
	public ResponseEntity<byte[]> generarReporteConsultaInfraccion(
			@RequestParam(value = "infracNum") String infracNum,
			@RequestParam(value = "articulo") String articulo,
			@RequestParam(value = "motivacion") String motivacion,
			@RequestParam(value = "listaImgRepExp") String listaImgRepExp
		){
		
		List<ImagenesExpedientesReporteVO> listaImgExpediente = conversionAngularJavaFiles(listaImgRepExp);
		List<ImagenesExpedientesReporteVO> listaImgExpedienteInfrac = new ArrayList<ImagenesExpedientesReporteVO>();
		List<ImagenesExpedientesReporteVO> listaImgExpedienteHandhe = new ArrayList<ImagenesExpedientesReporteVO>();
		List<ImagenesExpedientesReporteVO> listaImgExpedienteExpPago = new ArrayList<ImagenesExpedientesReporteVO>();
		List<ImagenesExpedientesReporteVO> listaImgExpedienteExpAR = new ArrayList<ImagenesExpedientesReporteVO>();
		Integer countListaInfrac = 0;
		Integer countListaHandhe = 0;
		for(int f = 0; f < listaImgExpediente.size(); f++){
			String rutaImg = null;
			String nombre = listaImgExpediente.get(f).getFilename(); 
			String tipo = listaImgExpediente.get(f).getFiletype();
			String base64 = listaImgExpediente.get(f).getBase64();
			Boolean fileEnBase = listaImgExpediente.get(f).getFileEnBase();
			String fileNombreCatDocumento = listaImgExpediente.get(f).getFileNombreCatDocumento();
			ImagenesExpedientesReporteVO imagenTmp = new ImagenesExpedientesReporteVO();
			if(tipo.equals("INFRACCION")){
				try {
					//infraccionesImagenesDTO = infraccionService.buscaFotoPorNombreArchivo(nombre);
					InfraccionesImagenesDTO infraccionesImagenesDTO = new InfraccionesImagenesDTO();
					infraccionesImagenesDTO = infraccionService.buscaFotoPorNombreArchivo(nombre);
					
					Blob blob = infraccionesImagenesDTO.getFoto();
					byte[] blobAsBytes = null;
					int blobLength;
					blobLength = (int) blob.length();
					blobAsBytes = blob.getBytes(1, blobLength);
				
					imagenTmp.setFileBase64toBlob(new ByteArrayInputStream(blobAsBytes));
					imagenTmp.setFilename(nombre);
					imagenTmp.setFiletype(tipo);
					listaImgExpedienteInfrac.add(countListaInfrac, imagenTmp);
				} catch (SQLException e) {
					
				}
			}else if(tipo.equals("HANDHELD")){
				try {
					//http://imagenes.infracciones.df.gob.mx/
					String rutaImagenHandheld = "";
					byte currentXMLBytesImagenHandheld[] = null;
					
					rutaImg = expedienteService.consultaRutaImg(tipo);
					
					ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
					BufferedImage imagen = null;
					URL url = null;
					//rutaImagenHandheld = "http://www.frontera.info/Edicionenlinea/Fotos/Noticias/1895139-N.JPG";
					rutaImagenHandheld = rutaImg + nombre;
					url = new URL(rutaImagenHandheld);
					imagen = ImageIO.read(url);
					ImageIO.write(imagen, "jpg", outputStream);
					
					outputStream.flush();
					currentXMLBytesImagenHandheld = outputStream.toByteArray();
					outputStream.close();
					imagenTmp.setFileBase64toBlob(new ByteArrayInputStream(currentXMLBytesImagenHandheld));
					imagenTmp.setFilename(nombre);
					imagenTmp.setFiletype(tipo);
					listaImgExpedienteHandhe.add(countListaInfrac, imagenTmp);
				} catch (IOException e1) {
					
				}
			}else if(tipo.equals("EXPPAGO")){
				if(fileEnBase){
					byte[] blobAsBytes = Base64.decodeBase64(base64);
					imagenTmp.setFileBase64toBlob(new ByteArrayInputStream(blobAsBytes));
					imagenTmp.setFilename(nombre);
					imagenTmp.setFiletype(tipo);
					imagenTmp.setFileNombreCatDocumento(fileNombreCatDocumento); 
					listaImgExpedienteExpPago.add(countListaInfrac, imagenTmp);
				}
			}else if(tipo.equals("EXP-AR")){
				try {
					String rutaImagenHandheld = "";
					byte currentXMLBytesImagenHandheld[] = null;
					
					ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
					BufferedImage imagen = null;
					URL url = null;
					
					rutaImagenHandheld = "https://www.eldictamen.mx/wp-content/uploads/2018/03/multas2.jpg";
					url = new URL(rutaImagenHandheld);
					imagen = ImageIO.read(url);
					ImageIO.write(imagen, "jpg", outputStream);
					
					outputStream.flush();
					currentXMLBytesImagenHandheld = outputStream.toByteArray();
					outputStream.close();
					//listaImgExpedienteHandhe.get(f).setFileBase64toBlob(new ByteArrayInputStream(currentXMLBytesImagenHandheld));
					imagenTmp.setFileBase64toBlob(new ByteArrayInputStream(currentXMLBytesImagenHandheld));
					imagenTmp.setFilename(nombre);
					imagenTmp.setFiletype(tipo);
					listaImgExpedienteExpAR.add(countListaInfrac, imagenTmp);
				} catch (IOException e1) {
					
				}
			}
		}
		
		String filename = "Reporte_Expediente.pdf";
		
		ByteArrayOutputStream outputStream = expedienteService.generaReporteExpedienteClasico(
					infracNum, articulo, motivacion, listaImgExpedienteInfrac, listaImgExpedienteHandhe, 
					listaImgExpedienteExpPago, listaImgExpedienteExpAR);
		byte[] bytes = outputStream.toByteArray();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		headers.add("Content-Disposition", "attachmnt; filename =" + filename);
		headers.add("filename", filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		headers.setContentLength(bytes.length);
		return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
	}
	
	private List<ImagenesExpedientesReporteVO> conversionAngularJavaFiles (String files){
		List<ImagenesExpedientesReporteVO> newObject = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			newObject = mapper.readValue(files.toString(),  new TypeReference<List<ImagenesExpedientesReporteVO>>(){});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newObject;
	}
}
