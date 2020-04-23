package mx.com.teclo.saicdmx.negocio.service.lineadecaptura;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.mybatis.dao.lineadecaptura.ConsultaLCMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura.ConsultaInfraccReasignacionEstadisticaVO;
import mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura.ConsultaInfraccReasignacionHistoricoVO;
import mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura.DetalleDeReasignacionesOficialVO;
import mx.com.teclo.saicdmx.util.comun.RutinasTiempoImpl;
import mx.com.teclomexicana.generaexcel.reporteexcel.PeticionReporteBOImpl;
import mx.com.teclomexicana.generaexcel.vo.PeticioReporteVO;
import mx.com.teclomexicana.generaexcel.vo.PropiedadesReporte;

@Service
public class ConsultaLCServiceImpl implements ConsultaLCService{

	@Autowired
	private ConsultaLCMyBatisDAO consultaLCMyBatisDAO;
	@SuppressWarnings("rawtypes")
	private Map reporteHistorico;
	@SuppressWarnings("rawtypes")
	private Map reporteEstadistico;
	
	@SuppressWarnings("rawtypes")
	private Map reporteEstadisticoPorPersona;
	
	private RutinasTiempoImpl rutinastiempo;
	/***
	 * {@inheritDoc}
	 */
	@Override
	public List<ConsultaInfraccReasignacionHistoricoVO> buscarHistoricoLC(String fechaInicio, String fechaFin,
			String noInfraccion, String placaOficial, String placaVehiculo) {
		
		int addEstatusConsumo = 0;
		Integer estatusConsumo = null;
		if(fechaInicio.isEmpty()){
			fechaInicio = null; addEstatusConsumo += 1;
			}
		if(fechaFin.isEmpty()){
			fechaFin = null; addEstatusConsumo += 1;
			}
		if(noInfraccion.isEmpty()){
			noInfraccion = null; addEstatusConsumo += 1;
			}
		if(placaOficial.isEmpty()){
			placaOficial = null; addEstatusConsumo += 1;
			}
		if(placaVehiculo.isEmpty()){
			placaVehiculo = null;
		}
		
		if(addEstatusConsumo == 4){
			if(placaVehiculo==null)
				estatusConsumo = 0;
		}
		List<ConsultaInfraccReasignacionHistoricoVO> historicos = null;
		try {
			historicos = consultaLCMyBatisDAO.buscarHistoricoLC(fechaInicio, fechaFin, noInfraccion, placaOficial, placaVehiculo, estatusConsumo);
			if(!historicos.isEmpty()){
				reporteHistorico = generaReporteHistoricoLC(historicos, fechaInicio, fechaFin);
			}
		}catch (Exception e) {
			historicos = null;
		}
		
		return historicos;
	}

	/***
	 * {@inheritDoc}
	 */
	@Override
	public List<ConsultaInfraccReasignacionEstadisticaVO> buscarEstadisticaLC(String fechaInicio, String fechaFin,
			String placaOficial, String nombreOficial) {
		
		if(fechaInicio.isEmpty()){fechaInicio = null;}
		if(fechaFin.isEmpty()){fechaFin = null;}
		if(nombreOficial.isEmpty()){nombreOficial = null;}
		if(placaOficial.isEmpty()){placaOficial = null;}
		List<ConsultaInfraccReasignacionEstadisticaVO> estadisticas = consultaLCMyBatisDAO.buscarEstadisticaLC(fechaInicio, fechaFin, placaOficial, nombreOficial);
		if(!estadisticas.isEmpty()){
			this.reporteEstadistico = this.generaReporteEstadisiticaLC(estadisticas, fechaInicio, fechaFin);
		}
		
		return estadisticas;
	}
	
	/***
	 * {@inheritDoc}
	 */
	@Override
	public List<DetalleDeReasignacionesOficialVO> ConsultaDetalleReasignacionesByOficial(String placaOficial,
																	String fechaInicio, String fechaFin) {
		int estatusConsumo = 0;
		
		if(StringUtils.isBlank(fechaInicio) && StringUtils.isBlank(fechaFin)){
			fechaInicio = null;
			fechaFin = null;
		}
		
		List<DetalleDeReasignacionesOficialVO> lista = consultaLCMyBatisDAO.ConsultaDetalleReasignacionesByOficial(placaOficial, estatusConsumo, fechaInicio, fechaFin);
		if(!lista.isEmpty()){
			reporteEstadisticoPorPersona = generaReporteEstadisiticaLCPorPersona(placaOficial, lista.get(0).getNombreEmpleado(), lista, fechaInicio, fechaFin);
		}
		
		return lista;
	}
	
