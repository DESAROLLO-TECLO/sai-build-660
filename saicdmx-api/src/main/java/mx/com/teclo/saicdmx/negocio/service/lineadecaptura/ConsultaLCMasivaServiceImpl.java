package mx.com.teclo.saicdmx.negocio.service.lineadecaptura;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.mybatis.dao.lineadecaptura.ConsultaLCMMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.lineadecaptura.ReasignacionLCMMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura.ConsultaInfraccReasignacionHistoricoVO;
import mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura.ConsultaLoteLCMVO;
import mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura.InfraccionLoteLCMVO;
import mx.com.teclo.saicdmx.util.comun.RutinasTiempoImpl;
import mx.com.teclomexicana.generaexcel.reporteexcel.PeticionReporteBOImpl;
import mx.com.teclomexicana.generaexcel.vo.PeticioReporteVO;
import mx.com.teclomexicana.generaexcel.vo.PropiedadesReporte;

@Service
public class ConsultaLCMasivaServiceImpl implements ConsultaLCMasivaService {
	
	@Autowired
	ConsultaLCMMyBatisDAO consultaLCMMBDAO;
	
	private RutinasTiempoImpl rutinastiempo;
	
	@Override
	public Map<String, Object> 	consultaLotes(String fechaInicio, String fechaFin, Integer cbCampoBusqueda, Integer idLote, String nameLote, Integer cbTipoFecha, Integer cbEstatusLotes){
		Map<String, Object> respuesta = new HashMap<String, Object>();
		List<InfraccionLoteLCMVO> lista = new ArrayList<InfraccionLoteLCMVO>();
		/*
		if (!fechaFin.equals("") && !fechaInicio.equals("")) {
			//respuesta.put("data", consultaLCMMBDAO.obtenerPorFechasInicioFin(fechaInicio, fechaFin));
			lista = consultaLCMMBDAO.obtenerPorFechasInicioFin(fechaInicio, fechaFin);
			//return respuesta;
		}else{
			if (fechaFin.equals("") && !fechaInicio.equals("")) {
				//respuesta.put("data", consultaLCMMBDAO.obtenerPorFechaInicio(fechaInicio));
				lista = consultaLCMMBDAO.obtenerPorFechaInicio(fechaInicio);
				//return respuesta;
			}else{
				if (!fechaFin.equals("") && fechaInicio.equals("")) {
					//respuesta.put("data", consultaLCMMBDAO.obtenerPorFechaFin(fechaFin));
					lista = consultaLCMMBDAO.obtenerPorFechaFin(fechaFin);
					//return respuesta;
				}else{
					//respuesta.put("data", consultaLCMMBDAO.obtenerSinFecha());
					lista = consultaLCMMBDAO.obtenerSinFecha();
					//return respuesta;
				}
			}
		}
		*/
		
		if(cbTipoFecha.equals("")){
			cbTipoFecha = 1;
		}
		if(cbTipoFecha == 1){
			fechaInicio = null;
			fechaFin = null;
		}else if(cbTipoFecha == 2 || cbTipoFecha == 3){
			if (fechaFin.equals("") && fechaInicio.equals("")) {//ambas fechas
				fechaInicio = null;
				fechaFin = null;
			}else{
				if (fechaFin.equals("") && !fechaInicio.equals("")) {//Sin fechafin pero con fechainicio
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					fechaFin = sdf.format(new Date());
				}else{
					if (!fechaFin.equals("") && fechaInicio.equals("")) {//Sin fechainicio pero con fechafin
						fechaInicio = "01/01/1500";
					}else{
					}
				}
			}
		}
		
		if(cbCampoBusqueda.equals("")){
			cbCampoBusqueda = 1;
		}
		if(cbCampoBusqueda == 1){
			idLote = null;
			nameLote = null;
		}else if(cbCampoBusqueda == 2){
			if(idLote.equals("")){
				fechaInicio = null;
			}
			nameLote = null;
		}else if(cbCampoBusqueda == 3){
			idLote = null;
			if(nameLote.isEmpty()){
				nameLote = null;
			}else{
				nameLote = nameLote.toLowerCase();
			}
		}
		
		/*if(cbEstatusLotes.equals("")){
			cbEstatusLotes = 1;
		}*/
		
		lista = consultaLCMMBDAO.obtenerLoteFoliosConParametros(fechaInicio, fechaFin, cbCampoBusqueda, idLote, nameLote, cbTipoFecha, cbEstatusLotes);
		
		if(lista.isEmpty()){
			respuesta.put("respuesta", Boolean.FALSE);
		}else{
			respuesta.put("respuesta", Boolean.TRUE);
			respuesta.put("data", lista);
		}
		
		return respuesta;
	}

