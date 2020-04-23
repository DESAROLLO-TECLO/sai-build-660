package mx.com.teclo.saicdmx.negocio.service.fotomulta;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.mybatis.dao.fotomultaestadistica.FotomultaEstadisticasMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.FotoMultaDeteccionesRechazadasReporteVO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.FotoMultaEstPorPersonaVO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.FotoMultaEstPorTurno;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.FotoMultaReporteSSPVO;
import mx.com.teclo.saicdmx.util.comun.RutinasTiempoImpl;
import mx.com.teclo.saicdmx.util.enumerados.FotoMultaReporteEstadistica;
import mx.com.teclomexicana.generaexcel.reporteexcel.PeticionReporteBOImpl;
import mx.com.teclomexicana.generaexcel.vo.PeticioReporteVO;
import mx.com.teclomexicana.generaexcel.vo.PropiedadesReporte;

@Service
public class EstadisticaServiceImpl implements EstadisticaService{

	@Autowired
	private FotomultaEstadisticasMyBatisDAO fotomultaEstadisticasMyBatisDAO;

	private RutinasTiempoImpl rutina;
	
	@SuppressWarnings("rawtypes")
	private Map reporteRendimiento;
	
	@SuppressWarnings("rawtypes")
	private Map reporteDeteccionesRechazadas;
	
	/***
	 * {@inheritDoc}
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map buscaPrevalidacionesPorTipoReporte(String fechaInicio, String fechaFin, Integer canceladas, Integer tipoReporte){
		Map result = new HashMap();
		rutina = new RutinasTiempoImpl();
		
		Date fechaI = null;
		Date fechaF = null;
		
		if(StringUtils.isNotBlank(fechaInicio) && StringUtils.isNotBlank(fechaFin)){
			fechaI = rutina.convertirStringDate(fechaInicio);
			fechaF = rutina.convertirStringDate(fechaFin);
		}
		
		if(tipoReporte == FotoMultaReporteEstadistica.PREVALIDACIONES_POR_PERSONA){
			List<FotoMultaEstPorPersonaVO> lista = fotomultaEstadisticasMyBatisDAO.buscaPrevalidacionesParaReportePorPersona(fechaI, fechaF, canceladas);
			result.put("list", lista);
			
			if(!lista.isEmpty()){
				reporteRendimiento = generaReporteRendimiento(fechaInicio, fechaFin, tipoReporte, result);
			}
		}else if(tipoReporte == FotoMultaReporteEstadistica.PREVALIDACIONES_POR_TURNO){
			List<FotoMultaEstPorTurno> lista = fotomultaEstadisticasMyBatisDAO.buscaPrevalidacionesParaReportePorTurno(fechaI, fechaF, canceladas);
			result.put("list", lista);
			
			if(!lista.isEmpty()){
				reporteRendimiento = generaReporteRendimiento(fechaInicio, fechaFin, tipoReporte, result);
			}
		}else if(tipoReporte == FotoMultaReporteEstadistica.VALIDACIONES_SSP){
			List<FotoMultaReporteSSPVO> lista =  fotomultaEstadisticasMyBatisDAO.buscaValidacionesParaReporteSSP(fechaI, fechaF);
			result.put("list", lista);
			
			if(!lista.isEmpty()){
				reporteRendimiento = generaReporteRendimiento(fechaInicio, fechaFin, tipoReporte, result);
			}
		}
		
		return result;
	}

	/***
	 * {@inheritDoc}
	 */
	@Override
	public List<FotoMultaDeteccionesRechazadasReporteVO> buscaPrevalidacionesRechazadasReporteGeneral(
			String fechaInicio, String fechaFin, Integer canceladas) {
		List<FotoMultaDeteccionesRechazadasReporteVO> lista = null;
		Date fechaI = null;
		Date fechaF = null;
		rutina = new RutinasTiempoImpl();
		
		if(StringUtils.isNotBlank(fechaInicio) && StringUtils.isNotBlank(fechaFin)){
			fechaI = rutina.convertirStringDate(fechaInicio);
			fechaF = rutina.convertirStringDate(fechaFin);
		}
		
		//Tipo Reporte 3
		lista = fotomultaEstadisticasMyBatisDAO.buscaPrevalidacionesRechazadasReporteGeneral(fechaI, fechaF, canceladas);
		if(!lista.isEmpty()){
			reporteDeteccionesRechazadas = generarReporteDeteccionesRechazadas("", fechaInicio, fechaFin, 3, lista);
		}
		
		return lista;
	}

