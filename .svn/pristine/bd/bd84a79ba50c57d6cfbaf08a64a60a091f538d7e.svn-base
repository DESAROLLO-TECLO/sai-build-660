package mx.com.teclo.saicdmx.negocio.service.semovi;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.FormatterClosedException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.semoviarchivoslicencia.SemoviArchivosLicenciaDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.semoviarchivoslicencia.SemoviVehiculosRobadosDAOImpl;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.semovicattipoarchivo.SemoviCatTipoArchivoDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.semovicontrollicencia.SemoviControlLicenciasDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.semovilicenciacanceladas.SemoviLicenciaCanceladasDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.semovilicenciapuntosacum.SemoviLicenciaPuntosAcumDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.semovi.SemoviArchivosLicenciaDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.semovi.SemoviControlLicenciasDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.semovi.SemoviLicenciaCanceladasDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.semovi.SemoviLicenciaPuntosAcumDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.semovi.SemoviVehiculoRobadoDTO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.infracciones.InfraccionesPuntosLicenciaMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.semovi.SemoviVehiculosRobadosMybatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.VCajaConsultaConTieneOperacionesVO;
import mx.com.teclo.saicdmx.persistencia.vo.comuns.FilterValuesVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.InfraccionesConPuntosLicenciaVO;
import mx.com.teclo.saicdmx.persistencia.vo.semovi.SemoviArchivosLicenciaVO;
import mx.com.teclo.saicdmx.persistencia.vo.semovi.VehiculoRobadoHistoricoVO;
import mx.com.teclo.saicdmx.persistencia.vo.semovi.VehiculosRobadosVO;
import mx.com.teclo.saicdmx.util.comun.RutinasTiempoImpl;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.UsuarioFirmadoVO;
import mx.com.teclomexicana.generaexcel.reporteexcel.PeticionReporteBOImpl;
import mx.com.teclomexicana.generaexcel.vo.PeticioReporteVO;
import mx.com.teclomexicana.generaexcel.vo.PropiedadesReporte;



/**
 * 
 * @author javier07
 *
 */
@Service
public class SemoviServiceImpl implements SemoviService {
	
	private static final Logger logger = Logger
			.getLogger(SemoviServiceImpl.class);
	
	@Autowired
	private InfraccionesPuntosLicenciaMyBatisDAO infraccionesPuntosLicenciaMyBatisDAO;
	
	@Autowired
	private SemoviArchivosLicenciaDAO semoviArchivosLicenciaDAO;
	
	@Autowired
	private SemoviCatTipoArchivoDAO semoviCatTipoArchivoDAO;
	
	@Autowired
	private SemoviLicenciaCanceladasDAO semoviLicenciaCanceladasDAO;
	
	@Autowired
	private SemoviLicenciaPuntosAcumDAO semoviLicenciaPuntosAcumDAO;
	
	@Autowired
	private SemoviControlLicenciasDAO semoviControlLicenciasDAO;
	
	@Autowired
	private ParametroService parametros;
	
	@Autowired
	private ArchivoService archivoService;
	
	@Autowired
	private ArchivoInfraccionesExcel archivoInfraccionesExcel;
	
	@Autowired
	private SemoviVehiculosRobadosMybatisDAO semoviVehiculosRobados;
	
	RutinasTiempoImpl rutinasTiempoImpl = new RutinasTiempoImpl();
	
	@Autowired
	UsuarioFirmadoService usuarioFirmadoService;
	
	@Autowired
	SemoviVehiculosRobadosDAOImpl  SemoviVehiculosRobadosDAO;
	
	
	private ByteArrayOutputStream reporteVehRob;
	
	private ByteArrayOutputStream byteArrayOutputStream = null;
	/**
	 * {@inheritDoc}
	 */
	@Transactional
	public Map<String, Object> generaArchivoPuntoInfraccion(String nombreArchivo, String fechaArchivo, String empleadoId,List<InfraccionesConPuntosLicenciaVO> listaInfraccionesConPuntosLicenciaVO){
		SemoviArchivosLicenciaDTO semoviArchivosLicenciaDTO = new SemoviArchivosLicenciaDTO();
		Date fechaCreacionArchivo = new Date();
		String nombreArchivoExcel = nombreArchivo + fechaArchivo.replaceAll("-", "") + "-" +rutinasTiempoImpl.getStringDateFromFormta("dd-MM-yyy HH:mm:ss", fechaCreacionArchivo).replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "") +".xls";

		semoviArchivosLicenciaDTO.setNombreArchivo(nombreArchivoExcel);
		semoviArchivosLicenciaDTO.setFechaArchivo(rutinasTiempoImpl.convertirStringDate(fechaArchivo.replaceAll("-", "/")));
		semoviArchivosLicenciaDTO.setSemoviCatTipoArchivoDTO(semoviCatTipoArchivoDAO.buscaTipoInfraccionPuntos());
		semoviArchivosLicenciaDTO.setCreadoPor(Long.parseLong(empleadoId));
		semoviArchivosLicenciaDTO.setFechaCreacion(new Date());
		semoviArchivosLicenciaDTO.setActivo(true);
		semoviArchivosLicenciaDTO.setModificadoPor(Long.parseLong(empleadoId));
		semoviArchivosLicenciaDTO.setUltimaModificacion(new Date());
		semoviArchivosLicenciaDTO.setNumeroRegistros(listaInfraccionesConPuntosLicenciaVO.size());
		
		semoviArchivosLicenciaDAO.save(semoviArchivosLicenciaDTO);
		
