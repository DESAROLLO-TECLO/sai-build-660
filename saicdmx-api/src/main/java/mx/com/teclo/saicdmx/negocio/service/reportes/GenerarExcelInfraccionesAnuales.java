package mx.com.teclo.saicdmx.negocio.service.reportes;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.vo.reportes.RptInfraccionesEmpleado;
import mx.com.teclo.saicdmx.persistencia.vo.reportes.infraccionesAnualesVO;
import mx.com.teclo.saicdmx.util.comun.RutinasTiempoImpl;
import mx.com.teclomexicana.generaexcel.reporteexcel.PeticionReporteBOImpl;
import mx.com.teclomexicana.generaexcel.vo.PeticioReporteVO;
import mx.com.teclomexicana.generaexcel.vo.PropiedadesReporte;

@Service("generarExcelInfraccionesAnuales")
public class GenerarExcelInfraccionesAnuales {
	private ByteArrayOutputStream byteArrayOutputStream = null;
	private RutinasTiempoImpl rutinasTiempo;
	
	public ByteArrayOutputStream generarReporteExcel(List<infraccionesAnualesVO> listaRegistros,String nombreReporte, long parametroBusqueda){
		
		PeticioReporteVO peticioReporteVO = new PeticioReporteVO();
		PropiedadesReporte propiedadesReporte = new PropiedadesReporte();
		PeticionReporteBOImpl peticionReporteBOImpl = new PeticionReporteBOImpl();
		
		rutinasTiempo = new  RutinasTiempoImpl();
		
		List<Object> contenido = new ArrayList<Object>();
		List<Object> contenido1 = new ArrayList<Object>();
		List<Object> encabezadoTitulo = new ArrayList<Object>();
		List<String> titulos = new ArrayList<String>();
		List<String> subtitulos = new ArrayList<String>();

		if(!nombreReporte.equals("Infracciónes Anules (Total Anual) ")){
			titulos.add("MES");
		}
		
		titulos.add("DEPÓSITO");
		titulos.add("TOTAL");
		
		/*Parametros de busqueda */
		subtitulos.add("Fecha de Consulta: "+ rutinasTiempo.getFechaActualFO());
		subtitulos.add("");
		subtitulos.add("Filtros de Búsqueda");	
		subtitulos.add("Año : "+parametroBusqueda);
		
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
		
		
		
 		propiedadesReporte.setNombreReporte("Reporte" + nombreReporte);
		propiedadesReporte.setExtencionArchvio(".xls");
		
// 		propiedadesReporte.setFechaI(fechaInicio);
//		propiedadesReporte.setFechaF(fechaFin);
//		propiedadesReporte.setPersonaReporte("Empleados Buscados: "+placaBuscada);

		
		propiedadesReporte.setTituloExcel(nombreReporte);
		propiedadesReporte.setSubtitulos(subtitulos);
		encabezadoTitulo.add(titulos);
		
		//se crean los cuerpo del reporte 
		List<String> listaContenido1;
		if(nombreReporte.equals("Infracciónes Anules (Total Anual) ")){
			for (infraccionesAnualesVO Detalles : listaRegistros) {
				listaContenido1 = new ArrayList<String>();
				listaContenido1.add(Detalles.getDeposito());
				listaContenido1.add(Detalles.getTotal());
				contenido1.add(listaContenido1);
			}
			
		}else{
			for (infraccionesAnualesVO Detalles : listaRegistros) {
				listaContenido1 = new ArrayList<String>();
				listaContenido1.add(Detalles.getMes());
				listaContenido1.add(Detalles.getDeposito());
				listaContenido1.add(Detalles.getTotal());
				contenido1.add(listaContenido1);
			}
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