	/***
	 * @author Jesus Gutierrez
	 * @param consultaInfraccReaHistoricoVO
	 * @param fechaInicio
	 * @param fechaFin
	 * @return Map
	 */
	@SuppressWarnings({"unchecked", "rawtypes" })
	public Map generaReporteHistoricoLC(List<ConsultaInfraccReasignacionHistoricoVO> listConsultaInfraccReaHistoricoVO,
			String fechaInicio, String fechaFin) {
		
		Map mapReporte = new HashMap();
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
		
		titulos.add("Folio");
		titulos.add("Nombre Usuario");
		titulos.add("Usuario");
		titulos.add("Placa Vehículo");
		titulos.add("Línea de Captura");
		titulos.add("Fecha Reasignación");
		titulos.add("Fecha Creación");
		titulos.add("Vigencia");
		titulos.add("Estatus");
		titulos.add("Importe");
		titulos.add("Recargos");
		titulos.add("Descuento");
		titulos.add("Total");
		encabezadoTitulo.add(titulos);
		
		String fechaForTitulo = "";
		
		propiedadesReporte.setNombreReporte("ReporteConsultaGeneral_LC");
		propiedadesReporte.setExtencionArchvio(".xls");
		
		if(fechaInicio != null && fechaFin != null){
			propiedadesReporte.setFechaI(fechaInicio);
			propiedadesReporte.setFechaF(fechaFin);
			fechaForTitulo = fechaInicio + "-" + fechaFin;
		}else{
			rutinastiempo = new RutinasTiempoImpl();
			fechaForTitulo = rutinastiempo.getStringDateFromFormta("dd/MM/yyyy", new Date());
		}
		
		propiedadesReporte.setTituloExcel("Consulta Linea de Captura " + fechaForTitulo);
		//cuerpo del reporte
		List<String> listaContenido1;
		
		for(ConsultaInfraccReasignacionHistoricoVO InfraccReasignacionHistVO : listConsultaInfraccReaHistoricoVO){
			listaContenido1 = new ArrayList<String>();
			listaContenido1.add(InfraccReasignacionHistVO.getNumeroInfraccion());
			listaContenido1.add(InfraccReasignacionHistVO.getNombreOficial());
			listaContenido1.add(InfraccReasignacionHistVO.getPlacaOficial());
			listaContenido1.add(InfraccReasignacionHistVO.getPlacaVehiculo());
			listaContenido1.add(InfraccReasignacionHistVO.getLineaCaptura());
			listaContenido1.add(InfraccReasignacionHistVO.getFechaReasignacion() != null ? InfraccReasignacionHistVO.getFechaReasignacion() : "" );
			listaContenido1.add(InfraccReasignacionHistVO.getFechaCreacion() != null ? InfraccReasignacionHistVO.getFechaCreacion() : "" );
			listaContenido1.add(InfraccReasignacionHistVO.getVigencia() != null ? InfraccReasignacionHistVO.getVigencia() : "" );
			listaContenido1.add(InfraccReasignacionHistVO.getEstatusResultado().equals("OK") ? "OK" : "ERROR");
			listaContenido1.add(InfraccReasignacionHistVO.getImporte().toString());
			listaContenido1.add(InfraccReasignacionHistVO.getRecargos().toString());
			listaContenido1.add(InfraccReasignacionHistVO.getDescuento().toString());
			listaContenido1.add(InfraccReasignacionHistVO.getTotal().toString());
			
			contenido1.add(listaContenido1);
		}
		
		contenido.add(contenido1);
		
		peticionReporteVO.setPropiedadesReporte(propiedadesReporte);
		peticionReporteVO.setEncabezado(encabezadoTitulo);
		peticionReporteVO.setContenido(contenido);
		
		try {
			reporte = peticionReporteBOImpl.generaReporteExcel(peticionReporteVO);
			mapReporte.put("nombre", propiedadesReporte.getNombreReporte());
			mapReporte.put("reporte", reporte);
		} catch (IOException e) {
 			e.printStackTrace();
		}
	
 		return mapReporte;
	}
	
