package mx.com.teclo.saicdmx.negocio.service.reportes;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.vo.reportes.InfraccionesDiariasVO;
import mx.com.teclo.saicdmx.util.comun.RutinasTiempoImpl;
import mx.com.teclomexicana.generaexcel.reporteexcel.PeticionReporteBOImpl;
import mx.com.teclomexicana.generaexcel.vo.PeticioReporteVO;
import mx.com.teclomexicana.generaexcel.vo.PropiedadesReporte;

@Service("GeneraExcelService")
public class GeneraExcelService {
	private ByteArrayOutputStream byteArrayOutputStream = null;
	private RutinasTiempoImpl rutinasTiempo;
	
public ByteArrayOutputStream generarReporteExcel(List<InfraccionesDiariasVO> listaRegistros,String nombreReporte, String fechaInicio,String motivo){
		
		PeticioReporteVO peticioReporteVO = new PeticioReporteVO();
		PropiedadesReporte propiedadesReporte = new PropiedadesReporte();
		PeticionReporteBOImpl peticionReporteBOImpl = new PeticionReporteBOImpl();
		
		rutinasTiempo = new  RutinasTiempoImpl();
		
		List<Object> contenido = new ArrayList<Object>();
		List<Object> contenido1 = new ArrayList<Object>();
		List<Object> encabezadoTitulo = new ArrayList<Object>();
		List<String> titulos = new ArrayList<String>();
		List<String> subtitulos = new ArrayList<String>();

		/* Filtros de Busqueda */
		subtitulos.add("Fecha de Consulta: "+ rutinasTiempo.getFechaActualFO());
		subtitulos.add("");
		subtitulos.add("Filtros de Búsqueda");
		subtitulos.add("Fecha Inicio: "+fechaInicio);
		
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
		
		
		
		if(motivo.equals("medio")){
			titulos.add("DEPÓSITO");
			titulos.add("MEDIO DE INGRESO");
			titulos.add("TOTAL");
		}else{
			titulos.add("DEPÓSITO");
			titulos.add("TOTAL");
		}
		

 		propiedadesReporte.setNombreReporte("Reporte" + nombreReporte);
		propiedadesReporte.setExtencionArchvio(".xls");
// 		propiedadesReporte.setFechaI(fechaInicio);
// 		propiedadesReporte.setPersonaReporte("Fecha Consultada: "+fechaInicio);
		

		propiedadesReporte.setTituloExcel(nombreReporte);
		propiedadesReporte.setSubtitulos(subtitulos);
		encabezadoTitulo.add(titulos);
		
		//se crean los cuerpo del reporte 
		List<String> listaContenido1;
		
		
		for (InfraccionesDiariasVO Detalles : listaRegistros) {
			
			listaContenido1 = new ArrayList<String>();
			listaContenido1.add(Detalles.getDEPOSITO());
			if(motivo.equals("medio")){
				listaContenido1.add(Detalles.getMEDIO());
			}
			listaContenido1.add(Detalles.getTOTAL());
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
