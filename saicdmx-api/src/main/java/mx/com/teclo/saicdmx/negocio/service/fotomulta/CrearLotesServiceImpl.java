package mx.com.teclo.saicdmx.negocio.service.fotomulta;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.saicdmx.negocio.service.semovi.ParametroService;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.fotomulta.FotomultaLotesDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.fotomulta.FotomultaLotesDTO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.fotomultadetecciones.FotomultaDeteccionesMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.fotomultalotes.FotomultaLotesMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.radararchivo.RadarArchivoMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.radararchivodetalle.RadarArchivoDetalleMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.FotoMultaLotesVO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.ReporteDeteccionesVO;
import mx.com.teclo.saicdmx.persistencia.vo.radares.RadarArchivoVO;
import mx.com.teclo.saicdmx.util.comun.RutinasTiempoImpl;
import mx.com.teclo.saicdmx.util.enumerados.RadarTipoArchivo;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclomexicana.generaexcel.reporteexcel.PeticionReporteBOImpl;
import mx.com.teclomexicana.generaexcel.vo.PeticioReporteVO;
import mx.com.teclomexicana.generaexcel.vo.PropiedadesReporte;

@Service
public class CrearLotesServiceImpl implements CrearLotesService{
	
	@Autowired
	private FotomultaLotesDAO fotomultaLotesDAO;
	
	@Autowired
	private FotomultaDeteccionesMyBatisDAO consultaDeteccionesDAO; 
	
	@Autowired 
	private FotomultaLotesMyBatisDAO crearLotesDAO;
	
	@Autowired 
	private ParametroService parametroService;
	
	@Autowired
	private RadarArchivoMyBatisDAO radarArchivoMyBatisDAO;
	
	@Autowired
	private RadarArchivoDetalleMyBatisDAO radarArchivoDetalleMyBatisDAO;
	
	private RutinasTiempoImpl rutina;
	private Map reporte;

	/***
	 * {@inheritDoc}
	 */
	@Override
	@Transactional (readOnly =  true)
	public Integer validarDeteccionesParaLote(String fechaInicio, String fechaFin, Integer tipoRadar, String nombreRadar, Integer archivoTipo) throws BusinessException{
		//Map response = new HashMap();
		Integer numberDetecciones = 0;
		Integer procesado = 0;
		Integer valido = 1;
		Integer autorizado = 1;
		
		FotomultaLotesDTO lote = fotomultaLotesDAO.buscarLotesEnCreacion(tipoRadar, archivoTipo);
		Integer origenPlaca = 0;
		if(archivoTipo==1){
			origenPlaca = 1;
		}
		if(lote == null){
			rutina = new RutinasTiempoImpl();
			Date fechaI = rutina.convertirStringDate(fechaInicio, "dd/MM/yy");
			Date fechaF = rutina.convertirStringDate(fechaFin, "dd/MM/yy");
			
			this.cancelarFotomultaDeteccionesDuplicadas(tipoRadar, fechaI, fechaF, archivoTipo);
			
			numberDetecciones = crearLotesDAO.numeroDetecciones(fechaI, fechaF, valido, procesado, tipoRadar, autorizado, origenPlaca);
			
			if(numberDetecciones < 1){
				throw new BusinessException("No hay detecciones para crear el lote");
			}
		}else{
			throw new BusinessException("Existe un lote en proceso de creación\nLote ID: " + lote.getLoteId()
			+"\nNombre: "+ lote.getNombre()+"\nEspere a que termine el proceso");
		}
		
		reporte = this.crearReporteDeteccionesPorLote(fechaInicio, fechaFin, tipoRadar, nombreRadar, origenPlaca);
		//eliminarArchivosAnteriores();
		return numberDetecciones;
	}
	