	/***
	 * @author Jesus Gutierrez
	 * @param consultaInfraccReaHistoricoVO
	 * @param fechaInicio
	 * @param fechaFin
	 * @return Map
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map generaReporteEstadisiticaLC(List<ConsultaInfraccReasignacionEstadisticaVO> listConsultaInfraccReaEstadisticaVO, 
															String fechaInicio, String fechaFin) {
		Map mapReporte = new HashMap();
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
		
		titulos.add("Nombre Usuario");
		titulos.add("Usuario");
		titulos.add("Num. Reasignaciones");
		encabezadoTitulo.add(titulos);
		
		String fechaForNombre = "";
		String fechaForTitulo = "";
		
		if(fechaInicio != null && fechaFin != null){
			propiedadesReporte.setFechaI(fechaInicio);
			propiedadesReporte.setFechaF(fechaFin);
			fechaForNombre = fechaInicio.replaceAll("/", "") + "-" + fechaFin.replaceAll("/", "");
			fechaForTitulo = fechaInicio + "-" + fechaFin;
		}else{
			rutinastiempo = new RutinasTiempoImpl();
			fechaForNombre = rutinastiempo.getStringDateFromFormta("dd/MM/yyyy", new Date()).replaceAll("/", "");
			fechaForTitulo = rutinastiempo.getStringDateFromFormta("dd/MM/yyyy", new Date());
		}
		
		propiedadesReporte.setNombreReporte("Reporte_Reasignaciones-"+fechaForNombre);
		propiedadesReporte.setTituloExcel("Reporte Reasigaciones " + fechaForTitulo);
		propiedadesReporte.setExtencionArchvio(".xls");
		
		
		//cuerpo del reporte
		List<String> listaContenido1;
		
		for(ConsultaInfraccReasignacionEstadisticaVO InfraccReasignacionEstaVO : listConsultaInfraccReaEstadisticaVO){
			listaContenido1 = new ArrayList<String>();
			listaContenido1.add(InfraccReasignacionEstaVO.getNombreOficial());
			listaContenido1.add(InfraccReasignacionEstaVO.getPlacaOficial());
			listaContenido1.add(InfraccReasignacionEstaVO.getNumeroReasignaciones().toString());
			
			contenido1.add(listaContenido1);
		}
		
		contenido.add(contenido1);
		
		peticionReporteVO.setPropiedadesReporte(propiedadesReporte);
		peticionReporteVO.setEncabezado(encabezadoTitulo);
		peticionReporteVO.setContenido(contenido);
		
		try {
			reporte = peticionReporteBOImpl.generaReporteExcel(peticionReporteVO);
			mapReporte.put("nombre", propiedadesReporte.getNombreReporte());
			mapReporte.put("reporte", reporte);
		} catch (IOException e) {
 			e.printStackTrace();
		}
	
 		return mapReporte;
	}
	
	/***
	 * @author Jesus Gutierrez
	 * @param placa
	 * @param oficial
	 * @param listado
	 * @param fechaInicio
	 * @param fechaFin
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map generaReporteEstadisiticaLCPorPersona(String placa, String oficial, 
													 List<DetalleDeReasignacionesOficialVO> listado, 
													 String fechaInicio, String fechaFin){
		
		Map mapReporte = new HashMap();
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
		
		titulos.add("Folio");
		titulos.add("Placa Vehículo");
		titulos.add("Línea de Captura");
		titulos.add("Fecha Reasignación");
		titulos.add("Fecha Creación");
		titulos.add("Vigencia");
		titulos.add("Estatus");
		titulos.add("Importe");
		titulos.add("Recargos");
		titulos.add("Descuento");
		titulos.add("Total");
		encabezadoTitulo.add(titulos);
		
		subtitulos.add("Nombre Usuario: " + oficial.toUpperCase());
		subtitulos.add("Usuario: " + placa);
		propiedadesReporte.setSubtitulos(subtitulos);
		
		String fechaForNombre = "";
		String fechaForTitulo = "";
		
		if(fechaInicio != null && fechaFin != null){
			propiedadesReporte.setFechaI(fechaInicio);
			propiedadesReporte.setFechaF(fechaFin);
			fechaForNombre = fechaInicio.replaceAll("/", "") + "-" + fechaFin.replaceAll("/", "");
			fechaForTitulo = fechaInicio + "-" + fechaFin;
		}else{
			rutinastiempo = new RutinasTiempoImpl();
			fechaForNombre = rutinastiempo.getStringDateFromFormta("dd/MM/yyyy", new Date()).replaceAll("/", "");
			fechaForTitulo = rutinastiempo.getStringDateFromFormta("dd/MM/yyyy", new Date());
		}
		
		propiedadesReporte.setNombreReporte("Reporte_Reasignaciones-"+fechaForNombre);
		propiedadesReporte.setTituloExcel("Reporte Reasigaciones " + fechaForTitulo);
		propiedadesReporte.setExtencionArchvio(".xls");
		
		//cuerpo del reporte
		List<String> listaContenido1;
		
		for(DetalleDeReasignacionesOficialVO InfraccReasignacionEstaVO : listado){
			listaContenido1 = new ArrayList<String>();
			listaContenido1.add(StringUtils.isNotBlank(InfraccReasignacionEstaVO.getFolioInfraccion()) ? InfraccReasignacionEstaVO.getFolioInfraccion() : " ");
			listaContenido1.add(StringUtils.isNotBlank(InfraccReasignacionEstaVO.getPlacaVehiculo()) ? InfraccReasignacionEstaVO.getPlacaVehiculo() : " ");
			listaContenido1.add(StringUtils.isNotBlank(InfraccReasignacionEstaVO.getLineaCaptura()) ? InfraccReasignacionEstaVO.getLineaCaptura() : " ");
			listaContenido1.add(StringUtils.isNotBlank(InfraccReasignacionEstaVO.getFechaReasigna()) ? InfraccReasignacionEstaVO.getFechaReasigna() : " ");
			listaContenido1.add(StringUtils.isNotBlank(InfraccReasignacionEstaVO.getFechaCreacion()) ? InfraccReasignacionEstaVO.getFechaCreacion() : " ");
			listaContenido1.add(StringUtils.isNotBlank(InfraccReasignacionEstaVO.getVigencia()) ? InfraccReasignacionEstaVO.getVigencia() : " ");
			listaContenido1.add(StringUtils.isNotBlank(InfraccReasignacionEstaVO.getEstatus()) ? InfraccReasignacionEstaVO.getEstatus() : " ");
			listaContenido1.add(InfraccReasignacionEstaVO.getImporte() != null ? InfraccReasignacionEstaVO.getImporte().toString() : BigDecimal.valueOf(0L).toString());
			listaContenido1.add(InfraccReasignacionEstaVO.getRecargos() != null ? InfraccReasignacionEstaVO.getRecargos().toString() : BigDecimal.valueOf(0L).toString());
			listaContenido1.add(InfraccReasignacionEstaVO.getDescuento() != null ? InfraccReasignacionEstaVO.getDescuento().toString() : BigDecimal.valueOf(0L).toString());
			listaContenido1.add(InfraccReasignacionEstaVO.getTotal() != null ? InfraccReasignacionEstaVO.getTotal().toString() : BigDecimal.valueOf(0L).toString());
			
			contenido1.add(listaContenido1);
		}
				
		contenido.add(contenido1);
		
		peticionReporteVO.setPropiedadesReporte(propiedadesReporte);
		peticionReporteVO.setEncabezado(encabezadoTitulo);
		peticionReporteVO.setContenido(contenido);
		
		try {
			reporte = peticionReporteBOImpl.generaReporteExcel(peticionReporteVO);
			mapReporte.put("nombre", propiedadesReporte.getNombreReporte());
			mapReporte.put("reporte", reporte);
		} catch (IOException e) {
 			e.printStackTrace();
		}
		
		return mapReporte;
	}

	/***
	 * {@inheritDoc}
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Map getReporteExcelHistorico() {
		return this.reporteHistorico;
	}

	/***
	 * {@inheritDoc}
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Map getReporteExcelEstadistica() {
		return this.reporteEstadistico;
	}

	/***
	 * {@inheritDoc}
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Map getReporteExcelEstadisticaPorPersona() {
		return reporteEstadisticoPorPersona;
	}

}