	/***
	 * {@inheritDoc}
	 */
	@Override
	public List<FotoMultaDeteccionesRechazadasReporteVO> buscaPrevalidacionesRechazadasReportePorPrevalidador(
			Long prevalidadorId, String fechaInicio, String fechaFin, Integer canceladas, String persona) {
		List<FotoMultaDeteccionesRechazadasReporteVO> lista = null;
		Date fechaI = null;
		Date fechaF = null;
		rutina = new RutinasTiempoImpl();
		
		if(StringUtils.isNotBlank(fechaInicio) && StringUtils.isNotBlank(fechaFin)){
			fechaI = rutina.convertirStringDate(fechaInicio);
			fechaF = rutina.convertirStringDate(fechaFin);
		}
		
		lista = fotomultaEstadisticasMyBatisDAO.buscaPrevalidacionesRechazadasReportePorPrevalidador(prevalidadorId, fechaI, fechaF, canceladas);
		if(!lista.isEmpty()){
			reporteDeteccionesRechazadas = generarReporteDeteccionesRechazadas(persona, fechaInicio, fechaFin, 1, lista);
		}
		return lista;
	}

	/***
	 * {@inheritDoc}
	 */
	@Override
	public List<FotoMultaDeteccionesRechazadasReporteVO> buscaDeteccionesRechazadasParaReporteGeneralSSP(
			String fechaInicio, String fechaFin) {
		List<FotoMultaDeteccionesRechazadasReporteVO> lista = null;
		Date fechaI = null;
		Date fechaF = null;
		rutina = new RutinasTiempoImpl();
		
		if(StringUtils.isNotBlank(fechaInicio) && StringUtils.isNotBlank(fechaFin)){
			fechaI = rutina.convertirStringDate(fechaInicio);
			fechaF = rutina.convertirStringDate(fechaFin);
		}
		
		lista = fotomultaEstadisticasMyBatisDAO.buscaDeteccionesRechazadasParaReporteGeneralSSP(fechaI, fechaF);
		if(!lista.isEmpty()){
			reporteDeteccionesRechazadas = generarReporteDeteccionesRechazadas("", fechaInicio, fechaFin, 4, lista);
		}
		return lista;
	}

	/***
	 * {@inheritDoc}
	 */
	@Override
	public List<FotoMultaDeteccionesRechazadasReporteVO> buscaDeteccionesRechazadasParaReportePorPersonaSSP(
														 String placa, String fechaInicio, String fechaFin,
														 String persona) {
		List<FotoMultaDeteccionesRechazadasReporteVO> lista = null;
		Date fechaI = null;
		Date fechaF = null;
		rutina = new RutinasTiempoImpl();
		
		if(StringUtils.isNotBlank(fechaInicio) && StringUtils.isNotBlank(fechaFin)){
			fechaI = rutina.convertirStringDate(fechaInicio);
			fechaF = rutina.convertirStringDate(fechaFin);
		}
		
		lista = fotomultaEstadisticasMyBatisDAO.buscaDeteccionesRechazadasParaReportePorPersonaSSP(placa, fechaI, fechaF);
		if(!lista.isEmpty()){
			reporteDeteccionesRechazadas = generarReporteDeteccionesRechazadas(persona, fechaInicio, fechaFin, 2, lista);
		}
		return lista;
	}
	
