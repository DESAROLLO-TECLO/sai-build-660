package mx.com.teclo.saicdmx.negocio.service.bloqueohh;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.vo.bloqueohh.BloqueohhRegistroVO;
import mx.com.teclo.saicdmx.util.comun.RutinasTiempoImpl;
import mx.com.teclomexicana.generaexcel.reporteexcel.PeticionReporteBOImpl;
import mx.com.teclomexicana.generaexcel.vo.PeticioReporteVO;
import mx.com.teclomexicana.generaexcel.vo.PropiedadesReporte;

/**
 * Copyright (c) 2016, Teclo Mexicana.
 * 
 * Descripcion					: BloqueohhReporteExcel
 * Historial de Modificaciones	:
 * Descripcion del Cambio		: Creacion
 * @author 						: fjmb 
 * @version 					: 1.0
 * Fecha						: 29/Septiembre/2016
 */

@Service("bloqueohhReporteExcel")
public class BloqueohhReporteExcel {
	
	private ByteArrayOutputStream byteArrayOutputStream = null;
	private RutinasTiempoImpl rutinasTiempo;
	
	public ByteArrayOutputStream generarReporteExcel( List<BloqueohhRegistroVO> listaRegistroVO,	  String nombreReporte, 
			  										  String fechaInicio,  String fechaFin ) {
		
		PeticioReporteVO peticioReporteVO = new PeticioReporteVO();
		PropiedadesReporte propiedadesReporte = new PropiedadesReporte();
		PeticionReporteBOImpl peticionReporteBOImpl = new PeticionReporteBOImpl();
		rutinasTiempo = new  RutinasTiempoImpl();
		List<Object> contenido = new ArrayList<Object>();
		List<Object> contenido1 = new ArrayList<Object>();
		List<Object> encabezadoTitulo = new ArrayList<Object>();
		List<String> titulos = new ArrayList<String>();
		List<String> subtitulos = new ArrayList<String>();

		titulos.add("Serie Handheld");
		titulos.add("Nombre del Oficial");
		titulos.add("Placa Oficial");
		titulos.add("Tipo de Bloqueo");
		titulos.add("Estatus");
		titulos.add("Fecha de Bloqueo");
		titulos.add("Fecha de Desbloqueo");

 		propiedadesReporte.setNombreReporte("Reporte Handheld");
		propiedadesReporte.setExtencionArchvio(".xls");
 		propiedadesReporte.setFechaI(fechaInicio);
		propiedadesReporte.setFechaF(fechaFin);

		
		propiedadesReporte.setTituloExcel(nombreReporte);
		propiedadesReporte.setSubtitulos(subtitulos);
		encabezadoTitulo.add(titulos);
		
		//se crean los cuerpo del reporte 
		List<String> listaContenido1;
		 
		for (BloqueohhRegistroVO bloqueohhRegistroVO : listaRegistroVO) {
			
			listaContenido1 = new ArrayList<String>();
			listaContenido1.add(bloqueohhRegistroVO.getNumeroSeriehh().toString());
			listaContenido1.add(bloqueohhRegistroVO.getNombreOficial().toString());
			listaContenido1.add(bloqueohhRegistroVO.getPlacaOficial().toString());
			listaContenido1.add(bloqueohhRegistroVO.getBloqueohhCatTipoBloqueo().getDescripcion().toString());
			listaContenido1.add(bloqueohhRegistroVO.getEstatusBloqueo().toString());
			listaContenido1.add(bloqueohhRegistroVO.getFechaBloqueo()  !=null ? rutinasTiempo.getStringDateFromFormta("dd/MM/yyyy hh:mm:ss", bloqueohhRegistroVO.getFechaBloqueo()) : "" );
			listaContenido1.add(bloqueohhRegistroVO.getFechaDesbloqueo() != null ? rutinasTiempo.getStringDateFromFormta("dd/MM/yyyy hh:mm:ss", bloqueohhRegistroVO.getFechaDesbloqueo()) : "");

			contenido1.add(listaContenido1);
		}
 
		contenido.add(contenido1);
		
		peticioReporteVO.setPropiedadesReporte(propiedadesReporte);
		peticioReporteVO.setEncabezado(encabezadoTitulo);
		peticioReporteVO.setContenido(contenido);
		
		try {
			byteArrayOutputStream =	peticionReporteBOImpl.generaReporteExcel(peticioReporteVO);
		} catch (IOException e) {
 			e.printStackTrace();
		}
	
 		return byteArrayOutputStream;
	}
	

}
