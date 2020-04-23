package mx.com.teclo.saicdmx.negocio.service.fotomulta;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.FotomultaCanceladasVO;
import mx.com.teclo.saicdmx.util.comun.RutinasTiempoImpl;
import mx.com.teclomexicana.generaexcel.reporteexcel.PeticionReporteBOImpl;
import mx.com.teclomexicana.generaexcel.vo.PeticioReporteVO;
import mx.com.teclomexicana.generaexcel.vo.PropiedadesReporte;

public class ReporteDeteccionesCanceladasExcel {
	
	private ByteArrayOutputStream byteArrayOutputStream = null;
	
	public byte[] generarReporteExcel( List<FotomultaCanceladasVO> listaRegistros,String nombreReporte, 
			  										  String fechaInicio,  String fechaFin ) {
		
		RutinasTiempoImpl rutinasTiempoImpl = null;
		PeticioReporteVO peticioReporteVO = new PeticioReporteVO();
		PropiedadesReporte propiedadesReporte = new PropiedadesReporte();
		PeticionReporteBOImpl peticionReporteBOImpl = new PeticionReporteBOImpl();
		List<Object> contenido = new ArrayList<Object>();
		List<Object> contenido1 = new ArrayList<Object>();
		List<Object> encabezadoTitulo = new ArrayList<Object>();
		List<String> titulos = new ArrayList<String>();
		List<String> subtitulos = new ArrayList<String>();

		titulos.add("Placa Vehicular");
		titulos.add("TDSKUID");
		titulos.add("Fecha");
		titulos.add("Hora");
		titulos.add("Nombre Usuario");
		titulos.add("Usuario");
		titulos.add("Tipo Radar");
		titulos.add("Origen Placa");
		titulos.add("Descripción Cancelación");
		titulos.add("Fecha Cancelación");
		titulos.add("Cancelado Por");
		titulos.add("Fecha Validación");
		titulos.add("Fecha Creación");
		
 		propiedadesReporte.setNombreReporte("Reporte_DeteccionesCanceladas");
		propiedadesReporte.setExtencionArchvio(".xls");
		if(!fechaInicio.equals("null")){
			propiedadesReporte.setFechaI(fechaInicio);
			propiedadesReporte.setFechaF(fechaFin);
			propiedadesReporte.setTituloExcel(nombreReporte);
		}else{
			rutinasTiempoImpl = new RutinasTiempoImpl();
			propiedadesReporte.setTituloExcel(nombreReporte+" "+rutinasTiempoImpl.getStringDateFromFormta("dd/MM/yyyy", new Date()));
		}

		propiedadesReporte.setSubtitulos(subtitulos);
		encabezadoTitulo.add(titulos);
		
		//se crean los cuerpo del reporte 
		List<String> listaContenido1;
		 
		for (FotomultaCanceladasVO deteccion : listaRegistros) {
			
			listaContenido1 = new ArrayList<String>();
		
			listaContenido1.add(deteccion.getPlaca());
			listaContenido1.add(deteccion.getTskuid());
			listaContenido1.add(deteccion.getFecha());
			listaContenido1.add(deteccion.getHora());
			listaContenido1.add(deteccion.getOficialNombre());
			listaContenido1.add(deteccion.getOficialPlaca());
			listaContenido1.add(deteccion.getCodigo());
			listaContenido1.add(deteccion.getOrigenPlaca()!="CDMX"?"Foránea":deteccion.getOrigenPlaca());
			listaContenido1.add(deteccion.getMotivoCancelacion());
			listaContenido1.add(deteccion.getFechaCancelacion());
			listaContenido1.add(deteccion.getModificadoPor());
			listaContenido1.add(deteccion.getFechaValidacion());
			listaContenido1.add(deteccion.getFechaCreacion());
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
	
 		return byteArrayOutputStream.toByteArray();
	}
	

}