	/***
	 * @author Jesus Gutierrez
	 * @param tipoReporte
	 * @param listado
	 * @return Map
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	Map generaReporteRendimiento(String fechaInicio, String fechaFin, Integer tipoReporte, Map listado){
		
		Map mapaReporte = new HashMap();
		ByteArrayOutputStream reporte = new ByteArrayOutputStream();
		PeticioReporteVO peticionReporteVO = new PeticioReporteVO();
		PropiedadesReporte propiedadesReporte = new PropiedadesReporte();
		PeticionReporteBOImpl peticionReporteBOImpl = new PeticionReporteBOImpl();
		
		//Resultados de la tabla
		List<Object> contenido = new ArrayList<Object>();
		List<Object> contenido1 = new ArrayList<Object>();
		
		//Leyendas de las columnas de las tablas
		List<Object> encabezadoTitulo = new ArrayList<Object>();
		List<String> titulos = new ArrayList<String>(); 
		
		String fechaReporte = "";
		propiedadesReporte.setExtencionArchvio(".xls");
		if(StringUtils.isNotBlank(fechaInicio) && StringUtils.isNotBlank(fechaFin)){
			propiedadesReporte.setFechaI(fechaInicio);
			propiedadesReporte.setFechaF(fechaFin);
			
			fechaReporte = fechaInicio + "-" + fechaFin;
		}else{
			rutina = new RutinasTiempoImpl();
			fechaReporte = rutina.getStringDateFromFormta("dd/MM/yyyy", new Date());
			//propiedadesReporte.setFechaI(fechaReporte);
		}
		
		List<String> listaContenido1;
		if(tipoReporte == FotoMultaReporteEstadistica.PREVALIDACIONES_POR_PERSONA){
			titulos.add("NOMBRE");
			titulos.add("TOTAL DE PREVALIDACIONES");
			titulos.add("PREVALIDACIONES ACEPTADAS");
			titulos.add("PREVALIDACIONES RECHAZADAS");
			titulos.add("PORCENTAJE DE PREVALIDACIONES");
			titulos.add("SSP TOTAL DE VALIDACIONES");
			titulos.add("SSP VALIDACIONES ACEPTADA");
			titulos.add("PORCENTAJE SSP");
			titulos.add("SSP PENDIENTE DE VALIDAR");
			
			propiedadesReporte.setTituloExcel("Reporte Prevalidaciones Por Persona " + fechaReporte);
			propiedadesReporte.setNombreReporte("Reporte_Prevalidaciones_Por_Persona_" + fechaReporte);
			
			List<FotoMultaEstPorPersonaVO> lista = (List<FotoMultaEstPorPersonaVO>) listado.get("list");
			
			for(FotoMultaEstPorPersonaVO object : lista){
				listaContenido1 = new ArrayList<String>();
				listaContenido1.add(object.getNombre());
				listaContenido1.add(object.getTotalPrevalidaciones());
				listaContenido1.add(object.getPrevalidacionesAceptadas());
				listaContenido1.add(object.getPrevalidacionesRechazadas());
				listaContenido1.add(object.getPrevalidacionesPorcentaje());
				listaContenido1.add(object.getSspTotalValidadas());
				listaContenido1.add(object.getSspValidadaAceptada());
				listaContenido1.add(object.getSspPorcentaje());
				listaContenido1.add(object.getSspPendiente());
				
				contenido1.add(listaContenido1);
			}
			
		}else if(tipoReporte == FotoMultaReporteEstadistica.PREVALIDACIONES_POR_TURNO){
			titulos.add("FECHA");
			titulos.add("TURNO");
			titulos.add("TOTAL");
			
			propiedadesReporte.setTituloExcel("Reporte Prevalidaciones Por Turno " + fechaReporte);
			propiedadesReporte.setNombreReporte("Reporte_Prevalidaciones_Por_Turno_" + fechaReporte);
			
			List<FotoMultaEstPorTurno> lista = (List<FotoMultaEstPorTurno>) listado.get("list");
			for(FotoMultaEstPorTurno object : lista){
				listaContenido1 = new ArrayList<String>();
				listaContenido1.add(object.getFechaPrevaliadacion());
				listaContenido1.add(object.getTurno());
				listaContenido1.add(object.getTotal());
				
				contenido1.add(listaContenido1);
			}
			
		}else if(tipoReporte == FotoMultaReporteEstadistica.VALIDACIONES_SSP){
			titulos.add("NOMBRE");
			titulos.add("TOTAL DE VALIDACIONES");
			titulos.add("TOTAL DE ACEPTADAS");
			titulos.add("TOTAL DE RECHAZADAS");
			titulos.add("PORCENTAJE");
			
			propiedadesReporte.setTituloExcel("Reporte Validaciones Por Persona " + fechaReporte);
			propiedadesReporte.setNombreReporte("Reporte_Validaciones_Por_Persona_" + fechaReporte);
			
			List<FotoMultaReporteSSPVO> lista = (List<FotoMultaReporteSSPVO>) listado.get("list");
			for(FotoMultaReporteSSPVO object : lista){
				listaContenido1 = new ArrayList<String>();
				listaContenido1.add(object.getNombre());
				listaContenido1.add(object.getTotalValidaciones());
				listaContenido1.add(object.getTotalAceptadas());
				listaContenido1.add(object.getTotalRechazadas());
				listaContenido1.add(object.getPorcentaje());
				
				contenido1.add(listaContenido1);
			}
		}
		
		encabezadoTitulo.add(titulos);
		contenido.add(contenido1);
		peticionReporteVO.setPropiedadesReporte(propiedadesReporte);
		peticionReporteVO.setEncabezado(encabezadoTitulo);
		peticionReporteVO.setContenido(contenido);
		
		try {
			reporte = peticionReporteBOImpl.generaReporteExcel(peticionReporteVO);
			mapaReporte.put("nombre", propiedadesReporte.getNombreReporte());
			mapaReporte.put("reporte", reporte);
		} catch (IOException e) {
 			e.printStackTrace();
		}
		
		return mapaReporte;
	}
	
	/***
	 * @author Jesus Gutierrez
	 * @param nombrePersona
	 * @param fechaInicio
	 * @param fechaFin
	 * @param tipoReporte
	 * @param listado
	 * @return Map
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	Map generarReporteDeteccionesRechazadas(String nombrePersona, String fechaInicio,
											String fechaFin, Integer tipoReporte, 
											List<FotoMultaDeteccionesRechazadasReporteVO> listado){
		
		Map mapaReporte = new HashMap();
		ByteArrayOutputStream reporte = new ByteArrayOutputStream();
		PeticioReporteVO peticionReporteVO = new PeticioReporteVO();
		PropiedadesReporte propiedadesReporte = new PropiedadesReporte();
		PeticionReporteBOImpl peticionReporteBOImpl = new PeticionReporteBOImpl();
		
		//Resultados de la tabla
		List<Object> contenido = new ArrayList<Object>();
		List<Object> contenido1 = new ArrayList<Object>();
		
		//Leyendas de las columnas de las tablas
		List<Object> encabezadoTitulo = new ArrayList<Object>();
		List<String> titulos = new ArrayList<String>(); 
		List<String> subtitulos = new ArrayList<String>();
		
		String fechaReporte = "";
		propiedadesReporte.setExtencionArchvio(".xls");
		if(StringUtils.isNotBlank(fechaInicio) && StringUtils.isNotBlank(fechaFin)){
			propiedadesReporte.setFechaI(fechaInicio);
			propiedadesReporte.setFechaF(fechaFin);
			
			fechaReporte = fechaInicio + "-" + fechaFin;
		}else{
			rutina = new RutinasTiempoImpl();
			fechaReporte = rutina.getStringDateFromFormta("dd/MM/yyyy", new Date());
			propiedadesReporte.setFechaI(fechaReporte);
		}
		
		List<String> listaContenido1;
		
		if(tipoReporte == 1 || tipoReporte == 2){
			titulos.add("TDSKUID");
			titulos.add("MOTIVO DESCARTE");
			subtitulos.add("Prevalidador: " + nombrePersona);
			propiedadesReporte.setSubtitulos(subtitulos);
			
			if(tipoReporte == 1){
				propiedadesReporte.setTituloExcel("Detecciones Rechazadas Prevalidador");
				propiedadesReporte.setNombreReporte("Detecciones_Rechazadas_Prevalidador-" + fechaReporte);
			}else{
				propiedadesReporte.setTituloExcel("Detecciones Rechazadas Validador SSP");
				propiedadesReporte.setNombreReporte("Detecciones_Rechazadas_Validador_SSP-" + fechaReporte);
			}
			
			for(FotoMultaDeteccionesRechazadasReporteVO object : listado){
				listaContenido1 = new ArrayList<String>();
				listaContenido1.add(object.getTdskuid());
				listaContenido1.add(object.getDescripcion());
				
				contenido1.add(listaContenido1);
			}
			
		}else if(tipoReporte == 3 || tipoReporte == 4){
			titulos.add("NOMBRE");
			titulos.add("TDSKUID");
			titulos.add("MOTIVO DESCARTE");
			
			if(tipoReporte == 3){
				propiedadesReporte.setTituloExcel("Detecciones Rechazadas Prevalidadores");
				propiedadesReporte.setNombreReporte("Detecciones_Rechazadas_Prevalidadores-" + fechaReporte);
			}else{
				propiedadesReporte.setTituloExcel("Detecciones Rechazadas Validaores SSP");
				propiedadesReporte.setNombreReporte("Detecciones_Rechazadas_Validadores_SSP-" + fechaReporte);
			}
			
			for(FotoMultaDeteccionesRechazadasReporteVO object : listado){
				listaContenido1 = new ArrayList<String>();
				listaContenido1.add(object.getNombre());
				listaContenido1.add(object.getTdskuid());
				listaContenido1.add(object.getDescripcion());
				
				contenido1.add(listaContenido1);
			}
		}
		
		encabezadoTitulo.add(titulos);
		contenido.add(contenido1);
		peticionReporteVO.setPropiedadesReporte(propiedadesReporte);
		peticionReporteVO.setEncabezado(encabezadoTitulo);
		peticionReporteVO.setContenido(contenido);
		
		try {
			reporte = peticionReporteBOImpl.generaReporteExcel(peticionReporteVO);
			mapaReporte.put("nombre", propiedadesReporte.getNombreReporte());
			mapaReporte.put("reporte", reporte);
		} catch (IOException e) {
 			e.printStackTrace();
		}
		
		return mapaReporte;
	}

	/***
	 * {@inheritDoc}
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Map getReporteRendimiento() {
		return reporteRendimiento;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Map getReporteDeteccionesRechazadas() {
		return reporteDeteccionesRechazadas;
	}
}