		return archivoInfraccionesExcel.creaArchivoExcel(listaInfraccionesConPuntosLicenciaVO, nombreArchivoExcel, fechaArchivo);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<Object> procesaArchivosSemovi(InputStream file, String fechaArchivo, Long usuarioId, Integer tipoArchivo, String nombreArchivo, Date fechaCreacion){
		List<Object> resultado = null;
		
		if(tipoArchivo == 2){
			resultado = this.lecturaArchivoPuntosDeLicencia(file, fechaArchivo, nombreArchivo, usuarioId, tipoArchivo, fechaCreacion);
		}else{
			resultado = this.lecturaArchivoLicenciasCanceladas(file, fechaArchivo, nombreArchivo, usuarioId, tipoArchivo, fechaCreacion);
		}
	
		return resultado;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Transactional
	public List<Object> cargaArchivosSemovi(List<Object> listaResultados, Long usuarioId, Integer tipoArchivo){
		List<Object> resultado = null;
		
		if(tipoArchivo == 2){
			this.guardaContenidoPuntosLicencia(listaResultados, usuarioId);
		}else{
			this.guardaContenidoLicenciasCanceladas(listaResultados, usuarioId);
		}
	
		return resultado;
	}
	
	/**
	 * @author UnitisDes0416
	 * @param file
	 * @param fechaArchivo
	 * @param nombreArchivo 
	 * @param usuarioId
	 * @param tipoArchivo
	 * @return
	 * @throws IOException
	 */
	@Transactional
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Object> lecturaArchivoPuntosDeLicencia(InputStream file, String fechaArchivo, String nombreArchivo, Long usuarioId, Integer tipoArchivo, Date fechaCreacion) {
		List<Object> listaResultado = new ArrayList<Object>();
		for (int i = 0; i < 4; i++) {
			listaResultado.add(new Object());
		}
		List<SemoviLicenciaPuntosAcumDTO> listaSemoviLicenciaPuntosAcumDTO = new ArrayList<SemoviLicenciaPuntosAcumDTO>();
		RutinasTiempoImpl rutinasTiempoImpl = new RutinasTiempoImpl();
		SemoviLicenciaPuntosAcumDTO semoviLicenciaPuntosAcumDTO= null;
		SemoviArchivosLicenciaDTO semoviArchivosLicenciaDTO = new SemoviArchivosLicenciaDTO();
		String resultado = null;
		listaResultado.set(1, nombreArchivo);
		Boolean archivoValido = null;
		// Se crea el archivo para la escribir en el servidor
		FileOutputStream archivo = null;
		String anio = rutinasTiempoImpl.obtenerAnioFecha(new Date());
		String rutaArchivo = parametros.getRutaSemovi() + "ENTRADA/"+ anio + "/" + nombreArchivo;
		archivoService.creaDirectoriosSemovi(anio);
		
        File archivoXLSX = new File(rutaArchivo);
        
        try {
			//termina de crear archivo
			
			XSSFWorkbook Libro_trabajo = new XSSFWorkbook(file);
			XSSFSheet Hoja_hssf = Libro_trabajo.getSheetAt(0);
			Iterator Iterador_de_Fila = Hoja_hssf.rowIterator();
			
			XSSFRow Fila_hssf= null;
			Iterator iterador = null;
			XSSFCell Celda_hssf = null;
			List Lista_Celda_temporal = null;
			Boolean validacionContenido = true;
			Integer contadorCelda = 0;
			Integer contadorFila = 0;
			Integer PuntosAcumulados = 0;
			
		    semoviArchivosLicenciaDTO = semoviArchivosLicenciaDAO.buscaArchivoNombreFechaCreacion(nombreArchivo);
			if(Iterador_de_Fila.hasNext()){
				Fila_hssf = (XSSFRow) Iterador_de_Fila.next();
				iterador = Fila_hssf.cellIterator();
				contadorFila++;
				
				if(iterador.hasNext()){
					while(iterador.hasNext()){
						Celda_hssf = (XSSFCell) iterador.next();
						
						contadorCelda ++;
						if(StringUtils.isBlank(Celda_hssf.toString())){
							validacionContenido = false;
							break;
						}
					}
				}else{
					contadorCelda ++;
					validacionContenido = false;
				}
				
				if(validacionContenido){
					if(contadorCelda == 5){
						while (Iterador_de_Fila.hasNext()) {
							contadorCelda = 0;
							Fila_hssf = (XSSFRow) Iterador_de_Fila.next();
							iterador = Fila_hssf.cellIterator();
							Lista_Celda_temporal = new ArrayList();
							contadorFila++;
							
							if(iterador.hasNext()){
								while (iterador.hasNext()) {
									Celda_hssf = (XSSFCell) iterador.next();
									
									contadorCelda ++;
									if(StringUtils.isNotBlank(Celda_hssf.toString())){
										if(contadorCelda != 4){
											Lista_Celda_temporal.add(Celda_hssf.toString());
										}else{
											try{
												Lista_Celda_temporal.add(Celda_hssf.getDateCellValue());
											}catch(Exception e){
												logger.error(e.getMessage());
												Lista_Celda_temporal.add("error");
											}
										}
									}else{
										validacionContenido = false;
										break;
									}
									
								}
							}else{
								contadorCelda ++;
								validacionContenido = false;
								break;
							}
							
							if(validacionContenido){
								if(Lista_Celda_temporal.size() == 5){
									try{
										if(Lista_Celda_temporal.get(3).equals("error")){
											throw new FormatterClosedException();
										}
										PuntosAcumulados = Integer.parseInt(Lista_Celda_temporal.get(4).toString().split("\\.")[0]);
										
										semoviLicenciaPuntosAcumDTO= new SemoviLicenciaPuntosAcumDTO();
										
										semoviLicenciaPuntosAcumDTO.setFolioLicencia(Lista_Celda_temporal.get(0).toString());
										semoviLicenciaPuntosAcumDTO.setTipoLicencia(Lista_Celda_temporal.get(1).toString());
										semoviLicenciaPuntosAcumDTO.setCurp(Lista_Celda_temporal.get(2).toString());
										semoviLicenciaPuntosAcumDTO.setFechaUltimaInfraccion((Date)Lista_Celda_temporal.get(3));
										semoviLicenciaPuntosAcumDTO.setPuntosdAcumulados(PuntosAcumulados);
										semoviLicenciaPuntosAcumDTO.setSemoviArchivosLicenciaDTO(semoviArchivosLicenciaDTO);
										semoviLicenciaPuntosAcumDTO.setCreadoPor(usuarioId);
										semoviLicenciaPuntosAcumDTO.setFechaCreacion(new Date());
										
										listaSemoviLicenciaPuntosAcumDTO.add(semoviLicenciaPuntosAcumDTO);
										
										resultado = "El archivo se cargo correctamente";
										archivoValido = true;
										listaResultado.set(0, resultado);
										listaResultado.set(3, archivoValido);
									}catch (FormatterClosedException e) {
										logger.error(e.getMessage());
										resultado = "El archivo tiene datos inconsistentes en Fila: " + contadorFila + " - Columna: 4";
										archivoValido = false;
										listaResultado.set(0, resultado);
										listaResultado.set(3, archivoValido);
										break;
									}catch (NumberFormatException e2) {
										logger.error(e2.getMessage());
										resultado = "El archivo tiene datos inconsistentes en Fila: " + contadorFila + " - Columna: 5";
										archivoValido = false;
										listaResultado.set(0, resultado);
										listaResultado.set(3, archivoValido);
										break;
									}
								}else{
									if(contadorCelda > 5){
										resultado = "El archivo excede las columnas requeridas en Fila: " + contadorFila + " - Columna: " + contadorCelda;
										archivoValido = false;
										listaResultado.set(0, resultado);
										listaResultado.set(3, archivoValido);
										break;
									}else if(contadorCelda < 5){
										resultado = "El archivo tiene menos columnas de las requeridas en Fila: " + contadorFila + " - Columna: " + contadorCelda;
										archivoValido = false;
										listaResultado.set(0, resultado);
										listaResultado.set(3, archivoValido);
										break;
									}
								}
							}else{
								resultado = "El archivo no tiene datos en Fila: " + contadorFila + " - Columna: " + contadorCelda;
								archivoValido = false;
								listaResultado.set(0, resultado);
								listaResultado.set(3, archivoValido);
								break;
							}
						}
						listaResultado.set(2, listaSemoviLicenciaPuntosAcumDTO);
					}else{
						if(contadorCelda > 5){
							resultado = "El archivo excede las columnas requeridas en Fila: " + contadorFila + " - Columna: " + contadorCelda;
							archivoValido = false;
							listaResultado.set(0, resultado);
							listaResultado.set(3, archivoValido);
						}else if(contadorCelda < 5){
							resultado = "El archivo tiene menos columnas de las requeridas en Fila: " + contadorFila + " - Columna: " + contadorCelda;
							archivoValido = false;
							listaResultado.set(0, resultado);
							listaResultado.set(3, archivoValido);
						}
					}
				}else{
					resultado = "El archivo no tiene datos en Fila: " + contadorFila + " - Columna: " + contadorCelda;
					archivoValido = false;
					listaResultado.set(0, resultado);
					listaResultado.set(3, archivoValido);
				}
			}else{
				resultado = "El archivo no tiene datos en Fila: " + contadorFila + " - Columna: " + contadorCelda;
				archivoValido = false;
				listaResultado.set(0, resultado);
				listaResultado.set(3, archivoValido);
			}
			
			//escribe el archivo en el servidor
			if(archivoValido){
				archivoXLSX.createNewFile();
				
				archivo = new FileOutputStream(archivoXLSX.getAbsoluteFile());
				
				Libro_trabajo.write(archivo);
				
				archivo.close();
			}
        } catch (FileNotFoundException e) {
        	resultado = "Fallo la lectura del archivo verificar su contenido";
        	listaResultado.set(0, resultado);
        	listaResultado.set(3, false);
			logger.error(e.getMessage());
		}catch (IOException e1) {
			resultado = "Fallo la lectura del archivo verificar su contenido ";
			listaResultado.set(0, resultado);
			logger.error(e1.getMessage());
			listaResultado.set(3, false);
		} catch(Exception e3){
			resultado = "Fallo la lectura del archivo verificar su contenido ";
			listaResultado.set(0, resultado);
			listaResultado.set(3, false);
			logger.error(e3.getMessage());
		}
        
        return listaResultado;
	}
	
	/**
	 * @author UnitisDes0416
	 * @param file InputStream
	 * @param fechaArchivo String
	 * @param nombreArchivo String
	 * @param usuarioId Long
	 * @param tipoArchivo Integer
	 * @param fechaCreacion Date
	 * @return List<Object>
	 */
	@Transactional
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Object> lecturaArchivoLicenciasCanceladas(InputStream file, String fechaArchivo, String nombreArchivo, Long usuarioId, Integer tipoArchivo, Date fechaCreacion) {
		List<Object> listaResultado = new ArrayList<Object>();
		for (int i = 0; i < 4; i++) {
			listaResultado.add(new Object());
		}
		List<SemoviLicenciaCanceladasDTO> listaSemoviLicenciaCanceladasDTO = new ArrayList<SemoviLicenciaCanceladasDTO>();
		RutinasTiempoImpl rutinasTiempoImpl = new RutinasTiempoImpl();
		SemoviLicenciaCanceladasDTO semoviLicenciaCanceladasDTO= null;
		SemoviArchivosLicenciaDTO semoviArchivosLicenciaDTO = new SemoviArchivosLicenciaDTO();
		String resultado = null;
		listaResultado.set(1, nombreArchivo);
		Boolean archivoValido = false;
		
		// Se crea el archivo para la escribir en el servidor
		FileOutputStream archivo = null;
		String anio = rutinasTiempoImpl.obtenerAnioFecha(new Date());
		String rutaArchivo = parametros.getRutaSemovi() + "ENTRADA/"+ anio + "/" + nombreArchivo;
		
		archivoService.creaDirectoriosSemovi(anio);
		
        File archivoXLSX = new File(rutaArchivo);
        
        try {
	        //termina de crear archivo
	        XSSFWorkbook Libro_trabajo = new XSSFWorkbook(file);
			XSSFSheet Hoja_hssf = Libro_trabajo.getSheetAt(0);
			Iterator Iterador_de_Fila = Hoja_hssf.rowIterator();
			
			XSSFRow Fila_hssf= null;
			Iterator iterador = null;
			XSSFCell Celda_hssf = null;
			List Lista_Celda_temporal = null;
			Boolean validacionContenido = true;
			Integer contadorCelda = 0;
			Integer contadorFila = 0;
			Integer PuntosAcumulados = 0;
			
		    semoviArchivosLicenciaDTO = semoviArchivosLicenciaDAO.buscaArchivoNombreFechaCreacion(nombreArchivo);
			if(Iterador_de_Fila.hasNext()){
				Fila_hssf = (XSSFRow) Iterador_de_Fila.next();
				iterador = Fila_hssf.cellIterator();
				contadorFila++;
				
				if(iterador.hasNext()){
					while(iterador.hasNext()){
						Celda_hssf = (XSSFCell) iterador.next();
						
						contadorCelda ++;
						if(StringUtils.isBlank(Celda_hssf.toString())){
							validacionContenido = false;
							break;
						}
					}
				}else{
					contadorCelda ++;
					validacionContenido = false;
				}
				
				if(validacionContenido){
					if(contadorCelda == 5){
						while (Iterador_de_Fila.hasNext()) {
							contadorCelda = 0;
							Fila_hssf = (XSSFRow) Iterador_de_Fila.next();
							iterador = Fila_hssf.cellIterator();
							Lista_Celda_temporal = new ArrayList();
							contadorFila++;
							
							if(iterador.hasNext()){
								while (iterador.hasNext()) {
									Celda_hssf = (XSSFCell) iterador.next();
									
									contadorCelda ++;
									if(StringUtils.isNotBlank(Celda_hssf.toString())){
										if(contadorCelda != 4){
											Lista_Celda_temporal.add(Celda_hssf.toString());
										}else{
											try{
												Lista_Celda_temporal.add(Celda_hssf.getDateCellValue());
											}catch(Exception e){
												Lista_Celda_temporal.add("error");
											}
										}
									}else{
										validacionContenido = false;
										break;
									}
									
								}
							}else{
								contadorCelda ++;
								validacionContenido = false;
								break;
							}
							
							if(validacionContenido){
								if(Lista_Celda_temporal.size() == 5){
									try{
										if(Lista_Celda_temporal.get(3).equals("error")){
											throw new FormatterClosedException();
										}
										PuntosAcumulados = Integer.parseInt(Lista_Celda_temporal.get(4).toString().split("\\.")[0]);
										
										semoviLicenciaCanceladasDTO= new SemoviLicenciaCanceladasDTO();
								
										semoviLicenciaCanceladasDTO.setFolioLicencia(Lista_Celda_temporal.get(0).toString());
										semoviLicenciaCanceladasDTO.setTipoLicencia(Lista_Celda_temporal.get(1).toString());
										semoviLicenciaCanceladasDTO.setCurp(Lista_Celda_temporal.get(2).toString());
										semoviLicenciaCanceladasDTO.setFechaUltimaInfraccion((Date)Lista_Celda_temporal.get(3));
										semoviLicenciaCanceladasDTO.setPuntosdAcumulados(PuntosAcumulados);
										semoviLicenciaCanceladasDTO.setSemoviArchivosLicenciaDTO(semoviArchivosLicenciaDTO);
										semoviLicenciaCanceladasDTO.setCreadoPor(usuarioId);
										semoviLicenciaCanceladasDTO.setFechaCreacion(new Date());
										
										listaSemoviLicenciaCanceladasDTO.add(semoviLicenciaCanceladasDTO);
										resultado = "El archivo se cargo correctamente";
										archivoValido = true;
										listaResultado.set(0, resultado);
										listaResultado.set(3, archivoValido);
									}catch (FormatterClosedException e) {
										logger.error(e.getMessage());
										resultado = "El archivo tiene datos inconsistentes en Fila: " + contadorFila + " - Columna: 4";
										archivoValido = false;
										listaResultado.set(0, resultado);
										listaResultado.set(3, archivoValido);
										break;
									}catch (NumberFormatException e2) {
										logger.error(e2.getMessage());
										resultado = "El archivo tiene datos inconsistentes en Fila: " + contadorFila + " - Columna: 5";
										archivoValido = false;
										listaResultado.set(0, resultado);
										listaResultado.set(3, archivoValido);
										break;
									}
								}else{
									if(contadorCelda > 5){
										resultado = "El archivo excede las columnas requeridas en Fila: " + contadorFila + " - Columna: " + contadorCelda;
										archivoValido = false;
										listaResultado.set(0, resultado);
										listaResultado.set(3, archivoValido);
										break;
									}else if(contadorCelda < 5){
										resultado = "El archivo tiene menos columnas de las requeridas en Fila: " + contadorFila + " - Columna: " + contadorCelda;
										archivoValido = false;
										listaResultado.set(0, resultado);
										listaResultado.set(3, archivoValido);
										break;
									}
								}
							}else{
								resultado = "El archivo no tiene datos en Fila: " + contadorFila + " - Columna: " + contadorCelda;
								archivoValido = false;
								listaResultado.set(0, resultado);
								listaResultado.set(3, archivoValido);
								break;
							}
						}
						listaResultado.set(2, listaSemoviLicenciaCanceladasDTO);
					}else{
						if(contadorCelda > 5){
							resultado = "El archivo excede las columnas requeridas en Fila: " + contadorFila + " - Columna: " + contadorCelda;
							archivoValido = false;
							listaResultado.set(0, resultado);
							listaResultado.set(3, archivoValido);
						}else if(contadorCelda < 5){
							resultado = "El archivo tiene menos columnas de las requeridas en Fila: " + contadorFila + " - Columna: " + contadorCelda;
							archivoValido = false;
							listaResultado.set(0, resultado);
							listaResultado.set(3, archivoValido);
						}
					}
				}else{
					resultado = "El archivo no tiene datos en Fila: " + contadorFila + " - Columna: " + contadorCelda;
					archivoValido = false;
					listaResultado.set(0, resultado);
					listaResultado.set(3, archivoValido);
				}
			}else{
				resultado = "El archivo no tiene datos en Fila: " + contadorFila + " - Columna: " + contadorCelda;
				archivoValido = false;
				listaResultado.set(0, resultado);
				listaResultado.set(3, archivoValido);
			}
			
			//escribe el archivo en el servidor
			if(archivoValido){
				archivoXLSX.createNewFile();
				
				archivo = new FileOutputStream(archivoXLSX.getAbsoluteFile());
				
				Libro_trabajo.write(archivo);
				archivo.close();
			}
        } catch (FileNotFoundException e) {
        	resultado = "Fallo la lectura del archivo verificar su contenido";
        	listaResultado.set(0, resultado);
        	listaResultado.set(3, false);
			logger.error(e.getMessage());
		}catch (IOException e1) {
			resultado = "Fallo la lectura del archivo verificar su contenido ";
			listaResultado.set(0, resultado);
			logger.error(e1.getMessage());
			listaResultado.set(3, false);
		} catch(Exception e3){
			resultado = "Fallo la lectura del archivo verificar su contenido ";
			listaResultado.set(0, resultado);
			listaResultado.set(3, false);
			logger.error(e3.getMessage());
		}
        
        return listaResultado;
	}
	
	/**
	 * @author UnitisDes0416
	 * @param listaResultados List<Object>
	 * @param idUsuario Long
	 */
	
	@SuppressWarnings("unchecked")
	public void guardaContenidoLicenciasCanceladas(List<Object> listaResultados, Long idUsuario){
		List<SemoviLicenciaCanceladasDTO> listalicenciasCanceladas;
		SemoviControlLicenciasDTO semoviControlLicenciasDTO;
		String nombreArchivo = (String) listaResultados.get(1);
		Boolean respuesta = (Boolean) listaResultados.get(3);
		SemoviArchivosLicenciaDTO semoviArchivosLicenciaDTO = semoviArchivosLicenciaDAO.buscaArchivoNombreFechaCreacion(nombreArchivo);
		
		if(respuesta){
			listalicenciasCanceladas = (List<SemoviLicenciaCanceladasDTO>) listaResultados.get(2);
			
			
			for (SemoviLicenciaCanceladasDTO semoviLicenciaCanceladasDTO : listalicenciasCanceladas) {
				semoviLicenciaCanceladasDTO.setSemoviArchivosLicenciaDTO(semoviArchivosLicenciaDTO);
				semoviLicenciaCanceladasDAO.save(semoviLicenciaCanceladasDTO);
				
				semoviControlLicenciasDTO = semoviControlLicenciasDAO.buscaLicenciaPorFolioLicencia(semoviLicenciaCanceladasDTO.getFolioLicencia());
					
				if(semoviControlLicenciasDTO == null){
					semoviControlLicenciasDTO = new SemoviControlLicenciasDTO();
					
					semoviControlLicenciasDTO.setFolioLicencia(semoviLicenciaCanceladasDTO.getFolioLicencia());
					semoviControlLicenciasDTO.setTipoLicencia(semoviLicenciaCanceladasDTO.getTipoLicencia());
					semoviControlLicenciasDTO.setCurp(semoviLicenciaCanceladasDTO.getCurp());
					semoviControlLicenciasDTO.setFechaUltimaInfraccion(semoviLicenciaCanceladasDTO.getFechaUltimaInfraccion());
					semoviControlLicenciasDTO.setPuntosdAcumulados(semoviLicenciaCanceladasDTO.getPuntosdAcumulados());
					semoviControlLicenciasDTO.setActivo(true);
					semoviControlLicenciasDTO.setEstatusLicencia(false);
					semoviControlLicenciasDTO.setCreadoPor(semoviLicenciaCanceladasDTO.getCreadoPor());
					semoviControlLicenciasDTO.setFechaCreacion(new Date());
					
					semoviControlLicenciasDAO.save(semoviControlLicenciasDTO);
				}else{
					semoviControlLicenciasDTO.setFechaUltimaInfraccion(semoviLicenciaCanceladasDTO.getFechaUltimaInfraccion());
					semoviControlLicenciasDTO.setPuntosdAcumulados(semoviLicenciaCanceladasDTO.getPuntosdAcumulados());
					semoviControlLicenciasDTO.setEstatusLicencia(false);
					semoviControlLicenciasDTO.setModificadoPor(semoviLicenciaCanceladasDTO.getCreadoPor());
					semoviControlLicenciasDTO.setUltimaModificacion(new Date());
					
					semoviControlLicenciasDAO.update(semoviControlLicenciasDTO);
				}
			}
			
			semoviArchivosLicenciaDTO.setModificadoPor(idUsuario);
			semoviArchivosLicenciaDTO.setUltimaModificacion(new Date());
			semoviArchivosLicenciaDTO.setNumeroRegistros(listalicenciasCanceladas.size());
			
			semoviArchivosLicenciaDAO.update(semoviArchivosLicenciaDTO);
		}else{
			semoviArchivosLicenciaDAO.delete(semoviArchivosLicenciaDTO);
		}
	}
	
	/**
	 * @author UnitisDes0416
	 * @param listaResultados List<Object>
	 * @param idUsuario Long
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public void guardaContenidoPuntosLicencia(List<Object> listaResultados, Long idUsuario){
		List<SemoviLicenciaPuntosAcumDTO> listaSemoviLicenciaPuntosAcum;
		SemoviControlLicenciasDTO semoviControlLicenciasDTO;
		String nombreArchivo = (String) listaResultados.get(1);
		Boolean respuesta = (Boolean) listaResultados.get(3);
		SemoviArchivosLicenciaDTO semoviArchivosLicenciaDTO = semoviArchivosLicenciaDAO.buscaArchivoNombreFechaCreacion(nombreArchivo);
		
		if(respuesta){
			listaSemoviLicenciaPuntosAcum = (List<SemoviLicenciaPuntosAcumDTO>) listaResultados.get(2);
			
			
			for (SemoviLicenciaPuntosAcumDTO semoviLicenciaPuntosAcumDTO : listaSemoviLicenciaPuntosAcum) {
				semoviLicenciaPuntosAcumDTO.setSemoviArchivosLicenciaDTO(semoviArchivosLicenciaDTO);
				semoviLicenciaPuntosAcumDAO.save(semoviLicenciaPuntosAcumDTO);
				
				semoviControlLicenciasDTO = semoviControlLicenciasDAO.buscaLicenciaPorFolioLicencia(semoviLicenciaPuntosAcumDTO.getFolioLicencia());
					
				if(semoviControlLicenciasDTO == null){
					semoviControlLicenciasDTO = new SemoviControlLicenciasDTO();
					
					semoviControlLicenciasDTO.setFolioLicencia(semoviLicenciaPuntosAcumDTO.getFolioLicencia());
					semoviControlLicenciasDTO.setTipoLicencia(semoviLicenciaPuntosAcumDTO.getTipoLicencia());
					semoviControlLicenciasDTO.setCurp(semoviLicenciaPuntosAcumDTO.getCurp());
					semoviControlLicenciasDTO.setFechaUltimaInfraccion(semoviLicenciaPuntosAcumDTO.getFechaUltimaInfraccion());
					semoviControlLicenciasDTO.setPuntosdAcumulados(semoviLicenciaPuntosAcumDTO.getPuntosdAcumulados());
					semoviControlLicenciasDTO.setActivo(true);
					semoviControlLicenciasDTO.setEstatusLicencia(true);
					semoviControlLicenciasDTO.setCreadoPor(semoviLicenciaPuntosAcumDTO.getCreadoPor());
					semoviControlLicenciasDTO.setFechaCreacion(new Date());
					
					semoviControlLicenciasDAO.save(semoviControlLicenciasDTO);
				}else{
					semoviControlLicenciasDTO.setFechaUltimaInfraccion(semoviLicenciaPuntosAcumDTO.getFechaUltimaInfraccion());
					semoviControlLicenciasDTO.setPuntosdAcumulados(semoviLicenciaPuntosAcumDTO.getPuntosdAcumulados());
					semoviControlLicenciasDTO.setEstatusLicencia(false);
					semoviControlLicenciasDTO.setModificadoPor(semoviLicenciaPuntosAcumDTO.getCreadoPor());
					semoviControlLicenciasDTO.setUltimaModificacion(new Date());
					
					semoviControlLicenciasDAO.update(semoviControlLicenciasDTO);
				}
			}
			semoviArchivosLicenciaDTO.setModificadoPor(idUsuario);
			semoviArchivosLicenciaDTO.setUltimaModificacion(new Date());
			semoviArchivosLicenciaDTO.setNumeroRegistros(listaSemoviLicenciaPuntosAcum.size());
			
			semoviArchivosLicenciaDAO.update(semoviArchivosLicenciaDTO);
		}else{			
			semoviArchivosLicenciaDAO.delete(semoviArchivosLicenciaDTO);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Transactional
	public void guardaSemoviArchivo(String nombreArchivo, String fechaArchivo, Long idUsuario, Date fechaCreacion, Integer tipoArchivo){
		SemoviArchivosLicenciaDTO semoviArchivosLicenciaDTO = new SemoviArchivosLicenciaDTO();
		RutinasTiempoImpl rutinasTiempoImpl = new RutinasTiempoImpl();
		
		if(tipoArchivo == 2){
			semoviArchivosLicenciaDTO.setNombreArchivo(nombreArchivo);
			semoviArchivosLicenciaDTO.setFechaArchivo(rutinasTiempoImpl.convertirStringDate(fechaArchivo));
			semoviArchivosLicenciaDTO.setSemoviCatTipoArchivoDTO(semoviCatTipoArchivoDAO.buscaTipoActivoPuntosLicencia());
			semoviArchivosLicenciaDTO.setActivo(true);
			semoviArchivosLicenciaDTO.setCreadoPor(idUsuario);
			semoviArchivosLicenciaDTO.setFechaCreacion(fechaCreacion);
			semoviArchivosLicenciaDTO.setModificadoPor(idUsuario);
			semoviArchivosLicenciaDTO.setUltimaModificacion(fechaCreacion);
			
			semoviArchivosLicenciaDAO.save(semoviArchivosLicenciaDTO);
		}else{
			semoviArchivosLicenciaDTO.setNombreArchivo(nombreArchivo);
			semoviArchivosLicenciaDTO.setFechaArchivo(rutinasTiempoImpl.convertirStringDate(fechaArchivo));
			semoviArchivosLicenciaDTO.setSemoviCatTipoArchivoDTO(semoviCatTipoArchivoDAO.buscaTipoActivoLicenciaCancelada());
			semoviArchivosLicenciaDTO.setActivo(true);
			semoviArchivosLicenciaDTO.setCreadoPor(idUsuario);
			semoviArchivosLicenciaDTO.setFechaCreacion(fechaCreacion);
			semoviArchivosLicenciaDTO.setModificadoPor(idUsuario);
			semoviArchivosLicenciaDTO.setUltimaModificacion(fechaCreacion);
			
			semoviArchivosLicenciaDAO.save(semoviArchivosLicenciaDTO);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional
    public List<SemoviArchivosLicenciaVO> obtenerArchivosLicencia(Long tipo, String fechaInicio,String fechaFin) {
  
    	Date fechaInicioDate = null;
    	Date fechaFinDate = null;
    	if(fechaInicio.equals("null") | fechaFin.equals("null")){
    		
    		fechaInicioDate = null;
    		fechaFin = null;
    		
    	}else{

   		 RutinasTiempoImpl utils = new RutinasTiempoImpl();     
   	     fechaInicioDate = utils.convertirStringDate(fechaInicio.replace('-', '/'));
   	     fechaFinDate = utils.convertirStringDate(fechaFin.replace('-', '/'));
    	}
       

		List<SemoviArchivosLicenciaVO> listaVO = new ArrayList<SemoviArchivosLicenciaVO>();
		List<SemoviArchivosLicenciaDTO> listaDTO = 	semoviArchivosLicenciaDAO.buscarArchivosLicencia(tipo, fechaInicioDate, fechaFinDate);
		listaVO = getListaArchivosLicenciaVO(listaDTO);    

        return listaVO;
	}

	/**
	 * {@inheritDoc}
	 */
	public byte[] recuperarArchivo(String ruta) throws IOException {
		File archivo = new File(ruta);
		archivo = archivo.getAbsoluteFile();
		byte[] arreglo = null;
	
		if(archivo.exists()){
			arreglo = archivoService.getBytesFromFile(archivo);
		}
		
		return arreglo;
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	public Map<String, Object> buscaCatTipoArchivos(){
		Map<String, Object> response = new HashMap<String, Object>();
			
//		response.put("listaInformacion", semoviCatTipoArchivoDAO.findAll());
		response.put("listaInformacion", semoviCatTipoArchivoDAO.findOrderByDesc());	
		return response;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Transactional
	public Map<String, Object> buscaCatTipoArchivosActivos(){
		Map<String, Object> response = new HashMap<String, Object>();
			
		response.put("listaInformacion", semoviCatTipoArchivoDAO.buscaCatTipoArchivosActivos());
		
		return response;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Transactional
	public Map<String, Object> validaArchivoExiste(String fechaArchivo, Integer codigoArchivo){
		Map<String, Object> reponse = new HashMap<String, Object>();
		RutinasTiempoImpl rutinasTiempoImpl = new RutinasTiempoImpl();
		Date fecha = rutinasTiempoImpl.convertirStringDate(fechaArchivo.replaceAll("-", "/"));
		
		List<SemoviArchivosLicenciaDTO> listaArchivos = semoviArchivosLicenciaDAO.buscaArchivoPorFecha(fecha, codigoArchivo);
		
		if(listaArchivos.isEmpty()){
			reponse.put("respuesta", Boolean.FALSE);
		}else{
			reponse.put("respuesta", Boolean.TRUE);
		}
		
		return reponse;
	}
	
	
	private List<SemoviArchivosLicenciaVO> getListaArchivosLicenciaVO(List<SemoviArchivosLicenciaDTO> listaArchivosLicencia) {

		List<SemoviArchivosLicenciaVO> listaArchivosLicenciaVO = new ArrayList<SemoviArchivosLicenciaVO>();
		RutinasTiempoImpl rutinasTiempoImpl = new RutinasTiempoImpl();
		
		for (SemoviArchivosLicenciaDTO archivosLicenciaDTO : listaArchivosLicencia) {

			SemoviArchivosLicenciaVO objetoVO = new SemoviArchivosLicenciaVO();
			objetoVO.setNombreArchivo(archivosLicenciaDTO.getNombreArchivo());
			objetoVO.setFechaArchivo(rutinasTiempoImpl.getStringDateFromFormta("dd/MM/yyyy",archivosLicenciaDTO.getFechaArchivo()));
			objetoVO.setFechaCreacion(rutinasTiempoImpl.getStringDateFromFormta("dd/MM/yyyy",archivosLicenciaDTO.getFechaCreacion()));
			objetoVO.setArchivolicenciaId(archivosLicenciaDTO.getArchivolicenciaId());
			objetoVO.setTipoArchivo(archivosLicenciaDTO.getSemoviCatTipoArchivoDTO().getCatTipoArchivoId());
			objetoVO.setCreadoPor(archivosLicenciaDTO.getCreadoPor());
            objetoVO.setDescripcion(archivosLicenciaDTO.getSemoviCatTipoArchivoDTO().getDescripcion());
            objetoVO.setCodigo(archivosLicenciaDTO.getSemoviCatTipoArchivoDTO().getCodigo().toString());
            objetoVO.setNumeroRegistros(archivosLicenciaDTO.getNumeroRegistros() == null ? new Long(0) : archivosLicenciaDTO.getNumeroRegistros());
            listaArchivosLicenciaVO.add(objetoVO);
		}

		return listaArchivosLicenciaVO;
	}

	@Override
	public List<InfraccionesConPuntosLicenciaVO> buscarInfraccPuntosLicencia(String fechaArchivo) {
		Date fechaCreacion = rutinasTiempoImpl.convertirStringDate(fechaArchivo.replaceAll("-", "/"));
		return  infraccionesPuntosLicenciaMyBatisDAO.buscaInfraccionesConPuntosInfraccion(fechaCreacion);
	}

	
	
//		INICIA VEHICULOS ROBADOS
	
	
	
	@Override
	public List<FilterValuesVO> buscarOpcionesVehRobados() {
		List <FilterValuesVO> filterValues = new ArrayList<FilterValuesVO>();
		
	
		for(int i=0; i<5; i++){
			FilterValuesVO filter = new FilterValuesVO();
			 if(i == 0){
				filter.setCodigoString("placa");
				filter.setDescripcion("Placa Vehicular");
			}else if(i == 1){
				filter.setCodigoString("numSerie");
				filter.setDescripcion("Número de Serie");
			}else if(i == 2){
				filter.setCodigoString("numMotor");
				filter.setDescripcion("Número de Motor");
			}else if(i == 3){
				filter.setCodigoString("turno");
				filter.setDescripcion("Turno DGant");
			}else if(i == 4){
				filter.setCodigoString("exp");
				filter.setDescripcion("Número de Expediente");
			}
			
			filterValues.add(filter);
		}
		return filterValues;
	}

	
	@Override
	public List<VehiculosRobadosVO> buscaVehiculosRobados(String opcion, String valor) {
		
		List<VehiculosRobadosVO> reporteRobVO = semoviVehiculosRobados.buscarVehiculosRobadosConsulta(opcion, valor);
		if(!reporteRobVO.isEmpty()){
			reporteVehRob = generarReporteVehiculo (reporteRobVO, "Reporte Robo");	
		}
		
		return reporteRobVO;
		
	}
	
	private ByteArrayOutputStream generarReporteVehiculo(List<VehiculosRobadosVO> reporteRobVO, String nombreReporte) {
		PeticioReporteVO peticionReporteVO = new PeticioReporteVO();
		PropiedadesReporte propiedadesReporte = new PropiedadesReporte();
		propiedadesReporte.setTituloExcel(nombreReporte);
		propiedadesReporte.setNombreReporte(nombreReporte);
		propiedadesReporte.setExtencionArchvio(".xls");
		/*propiedadesReporte.setFechaI("10/10/2016");
		propiedadesReporte.setFechaF("10/10/2016");*/

		PeticionReporteBOImpl peticionReporteBOImpl = new PeticionReporteBOImpl();

		List<String> titulos = new ArrayList<String>();
		titulos.add("TURNO DGANT");
		titulos.add("EXPEDIENTE");
		titulos.add("FECHA DE ROBO");
		titulos.add("ESTATUS DE ROBO");
		titulos.add("MARCA");
		titulos.add("NÚMERO DE MOTOR");
		titulos.add("NÚMERO DE SERIE");
		titulos.add("TIPO");
		titulos.add("MODELO");
		titulos.add("PLACA VEHICULAR");
		titulos.add("COLOR");
		
		List<Object> encabezadoTitulo = new ArrayList<Object>();
		encabezadoTitulo.add(titulos);

		List<Object> hojas = new ArrayList<Object>();
		List<Object> hoja1 = new ArrayList<Object>();
		List<String> listaContenido1;

		for (VehiculosRobadosVO record : reporteRobVO) {
			listaContenido1 = new ArrayList<String>();

			listaContenido1.add(record.getTurnoDgant());
			listaContenido1.add(record.getExpediente());
			listaContenido1.add(record.getFechaRobo());
			listaContenido1.add(record.getEstatus());
			listaContenido1.add(record.getMarca());
			listaContenido1.add(record.getNumMotor());
			listaContenido1.add(record.getNumSerie());
			listaContenido1.add(record.getModelo());
			listaContenido1.add(record.getAnio());
			listaContenido1.add(record.getPlacaVehiculo());
			listaContenido1.add(record.getColor());
			
			hoja1.add(listaContenido1);
		}
		hojas.add(hoja1);

		peticionReporteVO.setPropiedadesReporte(propiedadesReporte);
		peticionReporteVO.setEncabezado(encabezadoTitulo);
		peticionReporteVO.setContenido(hojas);

		try {
			byteArrayOutputStream = peticionReporteBOImpl.generaReporteExcel(peticionReporteVO);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return byteArrayOutputStream;
	}
	

	@Override
	public List<VehiculosRobadosVO> buscarVehiculosRobados(String valor) {
		
		List<VehiculosRobadosVO> reporteRobVO = semoviVehiculosRobados.buscarVehiculosRobados(valor);
		if(!reporteRobVO.isEmpty()){
			reporteVehRob = generarReporteVehiculo (reporteRobVO, "Expediente: "+ valor);	
		}
		return reporteRobVO;
		
	}
	
	

	
	@Override
	public List<VehiculoRobadoHistoricoVO>  consultaHistorico(Long idRobo) {
		 return semoviVehiculosRobados.buscarHistoricoVeh(idRobo);
		
	}

	@Override
	public VehiculosRobadosVO consultaDetalleVehiculo(Long idRobo) {
		VehiculosRobadosVO histoVO = new VehiculosRobadosVO();
		histoVO = semoviVehiculosRobados.detalleDatosVehiculo(idRobo);
		
		return histoVO != null ? histoVO : null; 
	}

	@Override
	public List<FilterValuesVO> buscarModelo() {
	
		return semoviVehiculosRobados.buscarModelo();
	}
	
	@Override
	public List<FilterValuesVO> buscarTipo(Long id) {
	
		return semoviVehiculosRobados.buscarTipo(id);
	}

	@Override
	public Long guardarVehiculoRobado(Long idStatus) {
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO(); 
		Long nextRobo = semoviVehiculosRobados.ultimoIdRobo();
		Long result = null;
		if(nextRobo != null){
			semoviVehiculosRobados.insertaHistoricoRoboVehiculo(nextRobo,idStatus,usuarioFirmadoVO.getId());
			result = nextRobo;
		}
		return result;
		
	}

	@Override
	@Transactional
	public Boolean guardarDataVehiculoRobado(VehiculosRobadosVO convertVO, Long next) throws ParseException {
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO(); 
		return SemoviVehiculosRobadosDAO.insertVehiculosRobados(convertVO,next, usuarioFirmadoVO.getId());
	}

	@Override
	@Transactional
	public SemoviVehiculoRobadoDTO updateDataVehiculoRobado(VehiculosRobadosVO convertVO) {
		SemoviVehiculoRobadoDTO vehiculoRobado = SemoviVehiculosRobadosDAO.buscaIdVehiculoRobado(convertVO.getIdRobo());
		return vehiculoRobado;
	}

	@Override
	public void insertaHistoricoRoboVehiculo(Long idRobo, Long idEstatus) {
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO(); 
		semoviVehiculosRobados.insertaHistoricoRoboVehiculo(idRobo, idEstatus, usuarioFirmadoVO.getId());
			}

	@Override
	@Transactional
	public SemoviVehiculoRobadoDTO updateDataVehiculoRob(VehiculosRobadosVO convertVO) throws ParseException {
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		return SemoviVehiculosRobadosDAO.updateVehiculoRob(convertVO,usuarioFirmadoVO.getId() );
	}

	@Override
	public List<FilterValuesVO> buscarVehiculosEstatus() {		
		return semoviVehiculosRobados.getEstatusVehiculo();
	}

	@Override
	public Long getExistExpediente(String valor) {
		return semoviVehiculosRobados.existExpediente(valor) ;
		
//		return idExp  != null ? true : false;
	}

	@Override
	public boolean createExpediente(String expediente, Long emp) {
		
		
		Long idExpediente = semoviVehiculosRobados.ultimoExpediente();
		return semoviVehiculosRobados.crearExpediente(idExpediente,expediente, emp);
	}

	@Override
	public void cancelaRegistroRobo(Long idRobo) {
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		semoviVehiculosRobados.cancelarRob(idRobo,usuarioFirmadoVO.getId() );
	
	}

	@Override
	public ByteArrayOutputStream generarReporteExcel() {
		return this.reporteVehRob;
	}

	@Override
	public Long verifiReporteExist(String exp, String turno) {
		return semoviVehiculosRobados.verificaReporteRobo(exp, turno);
	}

	@Override
	public void updateExistExperiente(String expNew, String expOld) {
		semoviVehiculosRobados.updateExpedienteActivos(expNew, expOld);
		
	}

	@Override
	public void updateVehiculosRobExp(String expNew, String expOld) {
		semoviVehiculosRobados.updateExpedientExpRob(expNew,expOld);
		
	}

	@Override
	public List<VehiculosRobadosVO> getExistExpedienteName(Long idExp) {
		return semoviVehiculosRobados.existExpedienteName(idExp) ;
	}
//	public String getExistExpedienteName(Long idExp) {
//		return semoviVehiculosRobados.existExpedienteName(idExp) ;
//	}
	
}