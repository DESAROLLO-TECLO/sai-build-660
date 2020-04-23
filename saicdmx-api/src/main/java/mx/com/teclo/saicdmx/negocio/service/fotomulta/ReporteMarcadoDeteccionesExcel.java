package mx.com.teclo.saicdmx.negocio.service.fotomulta;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.FotomultaMarcadoVO;
import mx.com.teclo.saicdmx.util.comun.RutinasTiempoImpl;
import mx.com.teclomexicana.generaexcel.reporteexcel.PeticionReporteBOImpl;
import mx.com.teclomexicana.generaexcel.vo.PeticioReporteVO;
import mx.com.teclomexicana.generaexcel.vo.PropiedadesReporte;

public class ReporteMarcadoDeteccionesExcel {
	
	private ByteArrayOutputStream byteArrayOutputStream = null;
	
	public byte[] generarReporteExcel( List<FotomultaMarcadoVO> listaRegistros,String nombreReporte, 
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
		titulos.add("Fecha Validación");
		titulos.add("Fecha Creación");
		titulos.add("Tipo Radar");
		titulos.add("Origen Placa");
		
 		propiedadesReporte.setNombreReporte("Reporte_DeteccionesCanceladas");
		propiedadesReporte.setExtencionArchvio(".xls");
		if(!fechaInicio.equals("null")){
			//propiedadesReporte.setFechaI(fechaInicio);
			//propiedadesReporte.setFechaF(fechaFin);
			propiedadesReporte.setTituloExcel(nombreReporte +" "+fechaInicio);
//			propiedadesReporte.setFechaI(fechaInicio);
			//subtitulos.add("Reporte General del : "+fechaInicio);
//			propiedadesReporte.setFechaF(fechaFin);
			propiedadesReporte.setTituloExcel(nombreReporte);
		}else{
			rutinasTiempoImpl = new RutinasTiempoImpl();
			propiedadesReporte.setTituloExcel(nombreReporte+" "+rutinasTiempoImpl.getStringDateFromFormta("dd/MM/yyyy", new Date()));
		}

		propiedadesReporte.setSubtitulos(subtitulos);
		encabezadoTitulo.add(titulos);
		
		//se crean los cuerpo del reporte 
		List<String> listaContenido1;
		 
		for (FotomultaMarcadoVO fmVO : listaRegistros) {
			
			listaContenido1 = new ArrayList<String>();
		
			listaContenido1.add(fmVO.getPlaca());
			listaContenido1.add(fmVO.getTskuid());
			listaContenido1.add(fmVO.getFecha());
			listaContenido1.add(fmVO.getHora());
			listaContenido1.add(fmVO.getOficialNombre());
			listaContenido1.add(fmVO.getOficialPlaca());
			listaContenido1.add(fmVO.getFechaValidacion());
			listaContenido1.add(fmVO.getFechaCreacion());
			listaContenido1.add(fmVO.getCodigo());
			listaContenido1.add(fmVO.getOrigenPlaca()!="CDMX"?"Foránea":fmVO.getOrigenPlaca());

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