	@Override
	public Map descargarReporteLote(String lote) {
		Map mapReporte = new HashMap();
		ByteArrayOutputStream reporte = new ByteArrayOutputStream();
		PeticioReporteVO peticionReporteVO = new PeticioReporteVO();
		PropiedadesReporte propiedadesReporte = new PropiedadesReporte();
		PeticionReporteBOImpl peticionReporteBOImpl = new PeticionReporteBOImpl();
		
		String fechaInicio = null;
		String fechaFin = null;
		
		//Obtener datos de Lote
		List<ConsultaLoteLCMVO> listConsultaLoteLCMVO = consultaLCMMBDAO.obtenerLoteFolios(lote);
		InfraccionLoteLCMVO listConsultaDatosLote = consultaLCMMBDAO.obtenerDatosLote(lote);
		
		//Resultados de la tabla
		List<Object> contenido = new ArrayList<Object>();
		List<Object> contenido1 = new ArrayList<Object>();
		
		//Leyendas de las columnas de las tablas
		List<Object> encabezadoTitulo = new ArrayList<Object>();
		List<String> titulos = new ArrayList<String>();
		List<String> datosLote1 = new ArrayList<String>();
		List<String> subtitulosLote = new ArrayList<String>();
		
		titulos.add("Folio");
		titulos.add("Linea de Captura");
		titulos.add("Nombre del Oficial");
		titulos.add("Placa Oficial");
		titulos.add("Dias de Unidad de Cuenta");
		titulos.add("Fecha Reasignación");
		titulos.add("Vigencia");
		titulos.add("Salario Mínimo");
		titulos.add("Importe");
		titulos.add("Recargos");
		titulos.add("Descuento");
		titulos.add("Total");
		titulos.add("Estatus");
		titulos.add("Fecha de Creación");
		encabezadoTitulo.add(titulos);
		
		subtitulosLote.add("Id Lote: " + listConsultaDatosLote.getId().toString());
		subtitulosLote.add("Nombre Lote: " + listConsultaDatosLote.getNombre());
		subtitulosLote.add("Fecha de Emision: " + listConsultaDatosLote.getFecha_emision());
		
		String fechaForTitulo = "";
		
		if(fechaInicio != null && fechaFin != null){
			propiedadesReporte.setFechaI(fechaInicio);
			propiedadesReporte.setFechaF(fechaFin);
			fechaForTitulo = fechaInicio + "-" + fechaFin;
		}else{
			rutinastiempo = new RutinasTiempoImpl();
			fechaForTitulo = rutinastiempo.getStringDateFromFormta("dd/MM/yyyy", new Date());
		}
		
		propiedadesReporte.setNombreReporte("ReporteConsultaLotes_LC_Masiva");
		propiedadesReporte.setExtencionArchvio(".xls");
		
		propiedadesReporte.setTituloExcel("Consulta Linea de Captura Masiva " + fechaForTitulo);
		propiedadesReporte.setSubtitulos(subtitulosLote);
		
		//cuerpo del reporte
		List<String> listaContenido1;
		
		for(ConsultaLoteLCMVO loteInfraccionVO : listConsultaLoteLCMVO){
			listaContenido1 = new ArrayList<String>();
			listaContenido1.add(loteInfraccionVO.getInfrac_num() != null ? loteInfraccionVO.getInfrac_num(): "N/A");
			listaContenido1.add(loteInfraccionVO.getLinea_captura()!= null && loteInfraccionVO.getLinea_captura() != ""? loteInfraccionVO.getLinea_captura(): "N/A");
			listaContenido1.add(loteInfraccionVO.getNombreCompleto() != null ? loteInfraccionVO.getNombreCompleto() : "N/A");
			listaContenido1.add(loteInfraccionVO.getEmp_placa() != null ? loteInfraccionVO.getEmp_placa() : "N/A");
			listaContenido1.add(loteInfraccionVO.getNumero_dias() != null ? loteInfraccionVO.getNumero_dias().toString() : "N/A");
			listaContenido1.add(loteInfraccionVO.getFecha_reasignacion() != null && loteInfraccionVO.getFecha_reasignacion() != " "? loteInfraccionVO.getFecha_reasignacion() : "N/A");
			listaContenido1.add(loteInfraccionVO.getVigencia() != null && loteInfraccionVO.getVigencia() != ""? loteInfraccionVO.getVigencia() : "N/A");
			listaContenido1.add(loteInfraccionVO.getSalario_minimo() != null ? loteInfraccionVO.getSalario_minimo().toString() : "N/A");
			listaContenido1.add(loteInfraccionVO.getImporte() != null ? loteInfraccionVO.getImporte().toString() : "N/A");
			listaContenido1.add(loteInfraccionVO.getRecargos() != null ? loteInfraccionVO.getRecargos().toString() : "N/A");
			listaContenido1.add(loteInfraccionVO.getDescuento() != null ? loteInfraccionVO.getDescuento().toString() : "N/A");
			listaContenido1.add(loteInfraccionVO.getTotal() != null ? loteInfraccionVO.getTotal().toString() : "N/A");
			listaContenido1.add(loteInfraccionVO.getEstatus_resultado() != null ? loteInfraccionVO.getEstatus_resultado() : "ERROR");
			listaContenido1.add(loteInfraccionVO.getFecha_creacion() != null &&loteInfraccionVO.getFecha_creacion() != "" ? loteInfraccionVO.getFecha_creacion() : "N/A");
			
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
}
