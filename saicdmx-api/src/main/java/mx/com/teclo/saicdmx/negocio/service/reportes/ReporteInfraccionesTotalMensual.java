package mx.com.teclo.saicdmx.negocio.service.reportes;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.vo.reportes.rptInfraccionesMensuales;
import mx.com.teclo.saicdmx.util.comun.RutinasTiempoImpl;
import mx.com.teclomexicana.generaexcel.reporteexcel.PeticionReporteBOImpl;
import mx.com.teclomexicana.generaexcel.vo.PeticioReporteVO;
import mx.com.teclomexicana.generaexcel.vo.PropiedadesReporte;

@Service("ReporteInfraccionesTotalMensual")
public class ReporteInfraccionesTotalMensual {

	private ByteArrayOutputStream byteArrayOutputStream = null;
	private RutinasTiempoImpl rutinasTiempo;
	
	public ByteArrayOutputStream generaTotalInfraccionesMensual(List<rptInfraccionesMensuales> listaRegistros,String nombreReporte, int mes,  String fechaFin,String Deposito){
		PeticioReporteVO peticioReporteVO = new PeticioReporteVO();
		PropiedadesReporte propiedadesReporte = new PropiedadesReporte();
		PeticionReporteBOImpl peticionReporteBOImpl = new PeticionReporteBOImpl();
		
		rutinasTiempo = new  RutinasTiempoImpl();
		
		
		List<Object> contenido = new ArrayList<Object>();
		List<Object> contenido1 = new ArrayList<Object>();
		List<Object> encabezadoTitulo = new ArrayList<Object>();
		List<String> titulos = new ArrayList<String>();
		List<String> subtitulos = new ArrayList<String>();

		titulos.add("MEDIO DE INGRESO");
		titulos.add("TOTAL");
		
		/*Paramtros de Busqueda*/
		String mesNombre = rutinasTiempo.getNombreMes(mes);
		/*Parametros de busqueda */
		subtitulos.add("Fecha de Consulta: "+ rutinasTiempo.getFechaActualFO());
		subtitulos.add("");
		subtitulos.add("Filtros de Búsqueda");	
		subtitulos.add("Fecha Inicio: "+"01/"+mesNombre +"/"+fechaFin);
		subtitulos.add("Fecha Fin: "+rutinasTiempo.numeroDiasXMesyAnio(Integer.parseInt(fechaFin),mes)+"/"+mesNombre+"/"+fechaFin);
		subtitulos.add("Depósito: "+ Deposito);
		subtitulos.add("Mes: "+mesNombre);
		subtitulos.add("Año: "+fechaFin);
		
		
 		propiedadesReporte.setNombreReporte("Reporte" + nombreReporte);
		propiedadesReporte.setExtencionArchvio(".xls");

		propiedadesReporte.setTituloExcel(nombreReporte);
		propiedadesReporte.setSubtitulos(subtitulos);
		encabezadoTitulo.add(titulos);
		
		
		//se crean los cuerpo del reporte 
		List<String> listaContenido1;
		
		
		for (rptInfraccionesMensuales Detalles : listaRegistros) {
			listaContenido1 = new ArrayList<String>();
			
			listaContenido1.add(Detalles.getMedioIngreso());
			listaContenido1.add(Detalles.getCantidad());
			
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
