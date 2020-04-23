package mx.com.teclo.saicdmx.negocio.service.reportes;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.vo.reportes.ReporteInfraccionesGralVO;
import mx.com.teclo.saicdmx.util.comun.RutinasTiempoImpl;
import mx.com.teclomexicana.generaexcel.reporteexcel.PeticionReporteBOImpl;
import mx.com.teclomexicana.generaexcel.vo.PeticioReporteVO;
import mx.com.teclomexicana.generaexcel.vo.PropiedadesReporte;

@Service("GenerarExcelInfraccArticulos")
public class GenerarExcelInfraccArticulos {

	private ByteArrayOutputStream byteArrayOutputStream = null;
	private RutinasTiempoImpl rutinasTiempo;
	
	
	public ByteArrayOutputStream generarReporteExcel(List<ReporteInfraccionesGralVO> listaRegistros,String nombreReporte, String fechaInicio,  String fechaFin,String articulos ){
	 
		PeticioReporteVO peticioReporteVO = new PeticioReporteVO();
		PropiedadesReporte propiedadesReporte = new PropiedadesReporte();
		PeticionReporteBOImpl peticionReporteBOImpl = new PeticionReporteBOImpl();
		
		rutinasTiempo = new  RutinasTiempoImpl();
		
		List<Object> contenido = new ArrayList<Object>();
		List<Object> contenido1 = new ArrayList<Object>();
		List<Object> encabezadoTitulo = new ArrayList<Object>();
		List<String> titulos = new ArrayList<String>();
		List<String> subtitulos = new ArrayList<String>();

		titulos.add("FECHA");
		titulos.add("SECTOR");
		titulos.add("ARTÍCULO");
		
		titulos.add("PLACA DEL OFICIAL");
		titulos.add("NOMBRE DEL OFICIAL");
		titulos.add("INFRACCIÓN");
		titulos.add("FOLIO PREIMPRESA");
		titulos.add("PLACA DE VEHÍCULO");
	
		titulos.add("TIPO DE LICENCIA");
		titulos.add("LICENCIA");
		titulos.add("TARJETA DE CIRCULACIÓN");
		titulos.add("DÍA/HORA DE INFRACCIÓN");
		
		titulos.add("FRACCIÓN");
		titulos.add("PÁRRAFO");
		titulos.add("INCISO");
		titulos.add("MARCA DE VEHÍCULO");
		titulos.add("MODELO DE VEHÍCULO");
		titulos.add("COLOR DE VEHÍCULO");
//		titulos.add("TIPO DE VEHÍCULO");
		titulos.add("CALLE");
		titulos.add("ENTRE CALLE");
		titulos.add("Y CALLE");
		titulos.add("COLONIA");
		titulos.add("DELEGACIÓN");
		
		/*Filtros de busqueda*/
		subtitulos.add("Fecha de Consulta: "+ rutinasTiempo.getFechaActualFO());
		subtitulos.add("");
		subtitulos.add("Filtros de Búsqueda");
		subtitulos.add("Fecha Inicio: "+fechaInicio);
		subtitulos.add("Fecha Fin: "+fechaFin);
		subtitulos.add("Artículos: " +articulos);
		
		/**
		 * Condision para agregar el mensaje en caso de que tenga 5,000 registros la consulta 
		 * @param listaRegistros
		 * @return mensaje personalizado 
		 */
		if(listaRegistros.size() == 5000){
			subtitulos.add("");
			subtitulos.add("NOTA: El reporte contiene más de 5,000 resultados, si requiere de la información completa por favor solicítela a soporte," +
	    	  		   " de lo contrario modifique los parámetros de búsqueda.");
			subtitulos.add("");
		}
		
		
 		propiedadesReporte.setNombreReporte("Reporte " + nombreReporte);
		propiedadesReporte.setExtencionArchvio(".xls");
 		

		
		propiedadesReporte.setTituloExcel(nombreReporte);
		propiedadesReporte.setSubtitulos(subtitulos);
		encabezadoTitulo.add(titulos);
		
		//se crean los cuerpo del reporte 
		List<String> listaContenido1;
		
		
		for (ReporteInfraccionesGralVO Detalles : listaRegistros) {
			listaContenido1 = new ArrayList<String>();
			
			listaContenido1.add(Detalles.getINFRAC_FECHA_CORTA());
			listaContenido1.add(Detalles.getINFRAC_SECTOR());
			listaContenido1.add(Detalles.getINFRAC_ARTICULO());
			
			listaContenido1.add(Detalles.getOFICIAL_PLACA());
			listaContenido1.add(Detalles.getOFICIAL_NOMBRE());
			listaContenido1.add(Detalles.getINFRACCION());
			listaContenido1.add(Detalles.getIMPRESA());
			listaContenido1.add(Detalles.getVEHICULO_PLACA());

			listaContenido1.add(Detalles.getLICENCIA_TIPO());
			listaContenido1.add(Detalles.getINFRACTOR_LICENCIA());
			listaContenido1.add(Detalles.getTARJETA_CIRCULACION());
			listaContenido1.add(Detalles.getINFRAC_FECHA());
		
			listaContenido1.add(Detalles.getINFRAC_FRACCION());
			listaContenido1.add(Detalles.getINFRAC_PARRAFO());
			listaContenido1.add(Detalles.getINFRAC_INCISO());
			listaContenido1.add(Detalles.getVEHICULO_MARCA());
			listaContenido1.add(Detalles.getVEHICULO_MODELO());
			listaContenido1.add(Detalles.getVEHICULO_COLOR());
//			listaContenido1.add(Detalles.getVEHICULO_TIPO());
			listaContenido1.add(Detalles.getINFRAC_CALLE());
			listaContenido1.add(Detalles.getINFRAC_ENTRE_CALLE());
			listaContenido1.add(Detalles.getINFRAC_Y_CALLE());
			listaContenido1.add(Detalles.getINFRAC_COLONIA());
			listaContenido1.add(Detalles.getINFRAC_DELEGACION());

			contenido1.add(listaContenido1);
		}
 
		//bloqueohhRegistroVO.getFechaDesbloqueo() != null ? rutinasTiempo.getStringDateFromFormta("dd/MM/yyyy hh:mm:ss", bloqueohhRegistroVO.getFechaDesbloqueo()) : ""
		
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