	private void cancelarFotomultaDeteccionesDuplicadas(Integer tipoRadar, Date fechaInicio, Date fechaFin, Integer archivoTipo){
		crearLotesDAO.actualizaDeteccionesTdskuidDuplicados(tipoRadar, fechaInicio, fechaFin);
		
		crearLotesDAO.actualizaDeteccionesPlacaFechaHoraDuplicados(tipoRadar, fechaInicio, fechaFin);
		
		crearLotesDAO.actualizaDeteccionesExistentesEnRadarArchiviDetalle(tipoRadar, fechaInicio, fechaFin, archivoTipo);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map crearReporteDeteccionesPorLote(String fechaInicio, String fechaFin, Integer tipoRadar, String nombreRadar, Integer origenPlaca) {
		Map response = new HashMap();
		ByteArrayOutputStream reporte = new ByteArrayOutputStream();
		
		PeticioReporteVO peticionReporteVO = new PeticioReporteVO();
		PropiedadesReporte propiedadesReporte = new PropiedadesReporte();
		PeticionReporteBOImpl peticionReporteBOImpl = new PeticionReporteBOImpl();
		rutina = new RutinasTiempoImpl();
		
		Integer valido = 1;
		Integer autorizado = 1;
		Integer procesado = 0;
		
		//Resultados de la tabla
		List<Object> contenido = new ArrayList<Object>();
		List<Object> contenido1 = new ArrayList<Object>();
		
		//Leyendas de las columnas de las tablas
		List<Object> encabezadoTitulo = new ArrayList<Object>();
		List<String> titulos = new ArrayList<String>();
		List<String> subtitulos = new ArrayList<String>();

		
		titulos.add("Placa");
		titulos.add("Fecha");
		titulos.add("Hora");
		titulos.add("TDSKUID");
		titulos.add("UT");
		titulos.add("Velocidad Detectada");
		titulos.add("Nombre");
		titulos.add("Apellido Paterno");
		titulos.add("Apellido Materno");
		titulos.add("Calle");
		titulos.add("Num. Exterior");
		titulos.add("Num. Interior");
		titulos.add("Colonia");
		titulos.add("Municipio");
		titulos.add("Codigo Postal");
		titulos.add("Entidad Federativa");
		titulos.add("Marca");
		titulos.add("Submarca");
		titulos.add("Modelo");
		titulos.add("Telefono");
		titulos.add("Serie");
		titulos.add("Motor");
		titulos.add("Oficial Placa");
		titulos.add("Oficial Nombre");
		encabezadoTitulo.add(titulos);
		
		String origenPlacaString = "Foránea";
		if(origenPlaca==0){origenPlacaString="CDMX";};
		
		subtitulos.add("Tipo de Radar: " + nombreRadar);
		subtitulos.add("Tipo de Placa: " + origenPlacaString);
		
		propiedadesReporte.setFechaI(fechaInicio);
		propiedadesReporte.setFechaF(fechaFin);
		String fechaReporte = fechaInicio + "-" + fechaFin;
		String fechaNReporte = fechaInicio.replace("/", "") + fechaFin.replace("/", "");
		
		propiedadesReporte.setNombreReporte("DETECCIONES"+fechaNReporte);
		propiedadesReporte.setTituloExcel("DETECCIONES " + fechaReporte);
		propiedadesReporte.setExtencionArchvio(".xls");
		
		//cuerpo del reporte
		List<String> listaContenido1;
		List<ReporteDeteccionesVO> listresult = null;
		Date fechaI = rutina.convertirStringDate(fechaInicio, "dd/MM/yy");
		Date fechaF = rutina.convertirStringDate(fechaFin, "dd/MM/yy");
		
		listresult = consultaDeteccionesDAO.buscaFotomultasDetecionesRangoFechasByFechaInfraccion(fechaI, fechaF, valido, procesado, tipoRadar, new Long(autorizado), origenPlaca);
		if(!listresult.isEmpty()){	
			for(ReporteDeteccionesVO deteccionVO : listresult){
				listaContenido1 = new ArrayList<String>();
				listaContenido1.add(deteccionVO.getPlaca());
	            listaContenido1.add(deteccionVO.getFecha());
	            listaContenido1.add(deteccionVO.getHora());
	            listaContenido1.add(deteccionVO.getTdskuid());
	            listaContenido1.add(deteccionVO.getUt());
	            listaContenido1.add(deteccionVO.getVelocidadDetectada());
	            listaContenido1.add(deteccionVO.getNombre());
	            listaContenido1.add(deteccionVO.getApellidoPaterno());
	            listaContenido1.add(deteccionVO.getApellidoMaterno());
	            listaContenido1.add(deteccionVO.getCalle());
	            listaContenido1.add(deteccionVO.getNumExterior());
	            listaContenido1.add(deteccionVO.getNumInterior());
	            listaContenido1.add(deteccionVO.getColonia());
	            listaContenido1.add(deteccionVO.getMunicipio());
	            listaContenido1.add(deteccionVO.getCodigoPostal());
	            listaContenido1.add(deteccionVO.getEntidadFederativa());
	            listaContenido1.add(deteccionVO.getMarca());
	            listaContenido1.add(deteccionVO.getSubmarca());
	            listaContenido1.add(deteccionVO.getModelo());
	            listaContenido1.add(deteccionVO.getTelefono());
	            listaContenido1.add(deteccionVO.getSerie());
	            listaContenido1.add(deteccionVO.getMotor());
	            listaContenido1.add(deteccionVO.getOficialPlaca());
	            listaContenido1.add(deteccionVO.getOficialNombre());
				
				contenido1.add(listaContenido1);
			}
					
			contenido.add(contenido1);
			
			propiedadesReporte.setSubtitulos(subtitulos);
			peticionReporteVO.setPropiedadesReporte(propiedadesReporte);
			peticionReporteVO.setEncabezado(encabezadoTitulo);
			peticionReporteVO.setContenido(contenido);
		
			try {
				reporte = peticionReporteBOImpl.generaReporteExcel(peticionReporteVO);
				response.put("reporte", reporte);
				response.put("nombre", propiedadesReporte.getNombreReporte());
			} catch (IOException e) {
	 			e.printStackTrace();
			}
		}
		
		return response;
	}
	
	private void eliminarArchivosAnteriores(){
		RutinasTiempoImpl rutinasTiempo = new RutinasTiempoImpl();
		String rutaArchivo = parametroService.getDirTempUpload();

		File listaArchivos = new File(rutaArchivo);
		String[] arrayArchivos = listaArchivos.list();
		
		if(arrayArchivos != null){
			for (String nombre : arrayArchivos) {
				File archivo = new File(rutaArchivo + "/" + nombre);
				Date fechaArchivo = rutinasTiempo
						.obtenrFechaModificacionArchivo(archivo);
				Date fechaSinHoras = rutinasTiempo.getFechaActual();
	
				if (fechaArchivo.getTime() < fechaSinHoras.getTime()) {
					archivo.delete();
				}
			}
		}
	}
	
	/***
	 * {@inheritDoc}
	 */

	@SuppressWarnings("rawtypes")
	@Override
	public Map getReporteDeteccionesPorLote() {
		return reporte;
	}

	@SuppressWarnings("rawtypes")
	@Override
	@Transactional (readOnly = true)
	public FotoMultaLotesVO guardarLote(String fechaEmision, String nombreLote, String fechaInicio, 
			String fechaFin, Integer anoSalario, String usuario, Integer tipoRadar, Integer origenPlaca)throws BusinessException {
		
		FotoMultaLotesVO consultaLotesVO = null;
		FotomultaLotesDTO lote = fotomultaLotesDAO.buscarLotesEnCreacion(tipoRadar, origenPlaca);
		if(lote == null){
			Long radarArchivoId = guardaLoteArchivoEnCreacion(fechaEmision, nombreLote, fechaInicio, fechaFin, anoSalario, usuario, tipoRadar, origenPlaca);
			Map response = creaFotomultaLote(fechaEmision, nombreLote, fechaInicio, fechaFin, anoSalario, usuario, tipoRadar, radarArchivoId, origenPlaca);
			
			Integer guardar = (Integer) response.get("guardar");
			if(guardar > 0){
				consultaLotesVO = (FotoMultaLotesVO) response.get("FotoMultaLotesVO");
			}else{
				throw new BusinessException("No hay detecciones para crear el lote");
			}
		}else{
			throw new BusinessException("Existe un lote en proceso de creación\nLote ID: " + lote.getLoteId()
			+"\nNombre: "+ lote.getNombre()+"\nEspere a que termine el proceso");
		}
		return consultaLotesVO;
	}
	
	@Transactional
	public Long guardaLoteArchivoEnCreacion(String fechaEmision, String nombreLote, 
										  	String fechaInicio, String fechaFin,
										  	Integer anoSalario, String usuario, Integer tipoRadar, Integer archivoTipoF){
		
		rutina = new RutinasTiempoImpl();
		
		final Integer estatusID = 1;
		final Integer enProceso = 0;
		final Integer enCreacion = 1;
		final Integer numerDetecciones = -1;
		Integer tipoProceso = 1; //Proceso CDMX
		if (archivoTipoF.equals(RadarTipoArchivo.FOTOMULTA_FORANEA.getProcesoId())){
			tipoProceso = 2; //Proceso Foraneo
		}
		
		Date fechaEmi = rutina.convertirStringDate(fechaEmision);
		final Integer archivoTipo = archivoTipoF; //RadarTipoArchivo.FOTOMULTA.getProcesoId();
		
		Long radarArchivoId = radarArchivoMyBatisDAO.obtenerSiguienteSecuencia();
		String SalarioMin = radarArchivoMyBatisDAO.getAnioSalarioMin(String.valueOf(anoSalario));
		
		RadarArchivoVO objectInsert = new RadarArchivoVO();
		objectInsert.setRadarArchivoId(radarArchivoId);
		objectInsert.setArchivoNombre(nombreLote);
		objectInsert.setFechaEmision(fechaEmi);
		objectInsert.setEstatusProcesoId(new Long(estatusID));
		objectInsert.setEnProceso(enProceso);
		objectInsert.setEmpleadoId(new Long(usuario));
		objectInsert.setFehcaUltimaModificacion(new Date());
		objectInsert.setAnioSalario(Integer.parseInt(SalarioMin));
		objectInsert.setArchivoTipoId(new Long(archivoTipo));
		objectInsert.setArchivoTipoProcesoId(new Long(tipoProceso));
		objectInsert.setIdSalarioMin(Long.valueOf(anoSalario));
		
		radarArchivoMyBatisDAO.insertRadarArchivoFromFotomultas(objectInsert);
		
		FotoMultaLotesVO fotoMultaInsert = new FotoMultaLotesVO();
		fotoMultaInsert.setLoteID(radarArchivoId);
		fotoMultaInsert.setNombre(nombreLote);
		fotoMultaInsert.setFechaEmision(fechaEmision);
		fotoMultaInsert.setFechaProcInicio(fechaInicio);
		fotoMultaInsert.setFechaProcFin(fechaFin);
		fotoMultaInsert.setEmpleadoId(new Long(usuario));
		fotoMultaInsert.setCantidadProcesados(numerDetecciones);
		fotoMultaInsert.setTipoRadar(tipoRadar);
		fotoMultaInsert.setEnCreacion(enCreacion);
		fotoMultaInsert.setArchivoTipo(archivoTipo);
		crearLotesDAO.InsertarFotoMultaLote(fotoMultaInsert);
		
		return radarArchivoId;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public Map creaFotomultaLote(String fechaEmision, String nombreLote, 
								 String fechaInicio, String fechaFin,
							 	 Integer anoSalario, String usuario, Integer tipoRadar, 
							 	 Long radarArchivoId, Integer archivoTipo) {
		
		Map response = new HashMap();
		final Integer procesado = 1;
		final Integer noProcesado = 0;
		final Integer valido = 1;
		final Integer autorizado = 1;
		final Integer empleadoLogeado = Integer.parseInt(usuario);
		Integer cantidadProcesados = 0;
		rutina = new RutinasTiempoImpl();
		
		FotoMultaLotesVO fotomultaConsultaLotesVO = null;
		Date fechaI = rutina.convertirStringDate(fechaInicio, "dd/MM/yy");
		Date fechaF = rutina.convertirStringDate(fechaFin, "dd/MM/yy");
		
		Integer origenPlaca = 0;
		if(archivoTipo.equals(RadarTipoArchivo.FOTOMULTA_FORANEA.getProcesoId())){
			origenPlaca = 1;
		}
		
		this.cancelarFotomultaDeteccionesDuplicadas(tipoRadar, fechaI, fechaF, archivoTipo);
		
		List<ReporteDeteccionesVO> listaDetecciones = consultaDeteccionesDAO.
									buscaFotomultasDetecionesRangoFechasByFechaInfraccion(fechaI, fechaF, valido,
																	noProcesado, tipoRadar, new Long(autorizado), origenPlaca);

		if (!listaDetecciones.isEmpty()) {
			String velocidadDetectada;
			Integer loteId = Integer.parseInt(radarArchivoId.toString());
			
			for (ReporteDeteccionesVO fotomultaDeteccionesVO : listaDetecciones) {
				velocidadDetectada = fotomultaDeteccionesVO.getVelocidadDetectada() == null ? "0" : fotomultaDeteccionesVO.getVelocidadDetectada(); 
				fotomultaDeteccionesVO.setLoteId(loteId);
				fotomultaDeteccionesVO.setModificadoPor(empleadoLogeado);
				fotomultaDeteccionesVO.setVelocidadDetectada(velocidadDetectada);
				radarArchivoDetalleMyBatisDAO.insertarFotomultaDetalle(fotomultaDeteccionesVO);
				cantidadProcesados++;

				consultaDeteccionesDAO.actualizaFotomultaDeteccionesPorLote(fotomultaDeteccionesVO.getTdskuid(),
						procesado, radarArchivoId, valido,noProcesado, tipoRadar, autorizado);
			}
			
			radarArchivoMyBatisDAO.actualizaArchivosProcesados(radarArchivoId, cantidadProcesados);
			crearLotesDAO.actualizaLoteCreacion(listaDetecciones.size(), radarArchivoId);
			
			fotomultaConsultaLotesVO = new FotoMultaLotesVO();
			fotomultaConsultaLotesVO.setCantidadProcesados(listaDetecciones.size());
			fotomultaConsultaLotesVO.setComplementado(0);
			fotomultaConsultaLotesVO.setFechaEmision(fechaEmision);
			fotomultaConsultaLotesVO.setFechaProcInicio(fechaInicio);
			fotomultaConsultaLotesVO.setFechaProcFin(fechaFin);
			fotomultaConsultaLotesVO.setLoteID(radarArchivoId);
			fotomultaConsultaLotesVO.setNombre(nombreLote);

			response.put("guardar", 1);
			response.put("FotoMultaLotesVO", fotomultaConsultaLotesVO);
		} else {
			response.put("guardar", 0);
		}
		return response;
	}

	@Override
	public Integer realizarCompletacion(Long loteId) throws BusinessException {
		final Integer enProceso = 1;

		Integer ArchivoEnompletacion = radarArchivoMyBatisDAO.buscarArchivoEnComplementacion(enProceso);

		if (ArchivoEnompletacion == 0) {
			return radarArchivoMyBatisDAO.actualizaArchivoParaComplementacion(loteId, enProceso);
		}else{
			throw new BusinessException("Hay un lote en complementación. Intente mas tarde.");
		}
	}

	/*fotomultaDeteccionesVO.getPlaca(),
								fotomultaDeteccionesVO.getFecha(),
								fotomultaDeteccionesVO.getHora(),
								fotomultaDeteccionesVO.getTdskuid(),
								fotomultaDeteccionesVO.getUt(),
								fotomultaDeteccionesVO.getVelocidadDetectada(),
								fotomultaDeteccionesVO.getNombre(),
								fotomultaDeteccionesVO.getApellidoPaterno(),
								fotomultaDeteccionesVO.getApellidoMaterno(),
								fotomultaDeteccionesVO.getCalle(),
								fotomultaDeteccionesVO.getNumExterior(),
								fotomultaDeteccionesVO.getNumInterior(),
								fotomultaDeteccionesVO.getColonia(),
								fotomultaDeteccionesVO.getMunicipio(),
								fotomultaDeteccionesVO.getCodigoPostal(),
								fotomultaDeteccionesVO.getEntidadFederativa(),
								fotomultaDeteccionesVO.getMarca(),
								fotomultaDeteccionesVO.getSubmarca(),
								fotomultaDeteccionesVO.getModelo(),
								fotomultaDeteccionesVO.getTelefono(),
								fotomultaDeteccionesVO.getSerie(),
								fotomultaDeteccionesVO.getMotor(),
								fotomultaDeteccionesVO.getArticuloId(),
								empleadoLogeado,
								fotomultaDeteccionesVO.getOficialPlaca(),
								fotomultaDeteccionesVO.getOficialNombre()*/
}
