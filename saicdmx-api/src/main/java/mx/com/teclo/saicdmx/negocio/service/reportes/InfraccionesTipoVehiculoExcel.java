package mx.com.teclo.saicdmx.negocio.service.reportes;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.vo.reportes.InfraccionesporTipoVehiculoVO;
import mx.com.teclo.saicdmx.util.comun.RutinasTiempoImpl;
import mx.com.teclomexicana.generaexcel.reporteexcel.PeticionReporteBOImpl;
import mx.com.teclomexicana.generaexcel.vo.PeticioReporteVO;
import mx.com.teclomexicana.generaexcel.vo.PropiedadesReporte;

@Service("InfraccionesTipoVehiculoExcel")
public class InfraccionesTipoVehiculoExcel {
	
	private ByteArrayOutputStream byteArrayOutputStream = null;
	private RutinasTiempoImpl rutinasTiempo;
	
	public ByteArrayOutputStream generarExcelInfraccionesTipoElemento(List<InfraccionesporTipoVehiculoVO> listaRegistros,String nombreReporte,
			                                                           String fechaInicio,String fechaFin){
	 
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
		titulos.add("FOLIO");
		titulos.add("FECHA HORA");
		titulos.add("ARTÍCULO");
		titulos.add("FRACCIÓN");
		titulos.add("TIPO DE VEHÍCULO");
		titulos.add("DELEGACIÓN");
		
		
		/*Filtros de busqueda*/
		subtitulos.add("Fecha de Consulta: "+ rutinasTiempo.getFechaActualFO());
		subtitulos.add("");
		subtitulos.add("Filtros de Búsqueda");
		subtitulos.add("Fecha Inicio: "+fechaInicio);
		subtitulos.add("Fecha Fin: "+fechaFin);
		
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
		//	MOTIVACIÓN 	CALLE 	ENTRE CALLE 	Y CALLE 	COLONIA 	DELEGACIÓN 
		
		for (InfraccionesporTipoVehiculoVO Detalles : listaRegistros) {
			listaContenido1 = new ArrayList<String>();
			
			listaContenido1.add(Detalles.getFecha());	
			listaContenido1.add(Detalles.getFolio());
			listaContenido1.add(Detalles.getFecha_Hora());
			listaContenido1.add(Detalles.getArticulo());
			listaContenido1.add(Detalles.getFraccion());
			listaContenido1.add(Detalles.getTipo_Vehiculo());
			listaContenido1.add(Detalles.getDelegacion());
			
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
